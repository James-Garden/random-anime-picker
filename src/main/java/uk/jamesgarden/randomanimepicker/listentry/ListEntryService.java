package uk.jamesgarden.randomanimepicker.listentry;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.malrequest.ListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;

@Service
public class ListEntryService {

  private final ListEntryRepository listEntryRepository;

  @Autowired
  ListEntryService(ListEntryRepository listEntryRepository) {
    this.listEntryRepository = listEntryRepository;
  }

  public List<ListEntry> convertDtosToEntities(MalUser user, Collection<ListEntryDto> listEntryDtos) {
    return listEntryDtos.stream()
        .map(ListEntry::from)
        .peek(listEntry -> listEntry.setUser(user))
        .toList();
  }

  public List<ListEntry> getUserListEntries(MalUser user) {
    return listEntryRepository.findAllByUser(user);
  }

  public Optional<ListEntry> findListEntryById(UUID id) {
    return listEntryRepository.findById(id);
  }

  @Transactional
  public void updateListForUser(MalUser user, Iterable<ListEntry> listEntries) {
    listEntryRepository.deleteAllByUser(user);
    listEntryRepository.saveAll(listEntries);
  }
}
