package uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MalAnimeListDto(@JsonProperty("data") List<MalAnimeListEntryDto> animeListEntries,
                              @JsonProperty("paging") MalAnimeListPagingDto paging) {
}
