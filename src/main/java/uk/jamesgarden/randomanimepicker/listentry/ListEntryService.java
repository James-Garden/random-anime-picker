package uk.jamesgarden.randomanimepicker.listentry;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;

@Service
public class ListEntryService {

  private final ListEntryRepository listEntryRepository;

  @Autowired
  ListEntryService(ListEntryRepository listEntryRepository) {
    this.listEntryRepository = listEntryRepository;
  }

  public List<ListEntry> convertDtosToEntities(MalUser user, Collection<MalAnimeListEntryDto> listEntryDtos) {
    return listEntryDtos.stream()
        .map(listEntryDto -> ListEntry.from(listEntryDto, user))
        .toList();
  }

  public List<ListEntry> getUserListEntries(MalUser user) {
    return listEntryRepository.findAllByUser(user);
  }

  @Transactional
  public void updateListForUser(MalUser user, Iterable<ListEntry> listEntries) {
    listEntryRepository.deleteAllByUser(user);
    listEntryRepository.saveAll(listEntries);
  }
}
