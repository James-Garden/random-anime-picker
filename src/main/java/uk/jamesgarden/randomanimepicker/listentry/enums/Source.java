package uk.jamesgarden.randomanimepicker.listentry.enums;

import java.util.Arrays;

public enum Source {

  ORIGINAL("Original", "original"),
  MANGA("Manga", "manga"),
  WEB_MANGA("Web manga", "web_manga"),
  FOUR_KOMA_MANGA("4-koma Manga", "4_koma_manga"),
  NOVEL("Novel", "novel"),
  LIGHT_NOVEL("Light novel", "light_novel"),
  VISUAL_NOVEL("Visual novel", "visual_novel"),
  GAME("Game", "game"),
  OTHER("Other", "other");

  private final String displayName;
  private final String apiText;

  Source(String displayName, String apiText) {
    this.displayName = displayName;
    this.apiText = apiText;
  }

  @SuppressWarnings("unused")
  public String getDisplayName() {
    return displayName;
  }

  public static Source parse(String text) {
    return Arrays.stream(Source.values())
        .filter(value -> value.apiText.equals(text))
        .findFirst()
        .orElse(OTHER);
  }
}
