package uk.jamesgarden.randomanimepicker.randomentry;

import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.listentry.ListEntry;
import uk.jamesgarden.randomanimepicker.listentry.ListEntryService;
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
    var listEntryIds = listEntryService.getUserListEntryIds(user);
    if (listEntryIds.isEmpty()) {
      return Optional.empty();
    }
    var randomEntryId = listEntryIds.get(RANDOM.nextInt(listEntryIds.size()));
    return listEntryService.findListEntryById(randomEntryId);
  }
}
