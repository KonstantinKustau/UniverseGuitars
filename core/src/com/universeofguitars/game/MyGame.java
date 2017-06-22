
package com.universeofguitars.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.universeofguitars.game.screens.LoaderScreen;

import org.robovm.apple.uikit.UIBarButtonItem;

import java.util.HashMap;

import javax.swing.JOptionPane;

public class MyGame extends Game implements TestClass.MyCallback, TestInterface{

    private static final String FONT_CHARACTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯabcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.:;,{}\"´`\'<>";
    public BitmapFont baseFont;
    public BitmapFont titleFont;
    public BitmapFont bigFont;

    //TODO make support for other languages
    public HashMap<String, String> langStr = new HashMap();
    public AssetManager manager = new AssetManager();

    @Override
    public void create() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("russoone.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        Gdx.app.log("qwerty", "Gdx.graphics.getHeight() = " + Gdx.graphics.getHeight());
        Gdx.app.log("qwerty", "Gdx.graphics.getWidth() = " + Gdx.graphics.getWidth());
        param.size = Gdx.graphics.getHeight() / 100 * 4;
        param.color = Color.WHITE;
        param.shadowColor = Color.BLACK;
        param.shadowOffsetX = 3;
        param.shadowOffsetY = 3;
        param.characters = FONT_CHARACTERS;
        this.baseFont = generator.generateFont(param);
        param.size = Gdx.graphics.getHeight() / 100 * 7;
        this.titleFont = generator.generateFont(param);
        param.size = Gdx.graphics.getHeight() / 100 * 9;
        this.bigFont = generator.generateFont(param);
        generator.dispose();
//        this.setScreen(new LoaderScreen(this));

        TestClass testClass = new TestClass();
        testClass.registerCallback(this);
        testClass.doSomething();
        TestInterface testInterface = this;
        testInterface.doSomething();
    }

    @Override
    public void callbackReturn() {
        Gdx.app.log("qwerty", "callbackReturn");
    }

    @Override
    public void doSomething() {
        Gdx.app.log("qwerty", "TestInterface");
    }

    //TODO check all disposes
    //TODO to resolve the issue with fonts: poorly displayed and not run on older version of android
}
