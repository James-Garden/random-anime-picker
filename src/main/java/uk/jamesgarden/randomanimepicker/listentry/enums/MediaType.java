package uk.jamesgarden.randomanimepicker.listentry.enums;

import java.util.Arrays;

public enum MediaType {

  TV("TV", "tv"),
  MOVIE("Movie", "movie"),
  ONA("ONA", "ona"),
  OVA("OVA", "ova"),
  SPECIAL("Special", "special"),
  OTHER("Other", "other");

  private final String displayName;
  private final String apiText;

  MediaType(String displayName, String apiText) {
    this.displayName = displayName;
    this.apiText = apiText;
  }

  @SuppressWarnings("unused")
  public String getDisplayName() {
    return displayName;
  }

  public static MediaType parse(String text) {
    return Arrays.stream(MediaType.values())
        .filter(value -> value.apiText.equals(text))
        .findFirst()
        .orElse(OTHER);
  }
}
