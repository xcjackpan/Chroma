package com.aelment.thatj.chroma;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by thatj on 2017-07-28.
 */

public class GameFunction {

    int color1Random = 1;
    int color2Random = 1;
    int color3Random = 1;
    int color4Random = 1;
    int color5Random = 1;
    int color6Random = 1;
    int color7Random = 1;
    int color8Random = 1;
    int color9Random = 1;
    int textButtonRandom;
    int textColorRandom;
    int textColorTrick;
    int colorText;

    public RoundData newRound(int previous1Color, int previous2Color, int previous3Color, int previous4Color) {
        //if any of the colors are the same

        textButtonRandom = (int) (Math.random() * 4 + 1);
        //decides which button will be the correct answer

        textColorRandom = (int) (Math.random() * 4 + 1);
        //decides which color the text will be

        List colorsList = new ArrayList();
        colorsList.add(1);
        colorsList.add(2);
        colorsList.add(3);
        colorsList.add(4);
        colorsList.add(5);
        colorsList.add(6);
        colorsList.add(7);
        colorsList.add(8);
        colorsList.add(9);

        while (color1Random == color2Random || color1Random == color3Random || color1Random == color4Random || color2Random == color3Random || color2Random == color4Random || color3Random == color4Random) {
            Collections.shuffle(colorsList);
            color1Random = (int) colorsList.get(0);
            color2Random = (int) colorsList.get(1);
            color3Random = (int) colorsList.get(2);
            color4Random = (int) colorsList.get(3);
        } //generates random numbers and ensures that none of them are repeats

        switch (textButtonRandom) { //generates the String of the colour in the text
            case 1:
                colorText = color1Random;
                break;
            case 2:
                colorText = color2Random;
                break;
            case 3:
                colorText = color3Random;
                break;
            case 4:
                colorText = color4Random;
                break;
        } //colorText is now a variable from 1-8 which identifies which color the string will say

        switch (textColorRandom) {
            case 1:
                textColorTrick = color1Random;
                break;
            case 2:
                textColorTrick = color2Random;
                break;
            case 3:
                textColorTrick = color3Random;
                break;
            case 4:
                textColorTrick = color4Random;
                break;
        } //textColorTrick is now a variable from 1-8 which identifies the colour of the decoy button it will colour match

        RoundData roundInfo = new RoundData(color1Random, color2Random, color3Random, color4Random, colorText, textColorTrick);
        //pass in colors for each button, which button is correct, the text, and the color of the text
        return roundInfo;
    }

    public RoundDataHard newRoundHard (int previous1ColorHard, int previous2ColorHard, int previous3ColorHard, int previous4ColorHard, int previous5ColorHard, int previous6ColorHard, int previous7ColorHard, int previous8ColorHard, int previous9ColorHard) {

        textButtonRandom = (int) (Math.random() * 9 + 1);
        //decide which of the nine buttons is going to be correct

        textColorRandom = (int) (Math.random() * 9 + 1);
        //decides which color the text will be

        //create array list
        List colorsList = new ArrayList();
        colorsList.add(1);
        colorsList.add(2);
        colorsList.add(3);
        colorsList.add(4);
        colorsList.add(5);
        colorsList.add(6);
        colorsList.add(7);
        colorsList.add(8);
        colorsList.add(9);

        while (color1Random == previous1ColorHard || color2Random == previous2ColorHard || color3Random == previous3ColorHard || color4Random == previous4ColorHard || color5Random == previous5ColorHard || color6Random == previous6ColorHard || color7Random == previous7ColorHard || color8Random == previous8ColorHard || color9Random == previous9ColorHard) {
            Collections.shuffle(colorsList);
            color1Random = (int) colorsList.get(0);
            color2Random = (int) colorsList.get(1);
            color3Random = (int) colorsList.get(2);
            color4Random = (int) colorsList.get(3);
            color5Random = (int) colorsList.get(4);
            color6Random = (int) colorsList.get(5);
            color7Random = (int) colorsList.get(6);
            color8Random = (int) colorsList.get(7);
            color9Random = (int) colorsList.get(8);
        }

        switch (textButtonRandom) { //generates the String of the colour in the text
            case 1:
                colorText = color1Random;
                break;
            case 2:
                colorText = color2Random;
                break;
            case 3:
                colorText = color3Random;
                break;
            case 4:
                colorText = color4Random;
                break;
            case 5:
                colorText = color5Random;
                break;
            case 6:
                colorText = color6Random;
                break;
            case 7:
                colorText = color7Random;
                break;
            case 8:
                colorText = color8Random;
                break;
            case 9:
                colorText = color9Random;
                break;
        } //colorText is now a variable from 1-8 which identifies which color the string will say

        switch (textColorRandom) {
            case 1:
                textColorTrick = color1Random;
                break;
            case 2:
                textColorTrick = color2Random;
                break;
            case 3:
                textColorTrick = color3Random;
                break;
            case 4:
                textColorTrick = color4Random;
                break;
            case 5:
                textColorTrick = color5Random;
                break;
            case 6:
                textColorTrick = color6Random;
                break;
            case 7:
                textColorTrick = color7Random;
                break;
            case 8:
                textColorTrick = color8Random;
                break;
            case 9:
                textColorTrick = color9Random;
                break;
        }

        RoundDataHard roundInfoHard = new RoundDataHard(color1Random, color2Random, color3Random, color4Random, color5Random, color6Random, color7Random, color8Random, color9Random, colorText, textColorTrick);
        //need to pass in 9 colors as well as text color and trick color
        return roundInfoHard;
    }

    public int randomNumberGenerator(int min, int max, int exclusion) {
        int n = (int) (Math.random() * max + min);
        if (n == exclusion && n != max) {
            n++;
        } else if (n == exclusion && n == max) {
            n = min;
        }
        return n;
    }
}
