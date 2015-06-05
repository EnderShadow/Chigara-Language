package net.chigara.compiler;

import java.util.ArrayList;
import java.util.List;

import net.chigara.compiler.compiled.Attribute;
import net.chigara.compiler.compiled.ClassType;
import net.chigara.compiler.compiled.CompiledClass;
import net.chigara.compiler.exceptions.CompilationException;
import net.chigara.compiler.tokenizer.Token;
import net.chigara.compiler.tokenizer.TokenType;
import net.chigara.compiler.tokenizer.Tokenizer;

public class Compiler
{
	public static CompiledClass[] compile(String data)
	{
		Tokenizer tokenizer = new Tokenizer(data);
		List<Token> tokens = tokenizer.tokenize();
		
		List<CompiledClass> compiledClasses = new ArrayList<CompiledClass>();
		while(tokens.size() > 0)
		{
			compiledClasses.add(compileClass(tokens));
		}
		return compiledClasses.toArray(new CompiledClass[compiledClasses.size()]);
	}
}