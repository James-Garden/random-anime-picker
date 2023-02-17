package uk.jamesgarden.randomanimepicker.listupdate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;

@Controller
@RequestMapping("/{username}/update-list")
public class ListUpdateController {

  private final ListUpdateService listUpdateService;
  private final MalUserService malUserService;

  @Autowired
  ListUpdateController(ListUpdateService listUpdateService,
                       MalUserService malUserService) {
    this.listUpdateService = listUpdateService;
    this.malUserService = malUserService;
  }

  @PostMapping
  ModelAndView updateListAndRedirect(@PathVariable("username") String username) {
    var user = malUserService.getByUsername(username);
    listUpdateService.updateList(user);

    return new ModelAndView("redirect:/%s".formatted(username));
  }
}
