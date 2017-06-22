package com.universeofguitars.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.universeofguitars.game.objects.Element;
import com.universeofguitars.game.objects.Level;
import com.universeofguitars.game.objects.Store;
import com.universeofguitars.game.preferences.SessionPref;

public class JSONParse {

    private Level level;

    public JSONParse() {

        SessionPref sessionPref = new SessionPref();

        JsonValue rootElements = new JsonReader().parse(Gdx.files.internal("levels/" + sessionPref.getCurrentLevel() + ".json"));
        JsonValue elementsValue = rootElements.get("elements");
        JsonValue storeValue = rootElements.get("store");

        JsonValue rootRhythm = new JsonReader().parse(Gdx.files.internal("guitars/rhythm.json"));
        JsonValue rhythmValue = rootRhythm.get("guitars");

        JsonValue rootBass = new JsonReader().parse(Gdx.files.internal("guitars/bass.json"));
        JsonValue bassValue = rootBass.get("guitars");

        JsonValue rootSolo = new JsonReader().parse(Gdx.files.internal("guitars/solo.json"));
        JsonValue soloValue = rootSolo.get("guitars");

        JsonValue rootAcoustic = new JsonReader().parse(Gdx.files.internal("guitars/acoustic.json"));
        JsonValue acousticValue = rootAcoustic.get("guitars");

//        Matrix for json
//
//        acoustic:  rhythm:  solo:    bass:
//        500:       1500:    2500:    3500:
//        5000:      7000:    9000:    11000:
//        16000:     19000:   22000:   25000:
//        30000:     35000:   40000:   45000:

        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (sessionPref.getCurrentScore() >= acousticValue.get(i).get("guitarScore").asInt()) {
                count++;
            }
            if (sessionPref.getCurrentScore() >= rhythmValue.get(i).get("guitarScore").asInt()) {
                count++;
            }
            if (sessionPref.getCurrentScore() >= soloValue.get(i).get("guitarScore").asInt()) {
                count++;
            }
            if (sessionPref.getCurrentScore() >= bassValue.get(i).get("guitarScore").asInt()) {
                count++;
            }
        }

        String guitarName = "";
        int guitarCost = 0;

//        acoustic:  rhythm:  solo:    bass:
//        500:       1500:    2500:    3500:
//        5000:      7000:    9000:    11000:
//        16000:     19000:   22000:   25000:
//        30000:     35000:   40000:   45000:

        switch (count) {
            case 0:
                guitarName = "";
                guitarCost = 0;
                break;
            case 1:
                guitarName = acousticValue.get(0).get("guitarName").asString();
                guitarCost = acousticValue.get(0).get("guitarCost").asInt();
                break;
            case 2:
                guitarName = rhythmValue.get(0).get("guitarName").asString();
                guitarCost = rhythmValue.get(0).get("guitarCost").asInt();
                break;
            case 3:
                guitarName = soloValue.get(0).get("guitarName").asString();
                guitarCost = soloValue.get(0).get("guitarCost").asInt();
                break;
            case 4:
                guitarName = bassValue.get(0).get("guitarName").asString();
                guitarCost = bassValue.get(0).get("guitarCost").asInt();
                break;
            case 5:
                guitarName = acousticValue.get(1).get("guitarName").asString();
                guitarCost = acousticValue.get(1).get("guitarCost").asInt();
                break;
            case 6:
                guitarName = rhythmValue.get(1).get("guitarName").asString();
                guitarCost = rhythmValue.get(1).get("guitarCost").asInt();
                break;
            case 7:
                guitarName = soloValue.get(1).get("guitarName").asString();
                guitarCost = soloValue.get(1).get("guitarCost").asInt();
                break;
            case 8:
                guitarName = bassValue.get(1).get("guitarName").asString();
                guitarCost = bassValue.get(1).get("guitarCost").asInt();
                break;
            case 9:
                guitarName = acousticValue.get(2).get("guitarName").asString();
                guitarCost = acousticValue.get(2).get("guitarCost").asInt();
                break;
            case 10:
                guitarName = rhythmValue.get(2).get("guitarName").asString();
                guitarCost = rhythmValue.get(2).get("guitarCost").asInt();
                break;
            case 11:
                guitarName = soloValue.get(2).get("guitarName").asString();
                guitarCost = soloValue.get(2).get("guitarCost").asInt();
                break;
            case 12:
                guitarName = bassValue.get(2).get("guitarName").asString();
                guitarCost = bassValue.get(2).get("guitarCost").asInt();
                break;
            case 13:
                guitarName = acousticValue.get(3).get("guitarName").asString();
                guitarCost = acousticValue.get(3).get("guitarCost").asInt();
                break;
            case 14:
                guitarName = rhythmValue.get(3).get("guitarName").asString();
                guitarCost = rhythmValue.get(3).get("guitarCost").asInt();
                break;
            case 15:
                guitarName = soloValue.get(3).get("guitarName").asString();
                guitarCost = soloValue.get(3).get("guitarCost").asInt();
                break;
            case 16:
                guitarName = bassValue.get(3).get("guitarName").asString();
                guitarCost = bassValue.get(3).get("guitarCost").asInt();
                break;
        }

        String[] guitarNames = sessionPref.getGuitars();
        if (!guitarName.equals("")) {

            for (int i = 0; i < guitarNames.length; i++) {
                if (sessionPref.getCheckGuitars(i)) {
                    if(guitarName.equals(sessionPref.getGuitars()[i])) {
                        guitarName = "";
                    }
                }
            }
        }

        Array<Element> elementsArray = new Array<Element>();
        for (int i = 0; i < elementsValue.size; i++) {
            elementsArray.add(new Element(
                    elementsValue.get(i).get("element").asString(),
                    elementsValue.get(i).get("position").asIntArray(),
                    elementsValue.get(i).get("startTime").asFloat() + 1f,
                    elementsValue.get(i).get("splitTime").asFloat() + 1f,
                    elementsValue.get(i).get("endTime").asFloat() + 1f
            ));
        }

        Store store = new Store(
                storeValue.get("mediator").asInt(),
                storeValue.get("guitar_capo").asInt(),
                storeValue.get("drum_pad").asInt(),
                storeValue.get("cymbals").asInt(),
                guitarCost,
                guitarName);

        level = new Level(
                rootElements.get("background").asString(),
                rootElements.get("music").asString(),
                rootElements.get("goal").asInt(),
                rootElements.get("endTimeLevel").asFloat() + 2f,
                elementsArray,
                store);
    }

    public Level getLevel() {
        return level;
    }

}
