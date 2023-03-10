package uk.jamesgarden.randomanimepicker.malrequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.configuration.MalApiConfiguration;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListEntryDto;

@Service
public class MalRequestService {

  private final MalApiConfiguration malApiConfiguration;
  private final HttpClient client;

  @Autowired
  public MalRequestService(MalApiConfiguration malApiConfiguration,
                           HttpClient client) {
    this.malApiConfiguration = malApiConfiguration;
    this.client = client;
  }

  public List<MalAnimeListEntryDto> getUserList(String username) throws IOException, InterruptedException {
    var initialRequest = MalRequest.newBuilder()
        .animelist(username)
        .withClientId(malApiConfiguration.getClientId())
        .withDefaultFields()
        .withNsfw(true)
        .withLimit(1000)
        .build();
    var initialResult = initialRequest.execute(client);
    var malAnimeListEntryDtoList = new ArrayList<>(initialResult.animeListEntries());
    var nextUrl = initialResult.paging().nextListPartUrl();

    while (Objects.nonNull(nextUrl)) {
      var newRequest = MalRequest.from(URI.create(nextUrl), malApiConfiguration.getClientId());
      var newResult = newRequest.execute(client);
      malAnimeListEntryDtoList.addAll(newResult.animeListEntries());
      nextUrl = newResult.paging().nextListPartUrl();
    }

    return malAnimeListEntryDtoList;
  }
}
