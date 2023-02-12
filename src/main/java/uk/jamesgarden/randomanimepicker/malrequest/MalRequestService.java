package uk.jamesgarden.randomanimepicker.malrequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MalRequestService {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final RestTemplate TEMPLATE = new RestTemplate();
  private static final String BASE_URL = "https://myanimelist.net/animelist/%s/load.json?offset=%d";
  private static final Integer OFFSET = 300;
  private static final Random RANDOM = new Random();

  public List<ListEntryDto> getUserList(String username) throws JsonProcessingException {
    var userList = new ArrayList<ListEntryDto>();

    List<ListEntryDto> userListPart;
    do {
      var url = BASE_URL.formatted(username, userList.size());
      var jsonResponse = TEMPLATE.getForObject(url, String.class);
      userListPart = MAPPER.readValue(jsonResponse, new TypeReference<>() {});

      userList.addAll(userListPart);
    } while (userListPart.size() == OFFSET);

    return userList;
  }

  public ListEntryDto getRandomListEntry(List<ListEntryDto> userList) {
    return userList.get(RANDOM.nextInt(userList.size()));
  }


}
