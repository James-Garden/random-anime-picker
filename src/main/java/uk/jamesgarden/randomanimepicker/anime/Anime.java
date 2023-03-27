package uk.jamesgarden.randomanimepicker.anime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
import uk.jamesgarden.randomanimepicker.listentry.enums.MediaType;
import uk.jamesgarden.randomanimepicker.listentry.enums.Source;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;
import uk.jamesgarden.randomanimepicker.utils.ClassCastUtil;

@Entity
@Table(name = "anime")
public class Anime {

  private static final Logger LOGGER = LoggerFactory.getLogger(Anime.class);

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
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
  private Integer animeNumEpisodes;
  private Source animeSource;
  private Integer animeEpisodeDurationSeconds;

  @Enumerated
  private AgeRating ageRating;

  private String animeSeason;

  public void update(Anime updatedDetails) {
    try {
      Objects.requireNonNull(updatedDetails);
    } catch (NullPointerException e) {
      LOGGER.error("Got null when trying to update Anime with ID %s".formatted(id.toString()), e);
      return;
    }
    animeId = updatedDetails.getAnimeId();
    animeTitle = updatedDetails.getAnimeTitle();
    animeTitleEnglish = updatedDetails.getAnimeTitleEnglish();
    animeTitleJapanese = updatedDetails.getAnimeTitleJapanese();
    animeImage = updatedDetails.getAnimeImage();
    animeStartDate = updatedDetails.getAnimeStartDate();
    animeEndDate = updatedDetails.getAnimeEndDate();
    animeSynopsis = updatedDetails.getAnimeSynopsis();
    animeAverageScore = updatedDetails.getAnimeAverageScore();
    animeRankByScore = updatedDetails.getAnimeRankByScore();
    animeRankByPopularity = updatedDetails.getAnimeRankByPopularity();
    animeNumberOfUsers = updatedDetails.getAnimeNumberOfUsers();
    animeNumberOfScoringUsers = updatedDetails.getAnimeNumberOfScoringUsers();
    animeCreatedAtDateTime = updatedDetails.getAnimeCreatedAtDateTime();
    animeUpdatedAtDateTime = updatedDetails.getAnimeUpdatedAtDateTime();
    mediaType = updatedDetails.getMediaType();
    airingStatus = updatedDetails.getAiringStatus();
    animeNumEpisodes = updatedDetails.getAnimeNumEpisodes();
    animeSource = updatedDetails.getAnimeSource();
    animeEpisodeDurationSeconds = updatedDetails.getAnimeEpisodeDurationSeconds();
    ageRating = updatedDetails.getAgeRating();
    animeSeason = updatedDetails.getAnimeSeason();
  }

  public static Anime from(MalAnimeListEntryDto listEntryDto, MalUser user) {
    var animeData = listEntryDto.listEntryNodeDto();
    var anime = new Anime();
    var loggerPrefix = "User: %s, Anime: %d, Field: %s".formatted(user.getUsername(), animeData.id(), "%s");
    anime.animeId = animeData.id();
    anime.animeTitle = animeData.title();
    anime.animeTitleEnglish = animeData.alternativeTitles().en();
    anime.animeTitleJapanese = animeData.alternativeTitles().ja();
    if (Objects.nonNull(animeData.mainPicture().get("large"))) {
      anime.animeImage = animeData.mainPicture().get("large");
    } else {
      anime.animeImage = animeData.mainPicture().get("medium");
    }
    anime.animeStartDate = ClassCastUtil.castOrNull(
        () -> LocalDate.parse(animeData.startDate()),
        animeData.startDate(),
        LocalDate.class,
        loggerPrefix.formatted("animeStartDate")
    );
    anime.animeEndDate = ClassCastUtil.castOrNull(
        () -> LocalDate.parse(animeData.endDate()),
        animeData.endDate(),
        LocalDate.class,
        loggerPrefix.formatted("animeEndDate")
    );
    anime.animeSynopsis = animeData.synopsis();
    anime.animeAverageScore = animeData.averageScore();
    anime.animeRankByScore = animeData.rankByScore();
    anime.animeRankByPopularity = animeData.rankByPopularity();
    anime.animeNumberOfUsers = animeData.numberOfUsers();
    anime.animeNumberOfScoringUsers = animeData.numberOfScoringUsers();
    anime.animeCreatedAtDateTime = ClassCastUtil.castOrNull(
        () -> ZonedDateTime.parse(animeData.createdAtDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
        animeData.createdAtDateTime(),
        ZonedDateTime.class,
        loggerPrefix.formatted("animeCreatedAtDateTime")
    );
    anime.animeUpdatedAtDateTime = ClassCastUtil.castOrNull(
        () -> ZonedDateTime.parse(animeData.updatedAtDateTime(), DateTimeFormatter.ISO_OFFSET_DATE_TIME),
        animeData.updatedAtDateTime(),
        ZonedDateTime.class,
        loggerPrefix.formatted("animeUpdatedAtDateTime")
    );
    anime.mediaType = MediaType.parse(animeData.mediaType());
    anime.airingStatus = AiringStatus.parse(animeData.status());
    anime.animeNumEpisodes = animeData.numEpisodes();
    anime.animeSource = Source.parse(animeData.source());
    anime.animeEpisodeDurationSeconds = animeData.episodeDurationSeconds();
    anime.ageRating = AgeRating.parse(animeData.rating());
    anime.animeSeason = formatSeason(animeData.startSeason());
    return anime;
  }

  public UUID getId() {
    return id;
  }

  public Integer getAnimeId() {
    return animeId;
  }

  public String getAnimeTitle() {
    return animeTitle;
  }

  public String getAnimeTitleEnglish() {
    return animeTitleEnglish;
  }

  public String getAnimeTitleJapanese() {
    return animeTitleJapanese;
  }

  public String getAnimeImage() {
    return animeImage;
  }

  public LocalDate getAnimeStartDate() {
    return animeStartDate;
  }

  public LocalDate getAnimeEndDate() {
    return animeEndDate;
  }

  public String getAnimeSynopsis() {
    return animeSynopsis;
  }

  public Double getAnimeAverageScore() {
    return animeAverageScore;
  }

  public Integer getAnimeRankByScore() {
    return animeRankByScore;
  }

  public Integer getAnimeRankByPopularity() {
    return animeRankByPopularity;
  }

  public Integer getAnimeNumberOfUsers() {
    return animeNumberOfUsers;
  }

  public Integer getAnimeNumberOfScoringUsers() {
    return animeNumberOfScoringUsers;
  }

  public ZonedDateTime getAnimeCreatedAtDateTime() {
    return animeCreatedAtDateTime;
  }

  public ZonedDateTime getAnimeUpdatedAtDateTime() {
    return animeUpdatedAtDateTime;
  }

  public MediaType getMediaType() {
    return mediaType;
  }

  public AiringStatus getAiringStatus() {
    return airingStatus;
  }

  public Integer getAnimeNumEpisodes() {
    return animeNumEpisodes;
  }

  public Source getAnimeSource() {
    return animeSource;
  }

  public Integer getAnimeEpisodeDurationSeconds() {
    return animeEpisodeDurationSeconds;
  }

  public AgeRating getAgeRating() {
    return ageRating;
  }

  public String getAnimeSeason() {
    return animeSeason;
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


}
