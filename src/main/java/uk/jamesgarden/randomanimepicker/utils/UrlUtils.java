package uk.jamesgarden.randomanimepicker.utils;

public class UrlUtils {

  public static String getListUrl(String username) {
    return "https://myanimelist.net/animelist/%s".formatted(username);
  }
}
