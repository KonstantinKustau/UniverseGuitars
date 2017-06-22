package com.universeofguitars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universeofguitars.game.MyGame;
import com.universeofguitars.game.objects.Element;
import com.universeofguitars.game.preferences.GamePref;
import com.universeofguitars.game.preferences.LevelPref;
import com.universeofguitars.game.preferences.SessionPref;
import com.universeofguitars.game.utils.DrawingHelper;
import com.universeofguitars.game.utils.JSONParse;
import com.universeofguitars.game.utils.ListenerStage;

public class PlayScreen extends ScreenAdapter {

    private final MyGame game;

    private GamePref gamePref;
    private SessionPref sessionPref;
    private LevelPref levelPref;
    private SpriteBatch spriteBatch;
    private DrawingHelper drawingHelper;
    private ListenerStage listenerKey;
    private Animation animation;
    private TextureRegion currentFrame;
    private JSONParse jsonParsel;
    private Array<Element> elementsArray;
    private TextureAtlas atlasElements;
    private TextureAtlas atlasBackground;
    private Array<Rectangle> boundsArray;
    private Label moneyLabel;
    private Label goalLabel;
    private Label endGameLabel;
    private Label xLabel;
    private Music currentMusic;
    private Boolean onMusic = false;
    private Boolean endGameMode = false;
    private Boolean nextLevelMode = false;
    private Boolean endGameClick = false;

    private float currentTime = 0;
    private float stateTime;
    private float scoreCoefficient;
    private int score;

    private Array<String> trueGuitars;

