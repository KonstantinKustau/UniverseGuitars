package com.universeofguitars.game.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class SessionPref {

    private static final String PREFS_NAME = "SESSION_PREFERENCES";

    private static final String PREF_CURRENT_SCORE = "CURRENT_SCORE";
    private static final String PREF_CURRENT_LEVEL = "CURRENT_LEVEL";

    private Preferences pref;

    private GuitarsPrefHelper guitarsPrefHelper;

    public SessionPref() {
        pref = Gdx.app.getPreferences(PREFS_NAME);
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

    public void setCurrentScore(int score) {
        pref.putInteger(PREF_CURRENT_SCORE, score);
        pref.flush();
    }

    public int getCurrentScore() {
        return pref.getInteger(PREF_CURRENT_SCORE);
    }

    public void setCurrentLevel(int level) {
        pref.putInteger(PREF_CURRENT_LEVEL, level);
        pref.flush();
    }

    public int getCurrentLevel() {
        return pref.getInteger(PREF_CURRENT_LEVEL);
    }


    public void setAllFalse() {
        guitarsPrefHelper.setAllFalse();
    }

}
