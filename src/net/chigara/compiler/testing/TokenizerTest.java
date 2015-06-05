package net.chigara.compiler.testing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.stream.Collectors;

import net.chigara.compiler.exceptions.CompilationException;
import net.chigara.compiler.tokenizer.Token;
import net.chigara.compiler.tokenizer.Tokenizer;

public class TokenizerTest
{
	public static void main(String[] args)
	{
		String[] files = new String[]{"HelloWorld", "Variables"};
		for(String str : files)
		{
			try(BufferedReader br = new BufferedReader(new FileReader(new File("examples/" + str + ".chigara"))))
			{
				String code = br.lines().collect(Collectors.joining("\n"));
				Tokenizer tokenizer = new Tokenizer(code);
				while(tokenizer.hasNextToken())
				{
					Token t = tokenizer.nextToken();
					System.out.println(t.type + "\t" + t.token);
				}
			}
			catch(CompilationException e)
			{
				System.out.println("Failed to compile " + str + ".chigara");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}