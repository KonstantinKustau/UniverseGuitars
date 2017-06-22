package com.universeofguitars.game.utils;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.StringBuilder;

public class DrawingHelper {

    private int graphicsMatrixRows;
    private int graphicsMatrixCols;

    private SpriteBatch spriteBatch;
    private TextureAtlas atlasElements;

    public DrawingHelper(SpriteBatch spriteBatch) {
        this.spriteBatch = spriteBatch;
    }

    public DrawingHelper(SpriteBatch spriteBatch, TextureAtlas atlasElements, int graphicsMatrixRows,
                         int graphicsMatrixCols) {
        this.spriteBatch = spriteBatch;
        this.atlasElements = atlasElements;
        this.graphicsMatrixRows = graphicsMatrixRows;
        this.graphicsMatrixCols = graphicsMatrixCols;
    }

    /**
     * @param imageName      image name of atlas texture
     * @param number_x       the maximum number must not be more than graphicsMatrixCols
     * @param number_y       the maximum number must not be more than graphicsMatrixRows
     * @param scaling        scaling elements
     * @param padding_bottom padding bottom in percent
     * @param padding_top    padding top in percent
     */
    public void drawInstrument(String imageName, int number_x, int number_y, boolean scaling,
                               float padding_bottom, float padding_top) {

        float percent_x = (float) 100 / graphicsMatrixCols * number_x;
        float percent_y = ((100f - padding_top - padding_bottom) / (float) graphicsMatrixRows) *
                number_y + padding_bottom;
        float size_by_width = (float) Gdx.graphics.getWidth() / graphicsMatrixCols;
        float size_by_height = (float) Gdx.graphics.getHeight() / (float) graphicsMatrixRows;
        float indent_width;

        size_by_height *= 1f - ((padding_bottom * 100f + padding_top * 100f) /
                (size_by_height * size_by_height));
        indent_width = size_by_width / 2 - size_by_height / 2;

        if (scaling) {
            spriteBatch.draw(
                    atlasElements.findRegion(imageName),
                    newPoint((float) Gdx.graphics.getWidth() / 100 * percent_x + indent_width, size_by_height, 1.2f),
                    newPoint((float) Gdx.graphics.getHeight() / 100 * percent_y, size_by_height, 1.2f),
                    newLength(size_by_height, 1.2f),
                    newLength(size_by_height, 1.2f));
        } else {

            spriteBatch.draw(
                    atlasElements.findRegion(imageName),
                    (float) Gdx.graphics.getWidth() / 100 * percent_x + indent_width,
                    (float) Gdx.graphics.getHeight() / 100 * percent_y,
                    (size_by_height),
                    (size_by_height));
        }
    }

    /**
     * @param imageName      image name of atlas texture
     * @param number_x       the maximum number must not be more than graphicsMatrixCols
     * @param number_y       the maximum number must not be more than graphicsMatrixRows
     * @param scaling        scaling elements
     * @param padding_bottom padding bottom in percent
     * @param padding_top    padding top in percent
     * @param fix_size       specified number is multiplied by default size
     * @param margin_bottom  indent from the bottom cell edge
     */
    public void drawInstrument(String imageName, int number_x, int number_y, boolean scaling,
                               float padding_bottom, float padding_top, float fix_size, float margin_bottom) {

        float percent_x = (float) 100 / graphicsMatrixCols * number_x;
        float percent_y = ((100f - padding_top - padding_bottom) / (float) graphicsMatrixRows) *
                number_y + padding_bottom;
        float size_by_width = (float) Gdx.graphics.getWidth() / graphicsMatrixCols;
        float size_by_height = (float) Gdx.graphics.getHeight() / (float) graphicsMatrixRows;
        float indent_width;

        size_by_height *= (1f - ((padding_bottom * 100f + padding_top * 100f) /
                (size_by_height * size_by_height))) * fix_size;
        indent_width = size_by_width / 2 - size_by_height / 2;

        if (scaling) {
            spriteBatch.draw(
                    atlasElements.findRegion(imageName),
                    newPoint((float) Gdx.graphics.getWidth() / 100 * percent_x + indent_width, size_by_height, 1.2f),
                    newPoint((float) Gdx.graphics.getHeight() / 100 * percent_y * margin_bottom, size_by_height, 1.2f),
                    newLength(size_by_height, 1.2f),
                    newLength(size_by_height, 1.2f));
        } else {

            spriteBatch.draw(
                    atlasElements.findRegion(imageName),
                    (float) Gdx.graphics.getWidth() / 100 * percent_x + indent_width,
                    (float) Gdx.graphics.getHeight() / 100 * percent_y * margin_bottom,
                    (size_by_height),
                    (size_by_height));
        }
    }

