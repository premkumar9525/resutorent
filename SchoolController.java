package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/admin")
public class SchoolController {
	@Autowired
	private SchoolService schoolService;

	@GetMapping("/testschool")
	public String testschool() {
		return "schoolpage";
	}

	@PostMapping("/saveschool")
	public String saveschool(@ModelAttribute School school) {
		schoolService.saveschool(school);
		return "schoolform";
	}

	@PostMapping("/saveform")
	public String saveform(@ModelAttribute School school) {
		schoolService.saveschoolform(school);
		return "redirect:/admin/getschoollist";
	}

	@GetMapping("/getschoollist")
	public String getschoollist(Model model) {
		List<School> list = schoolService.findAll();
		model.addAttribute("Schooldata", list);
		return "schoollist";
	}

	@GetMapping("/editschoolByid/{id}")
	public String editschool(@PathVariable("id") long id, Model model) {
		Optional<School> data = schoolService.findByid(id);
		if (data.isPresent()) {
			model.addAttribute("schoolinfo", data.get());
		}

		return "editschool";
	}

	@PostMapping("/updateschool")
	public String updateschool(@ModelAttribute School school) {
		Optional<School> data = schoolService.findByid(school.getId());
		if (data.isPresent()) {
			schoolService.updateschool(data, school);
		}

		return "redirect:/admin/getschoollist";
	}

	@GetMapping("/deleteschoolById/{id}")
	public String deleteschool(@PathVariable("id") long id) {
		schoolService.deleteByid(id);
		return "redirect:/admin/getschoollist";
	}

	@PostMapping("saveschooldetails")
	public String savesschool(@ModelAttribute School school) {
		schoolService.saveschooll(school);
		return "record save";
	}

}