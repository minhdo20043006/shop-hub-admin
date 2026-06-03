package com.example.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.CategoryDTO;
import com.example.demo.dtos.CategoryUpdateStatusDTO;
import com.example.demo.enums.CategoryStatus;
import com.example.demo.service.CategoryService;

@Controller
@RequestMapping("category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	
	@GetMapping("list")
	public String list(ModelMap model) {
		model.put("categories", categoryService.findAllByStatusActive(CategoryStatus.ACTIVE));
		return "category/list";
	}

	
	@GetMapping("admin")
	public String adminList(ModelMap model) {
		model.put("categories", categoryService.findAllForAdmin());
		return "admin/category/list";
	}

	
	@GetMapping("admin/create")
	public String createForm(ModelMap model) {
		model.put("category", new CategoryDTO());
		return "admin/category/create";
	}

	
	@PostMapping("admin/create")
	public String create(@ModelAttribute CategoryDTO dto) {
		categoryService.Create(dto);
		return "redirect:/category/admin";
	}

	
	@GetMapping("admin/edit/{id}")
	public String edit(@PathVariable Integer id, ModelMap model) {
		model.put("categories", categoryService.findAllForAdmin());
		return "admin/category/edit";
	}

	
	@PostMapping("admin/update")
	public String update(@RequestParam Integer id, @ModelAttribute CategoryDTO dto) {
		categoryService.Update(id, dto);
		return "redirect:/category/admin";
	}

	
	@GetMapping("admin/status/{id}/{status}")
	public String updateStatus(@PathVariable Integer id, @PathVariable CategoryStatus status) {

		CategoryUpdateStatusDTO dto = new CategoryUpdateStatusDTO();
		dto.setStatus(status);

		categoryService.UpdateStatusCategory(id, dto);

		return "redirect:/category/admin";
	}

	
	@GetMapping("admin/delete/{id}")
	public String delete(@PathVariable Integer id) {
		categoryService.Delete(id);
		return "redirect:/category/admin";
	}
}
