package uk.jamesgarden.randomanimepicker.listupdate;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.exception.InternalServerErrorException;
import uk.jamesgarden.randomanimepicker.listentry.ListEntryService;
import uk.jamesgarden.randomanimepicker.malrequest.ListEntryDto;
import uk.jamesgarden.randomanimepicker.malrequest.MalRequestService;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;
import uk.jamesgarden.randomanimepicker.maluser.MalUserService;

@Service
public class ListUpdateService {

  private final MalRequestService malRequestService;
  private final ListEntryService listEntryService;
  private final MalUserService malUserService;

  @Autowired
  ListUpdateService(MalRequestService malRequestService,
                    ListEntryService listEntryService, MalUserService malUserService) {
    this.malRequestService = malRequestService;
    this.listEntryService = listEntryService;
    this.malUserService = malUserService;
  }

  public void updateList(MalUser user) {
    var listEntryDtos = getListEntryDtosOrThrow(user);
    var listEntries = listEntryService.convertDtosToEntities(user, listEntryDtos);
    listEntryService.updateListForUser(user, listEntries);
    malUserService.setLastUpdatedToNow(user);
  }

  private List<ListEntryDto> getListEntryDtosOrThrow(MalUser malUser) {
    try {
      return malRequestService.getUserList(malUser.getUsername());
    } catch (JsonProcessingException e) {
      throw new InternalServerErrorException("Could not process list for MalUser with ID [%s]".formatted(malUser.getId().toString()));
    }
  }
}
