package uk.jamesgarden.randomanimepicker.randomentry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.jamesgarden.randomanimepicker.listupdate.ListUpdateService;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;
import uk.jamesgarden.randomanimepicker.utils.DateTimeFormatUtil;

@Controller
@RequestMapping("/{username}")
class RandomEntryController {

  private final MalUserService malUserService;
  private final RandomEntryService randomEntryService;
  private final ListUpdateService listUpdateService;

  @Autowired
  RandomEntryController(MalUserService malUserService,
                        RandomEntryService randomEntryService,
                        ListUpdateService listUpdateService) {
    this.malUserService = malUserService;
    this.randomEntryService = randomEntryService;
    this.listUpdateService = listUpdateService;
  }

  @GetMapping
  ModelAndView renderRandomListEntry(@PathVariable("username") String username) {
    var user = malUserService.getByUsername(username);
    var listEntryOptional = randomEntryService.getRandomListEntryForUser(user);
    var addedToList = listEntryOptional
        .map(listEntry -> DateTimeFormatUtil.formatZonedDateTime(listEntry.getUserAnimeUpdatedAtDateTime()))
        .orElse("");

    return new ModelAndView("listEntry")
        .addObject("username", username)
        .addObject("listEntry", listEntryOptional)
        .addObject("addedToList", addedToList)
        .addObject("updateListUrl", "/%s/update-list".formatted(username))
        .addObject("lastUpdated", DateTimeFormatUtil.formatTimeSince(user.getLastUpdated()))
        .addObject("isListUpdatable", listUpdateService.isListUpdatable(user));
  }
}
