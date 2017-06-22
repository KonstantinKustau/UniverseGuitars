package com.universeofguitars.game.objects;

import com.badlogic.gdx.utils.Array;

public class Level {

    private String background;
    private String music;
    private int goalScore;
    private float endTimeLevel;
    private Array<Element> elementsArray;
    private Store store;

    public Level(String background, String music, int goalScore, float endTimeLevel,
                 Array<Element> elementsArray, Store store) {
        this.background = background;
        this.music = music;
        this.goalScore = goalScore;
        this.endTimeLevel = endTimeLevel;
        this.elementsArray = elementsArray;
        this.store = store;
    }

    public String getBackground() {
        return background;
    }

    public String getMusic() {
        return music;
    }

    public int getGoalScore() {
        return goalScore;
    }

    public float getEndTimeLevel() {
        return endTimeLevel;
    }

    public Array<Element> getElementsArray() {
        return elementsArray;
    }

    public Store getStore() {
        return store;
    }
}
