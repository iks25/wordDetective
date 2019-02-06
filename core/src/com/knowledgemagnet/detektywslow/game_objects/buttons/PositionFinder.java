package com.knowledgemagnet.detektywslow.game_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;
import com.knowledgemagnet.detektywslow.game_objects.LetterContainer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Igor on 31.01.2019.
 */

public class PositionFinder {

    public  ArrayList<String> findWordsOnLetter(Character character, List<String> nonDiscoverWords) {
        ArrayList<String> wordOnLetter=new ArrayList<>();

        if(!nonDiscoverWords.isEmpty()&&character!=null)
        for(String text:nonDiscoverWords){
            if(character.equals(text.charAt(0))){
                wordOnLetter.add(text);
            };
        }

        return wordOnLetter;
    }

    public boolean isInUP(Character[][] bricksLetters, String potentialWord, int x, int y) {
        bricksLetters=rotateArray(bricksLetters);
        if(potentialWord.equals("MYSZ")){
            showArray(bricksLetters);
        }
        if(y+potentialWord.length()-1>=bricksLetters.length){
        return false;
        }
        int nr=0;
        for(int a=y;a<potentialWord.length()+y;a++) {
            if (bricksLetters[x][a] == null)
                return false;
            if (!bricksLetters[x][a].equals(potentialWord.charAt(nr))) {
                return false;
            }
            nr++;
        }
        return true;
    }

    public boolean isInUP1(Character[][] bricksLetters, String potentialWord, int x, int y) {

        if(bricksLetters[x][y]==null){
            return false;
        }
        if(y+(potentialWord.length()-1)>bricksLetters.length-1){
            return false;
        }
        for(int a=0;a<potentialWord.length();a++){
            if(bricksLetters[x][y+a]==null){
                return false;
            }
            if(!bricksLetters[x][y+a].equals(potentialWord.charAt(a))){
                return false;
            }
        }
        return true;
    }

    public boolean isInDown(Character[][] bricksLetters, String potentialWord, int x, int y) {

        if(bricksLetters[x][y]==null){
            return false;
        }
        if(y-(potentialWord.length()-1)<0){
            return false;
        }
        for(int a=0;a<potentialWord.length();a++){
            if(bricksLetters[x][y-a]==null){
                return false;
            }
            if(!bricksLetters[x][y-a].equals(potentialWord.charAt(a))){
                return false;
            }
        }

        return true;
    }

    public boolean isInLeft(Character[][] bricksLetters, String potentialWord, int x, int y) {

        if(bricksLetters[x][y]==null){
            return false;
        }
        if(x-(potentialWord.length()-1)<0){
            return false;
        }
        for(int a=0;a<potentialWord.length();a++){
            if(bricksLetters[x-a][y]==null){
                return false;
            }
            if(!bricksLetters[x-a][y].equals(potentialWord.charAt(a))){
                return false;
            }
        }

    return true;
    }

    public void showArray(Character[][] testBoard) {
        for (int x = 0; x < testBoard.length; x++) {
            for (int y = 0; y < testBoard.length; y++) {
                System.out.print(testBoard[x][y]+" ");
            }
            System.out.print("\n");

        }
        System.out.print("\n");

        return ;
    }
    public Character[][] boardLetterCreate( LetterContainer[][] bricks){

        int arraySize=bricks.length;
        Character[][] bricksLetters=new Character[arraySize][arraySize];

        for (int x = 0; x < bricks.length; x++) {
            for (int y = 0; y < bricks.length; y++) {
                if(bricks[x][y]!=null)
                    bricksLetters[x][y]= bricks[x][y].getLetter();

            }
        }
        return bricksLetters;
    }


    public void showArrayINGDX(Character[][] testBoard) {
        String text="";
        for (int x = 0; x < testBoard.length; x++) {
            for (int y = 0; y < testBoard.length; y++) {
                System.out.print(testBoard[x][y]+" ");
                text+=testBoard[x][y]+" ";
            }
            Gdx.app.log("--",text);
            text="";
            System.out.print("\n");

        }
        System.out.print("\n");

        return ;
    }

    public Character[][] rotateArray(Character[][] arr){
        Character[][] newArray=new Character[arr.length][arr.length];
        for(int i=0; i<arr[0].length; i++){
            for(int j=arr.length-1; j>=0; j--){
                newArray[i][j] = arr[j][i];
            }
        }
        return newArray;
    }



}
