package uk.jamesgarden.randomanimepicker.listentry.enums;

import java.util.Arrays;

public enum AiringStatus {

  NOT_YET_AIRED("Not yet aired", "not_yet_aired"),
  CURRENTLY_AIRING("Currently airing", "currently_airing"),
  FINISHED_AIRING("Finished airing", "finished_airing"),
  OTHER("Other", "other");

  private final String displayName;
  private final String apiText;

  AiringStatus(String displayName, String apiText) {
    this.displayName = displayName;
    this.apiText = apiText;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static AiringStatus parse(String text) {
    return Arrays.stream(AiringStatus.values())
        .filter(value -> value.apiText.equals(text))
        .findFirst()
        .orElse(OTHER);
  }
}
