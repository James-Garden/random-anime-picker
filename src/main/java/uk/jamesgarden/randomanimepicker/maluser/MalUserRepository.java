package uk.jamesgarden.randomanimepicker.maluser;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface MalUserRepository extends CrudRepository<MalUser, UUID> {

  Optional<MalUser> getByUsername(String username);

}
