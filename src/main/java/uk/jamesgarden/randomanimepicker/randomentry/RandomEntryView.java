package uk.jamesgarden.randomanimepicker.randomentry;

public record RandomEntryView(boolean isEmpty,
                              Integer animeId,
                              String animeTitle,
                              String japaneseTitle,
                              String englishTitle,
                              String watchingStatus,
                              Double communityScore,
                              Integer numberOfEpisodes,
                              String dateAddedToList,
                              String airingStatus,
                              String ageRating,
                              String animeImageUrl) {

  public static RandomEntryView emptyView() {
    return new RandomEntryView(
        true,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        null
    );
  }
}
