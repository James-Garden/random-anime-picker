package uk.jamesgarden.randomanimepicker.randomentry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/{username}")
class RandomEntryController {

  @GetMapping
  ModelAndView renderRandomListEntry(@PathVariable("username") String username) {
    return new ModelAndView("listEntry")
        .addObject("username", username);
  }
}
