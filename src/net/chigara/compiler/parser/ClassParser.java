package net.chigara.compiler.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import net.chigara.compiler.exceptions.CompilationException;
import net.chigara.compiler.parser.blocks.Block;
import net.chigara.compiler.parser.blocks.ClassBlock;
import net.chigara.compiler.parser.enums.Attribute;
import net.chigara.compiler.tokenizer.Token;
import net.chigara.compiler.tokenizer.TokenType;
import net.chigara.compiler.tokenizer.Tokenizer;

public class ClassParser extends Parser<ClassBlock>
{
	@Override
	public boolean shouldParse(String line)
	{
		return line.matches("(final )?class [a-zA-Z][a-zA-Z0-9]*( extends [a-zA-Z][a-zA-Z0-9]*)?( implements [a-zA-Z][a-zA-Z0-9]*(, [a-zA-Z][a-zA-Z0-9]*)*)?");
	}
	
	@Override
	public ClassBlock parse(Block superBlock, Tokenizer t)
	{
		List<String> temp = new ArrayList<String>();
		Token token;
		while(!(token = t.nextToken()).token.equals("class"))
			temp.add(token.token);
		String[] attributes = temp.toArray(new String[temp.size()]);
		String name = t.nextToken().token;
		String superClass = "Object";
		String[] interfaces = new String[0];
		if((token = t.nextToken()).token.equals("extends"))
		{
			superClass = t.nextToken().token;
			if((token = t.nextToken()).token.equals("implements"))
			{
				temp.clear();
				while(!(token = t.nextToken()).token.equals("{"))
				{
					if(token.type == TokenType.IDENTIFIER)
						temp.add(token.token);
				}
				t.pushBack();
				if(temp.size() == 0)
					throw new CompilationException("Compilation Error: Expected an interface but found none");
				interfaces = temp.toArray(new String[temp.size()]);
			}
			else if(token.token.equals("{"))
			{
				t.pushBack();
			}
		}
		else if(token.token.equals("implements"))
		{
			temp.clear();
			while(!(token = t.nextToken()).token.equals("{"))
			{
				if(token.type == TokenType.IDENTIFIER)
					temp.add(token.token);
			}
			t.pushBack();
			if(temp.size() == 0)
				throw new CompilationException("Compilation Error: Expected an interface but found none");
			interfaces = temp.toArray(new String[temp.size()]);
		}
		else if(token.token.equals("{"))
		{
			t.pushBack();
		}
		Attribute[] tempa = Arrays.stream(attributes).map(attr -> Attribute.valueOf(attr.toUpperCase())).collect(Collectors.toList()).toArray(new Attribute[0]);
		return new ClassBlock(name, superClass, tempa, interfaces);
	}
}