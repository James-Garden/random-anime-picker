package uk.jamesgarden.randomanimepicker.randomentry;

import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import uk.jamesgarden.randomanimepicker.exception.BadRequestException;
import uk.jamesgarden.randomanimepicker.listupdate.ListUpdateService;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;
import uk.jamesgarden.randomanimepicker.utils.DateUtils;
import uk.jamesgarden.randomanimepicker.welcome.ListFilterForm;
import uk.jamesgarden.randomanimepicker.welcome.ListFilterFormValidator;

@Controller
@RequestMapping("/{username}")
class RandomEntryController {

  private final MalUserService malUserService;
  private final ListUpdateService listUpdateService;
  private final RandomEntryService randomEntryService;
  private final ListFilterFormValidator listFilterFormValidator;

  @Autowired
  RandomEntryController(MalUserService malUserService,
                        ListUpdateService listUpdateService,
                        RandomEntryService randomEntryService,
                        ListFilterFormValidator listFilterFormValidator) {
    this.malUserService = malUserService;
    this.listUpdateService = listUpdateService;
    this.randomEntryService = randomEntryService;
    this.listFilterFormValidator = listFilterFormValidator;
  }

  @GetMapping
  ModelAndView renderRandomListEntry(@PathVariable("username") String username) {
    var form = new ListFilterForm();
    form.setUsername(username);
    var bindingResult = new BeanPropertyBindingResult(form, "form");
    listFilterFormValidator.validate(form, bindingResult);
    if (bindingResult.hasErrors()) {
      throw new BadRequestException(
          "Could not validate that user with username [%s] exists, aborting request".formatted(username));
    }

    var user = malUserService.getOrCreateUser(username);
    if (Objects.isNull(user.getLastUpdated())) {
      listUpdateService.updateList(user);
    }
    var listEntry = randomEntryService.getRandomListEntryForUser(user)
        .orElseThrow(() -> new EntityNotFoundException(
            "Could not find any list entries for user with ID [%s]".formatted(user.getId().toString())));

    return new ModelAndView("listEntry")
        .addObject("username", username)
        .addObject("listEntry", listEntry)
        .addObject("addedToList", DateUtils.instantToDate(listEntry.getCreatedAt()));
  }
}
