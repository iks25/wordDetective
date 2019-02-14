package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.knowledgemagnet.detektywslow.game_objects.BrickClickListner;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;
import com.knowledgemagnet.detektywslow.game_objects.buttons.ButtonFind;
import com.knowledgemagnet.detektywslow.game_objects.buttons.ButtonLight;
import com.knowledgemagnet.detektywslow.game_objects.buttons.ButtonShoot;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Igor on 09.01.2019.
 */

public class Board implements IHelpulInformation{


    String title;
    char tab[][];
    List<Character> lettersToShoot;
    public BrickInArray[][] bricks;
    public Stage stage;
    MyGame game;
    ButtonShoot buttonShoot;
    public boolean shootingMode;
    public int numberOfBriks = 8;
    Sound soundBadAnswer,soundGoodAnswer;
    List<String> wordsToDiscover;
    List<String> nonDiscoverWords;
    List<String> discoverWord;

    public Board(Stage stage, MyGame game, ILeverReader level) {
        this.stage = stage;
        this.game = game;

        title=level.getText();
        bricks = new BrickInArray[numberOfBriks][numberOfBriks];
        lettersToShoot = level.getLettersToShootDown();

        //todo change on real levelReader

        tab = level.getBoardLevel(1);
        discoverWord=new ArrayList<>();
        wordsToDiscover=level.getWords();
        nonDiscoverWords=new ArrayList<>(wordsToDiscover.size()-1);
        for(int a=0;a<wordsToDiscover.size()-1;a++){
            nonDiscoverWords.add(wordsToDiscover.get(a));
        }

        soundBadAnswer=game.assetManager.get("sound/no.mp3",Sound.class);
        soundGoodAnswer=game.assetManager.get("sound/correctSound.mp3", Sound.class);
    }

    public Label selectedWordLabel, titleLabel;


    public void addBoardToStage() {

        BitmapFont bitmapFont;
        //bitmapFont = game.assetManager.get("font2.ttf", BitmapFont.class);
        bitmapFont = game.assetManager.get("font/balo.fnt", BitmapFont.class);
        float density=Gdx.graphics.getDensity();

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = bitmapFont;

        selectedWordLabel = new Label("selectedWordLabel 0", labelStyle);


        title=title+density;
        titleLabel = new Label(title, labelStyle);


        selectedWordLabel.setVisible(true);
        titleLabel.setY(Gdx.graphics.getHeight() - 100);
        selectedWordLabel.setY(Gdx.graphics.getHeight() - 250);

        AppearanceManager appearanceManager=new AppearanceManager();
        appearanceManager.centerHorizontally(titleLabel);


        stage.addActor(selectedWordLabel);
        stage.addActor(titleLabel);

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

//    public void moveDownBricks(int x, int y) {
//
//        for (int i = y; i < numberOfBriks - 1; i++) {
//            if (bricks[x][i + 1] != null) {
//                bricks[x][i] = bricks[x][i + 1];
//                bricks[x][i + 1].drop();
//            }
//                else {
//                bricks[x][y] = null;
//            }
//        }
//    }

    public void moveDownBricks(int x, int y) {


        for (int i = y; i < numberOfBriks - 1; i++) {
            //todo chceck egain
            if (bricks[x][i + 1] != null) {
                bricks[x][i] = bricks[x][i + 1];
                bricks[x][i + 1].drop(1);
            }
                else {
                bricks[x][i] = null;
            }
        }
        bricks[x][numberOfBriks-1]=null;
    }

    public void cheakWord(ArrayList<BrickInArray> selectedBricks, String word, boolean isVertical) {

        if (isCorrectWord(word)) {
            removeNonDiscoverWords(word);
            discoverWord.add(word);
            correctAnswerEffect(selectedBricks, isVertical);

           //todo chceck is it end
            if(isLVLend()){
                endLevel();
            }
        } else {
            soundBadAnswer.play();
            for (BrickInArray b : selectedBricks) {
                b.wrongAnwer();
            }
        }


    }

    private void removeNonDiscoverWords(String word) {
        for(String w:nonDiscoverWords){
            if(word.equals(w)){
                nonDiscoverWords.remove(w);
                return;
            }
        }
    }

    private boolean isLVLend() {
        if(wordsToDiscover.size()-1<=discoverWord.size()){
            return true;
        }
        return false;
    }

    private void endLevel() {
        //todo end level
        Gdx.app.log("level end ","END");
    }

    //todo zrob blokoade po efectach by klikac nie można było
    private void correctAnswerEffect(ArrayList<BrickInArray> selectedBricks, boolean isVertical) {
        soundGoodAnswer.play();
        for (BrickInArray b : selectedBricks) {
            //todo some efect b.wrongAnwer();
            b.clicable=false;
            //b.goodAnswerAnimation();
            b.setVisible(false);
        }
        if(!isVertical){
         for (BrickInArray brickInArray : selectedBricks) {
             brickInArray.goodAnswerAnimation();
             int x=brickInArray.getxPosition();
             int y=brickInArray.getyPosition();
             moveDownBricks(x,y);
         }
        }else{
            dropVerticalSelectedBriks(selectedBricks);
        }
    }
    int test=0;
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

        int jj=0;
        for(int j=0; j<dropSize;j++){
            bricks[x][lowestPositionY+j].setVisible(false);
            bricks[x][lowestPositionY+j]=null;
        jj=j;
        }

        int a=lowestPositionY;
        for(int k=highestPositionY;k<numberOfBriks;k++){
            if(bricks[x][k]!=null){
                bricks[x][k].drop(dropSize);
                bricks[x][k-dropSize]=bricks[x][k];
                bricks[x][k]=null;
            }
        }
    }


    private boolean isCorrectWord(String word) {
        for(int i=0;i<wordsToDiscover.size()-1;i++){
            String w=wordsToDiscover.get(i);
            if(word.equals(w)){
                return true;
            };
        }
        return false;
    }

    @Override
    public List<Vector2> getFakeBrickPositions() {
        return null;
    }

    @Override
    public BrickInArray[][] getBricksArray() {
        return bricks;
    }

    @Override
    public List<String> getNotDiscoverWords() {
        return nonDiscoverWords;
    }
    public String getTitle() {
        return title;
    }

    public List<String> getWordsToDiscover() {
        return wordsToDiscover;
    }
}


