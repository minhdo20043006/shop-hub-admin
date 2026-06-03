package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.AuthService;
import com.example.demo.service.AuthServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({ "auth" })
public class AuthController {

	@Autowired
	private AuthService authService;

	@GetMapping({ "login", "", "/" })
	public String Login() {

		return "auth/login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		try {
			if (authService.login(username, password) != null) {
				if (username.equals("Admin")) {
					return "redirect:/admin/dashboard";
				}
				return "redirect:/home";
			}

			return "redirect:/login";
		} catch (Exception e) {
			model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
			return "auth/login";
		}
	}

}
