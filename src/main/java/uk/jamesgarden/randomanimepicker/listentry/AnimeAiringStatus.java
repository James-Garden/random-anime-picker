package uk.jamesgarden.randomanimepicker.listentry;

import java.util.Arrays;

public enum AnimeAiringStatus {

  CURRENTLY_AIRING(1, "Currently Airing"),
  FINISHED_AIRING(2, "Finished Airing"),
  NOT_YET_AIRED(3, "Not Aired Yet"),
  UNKNOWN(999, "Unknown status"),
  ;

  private final Integer statusId;
  private final String displayName;

  AnimeAiringStatus(Integer statusId, String displayName) {
    this.statusId = statusId;
    this.displayName = displayName;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static AnimeAiringStatus from(Integer airingId) {
    return Arrays.stream(AnimeAiringStatus.values())
        .filter(status -> status.getStatusId().equals(airingId))
        .findFirst()
        .orElse(UNKNOWN);
  }
}
