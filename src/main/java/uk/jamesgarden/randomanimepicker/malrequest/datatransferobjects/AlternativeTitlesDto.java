package uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AlternativeTitlesDto(
    @JsonProperty("synonyms") List<String> synonyms,
    @JsonProperty("en") String en,
    @JsonProperty("ja") String ja
) {}
