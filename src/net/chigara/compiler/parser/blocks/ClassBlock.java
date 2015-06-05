package net.chigara.compiler.parser.blocks;

import net.chigara.compiler.parser.enums.Attribute;

public class ClassBlock extends Block
{
	public final String name;
	public final String superClass;
	public final String[] interfaces;
	public final Attribute[] attributes;
	
	public ClassBlock(String name, String superClass, Attribute[] attributes, String[] interfaces)
	{
		super(null);
		this.name = name;
		this.superClass = superClass;
		this.interfaces = interfaces;
		this.attributes = attributes;
	}
	
	@Override
	public void run()
	{
		// TODO run main method
	}
}