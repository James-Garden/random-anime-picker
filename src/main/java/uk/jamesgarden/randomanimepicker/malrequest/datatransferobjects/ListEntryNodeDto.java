package uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListEntryNodeDto(
    @JsonProperty("id") Integer id,
    @JsonProperty("title") String title,
    @JsonProperty("main_picture") Map<String, String> mainPicture,
    @JsonProperty("alternative_titles") AlternativeTitlesDto alternativeTitles,
    @JsonProperty("start_date") String startDate,
    @JsonProperty("end_date") String endDate,
    @JsonProperty("synopsis") String synopsis,
    @JsonProperty("mean") Double averageScore,
    @JsonProperty("rank") Integer rankByScore,
    @JsonProperty("popularity") Integer rankByPopularity,
    @JsonProperty("num_list_users") Integer numberOfUsers,
    @JsonProperty("num_scoring_users") Integer numberOfScoringUsers,
    @JsonProperty("created_at") String createdAtDateTime,
    @JsonProperty("updated_at") String updatedAtDateTime,
    @JsonProperty("media_type") String mediaType,
    @JsonProperty("status") String status,
    @JsonProperty("genres") List<Map<String, String>> genres,
    @JsonProperty("num_episodes") Integer numEpisodes,
    @JsonProperty("start_season") Map<String, String> startSeason,
    @JsonProperty("broadcast") Map<String, String> broadcast,
    @JsonProperty("source") String source,
    @JsonProperty("average_episode_duration") Integer episodeDurationSeconds,
    @JsonProperty("rating") String rating,
    @JsonProperty("studios") List<Map<String, String>> studios
    ) {
}
