package uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record MalAnimeListDto(@JsonProperty("data") List<MalAnimeListEntryDto> animeListEntries,
                              @JsonProperty("paging") MalAnimeListPagingDto paging) {
}
