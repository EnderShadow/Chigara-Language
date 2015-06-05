package net.chigara.compiler.parser.blocks;

import net.chigara.compiler.parser.enums.Attribute;
import net.chigara.compiler.parser.enums.Parameter;
import net.chigara.compiler.parser.enums.Type;
import net.chigara.compiler.parser.enums.Visibility;

public class MethodBlock extends Block
{
	public final String name;
	public final Visibility visibility;
	public final Attribute[] attributes;
	public final Type type;
	public final Parameter[] parameters;
	
	public MethodBlock(Block superBlock, String name, Visibility visibility, Type type, Attribute[] attributes, Parameter[] parameters)
	{
		super(superBlock);
		this.name = name;
		this.visibility = visibility;
		this.type = type;
		this.attributes = attributes;
		this.parameters = parameters;
	}
	
	@Override
	public void run()
	{
		// TODO run method
	}
}