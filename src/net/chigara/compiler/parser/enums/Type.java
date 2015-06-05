package net.chigara.compiler.parser.enums;

public enum Type
{
	INT8, INT16, INT32, INT64, INT128, UINT8, UINT16, UINT32, UINT64, UINT128, FLOAT, DOUBLE, QUADRUPLE, BOOLEAN, CHAR, TUPLE, OBJECT, VOID;
	
	public static Type getEnum(String str)
	{
		if(str.equals("QUAD"))
			return QUADRUPLE;
		Type t = valueOf(str);
		return t == null ? OBJECT : t;
	}
}