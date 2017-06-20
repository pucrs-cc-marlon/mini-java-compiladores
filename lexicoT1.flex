%%

%byaccj


%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }

  public int getLine() { return yyline; }

%}

NL  = \n | \r | \r\n

%%

"$TRACE_ON"  { yyparser.setDebug(true);  }
"$TRACE_OFF" { yyparser.setDebug(false); }

"class"     { return Parser.CLASS; }
"public"    { return Parser.PUBLIC; }
"static"    { return Parser.STATIC; }
"void"      { return Parser.VOID; }
"main"      { return Parser.MAIN; }
"String"      { return Parser.STRING; }
"extends"   { return Parser.EXTENDS; }
"return"    { return Parser.RETURN; }
"int"       { return Parser.INT; }
"boolean"   { return Parser.BOOL; }
"if"        { return Parser.IF; }
"else"      { return Parser.ELSE; }
"while"     { return Parser.WHILE; }
"length"    { return Parser.LEN; }
"System.out.println" { return Parser.PRINT; }
"true"      { return Parser.TRUE; }
"false"     { return Parser.FALSE; }
"this"      { return Parser.THIS; }
"new"       { return Parser.NEW; }
"&&"        { return Parser.AND; }


"," |
";" |
"(" |
"[" |
"]" |
")" |
"{" |
"}" |
"<" |
">" |
"!" |
"=" |
"+" |
"-" |
"." |
"*" |
"/" { return (int) yycharat(0); }

[a-zA-Z][a-zA-Z0-9_]* { return Parser.Identifier;}
[0-9]+	{ return Parser.INTEGER_LITERAL; }


[ \t]+ { }
{NL}+  { yyline++; }
"//"[^\n\r]* {}

.    { System.err.println("Error: unexpected character '"+yytext()+"' na linha "+yyline); return YYEOF; }






