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
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.jamesgarden.randomanimepicker.listentry.enums.AgeRating;
import uk.jamesgarden.randomanimepicker.listentry.enums.AiringStatus;
import uk.jamesgarden.randomanimepicker.listentry.enums.ListEntryStatus;
import uk.jamesgarden.randomanimepicker.listentry.enums.MediaType;
import uk.jamesgarden.randomanimepicker.listentry.enums.Source;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;

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
  private String animeTitle;
  private String animeTitleEnglish;
  private String animeTitleJapanese;
  private String animeImage;
  private LocalDate animeStartDate;
  private LocalDate animeEndDate;
  private String animeSynopsis;
  private Double animeAverageScore;
  private Integer animeRankByScore;
  private Integer animeRankByPopularity;
  private Integer animeNumberOfUsers;
  private Integer animeNumberOfScoringUsers;
  private ZonedDateTime animeCreatedAtDateTime;
  private ZonedDateTime animeUpdatedAtDateTime;

  @Enumerated
  private MediaType mediaType;

  @Enumerated
  private AiringStatus airingStatus;

  private String animeGenres;
  private Integer animeNumEpisodes;
  private Source animeSource;
  private Integer animeEpisodeDurationSeconds;

  @Enumerated
  private AgeRating ageRating;

  private String animeSeason;

  @Enumerated
  private ListEntryStatus listEntryStatus;

  private Integer userAnimeScore;
  private Integer userAnimeNumEpisodesWatched;
  private Boolean userAnimeIsRewatching;
  private ZonedDateTime userAnimeUpdatedAtDateTime;
  private LocalDate userAnimeStartDate;
  private LocalDate userAnimeFinishDate;

  public static ListEntry from(MalAnimeListEntryDto listEntryDto) {
    var animeData = listEntryDto.listEntryNodeDto();
    var userEntryData = listEntryDto.listEntryStatusDto();
    var listEntry = new ListEntry();
    listEntry.animeId = animeData.id();
    listEntry.animeTitle = animeData.title();
    listEntry.animeTitleEnglish = animeData.alternativeTitles().en();
    listEntry.animeTitleJapanese = animeData.alternativeTitles().ja();
    if (Objects.nonNull(animeData.mainPicture().get("large"))) {
      listEntry.animeImage = animeData.mainPicture().get("large");
    } else {
      listEntry.animeImage = animeData.mainPicture().get("medium");
    }
    if (Objects.nonNull(animeData.startDate())) {
      try {
        listEntry.animeStartDate = LocalDate.parse(animeData.startDate());
      } catch (Exception e) {
        LOGGER.error("Could not cast %s to LocalDate".formatted(animeData.startDate()), e.getCause());
      }

    }
    if (Objects.nonNull(animeData.endDate())) {
      try {
        listEntry.animeEndDate = LocalDate.parse(animeData.endDate());
      } catch (Exception e) {
        LOGGER.error("Could not cast %s to LocalDate".formatted(animeData.endDate()), e.getCause());
      }
    }
    listEntry.animeSynopsis = animeData.synopsis();
    listEntry.animeAverageScore = animeData.averageScore();
    listEntry.animeRankByScore = animeData.rankByScore();
    listEntry.animeRankByPopularity = animeData.rankByPopularity();
    listEntry.animeNumberOfUsers = animeData.numberOfUsers();
    listEntry.animeNumberOfScoringUsers = animeData.numberOfScoringUsers();
    if (Objects.nonNull(animeData.createdAtDateTime())) {
      try {
        listEntry.animeCreatedAtDateTime = ZonedDateTime.parse(animeData.createdAtDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
      } catch (Exception e) {
        LOGGER.error("Could not cast %s to ZonedDateTime".formatted(animeData.createdAtDateTime()), e.getCause());
      }
    }
    if (Objects.nonNull(animeData.updatedAtDateTime())) {
      try {
        listEntry.animeUpdatedAtDateTime = ZonedDateTime.parse(animeData.updatedAtDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
      } catch (Exception e) {
        LOGGER.error("Could not cast %s to ZonedDateTime".formatted(animeData.updatedAtDateTime()), e.getCause());
      }
    }
    listEntry.mediaType = MediaType.parse(animeData.mediaType());
    listEntry.airingStatus = AiringStatus.parse(animeData.status());
    listEntry.animeNumEpisodes = animeData.numEpisodes();
    listEntry.animeSource = Source.parse(animeData.source());
    listEntry.animeEpisodeDurationSeconds = animeData.episodeDurationSeconds();
    listEntry.ageRating = AgeRating.parse(animeData.rating());
    listEntry.animeSeason = formatSeason(animeData.startSeason());
    listEntry.listEntryStatus = ListEntryStatus.parse(userEntryData.status());
    listEntry.userAnimeScore = userEntryData.score();
    listEntry.userAnimeNumEpisodesWatched = userEntryData.numEpisodesWatched();
    listEntry.userAnimeIsRewatching = userEntryData.isRewatching();
    if (Objects.nonNull(animeData.updatedAtDateTime())) {
      try {
        listEntry.userAnimeUpdatedAtDateTime = ZonedDateTime.parse(userEntryData.updatedAtDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
      } catch (Exception e) {
        LOGGER.error("Could not cast %s to ZonedDateTime".formatted(userEntryData.updatedAtDateTime()), e.getCause());
      }
    }
    if (Objects.nonNull(userEntryData.startDate())) {
      try {
        listEntry.userAnimeStartDate = LocalDate.parse(userEntryData.startDate());
      } catch (Exception e) {
        LOGGER.error("Could not cast %s to LocalDate".formatted(userEntryData.startDate()), e.getCause());
      }
    }
    if (Objects.nonNull(userEntryData.finishDate())) {
      try {
        listEntry.userAnimeFinishDate = LocalDate.parse(userEntryData.finishDate());
      } catch (Exception e) {
        LOGGER.error("Could not cast %s to LocalDate".formatted(userEntryData.finishDate()), e.getCause());
      }
    }

    return listEntry;
  }

  private static String formatSeason(Map<String, String> seasonMap) {
    if (Objects.isNull(seasonMap)) {
      return null;
    }
    var year = seasonMap.get("year");
    var season = seasonMap.get("season");
    if (Objects.isNull(year) || Objects.isNull(season)) {
      return null;
    }
    var formattedSeason = switch (season) {
      case "spring" -> "Spring";
      case "summer" -> "Summer";
      case "fall" -> "Autumn";
      case "winter" -> "Winter";
      default -> "";
    };
    if (formattedSeason.isBlank()) {
      return null;
    }
    return "%s %s".formatted(formattedSeason, year);
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

  public String getAnimeTitle() {
    return animeTitle;
  }

  public void setAnimeTitle(String animeTitle) {
    this.animeTitle = animeTitle;
  }

  public String getAnimeTitleEnglish() {
    return animeTitleEnglish;
  }

  public void setAnimeTitleEnglish(String animeTitleEnglish) {
    this.animeTitleEnglish = animeTitleEnglish;
  }

  public String getAnimeTitleJapanese() {
    return animeTitleJapanese;
  }

  public void setAnimeTitleJapanese(String animeTitleJapanese) {
    this.animeTitleJapanese = animeTitleJapanese;
  }

  public String getAnimeImage() {
    return animeImage;
  }

  public void setAnimeImage(String animeImage) {
    this.animeImage = animeImage;
  }

  public LocalDate getAnimeStartDate() {
    return animeStartDate;
  }

  public void setAnimeStartDate(LocalDate animeStartDate) {
    this.animeStartDate = animeStartDate;
  }

  public LocalDate getAnimeEndDate() {
    return animeEndDate;
  }

  public void setAnimeEndDate(LocalDate animeEndDate) {
    this.animeEndDate = animeEndDate;
  }

  public String getAnimeSynopsis() {
    return animeSynopsis;
  }

  public void setAnimeSynopsis(String animeSynopsis) {
    this.animeSynopsis = animeSynopsis;
  }

  public Double getAnimeAverageScore() {
    return animeAverageScore;
  }

  public void setAnimeAverageScore(Double animeAverageScore) {
    this.animeAverageScore = animeAverageScore;
  }

  public Integer getAnimeRankByScore() {
    return animeRankByScore;
  }

  public void setAnimeRankByScore(Integer animeRankByScore) {
    this.animeRankByScore = animeRankByScore;
  }

  public Integer getAnimeRankByPopularity() {
    return animeRankByPopularity;
  }

  public void setAnimeRankByPopularity(Integer animeRankByPopularity) {
    this.animeRankByPopularity = animeRankByPopularity;
  }

  public Integer getAnimeNumberOfUsers() {
    return animeNumberOfUsers;
  }

  public void setAnimeNumberOfUsers(Integer animeNumberOfUsers) {
    this.animeNumberOfUsers = animeNumberOfUsers;
  }

  public Integer getAnimeNumberOfScoringUsers() {
    return animeNumberOfScoringUsers;
  }

  public void setAnimeNumberOfScoringUsers(Integer animeNumberOfScoringUsers) {
    this.animeNumberOfScoringUsers = animeNumberOfScoringUsers;
  }

  public ZonedDateTime getAnimeCreatedAtDateTime() {
    return animeCreatedAtDateTime;
  }

  public void setAnimeCreatedAtDateTime(ZonedDateTime animeCreatedAtDateTime) {
    this.animeCreatedAtDateTime = animeCreatedAtDateTime;
  }

  public ZonedDateTime getAnimeUpdatedAtDateTime() {
    return animeUpdatedAtDateTime;
  }

  public void setAnimeUpdatedAtDateTime(ZonedDateTime animeUpdatedAtDateTime) {
    this.animeUpdatedAtDateTime = animeUpdatedAtDateTime;
  }

  public MediaType getMediaType() {
    return mediaType;
  }

  public void setMediaType(MediaType mediaType) {
    this.mediaType = mediaType;
  }

  public AiringStatus getAiringStatus() {
    return airingStatus;
  }

  public void setAiringStatus(AiringStatus airingStatus) {
    this.airingStatus = airingStatus;
  }

  public String getAnimeGenres() {
    return animeGenres;
  }

  public void setAnimeGenres(String animeGenres) {
    this.animeGenres = animeGenres;
  }

  public Integer getAnimeNumEpisodes() {
    return animeNumEpisodes;
  }

  public void setAnimeNumEpisodes(Integer animeNumEpisodes) {
    this.animeNumEpisodes = animeNumEpisodes;
  }

  public Source getAnimeSource() {
    return animeSource;
  }

  public void setAnimeSource(Source animeSource) {
    this.animeSource = animeSource;
  }

  public Integer getAnimeEpisodeDurationSeconds() {
    return animeEpisodeDurationSeconds;
  }

  public void setAnimeEpisodeDurationSeconds(Integer animeEpisodeDurationSeconds) {
    this.animeEpisodeDurationSeconds = animeEpisodeDurationSeconds;
  }

  public AgeRating getAgeRating() {
    return ageRating;
  }

  public void setAgeRating(AgeRating ageRating) {
    this.ageRating = ageRating;
  }

  public String getAnimeSeason() {
    return animeSeason;
  }

  public void setAnimeSeason(String animeSeason) {
    this.animeSeason = animeSeason;
  }

  public ListEntryStatus getListEntryStatus() {
    return listEntryStatus;
  }

  public void setListEntryStatus(ListEntryStatus listEntryStatus) {
    this.listEntryStatus = listEntryStatus;
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
