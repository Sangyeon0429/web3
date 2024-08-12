package net.datasa.web3;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class HomeController {
	@GetMapping({ "", "/", "Home" })
	public String home() {
		return "Home";
	}

}
