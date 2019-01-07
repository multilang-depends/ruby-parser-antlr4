/*

MIT License

Copyright (c) 2019 Gang ZHANG (gangz@emergentdesign.cn)
(Contributors are welcome!)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

* History: 2019/01/06 Initial version
*/

package sample;

/*
   The file is a parse example.  Meanwhile, a special issue about performance
   is provided in the example.
   - To prevent the long-time parse, I tried SLL mode but it failed finally.
   - I set a MAX_PARSE_TIME_PER_FILE constant and use the timeout invocation 
     to avoid long time parse as a temporary solution.
*/
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class RubyFileParser implements FileParser {
	private static final long MAX_PARSE_TIME_PER_FILE = 180000L;
	private String fileFullPath;
	private ExecutorService executor;

	public RubyFileParser(String fileFullPath, ExecutorService executorService) {
        this.fileFullPath = fileFullPath;
        this.executor = executorService;
    }

	@Override
	public void parse() throws IOException {
        CharStream input = CharStreams.fromFileName(fileFullPath);
        Lexer lexer = new RubyLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        final Future<RuleContext> handler = executor.submit(new Callable<RuleContext>() {
			@Override
			public RuleContext call() throws Exception {
					RubyParser parser = new RubyParser(tokens);
					return parser.compilation_unit();
			}				
			
			/* The following function is try to optimize performance with SLL mode, but
			 * the actual result shows that there is no improvement
			 * so we remove it again */
			@SuppressWarnings("unused")
			public RuleContext callWithFallBack() throws Exception {
				RubyParser parser = new RubyParser(tokens);
			    parser.setErrorHandler(new BailErrorStrategy());
				parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
				try {
					return parser.compilation_unit();
				} catch (final ParseCancellationException e) {

					// fall-back to LL mode parsing if SLL fails
					tokens.reset();
					parser.reset();
					parser.removeErrorListeners();
				    parser.setErrorHandler(new DefaultErrorStrategy());
					parser.getInterpreter().setPredictionMode(PredictionMode.LL);
					return parser.compilation_unit();
				}				
			}
        });
        
        RuleContext rootContext =null;
        try {
        	rootContext = handler.get(MAX_PARSE_TIME_PER_FILE, TimeUnit.MILLISECONDS);
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
        	System.err.println("time out of parse error in " + fileFullPath); 
        	handler.cancel(true);
        	return;
        }
        if (rootContext==null) {
        	System.err.println("parse error in " + fileFullPath); 
        	return;
        }
        RubyListener bridge = new RubyListener(fileFullPath, entityRepo);
	    ParseTreeWalker walker = new ParseTreeWalker();
	    walker.walk(bridge, rootContext);
	}

}

