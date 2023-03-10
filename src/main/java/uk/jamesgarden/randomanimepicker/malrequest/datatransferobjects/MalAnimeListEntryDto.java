package uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MalAnimeListEntryDto(@JsonProperty("node") ListEntryNodeDto listEntryNodeDto,
                                   @JsonProperty("list_status") ListEntryStatusDto listEntryStatusDto) {
}
