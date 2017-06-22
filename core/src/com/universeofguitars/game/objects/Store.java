package com.universeofguitars.game.objects;

public class Store {

    private int mediator;
    private int guitar_capo;
    private int drum_pad;
    private int cymbals;
    private int guitar;
    private String guitarName;

    private boolean select_mediator;
    private boolean select_guitar_capo;
    private boolean select_drum_pad;
    private boolean select_cymbals;
    private boolean select_guitar;

    public Store(int mediator, int guitar_capo, int drum_pad, int cymbals, int guitar, String guitarName) {
        this.mediator = mediator;
        this.guitar_capo = guitar_capo;
        this.drum_pad = drum_pad;
        this.cymbals = cymbals;
        this.guitar = guitar;
        this.guitarName = guitarName;

        this.select_mediator = false;
        this.select_guitar_capo = false;
        this.select_drum_pad = false;
        this.select_cymbals = false;
        this.select_guitar = false;
    }

    public void setSelect_mediator(boolean select_mediator) {
        this.select_mediator = select_mediator;
    }

    public void setSelect_guitar_capo(boolean select_guitar_capo) {
        this.select_guitar_capo = select_guitar_capo;
    }

    public void setSelect_drum_pad(boolean select_drum_pad) {
        this.select_drum_pad = select_drum_pad;
    }

    public void setSelect_cymbals(boolean select_cymbals) {
        this.select_cymbals = select_cymbals;
    }

    public void setSelect_guitar(boolean select_guitar) {
        this.select_guitar = select_guitar;
    }

    public void setGuitarName(String guitarName) {
        this.guitarName = guitarName;
    }

    public boolean isSelect_mediator() {
        return select_mediator;
    }

    public boolean isSelect_guitar_capo() {
        return select_guitar_capo;
    }

    public boolean isSelect_drum_pad() {
        return select_drum_pad;
    }

    public boolean isSelect_cymbals() {
        return select_cymbals;
    }

    public boolean isSelect_guitar() {
        return select_guitar;
    }

    public int getMediator() {
        return mediator;
    }

    public int getGuitar_capo() {
        return guitar_capo;
    }

    public int getDrum_pad() {
        return drum_pad;
    }

    public int getCymbals() {
        return cymbals;
    }

    public int getGuitar() {
        return guitar;
    }

    public String getGuitarName() {
        return guitarName;
    }
}

//TODO selected field save in pref for than using their on next level
