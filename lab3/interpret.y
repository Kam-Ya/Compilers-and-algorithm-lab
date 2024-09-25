%{

#include <stdio.h>
#include <stdlib.h>
int yylex(void);
int yyerror(const char *s);

%}

int math();

%token expr

%%

program: 
         math bye
        ;

E : math   {
        return $$;
}

math:     
        math '+' math {$$ = $1 + $3} 
        | math '-' math {$$ = $1 - $3}
        | math '*' math {$$ = $1 * $3}
        | math '/' math {$$ = $1 / $3}
        | math "!=" math {if (&1 != $3) {&& = 1} else {$$ = 0}}
        | math "<" math {if (&1 < $3) {&& = 1} else {$$ = 0}}
        | math ">" math {if (&1 > $3) {&& = 1} else {$$ = 0}}
        | math "<=" math {if (&1 <= $3) {&& = 1} else {$$ = 0}}
        | math ">=" math {if (&1 >= $3) {&& = 1} else {$$ = 0}}
        | math "==" math {if (&1 == $3) {&& = 1} else {$$ = 0}}
        | '(' math ')' {$$ = $2} 
        ;
bye:    
        BYE    { printf("Bye World\n"); exit(0); }


int math() {

}