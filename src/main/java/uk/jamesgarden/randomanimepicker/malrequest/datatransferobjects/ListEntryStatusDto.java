package uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListEntryStatusDto(
    @JsonProperty("status") String status,
    @JsonProperty("score") Integer score,
    @JsonProperty("num_episodes_watched") Integer numEpisodesWatched,
    @JsonProperty("is_rewatching") Boolean isRewatching,
    @JsonProperty("updated_at") String updatedAtDateTime,
    @JsonProperty("start_date") String startDate,
    @JsonProperty("finish_date") String finishDate
) {
}
