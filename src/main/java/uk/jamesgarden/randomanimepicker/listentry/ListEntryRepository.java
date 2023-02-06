package uk.jamesgarden.randomanimepicker.listentry;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;

public interface ListEntryRepository extends CrudRepository<ListEntry, UUID> {

  void deleteAllByUser(MalUser user);

  @Query("SELECT le.id FROM ListEntry le WHERE le.user = ?1")
  List<UUID> findAllListEntryIdsByUser(MalUser user);

}
