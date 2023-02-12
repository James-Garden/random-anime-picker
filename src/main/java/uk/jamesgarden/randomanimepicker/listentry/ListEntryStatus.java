package uk.jamesgarden.randomanimepicker.listentry;

import java.util.Arrays;

public enum ListEntryStatus {

  WATCHING(1, "Watching"),
  COMPLETED(2, "Completed"),
  ON_HOLD(3, "On hold"),
  DROPPED(4, "Dropped"),
  PLANNED(6, "Plan to Watch"),
  UNKNOWN(999, "Unknown status"),
  ;

  private final Integer statusId;
  private final String displayName;

  ListEntryStatus(Integer statusId, String displayName) {
    this.statusId = statusId;
    this.displayName = displayName;
  }

  public Integer getStatusId() {
    return statusId;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static ListEntryStatus from(Integer statusId) {
    return Arrays.stream(ListEntryStatus.values())
        .filter(status -> status.getStatusId().equals(statusId))
        .findFirst()
        .orElse(UNKNOWN);
  }
}
