package practical;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by wli on 12/17/2015.
 */
@Controller
public class UserController
{

	@Resource
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String register(RegistrationForm form, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "/register";
		}

		try
		{
			userService.registerUser(form.getUsername(), form.getPassword(), form.getConfirmPassword());
		} catch (Exception e)
		{
			return "/register";
		}
		return "redirect: /profile";
	}
}
