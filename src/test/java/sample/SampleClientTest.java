package sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;

/**
 * Created by wli on 12/16/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class SampleClientTest
{
	@InjectMocks
	private SampleClient sampleClient;

	@Mock
	private SampleService service;

	@Test(expected = IllegalArgumentException.class)
	public void GivenEmptyStringShouldThrowException() {
		sampleClient.method1("");
	}

	@Test
	public void GivenNonEmptyStringShouldCallSampleService() {
		// given
		given(service.method1("str")).willReturn("processed");

		// when
		String str = sampleClient.method1("str");

		// then
		then(service).should().method1("str");
		assertThat(str, equalTo("processed"));
	}

	@Test
	public void method2ShouldCallService() {
		// given
		doNothing().when(service).voidMethod(anyString());

		// when
		sampleClient.method2("test");

		// then
		then(service).should(only()).voidMethod(anyString());
	}

	@Test
	public void method3ShouldCallServiceTwice() {
		// given
		doNothing().when(service).voidMethod(anyString());

		// when
		sampleClient.method3("test");

		// then
		then(service).should(times(2)).voidMethod(anyString());
	}

	@Test
	public void method4ShouldCallServiceTwiceWithUpperAndLowerCase() {
		// given
		doNothing().when(service).voidMethod(anyString());

		// when
		String arg = "TeSt";
		sampleClient.method4(arg);

		// then
		InOrder inOrder = inOrder(service);
		inOrder.verify(service).voidMethod(arg.toUpperCase());
		inOrder.verify(service).voidMethod(arg.toLowerCase());
		inOrder.verifyNoMoreInteractions();
	}

	@Test
	public void method5ShouldSwallowException() throws Exception
	{
		// given
		doThrow(new Exception()).when(service).voidMethodThrowsException();

		// when
		sampleClient.method5();

		// then
		then(service).should().voidMethodThrowsException();
	}
}