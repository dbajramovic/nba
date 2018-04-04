package nba.players;

import java.io.Serializable;

public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    private Boolean isNBAFranchise;
    private Boolean isAllStar;
    private String city;
    private String altCityName;
    private String fullName;
    private String tricode;
    private String nickname;
    private String urlName;
    private String teamId;
    private String confName;
    private String divName;

    public Boolean getIsNBAFranchise() {
        return isNBAFranchise;
    }

    public void setIsNBAFranchise(Boolean isNBAFranchise) {
        this.isNBAFranchise = isNBAFranchise;
    }

    public Boolean getIsAllStar() {
        return isAllStar;
    }

    public void setIsAllStar(Boolean isAllStar) {
        this.isAllStar = isAllStar;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAltCityName() {
        return altCityName;
    }

    public void setAltCityName(String altCityName) {
        this.altCityName = altCityName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTricode() {
        return tricode;
    }

    public void setTricode(String tricode) {
        this.tricode = tricode;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUrlName() {
        return urlName;
    }

    public void setUrlName(String urlName) {
        this.urlName = urlName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((altCityName == null) ? 0 : altCityName.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((confName == null) ? 0 : confName.hashCode());
        result = prime * result + ((divName == null) ? 0 : divName.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((isAllStar == null) ? 0 : isAllStar.hashCode());
        result = prime * result + ((isNBAFranchise == null) ? 0 : isNBAFranchise.hashCode());
        result = prime * result + ((nickname == null) ? 0 : nickname.hashCode());
        result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
        result = prime * result + ((tricode == null) ? 0 : tricode.hashCode());
        result = prime * result + ((urlName == null) ? 0 : urlName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Team other = (Team) obj;
        if (altCityName == null) {
            if (other.altCityName != null) {
                return false;
            }
        } else if (!altCityName.equals(other.altCityName)) {
            return false;
        }
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }
        if (confName == null) {
            if (other.confName != null) {
                return false;
            }
        } else if (!confName.equals(other.confName)) {
            return false;
        }
        if (divName == null) {
            if (other.divName != null) {
                return false;
            }
        } else if (!divName.equals(other.divName)) {
            return false;
        }
        if (fullName == null) {
            if (other.fullName != null) {
                return false;
            }
        } else if (!fullName.equals(other.fullName)) {
            return false;
        }
        if (isAllStar == null) {
            if (other.isAllStar != null) {
                return false;
            }
        } else if (!isAllStar.equals(other.isAllStar)) {
            return false;
        }
        if (isNBAFranchise == null) {
            if (other.isNBAFranchise != null) {
                return false;
            }
        } else if (!isNBAFranchise.equals(other.isNBAFranchise)) {
            return false;
        }
        if (nickname == null) {
            if (other.nickname != null) {
                return false;
            }
        } else if (!nickname.equals(other.nickname)) {
            return false;
        }
        if (teamId == null) {
            if (other.teamId != null) {
                return false;
            }
        } else if (!teamId.equals(other.teamId)) {
            return false;
        }
        if (tricode == null) {
            if (other.tricode != null) {
                return false;
            }
        } else if (!tricode.equals(other.tricode)) {
            return false;
        }
        if (urlName == null) {
            if (other.urlName != null) {
                return false;
            }
        } else if (!urlName.equals(other.urlName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Team [isNBAFranchise=");
        builder.append(isNBAFranchise);
        builder.append(", isAllStar=");
        builder.append(isAllStar);
        builder.append(", city=");
        builder.append(city);
        builder.append(", altCityName=");
        builder.append(altCityName);
        builder.append(", fullName=");
        builder.append(fullName);
        builder.append(", tricode=");
        builder.append(tricode);
        builder.append(", nickname=");
        builder.append(nickname);
        builder.append(", urlName=");
        builder.append(urlName);
        builder.append(", teamId=");
        builder.append(teamId);
        builder.append(", confName=");
        builder.append(confName);
        builder.append(", divName=");
        builder.append(divName);
        builder.append("]");
        return builder.toString();
    }

}
