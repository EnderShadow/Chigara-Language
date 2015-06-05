package net.chigara.compiler.tokenizer;

public class Token
{
	public final String token;
	public final TokenType type;
	
	public Token(String token, TokenType type)
	{
		this.token = token;
		this.type = type;
	}
}