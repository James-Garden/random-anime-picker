package uk.jamesgarden.randomanimepicker.welcome;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class WelcomeController {

  private final ListFilterFormValidator listFilterFormValidator;

  @Autowired
  WelcomeController(ListFilterFormValidator listFilterFormValidator) {
    this.listFilterFormValidator = listFilterFormValidator;
  }

  @GetMapping
  ModelAndView renderWelcomePage(@ModelAttribute("form") ListFilterForm form) {
    return new ModelAndView("welcome");
  }

  @PostMapping
  ModelAndView redirectToListEntry(@ModelAttribute("form") ListFilterForm form,
                                   BindingResult bindingResult) {
    listFilterFormValidator.validate(form, bindingResult);

    if (bindingResult.hasErrors()) {
      return new ModelAndView("welcome");
    }

    return new ModelAndView("redirect:/%s".formatted(form.getUsername()));
  }
}
