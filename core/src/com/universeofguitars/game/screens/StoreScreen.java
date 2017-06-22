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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.universeofguitars.game.MyGame;
import com.universeofguitars.game.preferences.SessionPref;
import com.universeofguitars.game.utils.DrawingHelper;
import com.universeofguitars.game.preferences.GamePref;
import com.universeofguitars.game.utils.JSONParse;
import com.universeofguitars.game.utils.ListenerStage;

public class StoreScreen extends ScreenAdapter {

    private final MyGame game;

    private SessionPref sessionPref;
    private SpriteBatch spriteBatch;
    private DrawingHelper drawingHelper;
    private ListenerStage listenerKey;
    private Animation animation;
    private TextureRegion currentFrame;
    private JSONParse jsonParsel;
    private TextureAtlas atlasBackground;
    private TextureAtlas atlasElements;
    private Label moneyLabel;
    private Label goalLabel;
    private Label nextLabel;
    private Label levelLabel;
    private Label clickAgainLabel;
    private Label[] costsLabel;
    private Label[] descriptionsLabel;
    private Array<Rectangle> boundsArray;
    private Rectangle boundNext;
    private String[] buyArray;
    private Boolean[] buyFirstSelectedMode;

    private float stateTime;
    private int score;

    public StoreScreen(MyGame myGame) {
        this.game = myGame;

        //TODO store input selected

        atlasBackground = game.manager.get("background0.atlas", TextureAtlas.class);
        atlasElements = game.manager.get("pack.atlas", TextureAtlas.class);

        sessionPref = new SessionPref();
        jsonParsel = new JSONParse();
        spriteBatch = new SpriteBatch();
        drawingHelper = new DrawingHelper(spriteBatch, atlasElements, 3, 5);
        listenerKey = new ListenerStage(new ScreenViewport());
        animation = new Animation(0.05f, atlasBackground.getRegions());
        stateTime = 0f;
        score = sessionPref.getCurrentScore();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.baseFont;

        if(score != 0) {
            moneyLabel = new Label("MONEY: " + score + "$", labelStyle);
        } else {
            moneyLabel = new Label("MONEY: " + score, labelStyle);
        }
        if(jsonParsel.getLevel().getGoalScore() != 0) {
            goalLabel = new Label("GOAL: " + jsonParsel.getLevel().getGoalScore() + "$", labelStyle);
        } else {
            goalLabel = new Label("GOAL: " + jsonParsel.getLevel().getGoalScore(), labelStyle);
        }

        nextLabel = new Label("NEXT", labelStyle);
        clickAgainLabel = new Label("Press again for purchase", labelStyle);
        costsLabel = new Label[]{
                new Label(jsonParsel.getLevel().getStore().getMediator() + "$", labelStyle),
                new Label(jsonParsel.getLevel().getStore().getGuitar_capo() + "$", labelStyle),
                new Label(jsonParsel.getLevel().getStore().getDrum_pad() + "$", labelStyle),
                new Label(jsonParsel.getLevel().getStore().getCymbals() + "$", labelStyle),
                new Label(jsonParsel.getLevel().getStore().getGuitar() + "$", labelStyle)
        };
        descriptionsLabel = new Label[]{
                new Label("1111111111111111111111111111\n" +
                        "1111111111111111111111111111",
                        labelStyle),
                new Label("2222222222222222222222222222\n" +
                        "2222222222222222222222222222\n" +
                        "2222222222222222222222222222",
                        labelStyle),
                new Label("3333333333333333333333333333\n" +
                        "3333333333333333333333333333\n" +
                        "3333333333333333333333333333",
                        labelStyle),
                new Label("4444444444444444444444444444\n" +
                        "4444444444444444444444444444\n" +
                        "4444444444444444444444444444",
                        labelStyle),
                new Label("55555555555555555555555555\n" +
                        "Гитара добавиться в достижения\n" +
                        "после прохождения уровня",
                        labelStyle),
        };
        labelStyle.font = game.titleFont;
        levelLabel = new Label("LEVEL " + (sessionPref.getCurrentLevel() + 1), labelStyle);

        buyArray = new String[]{"", "", "", "", ""};
        buyFirstSelectedMode = new Boolean[]{false, false, false, false, false};

        if (jsonParsel.getLevel().getStore().getMediator() != 0) {
            buyArray[0] = "mediator";
        }
        if (jsonParsel.getLevel().getStore().getGuitar_capo() != 0) {
            buyArray[1] = "guitar_capo";
        }
        if (jsonParsel.getLevel().getStore().getDrum_pad() != 0) {
            buyArray[2] = "drum_pad";
        }
        if (jsonParsel.getLevel().getStore().getCymbals() != 0) {
            buyArray[3] = "cymbals";
        }
        if (!jsonParsel.getLevel().getStore().getGuitarName().equals("")) {
            buyArray[4] = jsonParsel.getLevel().getStore().getGuitarName();
        }

        boundsArray = new Array<Rectangle>();
        for (int i = 0; i < 5; i++) {
            boundsArray.add(drawingHelper.drawInstrument(i, 1, 0.85f));
        }
        boundNext = drawingHelper.drawRectangle(82, 86, 100, 100);

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

        //TODO description and choose elements
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);

