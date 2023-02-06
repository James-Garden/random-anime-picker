package uk.jamesgarden.randomanimepicker.listentry;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;
import uk.jamesgarden.randomanimepicker.malrequest.ListEntryDto;
import uk.jamesgarden.randomanimepicker.maluser.MalUser;

@Entity
@Table(name = "user_list_entries")
public class ListEntry {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "mal_user_id")
  private MalUser user;

  private String animeTitle;

  public ListEntry() {
  }

  public UUID getId() {
    return id;
  }

  public MalUser getUser() {
    return user;
  }

  public void setUser(MalUser user) {
    this.user = user;
  }

  public String getAnimeTitle() {
    return animeTitle;
  }

  public void setAnimeTitle(String animeTitle) {
    this.animeTitle = animeTitle;
  }

  public static ListEntry from(ListEntryDto listEntryDto) {
    var listEntry = new ListEntry();
    listEntry.setAnimeTitle(listEntryDto.animeTitle());
    return listEntry;
  }
}
