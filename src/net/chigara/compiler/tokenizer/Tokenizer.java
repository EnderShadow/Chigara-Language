package net.chigara.compiler.tokenizer;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.chigara.compiler.exceptions.CompilationException;

public class Tokenizer
{
	private List<TokenData> tokenDatas;
	private String code;
	private Token lastToken;
	private boolean pushBack;
	
	public Tokenizer(String code)
	{
		this.code = code;
		lastToken = null;
		pushBack = false;
		
		tokenDatas = new ArrayList<TokenData>();
		tokenDatas.add(new TokenData(Pattern.compile("^([a-zA-Z][a-zA-Z0-9]*)"), TokenType.IDENTIFIER));
		tokenDatas.add(new TokenData(Pattern.compile("^(-?[0-9]+[^\\.])"), TokenType.INTEGER_LITERAL));
		tokenDatas.add(new TokenData(Pattern.compile("^(-?[0-9]*\\.[0-9]+)"), TokenType.FLOAT_LITERAL));
		tokenDatas.add(new TokenData(Pattern.compile("^(\".*\")"), TokenType.STRING_LITERAL));
		tokenDatas.add(new TokenData(Pattern.compile("^(\'.\')"), TokenType.CHARACTER_LITERAL));
		tokenDatas.add(new TokenData(Pattern.compile("^(\\{)"), TokenType.OPENBRACKET));
		tokenDatas.add(new TokenData(Pattern.compile("^(\\})"), TokenType.CLOSEBRACKET));
		
		//for(String str : new String[]{"=", "\\(", "\\)", "\\[", "\\]", "\\;", "\\{", "\\}", "\\.", "\\,"})
			tokenDatas.add(new TokenData(Pattern.compile("^([=\\(\\)\\[\\];\\.,])"), TokenType.TOKEN));
	}
	
	public List<Token> tokenize()
	{
		List<Token> tokens = new ArrayList<Token>();
		
		while(hasNextToken())
			tokens.add(nextToken());
		
		return tokens;
	}
	
	public Token nextToken()
	{
		if(pushBack)
		{
			pushBack = false;
			return lastToken;
		}
		code = code.trim();
		
		if(code.isEmpty())
			return (lastToken = new Token("", TokenType.EMPTY));
		
		for(TokenData tokenData : tokenDatas)
		{
			Matcher matcher = tokenData.pattern.matcher(code);
			
			if(matcher.find())
			{
				String token = matcher.group().trim();
				code = code.replaceFirst(tokenData.pattern.pattern(), "");
				
				if(tokenData.tokenType == TokenType.STRING_LITERAL || tokenData.tokenType == TokenType.CHARACTER_LITERAL)
					return (lastToken = new Token(token.substring(1, token.length() - 1), tokenData.tokenType));
				lastToken = new Token(token, tokenData.tokenType);
				
				if(lastToken.type == TokenType.IDENTIFIER)
				{
					TokenType newType;
					switch(lastToken.token)
					{
					case "public":
					case "protected":
					case "private":
						newType = TokenType.VISIBILITY;
						break;
					case "final":
						newType = TokenType.ATTRIBUTE;
						break;
					case "class":
					case "enum":
					case "value":
					case "interface":
					case "singleton":
						newType = TokenType.CLASS_TYPE;
						break;
					case "int8":
					case "int16":
					case "int32":
					case "int64":
					case "int128":
					case "uint8":
					case "uint16":
					case "uint32":
					case "uint64":
					case "uint128":
					case "float":
					case "double":
					case "quad":
					case "boolean":
					case "char":
					case "tuple":
					case "void":
						newType = TokenType.TYPE;
						break;
					case "true":
					case "false":
						newType = TokenType.BOOLEAN_LITERAL;
						break;
					case "if":
						newType = TokenType.IF;
						break;
					case "else":
						newType = TokenType.ELSE;
						break;
					case "while":
						newType = TokenType.WHILE;
						break;
					case "do":
						newType = TokenType.DO;
						break;
					case "for":
						newType = TokenType.FOR;
						break;
					case "switch":
						newType = TokenType.SWITCH;
						break;
					case "case":
						newType = TokenType.CASE;
						break;
					default:
						newType = TokenType.IDENTIFIER;
						break;
					}
					lastToken = new Token(lastToken.token, newType);
				}
				
				return lastToken;
			}
		}
		
		// TODO be more useful
		throw new CompilationException("Could not finish tokenizing code. Last token was: " + lastToken);
	}
	
	public boolean hasNextToken()
	{
		return !code.isEmpty();
	}
	
	public void pushBack()
	{
		if(lastToken != null)
			pushBack = true;
	}
}