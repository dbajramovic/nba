package nba.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameLight implements Serializable {

    private static final long serialVersionUID = 1L;
    private String matchDescription;
    private String gameId;
    private String gameUrl;
    private String arena;
    private String city;
    private String date;
    private Long id;

    public GameLight(String gameId, String gameUrl, String arena, String city, String date, Long id) {
        super();
        this.gameId = gameId;
        this.gameUrl = gameUrl;
        this.setCity(city);
        this.setArena(arena);
        this.setDate(date);
        this.setId(id);
        StringBuilder sb = new StringBuilder();
        sb.append(this.getDate().substring(0, 4));
        sb.append("/");
        sb.append(this.getDate().substring(4, 6));
        sb.append("/");
        sb.append(this.getDate().substring(6, 8));
        sb.append(" - ");
        sb.append(this.getArena());
        sb.append(" ");
        sb.append(this.getCity());
        sb.append(" - ");
        sb.append(this.getGameUrl().split("/")[1].substring(0, 3));
        sb.append("@");
        sb.append(this.getGameUrl().split("/")[1].substring(3, 6));
        this.setMatchDescription(sb.toString());
    }

}
