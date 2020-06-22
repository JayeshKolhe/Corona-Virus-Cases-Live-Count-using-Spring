package io.javabrains.coronavirustracker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@Autowired
	coronavirusDataservices coronavirusdataservice;
	
	@GetMapping("/")
	public String home(Model model) {
		List<Models_locationStats> allstats=coronavirusdataservice.getAllstats();
		int totalreportedcases=allstats.stream().mapToInt(stat-> stat.getLatestTotalcases()).sum();
		int totalNEWlycases=allstats.stream().mapToInt(stat-> stat.getDiffFromPrevDay()).sum();

		

		model.addAttribute("Models_locationStats",allstats );
		model.addAttribute("totalreportedcases",totalreportedcases );
		model.addAttribute("totalNEWlycases",totalNEWlycases );

		
		return "home";
	}
}
