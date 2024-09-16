%{
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include "y.tab.h"
    int yyerror(char * errormsg);
    char * quotes(char *quote);
%}

%%

("+"|"-"|"*"|"/"|"="|"!="|"<"|">"|"<="|">="|"==")"\n" { printf("Operator: %s\n", yytext);}

("if"|"else"|"then"|"endif"|"print"|"newline")"\n" {printf("Keyword: %s\n", yytext);}

(["'].*["'])"\n"  {printf("Quoted String: %s\n", quotes(yytext));}

("("|")")"\n" {printf("Parenthesis: %s\n", yytext);}

(";")"\n"  {printf("Semicolon: ;\n");}

^[A-Za-z]([0-9]|[A-Za-z])+$ {printf("Identifier: %s\n", yytext);}

([0-9]+)"\n" {printf("Integer: %s\n", yytext);}
.                     { yyerror("Unknown char");  }

%%

int main(void)
{
   yyparse();
   return 0;
}

int yywrap(void)
{
   return 0;
}

int yyerror(char *errormsg)
{
    fprintf(stderr, "%s\n", errormsg);
}

char * quotes(char *quote) {
    for (int i = 0; i < strlen(quote); i++) {
        quote[i] = quote[i + 1];
    }

    quote[strlen(quote) - 2] = '\0';

    return quote;
}
