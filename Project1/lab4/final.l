%{
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include "y.tab.h"
    char * quotes(char *quote);
    char str[1024];
%}

%%

" "* {}
"\t" {}
"//".* {}

("print") {return PRINT;}
("newline") {return NEWLINE;}
"if" {return IF;}
"else" {return ELSE;}
"endif" {return ENDIF;}
"then" {return THEN;}

(["'].*["'])  {strncpy(str, &(yytext[1]), strlen(yytext)-2);
                str[strlen(yytext)-2] = '\0';
                yylval.strval = strdup(str);
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
"=" {return '=';}
"<=" {return LE;}
">=" {return GE;}
"==" {return EE;}
"!=" {return NE;}

[A-Za-z]([0-9]|[A-Za-z])* {yylval.strval = strdup(yytext); return ID;}


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