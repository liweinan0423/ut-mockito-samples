package practical;

import org.junit.Before;
import org.junit.Test;
import org.omg.IOP.ExceptionDetailMessage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Created by wli on 12/17/2015.
 */
public class UserServiceTest
{
	private UserService userService;

	@Before
	public void setUp() {
		userService = new UserService();
	}

	@Test
	public void GivenIdenticalPasswordsShouldRegisterSuccessfully() throws Exception
	{

		// given identical passwords
		// when registers
		userService.registerUser("demo", "password", "password");

		// then user is registered
		assertThat(userService.findUserByUsername("demo").getUsername(), equalTo("demo"));
	}

	@Test(expected = Exception.class)
	public void GivenDifferentPasswordsShouldFailsRegistration() throws Exception
	{
		// given different passwords
		// when registers
		userService.registerUser("demo", "password", "pwd");

		// then exception thrown
	}
}