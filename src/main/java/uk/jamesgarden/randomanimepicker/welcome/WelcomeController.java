package uk.jamesgarden.randomanimepicker.welcome;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WelcomeController {

  @GetMapping
  ModelAndView renderWelcomePage(@ModelAttribute("form") UsernameForm form) {
    return new ModelAndView("welcome");
  }

  @PostMapping
  ModelAndView redirectToListEntry(@ModelAttribute("form") UsernameForm form) {
    return new ModelAndView("redirect:/%s".formatted(form.getUsername()));
  }
}
