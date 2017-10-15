package com.aelment.thatj.chroma;

/**
 * Created by thatj on 2017-07-28.
 */

public class RoundData {

    //need variables + setters/getters for each button color, text color, and correct button

    int button1Color;
    int button2Color;
    int button3Color;
    int button4Color;
    int textColor;
    int textColorTrick; //whichever button matches textColor is the correct one

    public RoundData(int button1Color, int button2Color, int button3Color, int button4Color, int textColor, int textColorTrick) {
        this.button1Color = button1Color;
        this.button2Color = button2Color;
        this.button3Color = button3Color;
        this.button4Color = button4Color;
        this.textColor = textColor;
        this.textColorTrick = textColorTrick;
    }

    public int getButton1Color() {
        return button1Color;
    }

    public void setButton1Color(int button1Color) {
        this.button1Color = button1Color;
    }

    public int getButton2Color() {

        return button2Color;
    }

    public void setButton2Color(int button2Color) {
        this.button2Color = button2Color;
    }

    public int getButton3Color() {

        return button3Color;
    }

    public void setButton3Color(int button3Color) {
        this.button3Color = button3Color;
    }

    public int getButton4Color() {

        return button4Color;
    }

    public void setButton4Color(int button4Color) {
        this.button4Color = button4Color;
    }

    public int getTextColor() {

        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextColorTrick() {
        return textColorTrick;
    }

    public void setTextColorTrick(int textColorTrick) {
        this.textColorTrick = textColorTrick;
    }
}
