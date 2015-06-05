package net.chigara.compiler.parser;

import net.chigara.compiler.parser.blocks.Block;
import net.chigara.compiler.tokenizer.Tokenizer;

public abstract class Parser<T extends Block>
{
	public abstract boolean shouldParse(String line);
	public abstract T parse(Block superBlock, Tokenizer t);
}