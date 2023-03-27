package uk.jamesgarden.randomanimepicker.anime;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

public interface AnimeRepository extends CrudRepository<Anime, UUID> {

  Set<Anime> findAllByAnimeIdIn(Collection<Integer> animeIds);

  Anime getByAnimeId(Integer animeId);
}
