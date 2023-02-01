package uk.jamesgarden.randomanimepicker.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WelcomeController {

  @GetMapping
  ModelAndView renderWelcomePage() {
    return new ModelAndView("welcome");
  }

}
