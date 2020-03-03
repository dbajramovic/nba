package nba.dao.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    Long id;

    String gameId;
    String gameUrl;
    String seasonId;
    String date;
    String time;
    String arena;
    String city;
    String state;
    String country;
    String homeStartDate;
    String homeStartTime;
    String visitorStartDate;
    String visitorStartTime;
    String previewAvailable;
    String recapAvailable;
    String notebookAvailable;
    String tntOt;

    @OneToMany(mappedBy = "game")
    List<PlayEntity> plays;

}
