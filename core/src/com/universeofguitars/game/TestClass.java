package com.universeofguitars.game;

import com.badlogic.gdx.Gdx;

public class TestClass {

    interface MyCallback{
        void callbackReturn();
    }

    MyCallback myCallback;

    void registerCallback(MyCallback myCallback) {
        this.myCallback = myCallback;
    }

    void doSomething() {
        Gdx.app.log("qwerty", "doSomething");

        myCallback.callbackReturn();
    }
}
