# ruby-antlr-parser

The ANTLR v4 grammar for Ruby. 


## Notes/Improve points
- Most of ruby grammar till 2.6.0-rc2 was supported. 
- Performance: not good enough. Any contribution on performance is welcome.
- Regex: the lexer for regular expression is limited the 'blank' due to it is difficult to distiguish the regular expr / ... .../ and the divide operator of a / b + c / d

## Test
Step 1: create an alias like ```antlr4``` in your execution path. For example:

    #!/bin/sh

    CLASSPATH=/usr/share/java/stringtemplate4.jar:/usr/share/java/antlr4.jar:/usr/share/java/antlr4-runtime.jar:/usr/share/java/antlr3-runtime.jar/:/usr/share/java/treelayout.jar
    exec java -cp $CLASSPATH org.antlr.v4.Tool "$@"

Step 2: to compile and test the files

Under ruby directory, enter the following command:

```./tool/compiletest.sh test/t1.rb ```
    
There are also other commands under tool directory for convience.

## Contact
Any further questions, please kindly contact with Gang ZHANG (gangz@emergentdesign.cn) please. 
