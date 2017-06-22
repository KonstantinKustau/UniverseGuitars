package com.universeofguitars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universeofguitars.game.MyGame;
import com.universeofguitars.game.preferences.GamePref;
import com.universeofguitars.game.preferences.LevelPref;
import com.universeofguitars.game.preferences.SessionPref;
import com.universeofguitars.game.utils.DrawingHelper;
import com.universeofguitars.game.utils.JSONParse;
import com.universeofguitars.game.utils.ListenerStage;

public class MainMenuScreen extends ScreenAdapter {

    private final MyGame game;

    private GamePref gamePref;
    private SpriteBatch spriteBatch;
    private DrawingHelper drawingHelper;
    private ListenerStage listenerKey;
    private Animation animation;
    private TextureRegion currentFrame;
    private JSONParse jsonParsel;
    private TextureAtlas atlasBackground;
    private Label continueLabel;
    private Label newGameLabel;
    private Label achievementsLabel;
    private Label exitLabel;
    private Rectangle bounds0;
    private Rectangle bounds1;
    private Rectangle bounds2;

    private float stateTime;

    public MainMenuScreen(MyGame myGame) {
        this.game = myGame;

        atlasBackground = game.manager.get("background0.atlas", TextureAtlas.class);

        gamePref = new GamePref();
        spriteBatch = new SpriteBatch();
        drawingHelper = new DrawingHelper(spriteBatch);
        listenerKey = new ListenerStage(new ScreenViewport());
        animation = new Animation(0.05f, atlasBackground.getRegions());
        stateTime = 0f;

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.titleFont;
        continueLabel = new Label("CONTINUE", labelStyle);
        newGameLabel = new Label("NEW GAME", labelStyle);
        achievementsLabel = new Label("ACHIEVEMENTS", labelStyle);
        exitLabel = new Label("EXIT", labelStyle);

        bounds0 = drawingHelper.drawRectangle(58);
        bounds1 = drawingHelper.drawRectangle(43);
        bounds2 = drawingHelper.drawRectangle(28);

        Gdx.input.setInputProcessor(listenerKey);
        Gdx.input.setCatchBackKey(true);
        listenerKey.setHardKeyListener(new ListenerStage.OnHardKeyListener() {
            @Override
            public void onHardKey(int keyCode, int state) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    Gdx.app.exit();
                    System.exit(0);
                    dispose();
                }
            }
        });
    }

    private void initStartValue() {

        SessionPref sessionPref = new SessionPref();
        sessionPref.setCurrentLevel(0);
        sessionPref.setCurrentScore(0);
        sessionPref.setAllFalse();

        LevelPref levelPref = new LevelPref();
        levelPref.setAllFalse();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();


        currentFrame = animation.getKeyFrame(stateTime, true);
        //TODO не отображается анимация на престижео

        spriteBatch.begin();

        if (gamePref.getStartupType().equals("") || gamePref.getStartupType().equals(GamePref.NEW_GAME_STARTUP)) {

            drawingHelper.drawTextureTextStyle(currentFrame, 63);
            drawingHelper.drawTextureTextStyle(currentFrame, 48);
            drawingHelper.drawTextureTextStyle(currentFrame, 33);
            drawingHelper.drawTextureTextStyle(currentFrame, 18);

            drawingHelper.drawText(game.titleFont, newGameLabel, true, 66);
            drawingHelper.drawText(game.titleFont, achievementsLabel, true, 51);
            drawingHelper.drawText(game.titleFont, exitLabel, true, 36);

            spriteBatch.end();

            if (Gdx.input.justTouched()) {

                if (bounds0.contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                    Gdx.input.vibrate(20);
                    if (gamePref.getStartupType().equals("")) {
                        game.setScreen(new TrainingScreen(game));
                    } else {
                        initStartValue();
                        jsonParsel = new JSONParse();
                        game.setScreen(new PlayScreen(game, jsonParsel, 0));
                    }
                    dispose();
                }
                if (bounds1.contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                    Gdx.input.vibrate(20);
                    game.setScreen(new AchievementsScreen(game));
                    dispose();
                }
                if (bounds2.contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                    Gdx.input.vibrate(20);
                    Gdx.app.exit();
                    System.exit(0);
                    dispose();
                }

            }
        }

        if (gamePref.getStartupType().equals(GamePref.CONTINUE_STARTUP)) {

            drawingHelper.drawTextureTextStyle(currentFrame, 63);
            drawingHelper.drawTextureTextStyle(currentFrame, 48);
            drawingHelper.drawTextureTextStyle(currentFrame, 33);
            drawingHelper.drawTextureTextStyle(currentFrame, 18);

            drawingHelper.drawText(game.titleFont, continueLabel, true, 66);
            drawingHelper.drawText(game.titleFont, newGameLabel, true, 51);
            drawingHelper.drawText(game.titleFont, achievementsLabel, true, 36);

            spriteBatch.end();

            if (Gdx.input.justTouched()) {
                if (bounds0.contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                    Gdx.input.vibrate(20);
                    game.setScreen(new StoreScreen(game));
                    dispose();
                }
                if (bounds1.contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                    Gdx.input.vibrate(20);
                    gamePref.setStartupType(GamePref.NEW_GAME_STARTUP);
                    initStartValue();
                    jsonParsel = new JSONParse();
                    game.setScreen(new PlayScreen(game, jsonParsel, 0));
                    dispose();
                }
                if (bounds2.contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                    Gdx.input.vibrate(20);
                    game.setScreen(new AchievementsScreen(game));
                    dispose();
                }
            }
        }
    }

    @Override
    public void dispose() {
        //TODO atlasBackground dispose for this class and other classes
        spriteBatch.dispose();
        listenerKey.dispose();

        game.dispose();
    }
}
