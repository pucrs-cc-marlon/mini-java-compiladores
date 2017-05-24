%%

%byaccj


%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NL  = \n | \r | \r\n

%%

"class" 	{ return Parser.CLASS; }
"public"	{ return Parser.PUBLIC; }
"static"	{ return Parser.STATIC; }
"void"		{ return Parser.VOID; }
"main"		{ return Parser.MAIN; }
"String"	{ return Parser.STRING; }
"extends"	{ return Parser.EXTENDS; }
"return"	{ return Parser.RETURN; }
"true"		{ return Parser.TRUE; }
"false"		{ return Parser.FALSE; }
"this"		{ return Parser.THIS; }
"if"		{ return Parser.IF; }
"while"		{ return Parser.WHILE; }
"int"		{ return Parser.INT; }
"boolean"	{ return Parser.BOOLEAN; }
"System.out.println" { return Parser.SYSOUT; }
"else"		{ return Parser.ELSE; }
"length"	{ return Parser.LENGTH; }
"new"		{ return Parser.NEW; }


"," |
";" |
"(" |
"[" |
"]" |
")" |
"{" |
"}" |
"&&" |
"<" |
">" |
"!" |
"=" |
"+" |
"-" |
"." |
"*" |
"/" { return (int) yycharat(0); }

[a-zA-Z][a-zA-Z0-9_]* { return Parser.IDENTIFIER;}
[0-9]+	{ return Parser.INTEGER_LITERAL; }


[ \t]+ { }
{NL}+  { }

.    { System.err.println("Error: unexpected character '"+yytext()+"' na linha "+yyline); return YYEOF; }






