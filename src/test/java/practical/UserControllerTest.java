package practical;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

/**
 * Created by wli on 12/17/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest
{
	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;
	@Mock
	private RegistrationForm registrationForm;
	@Mock
	private BindingResult bindingResult;

	@Test
	public void testRegistrationSucceeded() throws Exception
	{
		//given
		given(registrationForm.getUsername()).willReturn("demo");
		given(registrationForm.getPassword()).willReturn("password");
		given(registrationForm.getConfirmPassword()).willReturn("password");
		given(bindingResult.hasErrors()).willReturn(false);
		doNothing().when(userService).registerUser(any(), any(), any());

		// when
		String result = userController.register(registrationForm, bindingResult);

		// then
		then(bindingResult).should().hasErrors();
		then(userService).should().registerUser("demo", "password", "password");
		assertThat(result, equalTo("redirect: /profile"));
	}

	@Test
	public void testRegistrationFailed() throws Exception
	{
		// given
		given(registrationForm.getUsername()).willReturn("demo");
		given(registrationForm.getPassword()).willReturn("password");
		given(registrationForm.getConfirmPassword()).willReturn("pwd");
		given(bindingResult.hasErrors()).willReturn(false);
		doThrow(new Exception()).when(userService).registerUser("demo", "password", "pwd");

		// when
		String result = userController.register(registrationForm, bindingResult);

		// then
		assertThat(result, equalTo("/register"));
	}
}