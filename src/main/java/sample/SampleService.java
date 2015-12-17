package sample;

public class SampleService
{
	public String method1(String arg)
	{
		return arg;
	}

	public void voidMethod(String arg)
	{
		// db operations
	}

	public void voidMethodThrowsException() throws Exception
	{
		throw new Exception();
	}
}
