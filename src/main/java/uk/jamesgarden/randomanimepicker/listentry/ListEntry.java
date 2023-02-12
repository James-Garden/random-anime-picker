package uk.jamesgarden.randomanimepicker.listentry;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import uk.jamesgarden.randomanimepicker.malrequest.ListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;
import uk.jamesgarden.randomanimepicker.utils.DateUtils;

@SuppressWarnings("unused")
@Entity
@Table(name = "user_list_entries")
public class ListEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "mal_user_id")
  private MalUser user;

  @Enumerated
  private ListEntryStatus status;
  private Integer score;
  private String tags;
  private Integer isRewatching;
  private Integer numWatchedEpisodes;
  private Instant createdAt;
  private Instant updatedAt;
  private String animeTitle;
  private String animeTitleEng;
  private Integer animeNumEpisodes;

  @Enumerated
  private AnimeAiringStatus animeAiringStatus;

  private Integer animeId;
  private Integer animeTotalMembers;
  private Integer animeTotalScores;
  private Double animeScoreVal;
  private Boolean hasEpisodeVideo;
  private Boolean hasPromotionVideo;
  private Boolean hasVideo;
  private String videoUrl;
  private String titleLocalized;
  private String animeUrl;
  private String animeImagePath;
  private Boolean isAddedToList;
  private String animeMediaTypeString;
  private String animeMpaaRatingString;
  private String startDateString;
  private String finishDateString;
  private String animeStartDateString;
  private String animeEndDateString;
  private String daysString;
  private String storageString;
  private String priorityRating;
  private String notes;
  private String editableNotes;

  public ListEntry() {
  }

  public UUID getId() {
    return id;
  }

  public MalUser getUser() {
    return user;
  }

  public void setUser(MalUser user) {
    this.user = user;
  }

  public static ListEntry from(ListEntryDto listEntryDto) {
    var listEntry = new ListEntry();
    listEntry.setStatus(ListEntryStatus.from(listEntryDto.status()));
    listEntry.setScore(listEntryDto.score());
    listEntry.setTags(listEntryDto.tags());
    listEntry.setIsRewatching(listEntryDto.isRewatching());
    listEntry.setNumWatchedEpisodes(listEntryDto.numWatchedEpisodes());
    listEntry.setCreatedAt(DateUtils.instantFromEpochSecond(listEntryDto.createdAt()));
    listEntry.setUpdatedAt(DateUtils.instantFromEpochSecond(listEntryDto.updatedAt()));
    listEntry.setAnimeTitle(listEntryDto.animeTitle());
    listEntry.setAnimeTitleEng(listEntryDto.animeTitleEng());
    listEntry.setAnimeNumEpisodes(listEntryDto.animeNumEpisodes());
    listEntry.setAnimeAiringStatus(AnimeAiringStatus.from(listEntryDto.animeAiringStatus()));
    listEntry.setAnimeId(listEntryDto.animeId());
    listEntry.setAnimeTotalMembers(listEntryDto.animeTotalMembers());
    listEntry.setAnimeTotalScores(listEntryDto.animeTotalScores());
    listEntry.setAnimeScoreVal(listEntryDto.animeScoreVal());
    listEntry.setHasEpisodeVideo(listEntryDto.hasEpisodeVideo());
    listEntry.setHasPromotionVideo(listEntryDto.hasPromotionVideo());
    listEntry.setHasVideo(listEntryDto.hasVideo());
    listEntry.setVideoUrl(listEntryDto.videoUrl());
    listEntry.setTitleLocalized(listEntryDto.titleLocalized());
    listEntry.setAnimeUrl(listEntryDto.animeUrl());
    {
      var largeAnimeImage = listEntryDto.animeImagePath()
          .replace("/r/192x272", "")
          .replaceFirst("/images/anime/\\d+/\\d+", "$0l");
      listEntry.setAnimeImagePath(largeAnimeImage);
    }
    listEntry.setAddedToList(listEntryDto.isAddedToList());
    listEntry.setAnimeMediaTypeString(listEntryDto.animeMediaTypeString());
    listEntry.setAnimeMpaaRatingString(listEntryDto.animeMpaaRatingString());
    listEntry.setStartDateString(listEntryDto.startDateString());
    listEntry.setFinishDateString(listEntryDto.finishDateString());
    listEntry.setAnimeStartDateString(listEntryDto.animeStartDateString());
    listEntry.setAnimeEndDateString(listEntryDto.animeEndDateString());
    listEntry.setDaysString(listEntryDto.daysString());
    listEntry.setStorageString(listEntryDto.storageString());
    listEntry.setPriorityRating(listEntryDto.priorityRating());
    listEntry.setNotes(listEntryDto.notes());
    listEntry.setEditableNotes(listEntryDto.editableNotes());
    return listEntry;
  }

  public ListEntryStatus getStatus() {
    return status;
  }

  public void setStatus(ListEntryStatus status) {
    this.status = status;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public Integer getIsRewatching() {
    return isRewatching;
  }

  public void setIsRewatching(Integer isRewatching) {
    this.isRewatching = isRewatching;
  }

  public Integer getNumWatchedEpisodes() {
    return numWatchedEpisodes;
  }

  public void setNumWatchedEpisodes(Integer numWatchedEpisodes) {
    this.numWatchedEpisodes = numWatchedEpisodes;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Instant createdAt) {
    this.createdAt = createdAt;
  }

  public Instant getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Instant updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getAnimeTitle() {
    return animeTitle;
  }

  public void setAnimeTitle(String animeTitle) {
    this.animeTitle = animeTitle;
  }

  public String getAnimeTitleEng() {
    return animeTitleEng;
  }

  public void setAnimeTitleEng(String animeTitleEng) {
    this.animeTitleEng = animeTitleEng;
  }

  public Integer getAnimeNumEpisodes() {
    return animeNumEpisodes;
  }

  public void setAnimeNumEpisodes(Integer animeNumEpisodes) {
    this.animeNumEpisodes = animeNumEpisodes;
  }

  public AnimeAiringStatus getAnimeAiringStatus() {
    return animeAiringStatus;
  }

  public void setAnimeAiringStatus(AnimeAiringStatus animeAiringStatus) {
    this.animeAiringStatus = animeAiringStatus;
  }

  public Integer getAnimeId() {
    return animeId;
  }

  public void setAnimeId(Integer animeId) {
    this.animeId = animeId;
  }

  public Integer getAnimeTotalMembers() {
    return animeTotalMembers;
  }

  public void setAnimeTotalMembers(Integer animeTotalMembers) {
    this.animeTotalMembers = animeTotalMembers;
  }

  public Integer getAnimeTotalScores() {
    return animeTotalScores;
  }

  public void setAnimeTotalScores(Integer animeTotalScores) {
    this.animeTotalScores = animeTotalScores;
  }

  public Double getAnimeScoreVal() {
    return animeScoreVal;
  }

  public void setAnimeScoreVal(Double animeScoreVal) {
    this.animeScoreVal = animeScoreVal;
  }

  public Boolean getHasEpisodeVideo() {
    return hasEpisodeVideo;
  }

  public void setHasEpisodeVideo(Boolean hasEpisodeVideo) {
    this.hasEpisodeVideo = hasEpisodeVideo;
  }

  public Boolean getHasPromotionVideo() {
    return hasPromotionVideo;
  }

  public void setHasPromotionVideo(Boolean hasPromotionVideo) {
    this.hasPromotionVideo = hasPromotionVideo;
  }

  public Boolean getHasVideo() {
    return hasVideo;
  }

  public void setHasVideo(Boolean hasVideo) {
    this.hasVideo = hasVideo;
  }

  public String getVideoUrl() {
    return videoUrl;
  }

  public void setVideoUrl(String videoUrl) {
    this.videoUrl = videoUrl;
  }

  public String getTitleLocalized() {
    return titleLocalized;
  }

  public void setTitleLocalized(String titleLocalized) {
    this.titleLocalized = titleLocalized;
  }

  public String getAnimeUrl() {
    return animeUrl;
  }

  public void setAnimeUrl(String animeUrl) {
    this.animeUrl = animeUrl;
  }

  public String getAnimeImagePath() {
    return animeImagePath;
  }

  public void setAnimeImagePath(String animeImagePath) {
    this.animeImagePath = animeImagePath;
  }

  public Boolean getAddedToList() {
    return isAddedToList;
  }

  public void setAddedToList(Boolean addedToList) {
    isAddedToList = addedToList;
  }

  public String getAnimeMediaTypeString() {
    return animeMediaTypeString;
  }

  public void setAnimeMediaTypeString(String animeMediaTypeString) {
    this.animeMediaTypeString = animeMediaTypeString;
  }

  public String getAnimeMpaaRatingString() {
    return animeMpaaRatingString;
  }

  public void setAnimeMpaaRatingString(String animeMpaaRatingString) {
    this.animeMpaaRatingString = animeMpaaRatingString;
  }

  public String getStartDateString() {
    return startDateString;
  }

  public void setStartDateString(String startDateString) {
    this.startDateString = startDateString;
  }

  public String getFinishDateString() {
    return finishDateString;
  }

  public void setFinishDateString(String finishDateString) {
    this.finishDateString = finishDateString;
  }

  public String getAnimeStartDateString() {
    return animeStartDateString;
  }

  public void setAnimeStartDateString(String animeStartDateString) {
    this.animeStartDateString = animeStartDateString;
  }

  public String getAnimeEndDateString() {
    return animeEndDateString;
  }

  public void setAnimeEndDateString(String animeEndDateString) {
    this.animeEndDateString = animeEndDateString;
  }

  public String getDaysString() {
    return daysString;
  }

  public void setDaysString(String daysString) {
    this.daysString = daysString;
  }

  public String getStorageString() {
    return storageString;
  }

  public void setStorageString(String storageString) {
    this.storageString = storageString;
  }

  public String getPriorityRating() {
    return priorityRating;
  }

  public void setPriorityRating(String priorityRating) {
    this.priorityRating = priorityRating;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getEditableNotes() {
    return editableNotes;
  }

  public void setEditableNotes(String editableNotes) {
    this.editableNotes = editableNotes;
  }
}
