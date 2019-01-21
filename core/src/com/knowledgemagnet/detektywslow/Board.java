package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.knowledgemagnet.detektywslow.game_objects.Brick;
import com.knowledgemagnet.detektywslow.game_objects.BrickClickListner;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;
import com.knowledgemagnet.detektywslow.game_objects.buttons.ButtonFind;
import com.knowledgemagnet.detektywslow.game_objects.buttons.ButtonLight;
import com.knowledgemagnet.detektywslow.game_objects.buttons.ButtonShoot;

import java.util.ArrayList;
import java.util.List;

import sun.rmi.runtime.Log;


/**
 * Created by Igor on 09.01.2019.
 */

public class Board {
    char tab[][];
    List<Character> lettersToShoot;
    public BrickInArray[][] bricks;
    Stage stage;
    MyGame game;
    ButtonShoot buttonShoot;
    public boolean shootingMode;
    public int numberOfBriks = 8;
    Sound soundBadAnswer,soundGoodAnswer;

    public Board(Stage stage, MyGame game, ILeverReader level) {
        this.stage = stage;
        this.game = game;

        bricks = new BrickInArray[numberOfBriks][numberOfBriks];
        lettersToShoot = level.getLettersToShootDown();
        //todo change on real levelReader
        tab = level.getBoardLevel(1);
        soundBadAnswer=game.assetManager.get("sound/no.mp3",Sound.class);
        soundGoodAnswer=game.assetManager.get("sound/correctSound.mp3", Sound.class);


    }

    Label up, down;


    public void addBoardToStage() {

        BitmapFont bitmapFont;
        bitmapFont = game.assetManager.get("font1.ttf", BitmapFont.class);
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;
        up = new Label("up 0", labelStyle);
        down = new Label("down 0", labelStyle);
        up.setVisible(true);
        down.setY(Gdx.graphics.getHeight() - 100);
        up.setY(Gdx.graphics.getHeight() - 250);


        stage.addActor(up);
        stage.addActor(down);

        //todo add labels

        for (int i = 0; i < numberOfBriks; i++) {
            for (int j = 0; j < numberOfBriks; j++) {
                if (tab[i][j] != '0') {
                    BrickInArray b = null;
                    if (tab[i][j] == '?') {
                        if (!lettersToShoot.isEmpty()) {
                            b = new BrickInArray(lettersToShoot.get(lettersToShoot.size() - 1), game, i, j);

                            lettersToShoot.remove(lettersToShoot.size() - 1);
                        } else {//error reading

                            b = new BrickInArray('+', game, i, j);
                            lettersToShoot.remove(lettersToShoot.size() - 1);
                        }
                        b.setIsFake(true);
                    } else {
                        b = new BrickInArray(tab[i][j], game, i, j);
                    }
                    bricks[i][j] = b;

                    BrickClickListner brickClickListner = new BrickClickListner(this);
                    bricks[i][j].addListener(brickClickListner.getListener(bricks[i][j]));
                    stage.addActor(bricks[i][j]);
                } else
                    bricks[i][j] = null;
            }
        }


    }

    public void addButtonsToStage() {
        buttonShoot = ButtonShoot.createButtonShoot(this);
        stage.addActor(buttonShoot);
        ButtonLight buttonLight = ButtonLight.createButtonLigh(this);
        stage.addActor(buttonLight);
        ButtonFind buttonFind = ButtonFind.createButtonShoot(this);
        stage.addActor(buttonFind);
    }

    public MyGame getGame() {
        return game;
    }

    public void shootingModeEnd() {
        buttonShoot.finishShootingMode();
        buttonShoot.decriseNrOfShooting();
    }

    public void moveDownBricks(int x, int y) {

        for (int i = y; i < numberOfBriks - 1; i++) {
            if (bricks[x][i + 1] != null) {
                bricks[x][i] = bricks[x][i + 1];
                bricks[x][i + 1].drop();
            }
            else{
                bricks[x][i]=null;
            }
        }
    }

    public void cheakWord(ArrayList<BrickInArray> selectedBricks, String word, boolean isVertical) {

        if (isCorrectWord(word)) {
            soundGoodAnswer.play();
            for (BrickInArray b : selectedBricks) {
                //todo some efect b.wrongAnwer();
               // b.setVisible(false);
            }
            Gdx.app.log("is","isVertical   "+isVertical);
           if(!isVertical){
            for (BrickInArray brickInArray : selectedBricks) {
                brickInArray.setVisible(false);
                int x=brickInArray.getxPosition();
                int y=brickInArray.getyPosition();
                moveDownBricks(x,y);
            }
           }else{
               dropVerticalSelectedBriks(selectedBricks);
           }

           //todo chceck is it end
        } else {
            soundBadAnswer.play();
            for (BrickInArray b : selectedBricks) {
                b.wrongAnwer();
            }
        }


    }

    private void dropVerticalSelectedBriks(ArrayList<BrickInArray> selectedBricks) {
        int dropSize=selectedBricks.size();
        int highestPositionY;
        int lowestPositionY;
        int yfirst=selectedBricks.get(0).getyPosition();
        int ylast=selectedBricks.get(selectedBricks.size()-1).getyPosition();
        int x=selectedBricks.get(0).getxPosition();

        if(yfirst>ylast){
            highestPositionY=yfirst;
             lowestPositionY = ylast;
        }
        else {
            highestPositionY = ylast;
            lowestPositionY=yfirst;
        }

        for(int j=0; j<dropSize;j++){
            bricks[x][lowestPositionY+j].setVisible(false);
            bricks[x][lowestPositionY+j]=null;
        }
        for (int i = highestPositionY; i < numberOfBriks - 1; i++) {
            if (bricks[x][i + 1] != null) {
                bricks[x][i-dropSize+1] = bricks[x][i + 1];
                bricks[x][i + 1].drop(dropSize);
            }
            else{
                bricks[x][i-dropSize+1] = null;
            }
        }
    }

    private boolean isCorrectWord(String word) {
        return true;
        //todo iscorrectWord

    }
}