package uk.jamesgarden.randomanimepicker.maluser;

import jakarta.transaction.Transactional;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.exception.NotFoundException;

@Service
public class MalUserService {

  private final MalUserRepository malUserRepository;
  private final Clock clock;

  @Autowired
  MalUserService(MalUserRepository malUserRepository, Clock clock) {
    this.malUserRepository = malUserRepository;
    this.clock = clock;
  }

  @Transactional
  public MalUser getOrCreateUser(String username) {
    var existingUser = findByUsername(username);
    return existingUser.orElseGet(() -> createUser(username));
  }

  @Transactional
  MalUser createUser(String username) {
    var newUser = new MalUser(username);
    malUserRepository.save(newUser);

    return newUser;
  }

  @Transactional
  public void setLastUpdatedToNow(MalUser user) {
    var now = ZonedDateTime.ofInstant(clock.instant(), clock.getZone());
    user.setLastUpdated(now);
    malUserRepository.save(user);
  }

  public boolean existsByUsername(String username) {
    return malUserRepository.existsByUsername(username);
  }

  public MalUser getByUsername(String username) {
    return findByUsername(username).orElseThrow(() ->
        new NotFoundException("Could not find list for user with name '%s'".formatted(username)));
  }

  private Optional<MalUser> findByUsername(String username) {
    return malUserRepository.findByUsername(username);
  }
}