    public void drawStand(float number_y, float padding_top) {

        float percent_y = ((100f - padding_top) / (float) graphicsMatrixRows) * number_y;
        float size_by_height = (float) Gdx.graphics.getHeight() / (float) graphicsMatrixRows / 5f;

        size_by_height *= 1f - (padding_top * 100f) /
                (size_by_height * size_by_height);

        spriteBatch.draw(
                atlasElements.findRegion("stand"),
                0,
                (float) Gdx.graphics.getHeight() / 100 * percent_y,
                (float) Gdx.graphics.getWidth(),
                size_by_height);
    }

    /**
     * Vertical center
     **/
    public void drawTextureTextStyle(TextureRegion region, int percent_y) {

        spriteBatch.draw(region,
                (float) Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth() / 2) / 2,
                (float) Gdx.graphics.getHeight() / 100 * percent_y,
                (float) Gdx.graphics.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 6);
    }

    public void drawText(BitmapFont font, Label label, int percent_x, int percent_y) {

        font.draw(spriteBatch,
                label.getText(),
                (float) Gdx.graphics.getWidth() / 100 * percent_x,
                (float) Gdx.graphics.getHeight() / 100 * percent_y);
    }

    public void drawText(BitmapFont font, Label label, int percent_x, int percent_y, float indent_width) {

        font.draw(spriteBatch,
                label.getText(),
                (float) Gdx.graphics.getWidth() / 100 * percent_x + indent_width,
                (float) Gdx.graphics.getHeight() / 100 * percent_y);
    }

    /**
     * Vertical center
     */
    public void drawText(BitmapFont font, Label label, Boolean visible, int percent_y) {

        if (visible) font.draw(spriteBatch,
                label.getText(),
                (float) Gdx.graphics.getWidth() / 2 - label.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 100 * percent_y);
    }

    public void drawTextMatrix(BitmapFont font, Label label, int number_x, int number_y) {
        float percent_x = (float) 100 / graphicsMatrixCols * number_x;
        float percent_y = ((100f) / (float) graphicsMatrixRows) * number_y;
        float indent_width = (Gdx.graphics.getWidth() / graphicsMatrixCols) / 2 - label.getWidth() / 2;

        font.draw(spriteBatch,
                label.getText(),
                (float) Gdx.graphics.getWidth() / 100 * percent_x + indent_width,
                (float) Gdx.graphics.getHeight() / 100 * percent_y);
    }

    public Rectangle drawInstrument(int number_x, int number_y, float scale) {

        float percent_x = (float) 100 / graphicsMatrixCols * number_x;
        float percent_y = (float) 100 / graphicsMatrixRows * number_y;
        float size_by_width = (float) Gdx.graphics.getWidth() / graphicsMatrixCols;
        float size_by_height = (float) Gdx.graphics.getHeight() / graphicsMatrixRows;
        float indent_width = size_by_width / 2 - size_by_height / 2;

        return new Rectangle(
                newPoint((float) Gdx.graphics.getWidth() / 100 * percent_x + indent_width, size_by_height, 1.2f),
                newPoint((float) Gdx.graphics.getHeight() / 100 * percent_y, size_by_height, 1.2f),
                newLength(size_by_height, scale),
                newLength(size_by_height, scale));
    }

    public Rectangle drawRectangle(int percent_x, int percent_y, float width, float height, float scale) {

        return new Rectangle(
                newPoint((float) Gdx.graphics.getWidth() / 100 * percent_x, width, scale),
                newPoint((float) Gdx.graphics.getHeight() / 100 * percent_y, height, scale),
                newLength(width, scale),
                newLength(height, scale));
    }

    public Rectangle drawRectangle(int per_start_x, int per_start_y, int per_end_x, int per_end_y) {

        return new Rectangle(
                (float) Gdx.graphics.getWidth() / 100 * per_start_x,
                (float) Gdx.graphics.getHeight() / 100 * per_start_y,
                (float) Gdx.graphics.getWidth() / 100 * (per_end_x - per_start_x),
                (float) Gdx.graphics.getHeight() / 100 * (per_end_y - per_start_y));
    }

    public Rectangle drawRectangle(int percent_y) {

        return new Rectangle(
                (float) Gdx.graphics.getWidth() / 2 - (Gdx.graphics.getWidth() / 2) / 2,
                (float) Gdx.graphics.getHeight() / 100 * percent_y,
                (float) Gdx.graphics.getWidth() / 2,
                (float) Gdx.graphics.getHeight() / 8);
    }

    /**
     * Example:
     * newPoint(x, width, 5), newPoint(y, height, 5)
     */
    private float newPoint(float old_point, float old_length, float scaling) {
        return ((old_length + old_point) * (1 - scaling) + old_point * (1 + scaling)) / 2;
    }

    /**
     * Example:
     * newLength(width, 5), newLength(height, 5)
     */
    private float newLength(float old_length, float scaling) {
        return old_length * scaling;
    }

    public int getGraphicsMatrixRows() {
        return graphicsMatrixRows;
    }

    public int getGraphicsMatrixCols() {
        return graphicsMatrixCols;
    }
}
