package sample;

/**
 * Created by wli on 12/16/2015.
 */
public class SampleClient
{
	private SampleService service;


	public String method1(String arg)
	{
		if (arg == null || "".equals(arg))
		{
			throw new IllegalArgumentException();
		} else
		{
			return service.method1(arg);
		}
	}

	public void method2(String arg) {
		service.voidMethod(arg);
	}

	public void method3(String arg) {
		service.voidMethod(arg);
		service.voidMethod(arg);
	}

	public void method4(String arg) {
		service.voidMethod(arg.toUpperCase());
		service.voidMethod(arg.toLowerCase());
	}

	public void method5()
	{
		try
		{
			service.voidMethodThrowsException();
		} catch (Exception e)
		{
			//
		}
	}
}
