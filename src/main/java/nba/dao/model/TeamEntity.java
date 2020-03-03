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
public class TeamEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", updatable = false, nullable = false)
    Long id;
    @Column(name = "IS_NBA_FRANCHISE")
    Boolean isNBAFranchise;
    @Column(name = "IS_ALL_STAR")
    Boolean isAllStar;
    @Column(name = "CITY")
    String city;
    @Column(name = "ALT_CITY_NAME")
    String altCityName;
    @Column(name = "FULL_NAME")
    String fullName;
    @Column(name = "TRICODE")
    String tricode;
    @Column(name = "NICKNAME")
    String nickname;
    @Column(name = "URL_NAME")
    String urlName;
    @Column(name = "TEAM_ID")
    String teamId;
    @Column(name = "CONF_NAME")
    String confName;
    @Column(name = "DIV_NAME")
    String divName;
    @Column(name = "YEAR")
    String year;

    String logoUrl;

    @OneToMany(mappedBy = "team")
    List<PlayerTeamEntity> playerTeams;

}
