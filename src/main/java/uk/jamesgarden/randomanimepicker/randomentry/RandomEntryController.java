package uk.jamesgarden.randomanimepicker.randomentry;

import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.jamesgarden.randomanimepicker.listupdate.ListUpdateService;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;

@Controller
@RequestMapping("/{username}")
class RandomEntryController {

  private final MalUserService malUserService;
  private final ListUpdateService listUpdateService;
  private final RandomEntryService randomEntryService;

  @Autowired
  RandomEntryController(MalUserService malUserService,
                        ListUpdateService listUpdateService,
                        RandomEntryService randomEntryService) {
    this.malUserService = malUserService;
    this.listUpdateService = listUpdateService;
    this.randomEntryService = randomEntryService;
  }

  @GetMapping
  ModelAndView renderRandomListEntry(@PathVariable("username") String username) {
    var user = malUserService.getOrCreateUser(username);
    if (Objects.isNull(user.getLastUpdated())) {
      listUpdateService.updateList(user);
    }
    var listEntry = randomEntryService.getRandomListEntryForUser(user)
        .orElseThrow(() -> new EntityNotFoundException(
            "Could not find any list entries for user with ID [%s]".formatted(user.getId().toString())
        ));

    return new ModelAndView("listEntry")
        .addObject("username", username)
        .addObject("listEntry", listEntry);
  }
}
