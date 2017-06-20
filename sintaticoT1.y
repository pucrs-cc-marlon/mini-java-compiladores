%{
  import java.io.*;
%}
   
%token CLASS, PUBLIC, STATIC, VOID, MAIN , STRING, STRINGAF EXTENDS, RETURN, INT, BOOL
%token IF, ELSE, WHILE, LEN, PRINT, TRUE, FALSE, NEW, AND, THIS 
%token Identifier, INTEGER_LITERAL 

%right '='
%nonassoc '<'
%left AND
%left '-' '+'
%left '*'
%right '!'
%left '.'
%left '['

%%
Goal :  MainClass ClassDeclarationList  
     ;

ClassDeclarationList : ClassDeclarationList ClassDeclaration
                     |
                     ;
 
MainClass : CLASS Identifier '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' Identifier ')' '{' Statement '}' '}'
          ;

ClassDeclaration :  CLASS Identifier extendsOPC '{' VarMethodDeclarationList '}'
                 ;

extendsOPC : EXTENDS Identifier
           |
           ;

VarMethodDeclarationList : Type Identifier ';' VarMethodDeclarationList
                         | MethodDeclarationList
                         ;


MethodDeclarationList : MethodDeclarationList MethodDeclaration
                      |
                      ;


MethodDeclaration : PUBLIC Type Identifier '(' ParamListOpc ')' '{' VarStatementList RETURN Exp ';' '}'
                  ;

VarStatementList : Type Identifier ';'     VarStatementList
           | Statement          StatementList
         |
                 ;

ParamListOpc  : Type Identifier  ParamList
              |
              ;

ParamList : ',' Type Identifier ParamList
          | 
          ;


StatementList : StatementList Statement
              |
              ;


BaseType :  INT '[' ']'
       |  BOOL
       |  INT
         ;

Type : BaseType
   |  Identifier
     ;

Statement   :   '{' StatementList '}'
  |   IF '(' Exp ')' Statement ELSE Statement
  |   WHILE '(' Exp ')' Statement
  |   PRINT '(' Exp ')' ';'
  |   Identifier '=' Exp ';'
  |   Identifier '[' Exp ']' '=' Exp ';'

Exp : Exp AND Exp
    | Exp '<' Exp
    | Exp '+' Exp
    | Exp '-' Exp
    | Exp '*'Exp
  | Exp '[' Exp ']'
  | Exp '.' LEN
  | Exp '.' Identifier '(' LExpOpc ')'
  | INTEGER_LITERAL
  | TRUE
  | FALSE
  | Identifier
  | THIS
  | NEW INT '[' Exp ']'
  | NEW Identifier '(' ')'
  | '!' Exp
  | '(' Exp ')'
    ;

LExpOpc : Exp LExpList
        |
        ;

LExpList : ',' Exp  LExpList 
         |
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
      System.err.println("IO error: "+e.getMessage());
    }
    return yyl_return;
  }

  public void setDebug(boolean debug) {
    yydebug = debug;
  }

  public void yyerror (String error) {
    System.err.println ("Error: " + error);// + " at line: "+ lexer.getLine());
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }


  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println();

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
    
  //  if (interactive) {
      System.out.println();
      System.out.println("done!");
  //  }
  }
