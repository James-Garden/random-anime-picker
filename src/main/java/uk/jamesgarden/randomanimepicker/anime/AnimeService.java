package uk.jamesgarden.randomanimepicker.anime;

import jakarta.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.jamesgarden.randomanimepicker.malrequest.datatransferobjects.MalAnimeListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;

@Service
public class AnimeService {

  private final AnimeRepository animeRepository;

  @Autowired
  public AnimeService(AnimeRepository animeRepository) {
    this.animeRepository = animeRepository;
  }

  public List<Anime> convertDtosToEntities(MalUser user, Collection<MalAnimeListEntryDto> listEntryDtos) {
    return listEntryDtos.stream()
        .map(listEntryDto -> Anime.from(listEntryDto, user))
        .toList();
  }

  @Transactional
  public void updateAnime(Collection<Anime> anime) {
    var animeIds = anime.stream()
        .map(Anime::getAnimeId)
        .collect(Collectors.toSet());
    var existingAnime = animeRepository.findAllByAnimeIdIn(animeIds);
    var existingIds = existingAnime.stream()
        .map(Anime::getAnimeId)
        .collect(Collectors.toSet());
    var partitionedAnime = anime.stream()
        .collect(Collectors.groupingBy(listAnime -> existingIds.contains(listAnime.getAnimeId())));
    var newAnime = partitionedAnime.getOrDefault(false, Collections.emptyList());
    var updatedExistingAnime = partitionedAnime.getOrDefault(true, Collections.emptyList());
    var updatedAnime = updateAnime(existingAnime, updatedExistingAnime);

    var animeToSave = new HashSet<>(newAnime);
    animeToSave.addAll(updatedAnime);

    animeRepository.saveAll(animeToSave);
  }

  public Anime getByAnimeId(Integer animeId) {
    return animeRepository.getByAnimeId(animeId);
  }

  private Collection<Anime> updateAnime(Collection<Anime> oldAnime, Collection<Anime> newAnime) {
    var newAnimeMap = newAnime.stream()
        .collect(Collectors.toMap(
            Anime::getAnimeId,
            Function.identity()
        ));
    oldAnime.forEach(anime -> anime.update(newAnimeMap.get(anime.getAnimeId())));
    return oldAnime;
  }
}
