package nba.helpers;

import nba.dao.model.PlayInfo;

public class PlayDescriptionParser {

    private PlayDescriptionParser() {
    };

    public static PlayInfo determineTypeOfPlay(String play) {
        PlayInfo playInfo = new PlayInfo();
        String[] words = play.split(" ");
        Boolean isName = false;
        Boolean isPartOfDescription = false;
        Boolean isOutcome = false;
        Boolean isConnectedAction = false;
        for (String word : words) {
            if (PlayType.isPlayType(word.replace(":", "")) || PlayType.isPlayType(word)) {
                isPartOfDescription = false;
                playInfo.setPlayType(PlayType.byValue(word.replace(":", "")));
            }
            if (isName) {
                playInfo.setPlayer(word);
                isName = false;
            }
            if (isConnectedAction) {
                StringBuilder sb = new StringBuilder();
                if (playInfo.getConnectedAction() != null) {
                    sb.append(playInfo.getConnectedAction());
                }
                sb.append(" ");
                sb.append(word);
                playInfo.setConnectedAction(sb.toString());
            }
            if (isOutcome) {
                playInfo.setOutcome(word);
                isConnectedAction = true;
                isOutcome = false;
            }

            if (isPartOfDescription) {
                StringBuilder sb = new StringBuilder();
                sb.append(playInfo.getDescription());
                sb.append(" ");
                sb.append(word);
                playInfo.setDescription(sb.toString());
            }
            if (word.contains(":") && !word.contains("Assist")) {
                isOutcome = true;
            }
            if (word.contains("[") || word.contains("]")) {
                isName = true;
            }
            if (ShotDescriptor.isDescriptor(word)) {
                playInfo.setDescription(word);
                isPartOfDescription = true;
            }
        }
        return playInfo;
    }

}
