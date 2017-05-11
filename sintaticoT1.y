%{
  import java.io.*;
%}
%token CLASS, PUBLIC, STATIC, VOID, MAIN, STRING, EXTENDS, RETURN, TRUE, FALSE
%token THIS, IF, WHILE, INT, BOOLEAN, SYSOUT, ELSE, LENGTH, NEW
%token IDENTIFIER, INTEGER_LITERAL

%right '='
%nonassoc '<'
%left AND
%left '-' '+'
%left '*'
%right '!'
   
%%

Goal 	: 	MainClass ClassDeclarationList
	|
	;

ClassDeclarationList : ClassDeclarationList ClassDeclaration
	|
	;

MainClass 	: 	CLASS Identifier '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' Identifier ')' '{' Statement '}' '}'
	|
	;

ClassDeclaration 	: 	CLASS Identifier ( EXTENDS Identifier )? '{' ( VarDeclaration )* ( MethodDeclaration )* '}'
;
VarDeclaration 	: 	Type Identifier ';'
;
MethodDeclaration 	: 	PUBLIC Type Identifier '(' ( Type Identifier ( ',' Type Identifier )* )? ')' '{' ( VarDeclaration )* ( Statement )* RETURN Expression ';' '}'
;
Type 	: 	INT '[' ']'
	| 	BOOLEAN
	| 	INT
	| 	Identifier
;
Statement 	: 	'{' ( Statement )* '}'
	| 	IF '(' Expression ')' Statement ELSE Statement
	| 	WHILE '(' Expression ')' Statement
	| 	SYSOUT '(' Expression ')' ';'
	| 	Identifier '=' Expression ';'
	| 	Identifier '[' Expression ']' '=' Expression ';'
;
Expression 	: 	Expression ( '&&' | '<' | '+' | '-' | '*' ) Expression
	| 	Expression '[' Expression ']'
	| 	Expression '.' LENGTH
	| 	Expression '.' Identifier '(' ( Expression ( ',' Expression )* )? ')'
	| 	INTEGER_LITERAL
	| 	TRUE
	| 	FALSE
	| 	Identifier
	| 	THIS
	| 	NEW INT '[' Expression ']'
	| 	NEW Identifier '(' ')'
	| 	'!' Expression
	| 	'(' Expression ')'
;
Identifier 	: 	IDENTIFIER
;



%%

  private Yylex lexer;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e.getMessage());
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }


  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println("");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("> ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("done!");
    }
  }

