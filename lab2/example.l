%{
    #include <stdio.h>
    #include <stdlib.h>
    #include "y.tab/h"
    int yyerror(char * errormsg);
%


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
}}