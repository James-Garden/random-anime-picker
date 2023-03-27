package uk.jamesgarden.randomanimepicker.listentry;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.jamesgarden.randomanimepicker.listentry.enums.WatchingStatus;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;
import uk.jamesgarden.randomanimepicker.utils.ClassCastUtil;

@SuppressWarnings("unused")
@Entity
@Table(name = "user_list_entries")
public class ListEntry {

  private static final Logger LOGGER = LoggerFactory.getLogger(ListEntry.class);

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "mal_user_id")
  private MalUser user;

  private Integer animeId;

  @Enumerated
  private WatchingStatus watchingStatus;

  private Integer userAnimeScore;
  private Integer userAnimeNumEpisodesWatched;
  private Boolean userAnimeIsRewatching;
  private ZonedDateTime userAnimeUpdatedAtDateTime;
  private LocalDate userAnimeStartDate;
  private LocalDate userAnimeFinishDate;

  public static ListEntry from(MalAnimeListEntryDto listEntryDto, MalUser user) {
    var animeData = listEntryDto.listEntryNodeDto();
    var userEntryData = listEntryDto.listEntryStatusDto();
    var listEntry = new ListEntry();
    var loggerPrefix = "User: %s, Anime: %d, Field: %s".formatted(user.getUsername(), animeData.id(), "%s");
    listEntry.animeId = animeData.id();
    listEntry.watchingStatus = WatchingStatus.parse(userEntryData.status());
    listEntry.userAnimeScore = userEntryData.score();
    listEntry.userAnimeNumEpisodesWatched = userEntryData.numEpisodesWatched();
    listEntry.userAnimeIsRewatching = userEntryData.isRewatching();
    listEntry.userAnimeUpdatedAtDateTime = ClassCastUtil.castOrNull(
        () -> ZonedDateTime.parse(userEntryData.updatedAtDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
        userEntryData.updatedAtDateTime(),
        ZonedDateTime.class,
        loggerPrefix.formatted("userAnimeUpdatedAtDateTime")
    );
    listEntry.userAnimeStartDate = ClassCastUtil.castOrNull(
        () -> LocalDate.parse(userEntryData.startDate()),
        userEntryData.startDate(),
        LocalDate.class,
        loggerPrefix.formatted("userAnimeStartDate"));
    listEntry.userAnimeFinishDate = ClassCastUtil.castOrNull(
        () -> LocalDate.parse(userEntryData.finishDate()),
        userEntryData.finishDate(),
        LocalDate.class,
        loggerPrefix.formatted("userAnimeFinishDate")
    );
    listEntry.user = user;

    return listEntry;
  }

  public MalUser getUser() {
    return user;
  }

  public void setUser(MalUser user) {
    this.user = user;
  }

  public Integer getAnimeId() {
    return animeId;
  }

  public void setAnimeId(Integer animeId) {
    this.animeId = animeId;
  }

  public WatchingStatus getWatchingStatus() {
    return watchingStatus;
  }

  public void setWatchingStatus(WatchingStatus watchingStatus) {
    this.watchingStatus = watchingStatus;
  }

  public Integer getUserAnimeScore() {
    return userAnimeScore;
  }

  public void setUserAnimeScore(Integer userAnimeScore) {
    this.userAnimeScore = userAnimeScore;
  }

  public Integer getUserAnimeNumEpisodesWatched() {
    return userAnimeNumEpisodesWatched;
  }

  public void setUserAnimeNumEpisodesWatched(Integer userAnimeNumEpisodesWatched) {
    this.userAnimeNumEpisodesWatched = userAnimeNumEpisodesWatched;
  }

  public Boolean getUserAnimeIsRewatching() {
    return userAnimeIsRewatching;
  }

  public void setUserAnimeIsRewatching(Boolean userAnimeIsRewatching) {
    this.userAnimeIsRewatching = userAnimeIsRewatching;
  }

  public ZonedDateTime getUserAnimeUpdatedAtDateTime() {
    return userAnimeUpdatedAtDateTime;
  }

  public void setUserAnimeUpdatedAtDateTime(ZonedDateTime userAnimeUpdatedAtDateTime) {
    this.userAnimeUpdatedAtDateTime = userAnimeUpdatedAtDateTime;
  }

  public LocalDate getUserAnimeStartDate() {
    return userAnimeStartDate;
  }

  public void setUserAnimeStartDate(LocalDate userAnimeStartDate) {
    this.userAnimeStartDate = userAnimeStartDate;
  }

  public LocalDate getUserAnimeFinishDate() {
    return userAnimeFinishDate;
  }

  public void setUserAnimeFinishDate(LocalDate userAnimeFinishDate) {
    this.userAnimeFinishDate = userAnimeFinishDate;
  }
}
