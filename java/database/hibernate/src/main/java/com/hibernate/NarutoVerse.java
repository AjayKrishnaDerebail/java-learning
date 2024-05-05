package com.hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NarutoVerse {
    @Id
    private String ninjaName;
    private String ninjaAbility;
    private NinjaClan ninjaClan;

    public NarutoVerse () {
    }

    public String getNinjaName () {
        return ninjaName;
    }

    public void setNinjaName (String ninjaName) {
        this.ninjaName = ninjaName;
    }

    public String getNinjaAbility () {
        return ninjaAbility;
    }

    public void setNinjaAbility (String ninjaAbility) {
        this.ninjaAbility = ninjaAbility;
    }

    public NinjaClan getNinjaClan () {
        return ninjaClan;
    }

    public void setNinjaClan (NinjaClan ninjaClan) {
        this.ninjaClan = ninjaClan;
    }

    public NarutoVerse (String ninjaName, String ninjaAbility, NinjaClan ninjaClan) {
        this.ninjaName = ninjaName;
        this.ninjaAbility = ninjaAbility;
        this.ninjaClan = ninjaClan;
    }
}
