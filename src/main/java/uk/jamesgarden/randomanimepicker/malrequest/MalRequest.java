package uk.jamesgarden.randomanimepicker.malrequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.web.util.DefaultUriBuilderFactory;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListDto;

public class MalRequest {


  private static final String HTTPS_SCHEME = "https";
  private static final String API_CLIENT_ID_HEADER_NAME = "X-MAL-CLIENT-ID";
  private static final List<String> DEFAULT_FIELDS = List.of(
      "list_status",
      "id",
      "title",
      "main_picture",
      "alternative_titles",
      "start_date",
      "end_date",
      "synopsis",
      "mean",
      "rank",
      "popularity",
      "num_list_users",
      "num_scoring_users",
      "created_at",
      "updated_at",
      "media_type",
      "status",
      "genres",
      "my_list_status",
      "num_episodes",
      "start_season",
      "broadcast",
      "source",
      "average_episode_duration",
      "rating",
      "pictures",
      "background",
      "related_anime",
      "related_manga",
      "recommendations",
      "studios",
      "statistics"
  );
  private static final ObjectMapper MAPPER = new ObjectMapper();

  private final HttpRequest httpRequest;

  private MalRequest(HttpRequest httpRequest) {
    this.httpRequest = httpRequest;
  }

  public MalAnimeListDto execute(HttpClient client) throws IOException, InterruptedException {
    var responseBody = client.send(httpRequest, HttpResponse.BodyHandlers.ofString()).body();
    return MAPPER.readValue(responseBody, MalAnimeListDto.class);
  }

  public static MalRequestBuilder newBuilder() {
    return new MalRequestBuilder();
  }

  public static MalRequest from(URI uri, String clientId) {
    var httpRequest = HttpRequest.newBuilder()
        .uri(uri)
        .header("accept", "application/json")
        .header(API_CLIENT_ID_HEADER_NAME, clientId)
        .build();
    return new MalRequest(httpRequest);
  }

  public static class MalRequestBuilder {

    private String clientId;
    private String host;
    private String path;
    private Integer limit;
    private Boolean nsfw;
    private final List<String> fields = new ArrayList<>();

    public MalRequestBuilder withHost(String host) {
      this.host = host;
      return this;
    }

    public MalRequestBuilder withPath(String basePath, String relativePath, String username) {
      this.path = basePath + relativePath.formatted(username);
      return this;
    }

    public MalRequestBuilder withLimit(Integer limit) {
      this.limit = limit;
      return this;
    }

    public MalRequestBuilder withNsfw(Boolean nsfw) {
      this.nsfw = nsfw;
      return this;
    }

    public MalRequestBuilder withDefaultFields() {
      fields.addAll(DEFAULT_FIELDS);
      return this;
    }

    public MalRequestBuilder withClientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public MalRequest build() {
      Objects.requireNonNull(path);
      Objects.requireNonNull(clientId);

      var fieldsParam = String.join(",", this.fields);
      var uri = new DefaultUriBuilderFactory().builder()
          .scheme(HTTPS_SCHEME)
          .host(host)
          .path(path)
          .queryParamIfPresent("limit", Optional.ofNullable(limit))
          .queryParamIfPresent("nsfw", Optional.ofNullable(nsfw))
          .queryParam("fields", fieldsParam)
          .build();

      return MalRequest.from(uri, clientId);
    }
  }
}
