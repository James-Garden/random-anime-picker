package uk.jamesgarden.randomanimepicker.welcome;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.jamesgarden.randomanimepicker.listupdate.ListUpdateService;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;

@Controller
@RequestMapping("/")
public class WelcomeController {

  private final WelcomeFormValidator welcomeFormValidator;
  private final MalUserService malUserService;
  private final ListUpdateService listUpdateService;

  @Autowired
  WelcomeController(WelcomeFormValidator welcomeFormValidator,
                    MalUserService malUserService,
                    ListUpdateService listUpdateService) {
    this.welcomeFormValidator = welcomeFormValidator;
    this.malUserService = malUserService;
    this.listUpdateService = listUpdateService;
  }

  @GetMapping
  ModelAndView renderWelcomePage(@ModelAttribute("form") WelcomeForm form) {
    return new ModelAndView("welcome");
  }

  @PostMapping
  ModelAndView redirectToListEntry(@ModelAttribute("form") WelcomeForm form,
                                   BindingResult bindingResult) {
    welcomeFormValidator.validate(form, bindingResult);

    if (bindingResult.hasErrors()) {
      return new ModelAndView("welcome");
    }
    var user = malUserService.getOrCreateUser(form.getUsername());
    if (Objects.isNull(user.getLastUpdated())) {
      listUpdateService.updateList(user);
    }

    return new ModelAndView("redirect:/%s".formatted(user.getUsername()));
  }
}
