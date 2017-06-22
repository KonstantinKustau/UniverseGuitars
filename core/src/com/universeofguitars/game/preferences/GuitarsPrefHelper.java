package com.universeofguitars.game.preferences;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

class GuitarsPrefHelper {

    private static final String ACOUSTIC_1 = "ACOUSTIC_1";
    private static final String ACOUSTIC_2 = "ACOUSTIC_2";
    private static final String ACOUSTIC_3 = "ACOUSTIC_3";
    private static final String ACOUSTIC_4 = "ACOUSTIC_4";

    private static final String RHYTHM_1 = "RHYTHM_1";
    private static final String RHYTHM_2 = "RHYTHM_2";
    private static final String RHYTHM_3 = "RHYTHM_3";
    private static final String RHYTHM_4 = "RHYTHM_4";

    private static final String SOLO_1 = "SOLO_1";
    private static final String SOLO_2 = "SOLO_2";
    private static final String SOLO_3 = "SOLO_3";
    private static final String SOLO_4 = "SOLO_4";

    private static final String BASS_1 = "BASS_1";
    private static final String BASS_2 = "BASS_2";
    private static final String BASS_3 = "BASS_3";
    private static final String BASS_4 = "BASS_4";

    private Preferences pref;

    static String[] guitars = new String[]{
            "acoustic12", "rhythm12", "solo12", "bass12",
            "acoustic22", "rhythm22", "solo22", "bass22",
            "acoustic32", "rhythm32", "solo32", "bass32",
            "acoustic42", "rhythm42", "solo42", "bass42"
    };

    GuitarsPrefHelper(Preferences pref) {
        this.pref = pref;
    }

    void setCheckGuitars(int number) {
        switch (number) {
            case 0:
                setAcoustic1();
                break;
            case 1:
                setRhythm1();
                break;
            case 2:
                setSolo1();
                break;
            case 3:
                setBass1();
                break;
            case 4:
                setAcoustic2();
                break;
            case 5:
                setRhythm2();
                break;
            case 6:
                setSolo2();
                break;
            case 7:
                setBass2();
                break;
            case 8:
                setAcoustic3();
                break;
            case 9:
                setRhythm3();
                break;
            case 10:
                setSolo3();
                break;
            case 11:
                setBass3();
                break;
            case 12:
                setAcoustic4();
                break;
            case 13:
                setRhythm4();
                break;
            case 14:
                setSolo4();
                break;
            case 15:
                setBass4();
                break;
        }
    }

    boolean getCheckGuitars(int number) {
        switch (number) {
            case 0:
                return getAcoustic1();
            case 1:
                return getRhythm1();
            case 2:
                return getSolo1();
            case 3:
                return getBass1();
            case 4:
                return getAcoustic2();
            case 5:
                return getRhythm2();
            case 6:
                return getSolo2();
            case 7:
                return getBass2();
            case 8:
                return getAcoustic3();
            case 9:
                return getRhythm3();
            case 10:
                return getSolo3();
            case 11:
                return getBass3();
            case 12:
                return getAcoustic4();
            case 13:
                return getRhythm4();
            case 14:
                return getSolo4();
            case 15:
                return getBass4();
        }
        return false;
    }

    void setAllFalse() {
        pref.putBoolean(ACOUSTIC_1, false);
        pref.flush();
        pref.putBoolean(ACOUSTIC_2, false);
        pref.flush();
        pref.putBoolean(ACOUSTIC_3, false);
        pref.flush();
        pref.putBoolean(ACOUSTIC_4, false);
        pref.flush();

        pref.putBoolean(RHYTHM_1, false);
        pref.flush();
        pref.putBoolean(RHYTHM_2, false);
        pref.flush();
        pref.putBoolean(RHYTHM_3, false);
        pref.flush();
        pref.putBoolean(RHYTHM_4, false);
        pref.flush();

        pref.putBoolean(SOLO_1, false);
        pref.flush();
        pref.putBoolean(SOLO_2, false);
        pref.flush();
        pref.putBoolean(SOLO_3, false);
        pref.flush();
        pref.putBoolean(SOLO_4, false);
        pref.flush();

        pref.putBoolean(BASS_1, false);
        pref.flush();
        pref.putBoolean(BASS_2, false);
        pref.flush();
        pref.putBoolean(BASS_3, false);
        pref.flush();
        pref.putBoolean(BASS_4, false);
        pref.flush();
    }

