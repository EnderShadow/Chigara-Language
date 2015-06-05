package net.chigara.compiler.tokenizer;

public enum TokenType
{
	// represents an empty token
	EMPTY,
	
	// represents simple tokens ex: ( ) { } ; = ,
	TOKEN,
	
	// represents a name
	IDENTIFIER,
	
	// represents an integer literal
	INTEGER_LITERAL,
	
	// represents a float literal
	FLOAT_LITERAL,
	
	// represents a string literal; are always enclosed by ""
	STRING_LITERAL,
	
	// represents method, or variable visibility
	VISIBILITY,
	
	// represents an attribute such as final
	ATTRIBUTE,
	
	// represents class type: class, enum, value, interface, or singleton
	CLASS_TYPE,
	
	// represents variable or return type: byte, short, int, long, unsigned byte, unsigned short, unsigned int, unsigned long,
	//									   float, double, quadruple, boolean, char, tuple, object, void
	TYPE,
	
	// represents a boolean literal: true, false
	BOOLEAN_LITERAL,
	
	// represents an object literal: null
	
	// represents a character literal: 'a', 'b', '0', '1', '?'
	CHARACTER_LITERAL,
	
	IF, ELSE, WHILE, DO, FOR, SWITCH, CASE,
	
	OPENBRACKET, CLOSEBRACKET,
	
	/*CONSTANT,*/ VARIABLE, OBJECT, PRIMITIVE,
}