package com.universeofguitars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.universeofguitars.game.MyGame;
import com.universeofguitars.game.utils.DrawingHelper;

public class LoaderScreen extends ScreenAdapter {

    private final MyGame game;

    private SpriteBatch spriteBatch;
    private DrawingHelper drawingHelper;
    private Label loadingLabel;

    public LoaderScreen(MyGame myGame) {
        this.game = myGame;

        spriteBatch = new SpriteBatch();
        drawingHelper = new DrawingHelper(spriteBatch);

        String[] musics = {
                "Men Don't Cry.mp3",
                "Light Indie Rock.mp3",
                "Alternative Day.mp3",
                "Grunge Hotel.mp3"};

        game.manager.load("pack.atlas", TextureAtlas.class);
        for (int i = 0; i < 5; i++) {
            game.manager.load("background" + i + ".atlas", TextureAtlas.class);
        }
        for (String music : musics) {
            game.manager.load("Music/" + music, Music.class);
        }

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.titleFont;
        loadingLabel = new Label("LOADING...", labelStyle);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        drawingHelper.drawText(game.titleFont, loadingLabel, true, 55);
        spriteBatch.end();

        if (game.manager.update()) {
            game.setScreen(new MainMenuScreen(this.game));
            dispose();
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();

        game.dispose();
    }
}