        spriteBatch.begin();

        spriteBatch.draw(
                atlasElements.findRegion("stand"),
                0,
                (float) Gdx.graphics.getHeight() / 100 * 38,
                (float) Gdx.graphics.getWidth(),
                (float) Gdx.graphics.getHeight() / drawingHelper.getGraphicsMatrixRows() / 3);

        for (int i = 0; i < 5; i++) {

            if (!buyArray[i].equals("")) {
                if (buyArray[i].equals(buyArray[1])) {
                    drawingHelper.drawInstrument(buyArray[i], i, 1, buyFirstSelectedMode[i], 0, 0, 0.9f, 1.02f);
                } else if (buyArray[i].equals(buyArray[2])) {
                    drawingHelper.drawInstrument(buyArray[i], i, 1, buyFirstSelectedMode[i], 0, 0, 1f, 1f);
                } else if (buyArray[i].equals(buyArray[3])) {
                    drawingHelper.drawInstrument(buyArray[i], i, 1, buyFirstSelectedMode[i], 0, 0, 0.8f, 1.1f);
                } else if (buyArray[i].equals(buyArray[4])) {
                    drawingHelper.drawInstrument(buyArray[i], i, 1, buyFirstSelectedMode[i], 0, 0, 1f, 1.2f);
                } else {
                    drawingHelper.drawInstrument(buyArray[i], i, 1, buyFirstSelectedMode[i], 0, 0, 0.9f, 1.02f);
                }
                drawingHelper.drawText(game.baseFont, descriptionsLabel[i], buyFirstSelectedMode[i], 80);
                drawingHelper.drawText(game.baseFont, clickAgainLabel, buyFirstSelectedMode[i], 15);
                drawingHelper.drawTextMatrix(game.baseFont, costsLabel[i], i, 1);
            }

            if (Gdx.input.justTouched()) {
                if (boundsArray.get(i).contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                    if (!buyArray[i].equals("")) {
                        Gdx.input.vibrate(20);
                    }
                    if (!buyFirstSelectedMode[i]) {
                        buyFirstSelectedMode[i] = true;
                    } else if (buyFirstSelectedMode[i]) {
                        //TODO buy sound
                        if (buyArray[i].equals("mediator")) {
                            jsonParsel.getLevel().getStore().setSelect_mediator(true);
                            score -= jsonParsel.getLevel().getStore().getMediator();
                        } else if (buyArray[i].equals("guitar_capo")) {
                            jsonParsel.getLevel().getStore().setSelect_guitar_capo(true);
                            score -= jsonParsel.getLevel().getStore().getGuitar_capo();
                        } else if (buyArray[i].equals("drum_pad")) {
                            jsonParsel.getLevel().getStore().setSelect_drum_pad(true);
                            score -= jsonParsel.getLevel().getStore().getDrum_pad();
                        } else if (buyArray[i].equals("cymbals")) {
                            jsonParsel.getLevel().getStore().setSelect_cymbals(true);
                            score -= jsonParsel.getLevel().getStore().getCymbals();
                        } else if (buyArray[i].equals(jsonParsel.getLevel().getStore().getGuitarName())) {
                            jsonParsel.getLevel().getStore().setSelect_guitar(true);
                            score -= jsonParsel.getLevel().getStore().getGuitar();
                        }
                        buyArray[i] = "";

                        if(score != 0) {
                            moneyLabel.setText("MONEY: " + score + "$");
                        } else {
                            moneyLabel.setText("MONEY: " + score);
                        }
                    }
                } else {
                    buyFirstSelectedMode[i] = false;
                }
            }
        }

        spriteBatch.draw(currentFrame,
                (float) Gdx.graphics.getWidth() / 100 * 84,
                (float) Gdx.graphics.getHeight() / 100 * 94,
                (float) Gdx.graphics.getWidth() / 6,
                (float) Gdx.graphics.getHeight() / 15);
        spriteBatch.draw(currentFrame,
                (float) Gdx.graphics.getWidth() / 100 * 84,
                (float) Gdx.graphics.getHeight() / 100 * 87,
                (float) Gdx.graphics.getWidth() / 6,
                (float) Gdx.graphics.getHeight() / 15);

        drawingHelper.drawText(game.baseFont, nextLabel, 84, 95, (float) Gdx.graphics.getWidth() / 12 - nextLabel.getWidth() / 2);
        drawingHelper.drawText(game.titleFont, levelLabel, true, 97);
        drawingHelper.drawText(game.baseFont, moneyLabel, 1, 98);
        drawingHelper.drawText(game.baseFont, goalLabel, 1, 94);

        spriteBatch.end();

        if (Gdx.input.justTouched()) {
            if (boundNext.contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                Gdx.input.vibrate(20);
                game.setScreen(new PlayScreen(game, jsonParsel, score));
                dispose();
            }
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        listenerKey.dispose();

        game.dispose();
    }
}

//TODO When not displayed product call Gdx.input.vibrate(20);
