package com.universeofguitars.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.universeofguitars.game.preferences.GamePref;
import com.universeofguitars.game.utils.DrawingHelper;
import com.universeofguitars.game.utils.JSONParse;
import com.universeofguitars.game.utils.ListenerStage;

public class TrainingScreen extends ScreenAdapter {

    private final MyGame game;

    //TODO despription minus score from scaling and slpitTime
    private GamePref gamePref;
    private SpriteBatch spriteBatch;
    private DrawingHelper drawingHelper;
    private ListenerStage listenerKey;
    private Animation[] animation;
    private JSONParse jsonParsel;
    private TextureRegion currentFrame;
    private TextureAtlas atlasElements;
    private TextureAtlas[] atlasBackground;
    private Array<Rectangle> boundsArray;
    private Label moneyLabel;
    private Label goalLabel;
    private Label[] descriptionsLabel;

    private int[][] elementsArray;
    private float stateTime;
    private int score;
    private int countScreens;

    public TrainingScreen(final MyGame myGame) {
        this.game = myGame;

        atlasElements = game.manager.get("pack.atlas", TextureAtlas.class);
        atlasBackground = new TextureAtlas[4];
        for (int i = 0; i < 4; i++) {
            atlasBackground[i] = game.manager.get("background" + (i + 1) + ".atlas", TextureAtlas.class);
        }

        gamePref = new GamePref();
        spriteBatch = new SpriteBatch();
        drawingHelper = new DrawingHelper(spriteBatch, atlasElements, 3, 4);
        listenerKey = new ListenerStage(new ScreenViewport());
        stateTime = 0f;
        score = 0;
        countScreens = 0;
        animation = new Animation[4];
        for (int i = 0; i < 4; i++) {
            animation[i] = new Animation(0.1f, atlasBackground[i].getRegions());
        }

        elementsArray = new int[][]{
                {2, 2},
                {0, 1},
                {2, 1},
                {2, 1},
                {1, 1},
                {0, 0}
        };
        boundsArray = new Array<Rectangle>();

        boundsArray.add(drawingHelper.drawInstrument(elementsArray[0][0], elementsArray[0][1], 1.2f));
        boundsArray.add(drawingHelper.drawInstrument(elementsArray[1][0], elementsArray[1][1], 1.2f));
        boundsArray.add(drawingHelper.drawInstrument(elementsArray[2][0], elementsArray[2][1], 1.2f));
        boundsArray.add(drawingHelper.drawInstrument(elementsArray[3][0], elementsArray[3][1], 1.2f));


        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = game.baseFont;
        moneyLabel = new Label("MONEY: " + score, labelStyle);
        goalLabel = new Label("GOAL: 500", labelStyle);
        moneyLabel.setText("MONEY: " + score);
        descriptionsLabel = new Label[]{
                new Label("1111111111111111111111111111\n" +
                        "1111111111111111111111111111\n" +
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
                new Label("Click for play game\n",
                        labelStyle)
        };

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

        stateTime += Gdx.graphics.getDeltaTime();

        spriteBatch.begin();

        drawingHelper.drawText(game.baseFont, moneyLabel, 1, 98);
        drawingHelper.drawText(game.baseFont, goalLabel, 1, 94);

        switch (countScreens) {
            case 0:
                currentFrame = animation[0].getKeyFrame(stateTime, true);
                spriteBatch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                drawingHelper.drawText(game.baseFont, descriptionsLabel[0], true, 20);
                drawingHelper.drawInstrument("acoustic00", elementsArray[0][0], elementsArray[0][1], false, 0, 4);
                if (Gdx.input.justTouched()) {
                    countScreens++;
                }
                spriteBatch.end();
                break;
            case 1:
                currentFrame = animation[0].getKeyFrame(stateTime, true);
                spriteBatch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                drawingHelper.drawText(game.baseFont, descriptionsLabel[1], true, 20);
                drawingHelper.drawInstrument("acoustic01", elementsArray[0][0], elementsArray[0][1], false, 0, 4);
                if (Gdx.input.justTouched()) {
                    if (boundsArray.get(0).contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                        score += 100;
                        countScreens++;
                    }
                }
                moneyLabel.setText("MONEY:" + score);
                spriteBatch.end();
                break;
            case 2:
                currentFrame = animation[1].getKeyFrame(stateTime, true);
                spriteBatch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                drawingHelper.drawText(game.baseFont, descriptionsLabel[2], true, 20);
                drawingHelper.drawInstrument("rhythm01", elementsArray[1][0], elementsArray[1][1], false, 0, 4);
                drawingHelper.drawInstrument("drum_kit1", elementsArray[2][0], elementsArray[2][1], false, 0, 4);
                if (Gdx.input.justTouched()) {
                    if (boundsArray.get(1).contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                        score += 100;
                    } else if (boundsArray.get(2).contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                        score += 100;
                    }
                }
                if (score == 300) {
                    countScreens++;
                }
                moneyLabel.setText("MONEY:" + score);
                spriteBatch.end();
                break;
            case 3:
                currentFrame = animation[2].getKeyFrame(stateTime, true);
                spriteBatch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                drawingHelper.drawText(game.baseFont, descriptionsLabel[3], true, 20);
                //TODO star
                drawingHelper.drawInstrument("drum_kit1", elementsArray[3][0], elementsArray[3][1], false, 0, 4);

                drawingHelper.drawInstrument("rhythm01", elementsArray[4][0], elementsArray[4][1], false, 0, 4);
                drawingHelper.drawInstrument("bass01", elementsArray[5][0], elementsArray[5][1], false, 0, 4);
                if (Gdx.input.justTouched()) {
                    if (boundsArray.get(3).contains(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Gdx.graphics.getHeight()))) {
                        score += 200;
                        countScreens++;
                    }
                }
                moneyLabel.setText("MONEY:" + score);
                spriteBatch.end();
                break;
            case 4:
                currentFrame = animation[3].getKeyFrame(stateTime, true);
                spriteBatch.draw(currentFrame, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                drawingHelper.drawText(game.baseFont, descriptionsLabel[4], true, 20);
                //TODO animation
                if (Gdx.input.justTouched()) {
                    countScreens++;
                }
                spriteBatch.end();
                break;
            case 5:
                gamePref.setStartupType(GamePref.NEW_GAME_STARTUP);
                jsonParsel = new JSONParse();
                game.setScreen(new PlayScreen(game, jsonParsel, 0));
                countScreens++;
                spriteBatch.end();
                dispose();
                break;
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        listenerKey.dispose();

        game.dispose();
    }
}
