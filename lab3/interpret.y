%{

#include <stdio.h>
#include <stdlib.h>
int yylex(void);
void yyerror(const char *s);

%}


%union {
        int intval;
        char * strval;
}


%token SEMICOLON ELSE IF NEWLINE PRINT ENDIF THEN '/' '*' '+' '-'
%token <strval> STRING
%token <intval> NUM
%type <intval> exp
%type <intval> term
%type <intval> factor
%%

program:
        stmts
        ;
stmts:
        stmt stmts
        |
        ;

stmt:
        prints
        | exp
        ;

prints:
        PRINT STRING SEMICOLON {printf("%s", $2);}
        | PRINT NEWLINE SEMICOLON {printf("\n");}
        | PRINT exp SEMICOLON {printf("%d", $2);}
        ;

exp:     exp '+' term        {$$ = $1 + $3;    }
        | exp '-' term     {$$ = $1 - $3;    }
        | term
        ;
term:   term '*' factor     {$$ = $1 * $3;    }
        | term '/' factor     {$$ = $1 / $3;    }
        |factor
        ;
factor: '(' exp ')' {$$ = $2;}
        | NUM {$$ = $1;}
        ;        


%%

void yyerror(const char *s) {
    fprintf(stderr, "%s\n", s);
}