    public PlayScreen(MyGame myGame, JSONParse jsonParsel, int score) {
        this.game = myGame;
        this.jsonParsel = jsonParsel;
        this.score = score;

        elementsArray = jsonParsel.getLevel().getElementsArray();

        atlasElements = game.manager.get("pack.atlas", TextureAtlas.class);
        atlasBackground = game.manager.get(jsonParsel.getLevel().getBackground(), TextureAtlas.class);
        currentMusic = game.manager.get("Music/" + jsonParsel.getLevel().getMusic(), Music.class);

        currentMusic.setLooping(true);

        gamePref = new GamePref();
        sessionPref = new SessionPref();
        levelPref = new LevelPref();
        spriteBatch = new SpriteBatch();
        drawingHelper = new DrawingHelper(spriteBatch, atlasElements, 3, 4);
        listenerKey = new ListenerStage(new ScreenViewport());
        animation = new Animation(0.1f, atlasBackground.getRegions());
        stateTime = 0f;
        scoreCoefficient = 1f;
        //TODO сделать scoreCoefficient в зависимости от инструментов и выбранных покупок.
        //TODO сделать разные эффекты и цену для купленных инструментов в зависимости от уровня.

        boundsArray = new Array<Rectangle>();
        for (int i = 0; i < elementsArray.size; i++) {
            boundsArray.add(drawingHelper.drawInstrument(
                    elementsArray.get(i).getPosition()[0],
                    elementsArray.get(i).getPosition()[1],
                    1.2f));
        }

        LabelStyle labelStyle = new Label.LabelStyle();

        labelStyle.font = game.baseFont;

        if (score != 0) {
            moneyLabel = new Label("MONEY: " + score + "$", labelStyle);
        } else {
            moneyLabel = new Label("MONEY: " + score, labelStyle);
        }
        if (jsonParsel.getLevel().getGoalScore() != 0) {
            goalLabel = new Label("GOAL: " + jsonParsel.getLevel().getGoalScore() + "$", labelStyle);
        } else {
            goalLabel = new Label("GOAL: " + jsonParsel.getLevel().getGoalScore(), labelStyle);
        }

        labelStyle.font = game.titleFont;
        endGameLabel = new Label("END GAME", labelStyle);
        labelStyle.font = game.bigFont;
        xLabel = new Label("X2", labelStyle);

        for (int i = 0; i < sessionPref.getGuitars().length; i++) {
            if (sessionPref.getCheckGuitars(i)) {
                levelPref.setCheckGuitars(i);
            }
        }
        if (jsonParsel.getLevel().getStore().isSelect_guitar()) {
            int guitarNumber = levelPref.numberOfName(jsonParsel.getLevel().getStore().getGuitarName());
            levelPref.setCheckGuitars(guitarNumber);

        }
        trueGuitars = new Array<String>();
        trueGuitars = levelPref.namesOfTrue();

        Gdx.input.setInputProcessor(listenerKey);
        Gdx.input.setCatchBackKey(true);
        listenerKey.setHardKeyListener(new ListenerStage.OnHardKeyListener() {
            @Override
            public void onHardKey(int keyCode, int state) {
                if (keyCode == Input.Keys.BACK && state == 1) {
                    if (!endGameMode) {
                        game.setScreen(new MainMenuScreen(game));
                    } else {
                        gamePref.setStartupType(GamePref.NEW_GAME_STARTUP);
                        game.setScreen(new MainMenuScreen(game));
                    }
                    levelPref.setAllFalse();
                    dispose();
                }
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        scoreCoefficient = 19f;
        currentFrame = animation.getKeyFrame(stateTime, true);

        if (!onMusic) {
            if (stateTime > 2f && stateTime < 2.1f) {
                onMusic = true;
                currentMusic.play();
            }

        }

        spriteBatch.begin();

        spriteBatch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        drawingHelper.drawText(game.baseFont, moneyLabel, 1, 98);
        drawingHelper.drawText(game.baseFont, goalLabel, 1, 94);

        for (int i = 0; i < elementsArray.size; i++) {

            String element = elementsArray.get(i).getElement();
            if (element.contains("acoustic") || element.contains("rhythm") ||
                    element.contains("solo") || element.contains("bass")) {
                element = element + "0";
            }
            //если есть трушные гитары то им присваиваются их значения
            for (int j = 0; j < trueGuitars.size; j++) {
                String str = trueGuitars.get(j);
                //если текущее название элемента включает в себя обрезанное название [пример: "acoustic"12]
                if (element.contains(str.substring(0, str.length() - 2))) {
                    element = str.substring(0, str.length() - 1);
                }
            }

            //TODO Redact level
            if (currentTime != stateTime) {
                if (Gdx.input.justTouched()) {
                    currentTime = stateTime;
                    Gdx.app.log("Redact", " time: " + stateTime);
                }
            }

            if (elementsArray.get(i).getStartTime() <= stateTime && stateTime <= elementsArray.get(i).getSplitTime()) {

                if (!nextLevelMode || !endGameClick) {
                    drawingHelper.drawInstrument(
                            element + "0",
                            elementsArray.get(i).getPosition()[0],
                            elementsArray.get(i).getPosition()[1],
                            false, 3, 2);
                }

                if (Gdx.input.justTouched()) {
                    if (boundsArray.get(i).contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                        score -= 10 * scoreCoefficient;
                        Gdx.input.vibrate(30);

                        if (score != 0) {
                            moneyLabel.setText("MONEY: " + score + "$");
                        } else {
                            moneyLabel.setText("MONEY: " + score);
                        }
                    }
                }
            } else if (elementsArray.get(i).getSplitTime() <= stateTime && stateTime <= elementsArray.get(i).getEndTime()) {

                if (element.contains("star") && elementsArray.get(i).isScaling()) {
                    scoreCoefficient *= 2;
                    drawingHelper.drawText(game.bigFont, xLabel, 90, 97);
                    spriteBatch.draw(atlasElements.findRegion(element + "2"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                }

                if (!nextLevelMode || !endGameClick) {
                    drawingHelper.drawInstrument(
                            element + "1",
                            elementsArray.get(i).getPosition()[0],
                            elementsArray.get(i).getPosition()[1],
                            elementsArray.get(i).isScaling(),
                            3, 2);
                }

                //TODO fix
                if (Gdx.input.justTouched()) {
                    if (boundsArray.get(i).contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                        if (!elementsArray.get(i).isScaling()) {
                            if (!element.contains("star")) {
                                score += 10 * scoreCoefficient;
                            }
                        } else {
                            score -= 10 * scoreCoefficient;
                            Gdx.input.vibrate(30);
                        }

                        if (score != 0) {
                            moneyLabel.setText("MONEY: " + score + "$");
                        } else {
                            moneyLabel.setText("MONEY: " + score);
                        }
                        elementsArray.get(i).setScaling(true);
                    }
                }
            } else if (stateTime >= jsonParsel.getLevel().getEndTimeLevel()) {

                //TODO nextLevelMode for others screen
                //TODO  также nextLevelMode в render использовать в для повышения производительности
                if (!nextLevelMode) {
                    nextLevelMode = true;
                    sessionPref.setCurrentScore(score);
                    if (gamePref.getHighScore() < sessionPref.getCurrentScore()) {
                        gamePref.setHighScore(sessionPref.getCurrentScore());
                    }
                    if (score >= jsonParsel.getLevel().getGoalScore()) {
                        endGameClick = true;

                        if (jsonParsel.getLevel().getStore().isSelect_guitar()) {
                            for (int j = 0; j < levelPref.getGuitars().length; j++) {
                                if (levelPref.getCheckGuitars(j)) {
                                    sessionPref.setCheckGuitars(j);
                                    gamePref.setCheckGuitars(j);
                                }
                            }
                            levelPref.setAllFalse();
                        }

                        gamePref.setStartupType(GamePref.CONTINUE_STARTUP);
                        sessionPref.setCurrentLevel(sessionPref.getCurrentLevel() + 1);
                        game.setScreen(new StoreScreen(game));
                        spriteBatch.end();
                        dispose();
                    }
                } else {
                    if (!endGameClick) {
                        endGameMode = true;
                        drawingHelper.drawText(game.titleFont, endGameLabel, true, 50);

                        if (Gdx.input.justTouched()) {
                            endGameClick = true;
                            gamePref.setStartupType(GamePref.NEW_GAME_STARTUP);
                            sessionPref.setAllFalse();
                            levelPref.setAllFalse();
                            game.setScreen(new MainMenuScreen(game));
                            spriteBatch.end();
                            dispose();
                        }
                    }
                }
            }
        }
        if (!nextLevelMode || !endGameClick) {
            spriteBatch.end();
        }
    }

    @Override
    public void dispose() {
        //TODO clear massives
        currentMusic.stop();

        spriteBatch.dispose();
        listenerKey.dispose();

        game.dispose();
    }
}
