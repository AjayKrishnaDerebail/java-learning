package com.hibernate;

import jakarta.persistence.Embeddable;

@Embeddable
public class NinjaClan {
    private String clanName;
    private boolean hasEyeSpeciality;
    private String eyeSpeciality;
    private String village;

    public NinjaClan () {

    }

    public NinjaClan (String clanName, boolean hasEyeSpeciality, String eyeSpeciality, String village) {
        this.clanName = clanName;
        this.hasEyeSpeciality = hasEyeSpeciality;
        this.eyeSpeciality = eyeSpeciality;
        this.village = village;
    }

    public boolean isHasEyeSpeciality () {
        return hasEyeSpeciality;
    }

    public void setHasEyeSpeciality (boolean hasEyeSpeciality) {
        this.hasEyeSpeciality = hasEyeSpeciality;
    }

    public String getEyeSpeciality () {
        return eyeSpeciality;
    }

    public void setEyeSpeciality (String eyeSpeciality) {
        this.eyeSpeciality = eyeSpeciality;
    }

    public String getVillage () {
        return village;
    }

    public void setVillage (String village) {
        this.village = village;
    }

    public String getClanName () {
        return clanName;
    }

    public void setClanName (String clanName) {
        this.clanName = clanName;
    }

}
