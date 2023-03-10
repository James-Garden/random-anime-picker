package uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MalAnimeListEntryDto(@JsonProperty("node") ListEntryNodeDto listEntryNodeDto,
                                   @JsonProperty("list_status") ListEntryStatusDto listEntryStatusDto) {
}
