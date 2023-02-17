package uk.jamesgarden.randomanimepicker.maluser;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

interface MalUserRepository extends CrudRepository<MalUser, UUID> {

  Optional<MalUser> findByUsername(String username);

  boolean existsByUsername(String username);
}
