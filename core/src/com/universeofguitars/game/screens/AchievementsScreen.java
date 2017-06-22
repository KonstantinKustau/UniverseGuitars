package com.universeofguitars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universeofguitars.game.MyGame;
import com.universeofguitars.game.preferences.GamePref;
import com.universeofguitars.game.utils.DrawingHelper;
import com.universeofguitars.game.utils.ListenerStage;

public class AchievementsScreen extends ScreenAdapter {

    private final MyGame game;

    private GamePref gamePref;
    private SpriteBatch spriteBatch;
    private DrawingHelper drawingHelper;
    private ListenerStage listenerKey;
    private TextureAtlas atlasElements;
    private Label maxScoreLabel;

    private String[] arraySting;

    public AchievementsScreen(MyGame myGame) {
        this.game = myGame;
        atlasElements = game.manager.get("pack.atlas", TextureAtlas.class);

        gamePref = new GamePref();
        spriteBatch = new SpriteBatch();
        drawingHelper = new DrawingHelper(spriteBatch, atlasElements, 4, 5);
        listenerKey = new ListenerStage(new ScreenViewport());

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.baseFont;

        maxScoreLabel = new Label("HIGH SCORE: " + gamePref.getHighScore(), labelStyle);

        arraySting = new String[16];
        String name;
        for (int i = 0; i < gamePref.getGuitars().length; i++) {
            name = gamePref.getGuitars()[i];
            arraySting[i] = name.substring(0, name.length() - 2) + "00";
            for (int j = 0; j < gamePref.namesOfTrue().size; j++) {
                if (name.equals(gamePref.namesOfTrue().get(j))) {
                    arraySting[i] = gamePref.getGuitars()[i];
                }
            }
        }

        Gdx.input.setInputProcessor(listenerKey);
        Gdx.input.setCatchBackKey(true);
        listenerKey.setHardKeyListener(new ListenerStage.OnHardKeyListener() {
            @Override
            public void onHardKey(int keyCode, int state) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    game.setScreen(new MainMenuScreen(game));
                    dispose();
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        drawingHelper.drawStand(0, 4);
        drawingHelper.drawStand(1, 4);
        drawingHelper.drawStand(2, 4);
        drawingHelper.drawStand(3, 4);

        drawingHelper.drawInstrument("acoustic02", 0, 3, false, 0, 4);
        drawingHelper.drawInstrument("rhythm02", 0, 2, false, 0, 4);
        drawingHelper.drawInstrument("solo02", 0, 1, false, 0, 4);
        drawingHelper.drawInstrument("bass02", 0, 0, false, 0, 4);

        drawingHelper.drawInstrument(arraySting[0], 1, 3, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[1], 1, 2, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[2], 1, 1, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[3], 1, 0, false, 0, 4);

        drawingHelper.drawInstrument(arraySting[4], 2, 3, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[5], 2, 2, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[6], 2, 1, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[7], 2, 0, false, 0, 4);

        drawingHelper.drawInstrument(arraySting[8], 3, 3, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[9], 3, 2, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[10], 3, 1, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[11], 3, 0, false, 0, 4);

        drawingHelper.drawInstrument(arraySting[12], 4, 3, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[13], 4, 2, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[14], 4, 1, false, 0, 4);
        drawingHelper.drawInstrument(arraySting[15], 4, 0, false, 0, 4);

        drawingHelper.drawText(game.baseFont, maxScoreLabel, 1, 98);

        spriteBatch.end();
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        listenerKey.dispose();

        game.dispose();
    }
}

