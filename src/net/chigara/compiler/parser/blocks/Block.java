package net.chigara.compiler.parser.blocks;

import java.util.ArrayList;
import java.util.List;

public abstract class Block
{
	private Block superBlock;
	private List<Block> subBlocks;
	
	public Block(Block superBlock)
	{
		this.superBlock = superBlock;
		subBlocks = new ArrayList<Block>();
	}
	
	public Block getSuperBlock()
	{
		return superBlock;
	}
	
	public void addSubBlock(Block block)
	{
		subBlocks.add(block);
	}
	
	public abstract void run();
}