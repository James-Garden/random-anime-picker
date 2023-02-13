package uk.jamesgarden.randomanimepicker.malrequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.jamesgarden.randomanimepicker.utils.UrlUtils;

@Service
public class MalRequestService {

  private static final ObjectMapper MAPPER = new ObjectMapper();
  private static final RestTemplate TEMPLATE = new RestTemplate();
  private static final Integer OFFSET = 300;

  public List<ListEntryDto> getUserList(String username) throws JsonProcessingException {
    var userList = new ArrayList<ListEntryDto>();
    var baseUrl = UrlUtils.getListUrl(username);
    baseUrl += "/load.json?offset=%d";

    List<ListEntryDto> userListPart;
    do {
      var url = baseUrl.formatted(userList.size());
      var jsonResponse = TEMPLATE.getForObject(url, String.class);
      userListPart = MAPPER.readValue(jsonResponse, new TypeReference<>() {});

      userList.addAll(userListPart);
    } while (userListPart.size() == OFFSET);

    return userList;
  }


}
