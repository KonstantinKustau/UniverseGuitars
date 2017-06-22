package com.universeofguitars.game.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class LevelPref {

    private static final String PREFS_NAME = "LEVEL_PREFERENCES";

    private GuitarsPrefHelper guitarsPrefHelper;

    public LevelPref() {
        Preferences pref = Gdx.app.getPreferences(PREFS_NAME);
        guitarsPrefHelper = new GuitarsPrefHelper(pref);
    }

    public String[] getGuitars() {
        return GuitarsPrefHelper.guitars;
    }

    public void setCheckGuitars(int number) {
        guitarsPrefHelper.setCheckGuitars(number);
    }

    public boolean getCheckGuitars(int number) {
        return guitarsPrefHelper.getCheckGuitars(number);
    }

    public void setAllFalse() {
        guitarsPrefHelper.setAllFalse();
    }

    public int numberOfName(String guitarName) {
        return guitarsPrefHelper.numberOfName(guitarName);
    }

    public Array<String> namesOfTrue() {
        return guitarsPrefHelper.namesOfTrue();
    }
}
