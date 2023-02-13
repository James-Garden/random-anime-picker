package uk.jamesgarden.randomanimepicker.maluser;

import jakarta.transaction.Transactional;
import java.time.Clock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    var existingUser = malUserRepository.getByUsername(username);
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
    user.setLastUpdated(clock.instant());
    malUserRepository.save(user);
  }

  public boolean existsByUsername(String username) {
    return malUserRepository.existsByUsername(username);
  }
}
