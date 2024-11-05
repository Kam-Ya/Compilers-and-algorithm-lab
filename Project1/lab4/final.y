%{

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int yylex(void);
void yyerror(const char *s);
#define SIZE 10

int stack[SIZE] = {1,0,0,0,0,0,0,0,0,0};
int lastIn = 0;

struct identifier {
        char * id;
        int val;
};

struct identifier table[11];
int stored = 0;

int getVal(char *key) {
        for (int i = 0; i < stored; i++) {
                if (strcmp(table[i].id, key)) {
                        return table[i].val;
                }
        }
}

void setVal(char * key, int value) {
        for (int i = 0; i < stored; i++) {
                if (strcmp(table[i].id, key)) {
                        table[i].val = value;
                        return;
                }
        }
        table[stored].id = key;
        table[stored].val = value;
        // printf("stored = 1: %s: %d", table[stored].id, table[stored].val);
        stored++;
}


void push(int val);
void pop();
int top();
%}

%union {
        int intval;
        char * strval;
}


%token SEMICOLON ELSE IF NEWLINE PRINT ENDIF THEN '/' '*' '+' '-' '>' '<' '='
%token LE GE EE NE
%token <strval> STRING
%token <intval> NUM
%token <strval> ID
%type <intval> exp
%type <intval> term
%type <intval> factor
%type <strval> assign
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
        | ifs
        | assign
        ;

assign: 
        ID '=' exp SEMICOLON {setVal($1, $3);}
        ;

prints:
        PRINT STRING SEMICOLON {if (top() == 1) {printf("%s", $2);}}
        | PRINT NEWLINE SEMICOLON {if (top() == 1) {printf("\n");}}
        | PRINT exp SEMICOLON {if (top() == 1) {printf("%d", $2);}}
        ;

exp:     exp '+' term        {$$ = $1 + $3;    }
        | exp '-' term     {$$ = $1 - $3;    }
        | exp '<' term      {if ($1 < $3) {$$ = 1;} else {$$ = 0;}}
        | exp '>' term      {if ($1 > $3) {$$ = 1;} else {$$ = 0;}}
        | exp LE term      {if ($1 <= $3) {$$ = 1;} else {$$ = 0;}}
        | exp GE term      {if ($1 >= $3) {$$ = 1;} else {$$ = 0;}} 
        | exp EE term      {if ($1 == $3) {$$ = 1;} else {$$ = 0;}}
        | exp NE term      {if ($1 != $3) {$$ = 1;} else {$$ = 0;}}
        | term
        ;
term:   term '*' factor     {$$ = $1 * $3;    }
        | term '/' factor     {$$ = $1 / $3;    }
        |factor
        ;
factor: '(' exp ')' {$$ = $2;}
        | NUM {$$ = $1;}
        | ID {$$ = getVal($1);}
        ;        
ifs:
        IF exp THEN {top()==1 ? push($2!=0) : push(0);} stmts {pop();} ENDIF
        | IF exp THEN {top()==1 ? push($2!=0) : push(0);} stmts
        | ELSE {top()==1 ? push(0) : push(1);} stmts {pop(); pop();} ENDIF
        ;



%%

void yyerror(const char *s) {
    fprintf(stderr, "%s\n", s);
}

void push(int val) {
        if (lastIn == SIZE - 1) {
                yyerror("STACK OVERFLOW");
        } else {
                stack[++lastIn] = val;
        }
}

void pop() {
        if (lastIn == 0) {
                yyerror("STACK UNDERFLOW");
        } else {
                stack[lastIn] = 1;
                lastIn;
        }
}

int top() {
        return stack[lastIn];
}