    public int numberOfName(String guitarName) {
        int guitarNumber = -1;
        for (int i = 0; i < guitars.length; i++) {
            if (guitarName.equals(guitars[i])) {
                guitarNumber = i;
                break;
            }
        }
        return guitarNumber;
    }

    public Array<String> namesOfTrue() {
        Array<String> stringArray = new Array<String>();
        for (int i = 0; i < guitars.length; i++) {
            if (getCheckGuitars(i)) stringArray.add(guitars[i]);
        }
        return stringArray;
    }

    private void setAcoustic1() {
        pref.putBoolean(ACOUSTIC_1, true);
        pref.flush();
    }

    private void setAcoustic2() {
        pref.putBoolean(ACOUSTIC_2, true);
        pref.flush();
    }

    private void setAcoustic3() {
        pref.putBoolean(ACOUSTIC_3, true);
        pref.flush();
    }

    private void setAcoustic4() {
        pref.putBoolean(ACOUSTIC_4, true);
        pref.flush();
    }

    private void setRhythm1() {
        pref.putBoolean(RHYTHM_1, true);
        pref.flush();
    }

    private void setRhythm2() {
        pref.putBoolean(RHYTHM_2, true);
        pref.flush();
    }

    private void setRhythm3() {
        pref.putBoolean(RHYTHM_3, true);
        pref.flush();
    }

    private void setRhythm4() {
        pref.putBoolean(RHYTHM_4, true);
        pref.flush();
    }

    private void setSolo1() {
        pref.putBoolean(SOLO_1, true);
        pref.flush();
    }

    private void setSolo2() {
        pref.putBoolean(SOLO_2, true);
        pref.flush();
    }

    private void setSolo3() {
        pref.putBoolean(SOLO_3, true);
        pref.flush();
    }

    private void setSolo4() {
        pref.putBoolean(SOLO_4, true);
        pref.flush();
    }

    private void setBass1() {
        pref.putBoolean(BASS_1, true);
        pref.flush();
    }

    private void setBass2() {
        pref.putBoolean(BASS_2, true);
        pref.flush();
    }

    private void setBass3() {
        pref.putBoolean(BASS_3, true);
        pref.flush();
    }

    private void setBass4() {
        pref.putBoolean(BASS_4, true);
        pref.flush();
    }

    private boolean getAcoustic1() {
        return pref.getBoolean(ACOUSTIC_1);
    }

    private boolean getAcoustic2() {
        return pref.getBoolean(ACOUSTIC_2);
    }

    private boolean getAcoustic3() {
        return pref.getBoolean(ACOUSTIC_3);
    }

    private boolean getAcoustic4() {
        return pref.getBoolean(ACOUSTIC_4);
    }

    private boolean getRhythm1() {
        return pref.getBoolean(RHYTHM_1);
    }

    private boolean getRhythm2() {
        return pref.getBoolean(RHYTHM_2);
    }

    private boolean getRhythm3() {
        return pref.getBoolean(RHYTHM_3);
    }

    private boolean getRhythm4() {
        return pref.getBoolean(RHYTHM_4);
    }

    private boolean getSolo1() {
        return pref.getBoolean(SOLO_1);
    }

    private boolean getSolo2() {
        return pref.getBoolean(SOLO_2);
    }

    private boolean getSolo3() {
        return pref.getBoolean(SOLO_3);
    }

    private boolean getSolo4() {
        return pref.getBoolean(SOLO_4);
    }

    private boolean getBass1() {
        return pref.getBoolean(BASS_1);
    }

    private boolean getBass2() {
        return pref.getBoolean(BASS_2);
    }

    private boolean getBass3() {
        return pref.getBoolean(BASS_3);
    }

    private boolean getBass4() {
        return pref.getBoolean(BASS_4);
    }

}
