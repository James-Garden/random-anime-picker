package uk.jamesgarden.randomanimepicker.randomentry;

import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.listentry.ListEntry;
import uk.jamesgarden.randomanimepicker.listentry.ListEntryService;
import uk.jamesgarden.randomanimepicker.listentry.enums.ListEntryStatus;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;

@Service
class RandomEntryService {

  private final ListEntryService listEntryService;

  private static final Random RANDOM = new Random();

  @Autowired
  RandomEntryService(ListEntryService listEntryService) {
    this.listEntryService = listEntryService;
  }

  public Optional<ListEntry> getRandomListEntryForUser(MalUser user) {
    var listEntries = listEntryService.getUserListEntries(user);
    if (listEntries.isEmpty()) {
      return Optional.empty();
    }
    var filteredList = listEntries.stream()
        .filter(listEntry -> ListEntryStatus.PLAN_TO_WATCH.equals(listEntry.getListEntryStatus()))
        .toList();
    if (filteredList.isEmpty()) {
      return Optional.empty();
    }
    var randomEntry = filteredList.get(RANDOM.nextInt(filteredList.size()));
    return Optional.of(randomEntry);
  }
}
