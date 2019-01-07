# ruby-antlr-parser

The ANTLR v4 grammar for Ruby. 


## Notes/Improve points
- Ruby is a very flexiable lanaguage. The parser should consider many possibile special usages. It impacts the performance of the parser, but I belive there still should be some solution to solve it.
- Currently version is already cover most of ruby grammar, took a reference with  ```2.6.0-rc2```.
- Still several points should be mentioned:
  * Regex is not support well: an *expression* could be ```a / b + c / d```, which include two ```/``` marks. While the regex in ruby is also a style like ```/ ... .../```. I still have not found solution for that. For the time being, I just simple identify tokens without blank between ```/ ... .../``` as regex, and identify others as normal tokens.
  * Due to performance issue, parse on some special files took long time. An example is given under ```ruby/test/with_problem/long_time_to_parse.rb```. If I disable the rule of ```#ExprFunctionCall1``` and ```#ExprFunctionCall2```, the parser performance is improved far, but I cannot do that due to there are certainly functions without brackets.
 

## Test
Step 1: set up enviorment

Firstly you should have antlr4 installed on your operation system.
Then create an shortcut command likes ```antlr4``` in your execution path. The content likes following:

    #!/bin/sh

    CLASSPATH=/usr/share/java/stringtemplate4.jar:/usr/share/java/antlr4.jar:/usr/share/java/antlr4-runtime.jar:/usr/share/java/antlr3-runtime.jar/:/usr/share/java/treelayout.jar
    exec java -cp $CLASSPATH org.antlr.v4.Tool "$@"

Step 2: to compile and test the files

Please note that *all command should be invoked under directory of ```ruby```*.  Under the directory, enter the following command please:

```./tool/compiletest.sh test/helloworld.rb ```

Antlr will compile the grammer files and to parse the ```test/helloworld.rb``` file.
  
There are several commands under the ```tool``` directory for your convience.

## Contact
Any contribution and feedback on the project is highly appreciated. Any further questions, kindly contact with Gang ZHANG (gangz@emergentdesign.cn) please. 
