package uk.jamesgarden.randomanimepicker.listentry.enums;

import java.util.Arrays;

public enum AgeRating {

  GENERAL_AUDIENCES("G", "g"),
  PARENTAL_GUIDANCE("PG", "pg"),
  PARENTS_STRONGLY_CAUTIONED("PG-13", "pg_13"),
  RESTRICTED("R", "r"),
  RESTRICTED_PLUS("R+", "r+"),
  ADULT_ONLY("Rx", "rx"),
  OTHER("Other", "other");

  private final String displayName;
  private final String apiText;

  AgeRating(String displayName, String apiText) {
    this.displayName = displayName;
    this.apiText = apiText;
  }

  public String getDisplayName() {
    return displayName;
  }

  public static AgeRating parse(String text) {
    return Arrays.stream(AgeRating.values())
        .filter(value -> value.apiText.equals(text))
        .findFirst()
        .orElse(OTHER);
  }
}
