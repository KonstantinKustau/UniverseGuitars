package com.universeofguitars.game.objects;

public class Element {

    private String element;
    private int[] position;

    private float startTime;
    private float splitTime;
    private float endTime;

    //Field for tracking scaling element
    private boolean scaling;

    public Element(String element, int[] position, float startTime, float splitTime, float endTime) {
        this.element = element;
        this.position = position;
        this.startTime = startTime;
        this.splitTime = splitTime;
        this.endTime = endTime;
        this.scaling = false;
    }

    public void setScaling(boolean scaling) {
        this.scaling = scaling;
    }

    public boolean isScaling() {
        return scaling;
    }

    public String getElement() {
        return element;
    }

    public int[] getPosition() {
        return position;
    }

    public float getStartTime() {
        return startTime;
    }

    public float getSplitTime() {
        return splitTime;
    }

    public float getEndTime() {
        return endTime;
    }
}
