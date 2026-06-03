package com.example.demo.controller.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dtos.AccountDTO;
import com.example.demo.entities.Account;
import com.example.demo.entities.Category;
import com.example.demo.enums.StatusAccount;
import com.example.demo.service.AccountService;
import com.example.demo.service.AuthService;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping({ "account" })
public class AccountController {

	@Autowired
	private AuthService authService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private OrderService orderService;

	@GetMapping("/accountDetail")
	public String accountDetail(ModelMap modelMap) {
		try {
			Object account = authService.getCurrentAccount();

			modelMap.put("account", account);
			return "account/accountDetail";
		} catch (Exception e) {
			return "redirect:/auth/login";
		}
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("account", new Account());
		return "account/register";
	}

	@PostMapping("register")
	public String Add(@ModelAttribute("account") AccountDTO accountDto, RedirectAttributes redirect,
			ModelMap modelMap) {
		System.out.println("EMAIL = " + accountDto.getEmail());
		System.out.println("USERNAME = " + accountDto.getUsername());
		System.out.println("DOB = " + accountDto.getDob());
		accountDto.setAvatar("default.png");
		accountDto.setStatus(StatusAccount.ACTIVE);
		accountDto.setCreatedAt(new Date());
		accountDto.setUpdatedAt(new Date());

		boolean success = accountService.create(accountDto);

		if (success) {
			redirect.addFlashAttribute("msg", "Đăng ký thành công");
			return "redirect:/auth/login";
		} else {
			redirect.addFlashAttribute("error", "Đăng ký thất bại");
			return "account/register";
		}
	}

	@GetMapping({ "/updateAccount/{id}" })
	public String Edit(@PathVariable("id") Integer id, ModelMap modelMap) {

		modelMap.put("account", accountService.findById(id));

		return "account/updateAccount";
	}

	@PostMapping({ "/updateAccount" })
	public String Edit(ModelMap modelMap, @ModelAttribute("account") AccountDTO accountDTO,
			RedirectAttributes redirectAttributes) {
		accountDTO.setAvatar("default.png");
		accountDTO.setStatus(StatusAccount.ACTIVE);

		accountDTO.setUpdatedAt(new Date());

		if (accountService.update(accountDTO)) {

			return "redirect:/home";
		} else {
			redirectAttributes.addFlashAttribute("msg", "SUA THAT BAI");
			return "redirect:/account/updateAccount/" + accountDTO.getId();
		}

	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/auth/login";
	}

	@GetMapping({ "/delete/{id}" })
	public String Delete(ModelMap modelMap, RedirectAttributes redirectAttributes, @PathVariable("id") Integer id,
			HttpSession session) {

		if (accountService.delete(id)) {
			session.invalidate();
			redirectAttributes.addFlashAttribute("msg", "XOA THANH CONG");
			return "redirect:/home";
		} else {
			redirectAttributes.addFlashAttribute("msg", "XOA THAT BAI");
			return "redirect:/account/accountDetail/";
		}

	}

}
