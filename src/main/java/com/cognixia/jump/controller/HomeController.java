package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cognixia.jump.model.Location;
import com.cognixia.jump.service.DataService;

@Controller
public class HomeController {

	@Autowired
	DataService dataService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Location> allStats = dataService.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		model.addAttribute("locationStats", allStats);
		model.addAttribute("totalCases", totalCases);
		return "home";
	}
	
}
