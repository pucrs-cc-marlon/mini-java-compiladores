//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "sintaticoT1.y"
  import java.io.*;
//#line 19 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short CLASS=257;
public final static short PUBLIC=258;
public final static short STATIC=259;
public final static short VOID=260;
public final static short MAIN=261;
public final static short STRING=262;
public final static short STRINGAF=263;
public final static short EXTENDS=264;
public final static short RETURN=265;
public final static short INT=266;
public final static short BOOL=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short WHILE=270;
public final static short LEN=271;
public final static short PRINT=272;
public final static short TRUE=273;
public final static short FALSE=274;
public final static short NEW=275;
public final static short AND=276;
public final static short THIS=277;
public final static short Identifier=278;
public final static short INTEGER_LITERAL=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    2,    2,    1,    3,    5,    5,    6,    6,    8,
    8,    9,   11,   11,   11,   10,   10,   14,   14,   13,
   13,   15,   15,   15,    7,    7,    4,    4,    4,    4,
    4,    4,   12,   12,   12,   12,   12,   12,   12,   12,
   12,   12,   12,   12,   12,   12,   12,   12,   12,   16,
   16,   17,   17,
};
final static short yylen[] = {                            2,
    2,    2,    0,   17,    6,    2,    0,    4,    1,    2,
    0,   12,    4,    2,    0,    3,    0,    4,    0,    2,
    0,    3,    1,    1,    1,    1,    3,    7,    5,    5,
    4,    7,    3,    3,    3,    3,    3,    4,    3,    6,
    1,    1,    1,    1,    1,    5,    4,    2,    3,    2,
    0,    3,    0,
};
final static short yydefred[] = {                         0,
    0,    0,    3,    0,    0,    0,    0,    2,    0,    0,
    0,    0,    0,    0,    6,    0,    0,    0,   23,   26,
    0,    0,    0,   25,    0,    0,    5,    0,    0,   10,
    0,   22,    0,    0,    0,    8,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   16,    0,    0,
    0,    0,    0,   21,    0,    0,    0,   21,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   42,   43,    0,   45,   44,   41,    0,    0,    0,
    0,    0,    0,    0,   27,   20,    4,   18,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   31,    0,   13,    0,    0,    0,
   49,    0,    0,    0,    0,    0,   39,    0,    0,    0,
   29,   30,    0,   12,    0,   47,    0,   38,    0,    0,
   46,    0,    0,   28,   32,    0,   50,   40,    0,   52,
};
final static short yydgoto[] = {                          2,
    3,    5,    8,   58,   13,   21,   22,   23,   30,   42,
   60,   80,   66,   48,   24,  133,  137,
};
final static short yysindex[] = {                      -251,
 -268,    0,    0, -112, -230, -220, -233,    0, -210, -212,
 -194, -208,  -42, -166,    0, -249,   58,    9,    0,    0,
  -24, -168, -138,    0, -141,   34,    0,   71, -249,    0,
   40,    0, -249, -146,   44,    0,  110, -127, -249,  120,
 -113,  129,   55,  139,   62,  -82, -249,    0,  -99,  147,
  149,  151,  -52,    0,   67,  -80,  -52,    0,  -79,  -65,
  -33,  -33,  -33,  -33,  -33,  -75,   76,  139,  -82,  143,
  -33,    0,    0, -179,    0,    0,    0,  -33,  -33,  -20,
   -9,    1,   43,   51,    0,    0,    0,    0,  -99,   63,
  114,  166,   16,   13,  -33,  -33,  -33,  -33,  -33, -243,
  -33,  -82,  -82,  148,    0,  154,    0,   87,  -33,  167,
    0,  168,  113,  135,  135,   16,    0,  176,   73,  -46,
    0,    0,  -33,    0,   83,    0,  -33,    0,  -82,   93,
    0,  103,  183,    0,    0,  -33,    0,    0,  103,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,  217,    0,    0,    0,    0,  102,
    0,    0,    0,    0,    0, -117,    0,  -50,    0,    0,
    0,    0,  104,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -117,    0,    0,    0,    0,    0,  189,    0,
    0,    0,    0,  190,    0,    0,    0,    0,  -32,    0,
    0,    0,    0,    0,    0,    0,  -41,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  190,  -27,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -32,    0,
    0,    0,  -40,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   19,  116,   24,   31,  -29,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  191,    0,    0,    0,
    0,  198,    0,    0,    0,    0,    0,    0,  198,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,   11,    0,  210,  133,    0,    0,    0,
  159,  156,  192,  181,    0,    0,  119,
};
final static int YYTABLESIZE=389;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         78,
   48,   48,   48,   48,   48,    1,   79,   11,   64,    4,
    6,   37,   37,   37,   37,   37,   18,   19,   48,   48,
  102,   99,   98,   54,   97,  100,    7,  117,   20,   37,
   37,  103,   99,   98,  118,   97,  100,    9,   65,   96,
   54,  104,   99,   98,   10,   97,  100,   54,   11,   85,
   96,   12,   48,  111,   99,   98,   55,   97,  100,   33,
   96,  100,   33,   37,   36,   14,   36,   36,   36,   15,
  101,   35,   96,   35,   35,   35,   86,   33,   33,   86,
   16,  101,   36,   36,   99,   98,   91,   97,  100,   35,
   35,  101,   99,   98,   17,   97,  100,   25,   92,   26,
   27,  105,   96,  101,   99,   98,  101,   97,  100,   28,
   96,   33,  120,  121,   99,   98,   36,   97,  100,   29,
   31,  108,   96,   35,   99,   98,   32,   97,  100,   33,
   35,   37,   96,  101,   99,   98,   38,   97,  100,  134,
   11,  101,   96,  106,   99,   98,  136,   97,  100,   39,
   40,  135,   96,  101,   99,   98,   34,   97,  100,   34,
   43,   34,   96,  101,   44,  128,   18,   19,   50,   45,
   51,   41,   52,  101,   34,  131,   99,   46,   57,   56,
  100,   59,   47,  101,   49,   50,   61,   51,   62,   52,
   63,   67,   50,  101,   51,   53,   52,   68,   70,   71,
   87,   89,   53,  101,  109,  110,  122,  126,   34,   99,
   98,  124,   97,  100,  123,  127,    1,   81,   82,   83,
   84,   59,  129,  138,    7,  101,   90,   24,    9,   17,
   19,   51,   15,   93,   94,   48,   26,   14,   53,   72,
   73,   74,   36,   75,   76,   77,   37,  107,   88,   69,
  112,  113,  114,  115,  116,   95,  119,  140,  101,    0,
    0,    0,    0,    0,  125,    0,   95,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   95,    0,  130,    0,
    0,    0,  132,    0,    0,    0,    0,    0,   95,    0,
    0,  139,    0,    0,   33,    0,    0,    0,    0,   36,
    0,    0,    0,    0,    0,    0,   35,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
    0,    0,    0,    0,    0,    0,   95,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   95,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   42,   43,   44,   45,  257,   40,  125,   61,  278,
  123,   41,   42,   43,   44,   45,  266,  267,   59,   60,
   41,   42,   43,  123,   45,   46,  257,  271,  278,   59,
   60,   41,   42,   43,  278,   45,   46,  258,   91,   60,
  123,   41,   42,   43,  278,   45,   46,  123,  259,  125,
   60,  264,   93,   41,   42,   43,   46,   45,   46,   41,
   60,   46,   44,   93,   41,  260,   43,   44,   45,  278,
   91,   41,   60,   43,   44,   45,   66,   59,   60,   69,
  123,   91,   59,   60,   42,   43,  266,   45,   46,   59,
   60,   91,   42,   43,  261,   45,   46,   40,  278,   91,
  125,   59,   60,   91,   42,   43,   91,   45,   46,  278,
   60,   93,  102,  103,   42,   43,   93,   45,   46,  258,
  262,   59,   60,   93,   42,   43,   93,   45,   46,   59,
   91,  278,   60,   91,   42,   43,   93,   45,   46,  129,
  258,   91,   60,   93,   42,   43,   44,   45,   46,   40,
  278,   59,   60,   91,   42,   43,   41,   45,   46,   44,
   41,   29,   60,   91,  278,   93,  266,  267,  268,   41,
  270,   39,  272,   91,   59,   93,   42,  123,  278,   47,
   46,   49,   44,   91,  123,  268,   40,  270,   40,  272,
   40,  125,  268,   91,  270,  278,  272,  278,  278,  265,
  125,   59,  278,   91,   91,   40,   59,   41,   93,   42,
   43,  125,   45,   46,   61,   40,    0,   62,   63,   64,
   65,   89,  269,   41,  123,   91,   71,  278,  125,   41,
   41,   41,  265,   78,   79,  276,  278,  265,   41,  273,
  274,  275,   33,  277,  278,  279,  276,   89,   68,   58,
   95,   96,   97,   98,   99,  276,  101,  139,   91,   -1,
   -1,   -1,   -1,   -1,  109,   -1,  276,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,  123,   -1,
   -1,   -1,  127,   -1,   -1,   -1,   -1,   -1,  276,   -1,
   -1,  136,   -1,   -1,  276,   -1,   -1,   -1,   -1,  276,
   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  276,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'",null,null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CLASS","PUBLIC","STATIC","VOID",
