package practical;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wli on 12/17/2015.
 */
public class UserService
{

	private Map<String, User> users = new HashMap<>();

	public void registerUser(String username, String password, String confirmPassword) throws Exception
	{
		if (!password.equals(confirmPassword)) {
			throw new Exception("passwords don't match");
		}

		User user = new User(username);
		users.put(username, user);
	}

	public User findUserByUsername(String username) {
		if (!users.containsKey(username)) {
			throw new RuntimeException("user not found");
		}

		return users.get(username);
	}
}
