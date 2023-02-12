package uk.jamesgarden.randomanimepicker.malrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ListEntryDto(
    @JsonProperty("status") Integer status,
    @JsonProperty("score") Integer score,
    @JsonProperty("tags") String tags,
    @JsonProperty("is_rewatching") Integer isRewatching,
    @JsonProperty("num_watched_episodes") Integer numWatchedEpisodes,
    @JsonProperty("created_at") Integer createdAt,
    @JsonProperty("updated_at") Integer updatedAt,
    @JsonProperty("anime_title") String animeTitle,
    @JsonProperty("anime_title_eng") String animeTitleEng,
    @JsonProperty("anime_num_episodes") Integer animeNumEpisodes,
    @JsonProperty("anime_airing_status") Integer animeAiringStatus,
    @JsonProperty("anime_id") Integer animeId,
    @JsonProperty("anime_studios") String animeStudios,
    @JsonProperty("anime_licensors") String animeLicensors,
    @JsonProperty("anime_total_members") Integer animeTotalMembers,
    @JsonProperty("anime_total_scores") Integer animeTotalScores,
    @JsonProperty("anime_score_val") Double animeScoreVal,
    @JsonProperty("has_episode_video") Boolean hasEpisodeVideo,
    @JsonProperty("has_promotion_video") Boolean hasPromotionVideo,
    @JsonProperty("has_video") Boolean hasVideo,
    @JsonProperty("video_url") String videoUrl,
    @JsonProperty("genres") List<Map<String, String>> genres,
    @JsonProperty("demographics") List<Map<String, String>> demographics,
    @JsonProperty("title_localized") String titleLocalized,
    @JsonProperty("anime_url") String animeUrl,
    @JsonProperty("anime_image_path") String animeImagePath,
    @JsonProperty("is_added_to_list") Boolean isAddedToList,
    @JsonProperty("anime_media_type_string") String animeMediaTypeString,
    @JsonProperty("anime_mpaa_rating_string") String animeMpaaRatingString,
    @JsonProperty("start_date_string") String startDateString,
    @JsonProperty("finish_date_string") String finishDateString,
    @JsonProperty("anime_start_date_string") String animeStartDateString,
    @JsonProperty("anime_end_date_string") String animeEndDateString,
    @JsonProperty("days_string") String daysString,
    @JsonProperty("storage_string") String storageString,
    @JsonProperty("priority_rating") String priorityRating,
    @JsonProperty("notes") String notes,
    @JsonProperty("editable_notes") String editableNotes
) {}