"MAIN","STRING","STRINGAF","EXTENDS","RETURN","INT","BOOL","IF","ELSE","WHILE",
"LEN","PRINT","TRUE","FALSE","NEW","AND","THIS","Identifier","INTEGER_LITERAL",
};
final static String yyrule[] = {
"$accept : Goal",
"Goal : MainClass ClassDeclarationList",
"ClassDeclarationList : ClassDeclarationList ClassDeclaration",
"ClassDeclarationList :",
"MainClass : CLASS Identifier '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' Identifier ')' '{' Statement '}' '}'",
"ClassDeclaration : CLASS Identifier extendsOPC '{' VarMethodDeclarationList '}'",
"extendsOPC : EXTENDS Identifier",
"extendsOPC :",
"VarMethodDeclarationList : Type Identifier ';' VarMethodDeclarationList",
"VarMethodDeclarationList : MethodDeclarationList",
"MethodDeclarationList : MethodDeclarationList MethodDeclaration",
"MethodDeclarationList :",
"MethodDeclaration : PUBLIC Type Identifier '(' ParamListOpc ')' '{' VarStatementList RETURN Exp ';' '}'",
"VarStatementList : Type Identifier ';' VarStatementList",
"VarStatementList : Statement StatementList",
"VarStatementList :",
"ParamListOpc : Type Identifier ParamList",
"ParamListOpc :",
"ParamList : ',' Type Identifier ParamList",
"ParamList :",
"StatementList : StatementList Statement",
"StatementList :",
"BaseType : INT '[' ']'",
"BaseType : BOOL",
"BaseType : INT",
"Type : BaseType",
"Type : Identifier",
"Statement : '{' StatementList '}'",
"Statement : IF '(' Exp ')' Statement ELSE Statement",
"Statement : WHILE '(' Exp ')' Statement",
"Statement : PRINT '(' Exp ')' ';'",
"Statement : Identifier '=' Exp ';'",
"Statement : Identifier '[' Exp ']' '=' Exp ';'",
"Exp : Exp AND Exp",
"Exp : Exp '<' Exp",
"Exp : Exp '+' Exp",
"Exp : Exp '-' Exp",
"Exp : Exp '*' Exp",
"Exp : Exp '[' Exp ']'",
"Exp : Exp '.' LEN",
"Exp : Exp '.' Identifier '(' LExpOpc ')'",
"Exp : INTEGER_LITERAL",
"Exp : TRUE",
"Exp : FALSE",
"Exp : Identifier",
"Exp : THIS",
"Exp : NEW INT '[' Exp ']'",
"Exp : NEW Identifier '(' ')'",
"Exp : '!' Exp",
"Exp : '(' Exp ')'",
"LExpOpc : Exp LExpList",
"LExpOpc :",
"LExpList : ',' Exp LExpList",
"LExpList :",
};

//#line 112 "sintaticoT1.y"

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
//#line 411 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
