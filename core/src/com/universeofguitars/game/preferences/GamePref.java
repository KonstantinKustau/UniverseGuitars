package com.universeofguitars.game.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

public class GamePref {

    private static final String PREFS_NAME = "GAME_PREFERENCES";

    public static final String NEW_GAME_STARTUP = "NEW_GAME_STARTUP";
    public static final String CONTINUE_STARTUP = "CONTINUE_STARTUP";

    private static final String PREF_STARTUP_TYPE = "STARTUP_TYPE";
    private static final String PREF_HIGH_SCORE = "HIGH_SCORE";

    private Preferences pref;

    private GuitarsPrefHelper guitarsPrefHelper;

    public GamePref() {
        pref = Gdx.app.getPreferences(PREFS_NAME);
        guitarsPrefHelper = new GuitarsPrefHelper(pref);
    }

    /**
     * "" - default: FIRST_STARTUP
     * NEW_GAME_STARTUP
     * CONTINUE_STARTUP
     */
    public void setStartupType(String type) {
        pref.putString(PREF_STARTUP_TYPE, type);
        pref.flush();
    }

    public void setHighScore(int score) {
        pref.putInteger(PREF_HIGH_SCORE, score);
        pref.flush();
    }

    public String getStartupType() {
        return pref.getString(PREF_STARTUP_TYPE);
    }

    public int getHighScore() {
        return pref.getInteger(PREF_HIGH_SCORE);
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

    public int numberOfName(String guitarName) {
        return guitarsPrefHelper.numberOfName(guitarName);
    }

    public Array<String> namesOfTrue() {
        return guitarsPrefHelper.namesOfTrue();
    }
}
