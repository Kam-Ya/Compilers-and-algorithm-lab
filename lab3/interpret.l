%{
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include "y.tab.h"
    char * quotes(char *quote);
%}

%%

" "* {}

("print") {return PRINT;}

("newline") {return NEWLINE;}

(["'].*["'])  {yylval.strval = strdup(quotes(yytext));
                    return STRING;}

(";")  {return SEMICOLON;}

"+" {return '+';}
"-" {return '-';}
"*" {return '*';}
"/" {return '/';}
"(" {return '(';}
")" {return ')';}
">" {return '>';}
"<" {return '<';}
"<=" {return LE;}
">=" {return GE;}
"==" {return EE;}
"!=" {return NE;}

[0-9]+ {yylval.intval = atoi(yytext);
        return NUM;}

.                     { printf("Unknown char");  }

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

char * quotes(char *quote) {
    for (int i = 0; i < strlen(quote); i++) {
        quote[i] = quote[i + 1];
    }

    quote[strlen(quote) - 1] = '\0';

    return quote;
}