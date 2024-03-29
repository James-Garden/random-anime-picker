package uk.jamesgarden.randomanimepicker.listupdate;

import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.Clock;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.anime.Anime;
import uk.jamesgarden.randomanimepicker.anime.AnimeService;
import uk.jamesgarden.randomanimepicker.exception.InternalServerErrorException;
import uk.jamesgarden.randomanimepicker.listentry.ListEntry;
import uk.jamesgarden.randomanimepicker.listentry.ListEntryService;
import uk.jamesgarden.randomanimepicker.malrequest.MalRequestService;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;

@Service
public class ListUpdateService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListUpdateService.class);
  private static final Duration RATE_LIMIT = Duration.of(15, ChronoUnit.MINUTES);

  private final MalRequestService malRequestService;
  private final ListEntryService listEntryService;
  private final MalUserService malUserService;
  private final Clock clock;
  private final AnimeService animeService;

  @Autowired
  ListUpdateService(MalRequestService malRequestService,
                    ListEntryService listEntryService,
                    MalUserService malUserService,
                    Clock clock,
                    AnimeService animeService) {
    this.malRequestService = malRequestService;
    this.listEntryService = listEntryService;
    this.malUserService = malUserService;
    this.clock = clock;
    this.animeService = animeService;
  }

  public boolean isListUpdatable(MalUser user) {
    if (Objects.isNull(user.getLastUpdated())) {
      return true;
    }
    return clock.instant().minus(RATE_LIMIT).isAfter(user.getLastUpdated().toInstant());
  }

  public void updateList(MalUser user) {
    var listEntryDtos = getListEntryDtos(user);
    var listEntries = listEntryService.convertDtosToEntities(user, listEntryDtos);
    var anime = animeService.convertDtosToEntities(user, listEntryDtos);
    saveAnimeAndListEntries(user, anime, listEntries);
  }

  @Transactional
  public void saveAnimeAndListEntries(MalUser user,
                                      Collection<Anime> anime,
                                      Collection<ListEntry> listEntries) {
    animeService.updateAnime(anime);
    listEntryService.updateListForUser(user, listEntries);
    malUserService.setLastUpdatedToNow(user);
  }

  private List<MalAnimeListEntryDto> getListEntryDtos(MalUser malUser) {
    try {
      return malRequestService.getUserList(malUser.getUsername());
    } catch (IOException | InterruptedException e) {
      var error = "Could not process list for MalUser with ID [%s]".formatted(malUser.getId().toString());
      LOGGER.error(error);
      if (InterruptedException.class.equals(e.getClass())) {
        Thread.currentThread().interrupt();
      }
      throw new InternalServerErrorException(error);
    }
  }
}
