package net.chigara.compiler.tokenizer;

import java.util.regex.Pattern;

public class TokenData
{
	public final Pattern pattern;
	public final TokenType tokenType;
	
	public TokenData(Pattern pattern, TokenType tokenType)
	{
		this.pattern = pattern;
		this.tokenType = tokenType;
	}
}