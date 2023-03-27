package uk.jamesgarden.randomanimepicker.randomentry;

import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.anime.AnimeService;
import uk.jamesgarden.randomanimepicker.listentry.ListEntry;
import uk.jamesgarden.randomanimepicker.listentry.ListEntryService;
import uk.jamesgarden.randomanimepicker.listentry.enums.WatchingStatus;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;
import uk.jamesgarden.randomanimepicker.utils.DateTimeFormatUtil;

@Service
class RandomEntryService {

  private final ListEntryService listEntryService;
  private final AnimeService animeService;

  private static final Random RANDOM = new Random();

  @Autowired
  RandomEntryService(ListEntryService listEntryService, AnimeService animeService) {
    this.listEntryService = listEntryService;
    this.animeService = animeService;
  }

  public Optional<ListEntry> getRandomListEntryForUser(MalUser user) {
    var listEntries = listEntryService.getUserListEntries(user);
    if (listEntries.isEmpty()) {
      return Optional.empty();
    }
    var filteredList = listEntries.stream()
        .filter(listEntry -> WatchingStatus.PLAN_TO_WATCH.equals(listEntry.getWatchingStatus()))
        .toList();
    if (filteredList.isEmpty()) {
      return Optional.empty();
    }
    var randomEntry = filteredList.get(RANDOM.nextInt(filteredList.size()));
    return Optional.of(randomEntry);
  }

  public RandomEntryView getRandomEntryView(MalUser user) {
    var listEntryOptional = getRandomListEntryForUser(user);
    if (listEntryOptional.isEmpty()) {
      return RandomEntryView.emptyView();
    }
    var listEntry = listEntryOptional.get();
    var animeInfo = animeService.getByAnimeId(listEntry.getAnimeId());
    return new RandomEntryView(
        false,
        animeInfo.getAnimeId(),
        animeInfo.getAnimeTitle(),
        animeInfo.getAnimeTitleJapanese(),
        animeInfo.getAnimeTitleEnglish(),
        listEntry.getWatchingStatus().getDisplayName(),
        animeInfo.getAnimeAverageScore(),
        animeInfo.getAnimeNumEpisodes(),
        DateTimeFormatUtil.formatZonedDateTime(listEntry.getUserAnimeUpdatedAtDateTime()),
        animeInfo.getAiringStatus().getDisplayName(),
        animeInfo.getAgeRating().getDisplayName(),
        animeInfo.getAnimeImage()
    );
  }
}
