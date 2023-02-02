package uk.jamesgarden.randomanimepicker.randomentry;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/{username}")
class RandomEntryController {

  private final RandomEntryService randomEntryService;

  @Autowired
  RandomEntryController(RandomEntryService randomEntryService) {
    this.randomEntryService = randomEntryService;
  }

  @GetMapping
  ModelAndView renderRandomListEntry(@PathVariable("username") String username) throws JsonProcessingException {
    var userList = randomEntryService.getUserList(username);
    var listEntry = randomEntryService.getRandomListEntry(userList);

    return new ModelAndView("listEntry")
        .addObject("username", username)
        .addObject("listEntry", listEntry);
  }
}
