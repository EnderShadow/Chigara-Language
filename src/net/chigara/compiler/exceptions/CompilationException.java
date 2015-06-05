package net.chigara.compiler.exceptions;

public class CompilationException extends RuntimeException
{
	private static final long serialVersionUID = -4685835493121019077L;
	
	public CompilationException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public CompilationException(String message)
	{
		super(message);
	}
	
	public CompilationException(Throwable cause)
	{
		super(cause);
	}
	
	public CompilationException()
	{
		super();
	}
}