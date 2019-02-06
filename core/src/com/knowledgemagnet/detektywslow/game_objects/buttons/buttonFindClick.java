package com.knowledgemagnet.detektywslow.game_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.knowledgemagnet.detektywslow.IHelpulInformation;
import com.knowledgemagnet.detektywslow.game_objects.Brick;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;
import com.knowledgemagnet.detektywslow.game_objects.FindedWordPosition;
import com.knowledgemagnet.detektywslow.game_objects.ViewFinder;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

/**
 * Created by Igor on 26.01.2019.
 */

class buttonFindClick implements EventListener {
    IHelpulInformation helpulInformation;
    ViewFinder viewFinder;
    FindedWordPosition findedWordPosition;




    public buttonFindClick(IHelpulInformation helpulInformation, ViewFinder viewFinder) {
        this.helpulInformation=helpulInformation;
        this.viewFinder=viewFinder;
    }

    @Override
    public boolean handle(Event event) {

        //todo change effect add some block
        //showBrickToShoot();
        //pointOutWord();

//        BrickInArray[][] bricks=helpulInformation.getBricksArray();
//        PositionFinder positionFinder=new PositionFinder();
//        Character[][] bricksLetters=positionFinder.boardLetterCreate(bricks);
//
//      System.out.println((char)bricksLetters[2][2]+(char)bricksLetters[2][3]+(char)bricksLetters[2][4]+"-------------------");
        findedWordPosition=null;

        pointOutWord();

        if(findedWordPosition!=null){

        }



        return false;
    }

    private void pointOutWord() {

        BrickInArray[][] bricks=helpulInformation.getBricksArray();
        List<String> nonDiscoverWords=helpulInformation.getNotDiscoverWords();

        PositionFinder positionFinder=new PositionFinder();
        int numberOfBriks=bricks.length;
        Character[][] bricksLetters=positionFinder.boardLetterCreate(bricks);
//        System.out.println("     ");
//        positionFinder.showArray(positionFinder.rotateArray(bricksLetters));
//        System.out.println("     ");
     //   bricksLetters=positionFinder.rotateArray(bricksLetters);

        for (int x = 0; x < numberOfBriks; x++) {
            for (int y = 0; y < numberOfBriks; y++) {
                ArrayList<String> potentialWords=
                        positionFinder.findWordsOnLetter(bricksLetters[x][y],nonDiscoverWords);
                for(String checkedword:potentialWords){
                    //is good in up
                   if( positionFinder.isInUP(bricksLetters,checkedword,y,x)){
                       bricks[x][y].wrongAnwer();
                       int XX=x+checkedword.length()-1;
                       int yy=y;
                       bricks[XX][yy].wrongAnwer();
                       findedWordPosition=new FindedWordPosition(x,y,XX,yy);
                       System.out.println(potentialWords+"  UP");
//                       System.out.println(" up xx yy "+XX+" "+yy+" "+x+" "+y+"  word= "+checkedword);
                   return;
                   }
                    if( positionFinder.isInLeft(bricksLetters,checkedword,x,y)){
                        bricks[x][y].wrongAnwer();
                        bricks[x-(checkedword.length()-1)][y].wrongAnwer();
                        return;
                    }
                    if( positionFinder.isInUP1(bricksLetters,checkedword,x,y)){
                        bricks[x][y].wrongAnwer();
                        bricks[x][y+(checkedword.length()-1)].wrongAnwer();
                        return;
                    }
                    if(positionFinder.isInDown(bricksLetters,checkedword,x,y)){
                        bricks[x][y].wrongAnwer();
                        bricks[x][y-(checkedword.length()-1)].wrongAnwer();
                        return;
                    }


                }

            }
        }
    }



    private boolean isFirstLetterOfWors(Character character, List<String> nonDiscoverWords) {
        for(String text:nonDiscoverWords){
            if(character.equals(text.charAt(0))){
                return true;
            };

        }
        return false;
    }



    private int showBrickToShoot() {
        BrickInArray[][] brickInArrays= helpulInformation.getBricksArray();

        if(brickInArrays==null)
            return 3;//error

       if( isEmpty(brickInArrays))
                return 2;//error

            List<Vector2> fakeBricksPositions=findFakeBricksPositions(brickInArrays);
            if(fakeBricksPositions.isEmpty())
             return 0;

            Random random=new Random();
            int randomGoal=random.nextInt(fakeBricksPositions.size());
            Vector2 fakeBrickPosition=fakeBricksPositions.get(randomGoal);
            viewFinder.showGoal(fakeBrickPosition.x,fakeBrickPosition.y);
       // Gdx.app.log("random fake brick","  -- x "+fakeBrickPosition.x+"  y "+fakeBrickPosition.y);
        //    ViewFinder viewFinder=helpulInformation.getViewFinder();
            return 1;


    }



    private boolean isEmpty(BrickInArray[][] brickInArrays) {
        for(int x=0;x<brickInArrays.length;x++){
            for(int y=0;y<brickInArrays.length;y++)
            {
                if (brickInArrays[x][y]!=null)
                    return false;
            }
        }

        return true;
    }
    private List<Vector2> findFakeBricksPositions(BrickInArray[][] brickInArrays ) {
            List<Vector2> fakeBricksPositions=new ArrayList<>();
        for (int x = 0; x < brickInArrays.length; x++) {
            for (int y = 0; y < brickInArrays.length; y++) {

                if(brickInArrays[x][y]!=null)
                if(brickInArrays[x][y].isFakeBrick()){


                    float xx=brickInArrays[x][y].getX()+brickInArrays[x][y].getHeight()/2;
                    float yy=brickInArrays[x][y].getY()+brickInArrays[x][y].getWidth()/2;
                   // Gdx.app.log("fake brick ","  -- x "+brickInArrays[x][y].getxPosition()+"  y "+brickInArrays[x][y].getyPosition());

                    fakeBricksPositions.add(new Vector2(xx,yy));
                }
            }
        }
            return fakeBricksPositions;


    }



}
