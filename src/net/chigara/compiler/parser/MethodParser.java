package net.chigara.compiler.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.chigara.compiler.parser.blocks.Block;
import net.chigara.compiler.parser.blocks.MethodBlock;
import net.chigara.compiler.parser.enums.Attribute;
import net.chigara.compiler.parser.enums.Parameter;
import net.chigara.compiler.parser.enums.Type;
import net.chigara.compiler.parser.enums.Visibility;
import net.chigara.compiler.tokenizer.Token;
import net.chigara.compiler.tokenizer.TokenType;
import net.chigara.compiler.tokenizer.Tokenizer;

public class MethodParser extends Parser<MethodBlock>
{
	@Override
	public boolean shouldParse(String line)
	{
		return line.matches("((public)|(private)|(protected)) (final )?[a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*\\([a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*(, [a-zA-Z][a-zA-Z0-9]* [a-zA-Z][a-zA-Z0-9]*)*\\)");
	}
	
	@Override
	public MethodBlock parse(Block superBlock, Tokenizer t)
	{
		Visibility visibility = Visibility.valueOf(t.nextToken().token.toUpperCase());
		List<Object> temp = new ArrayList<Object>();
		Token token;
		while((token = t.nextToken()).type == TokenType.ATTRIBUTE)
			temp.add(token.token);
		Attribute[] attributes = temp.stream().map(attr -> Attribute.valueOf((String) attr)).collect(Collectors.toList()).toArray(new Attribute[temp.size()]);
		Type type = Type.getEnum(t.nextToken().token.toUpperCase());
		String name = t.nextToken().token;
		t.nextToken();
		temp.clear();
		while(!(token = t.nextToken()).token.equals(")"))
		{
			if(!token.token.equals(","))
			{
				temp.add(new Parameter(Type.getEnum(t.nextToken().token.toUpperCase()), t.nextToken().token));
			}
		}
		Parameter[] parameters = temp.stream().collect(Collectors.toList()).toArray(new Parameter[temp.size()]);
		
		return new MethodBlock(superBlock, name, visibility, type, attributes, parameters);
	}
}