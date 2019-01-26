package com.knowledgemagnet.detektywslow.game_objects.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.knowledgemagnet.detektywslow.IHelpulInformation;
import com.knowledgemagnet.detektywslow.game_objects.Brick;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;
import com.knowledgemagnet.detektywslow.game_objects.ViewFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;

/**
 * Created by Igor on 26.01.2019.
 */

class buttonFindClick implements EventListener {
    IHelpulInformation helpulInformation;
    ViewFinder viewFinder;



    public buttonFindClick(IHelpulInformation helpulInformation, ViewFinder viewFinder) {
        this.helpulInformation=helpulInformation;
        this.viewFinder=viewFinder;
    }

    @Override
    public boolean handle(Event event) {

        showBrickToShoot();

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
