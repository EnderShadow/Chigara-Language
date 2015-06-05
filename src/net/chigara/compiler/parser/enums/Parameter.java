package net.chigara.compiler.parser.enums;

public class Parameter
{
	public final String name;
	public final Type type;
	
	public Parameter(Type type, String name)
	{
		this.name = name;
		this.type = type;
	}
}