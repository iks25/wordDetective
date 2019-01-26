package com.knowledgemagnet.detektywslow.game_objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Igor on 26.01.2019.
 */

public class ViewFinder extends Image{

    private float orginalSize;

    public ViewFinder(AssetManager assetManager){
        super(assetManager.get("viewfinder.png", Texture.class));
        this.setVisible(false);
        orginalSize=Gdx.graphics.getWidth()/6;
        setOrginalSize();
        setOrigin(this.getWidth()/2,this.getHeight()/2);
        setDebug(true);

    }

    private void setOrginalSize() {
        this.setWidth(orginalSize);
        this.setHeight(orginalSize);
        setScale(1);
    }

    public void showGoal(float x, float y){
        this.setX(Gdx.graphics.getWidth()/2-this.getWidth()/2);
        this.setY(Gdx.graphics.getDensity()*200);
        setOrginalSize();
        this.setVisible(true);


        x=x-this.getWidth()/2;
        y=y-this.getHeight()/2;
        Action action1= getSequence(x, y);
        this.addAction(action1);



    }

    private SequenceAction getSequence(float x, float y) {

        return Actions.sequence(
                Actions.moveTo(x,y,1.5f),
                Actions.scaleTo(0.8f,0.8f,1f),
                Actions.scaleTo(1.f,1.f,0.9f),
                Actions.scaleTo(0.7f,0.7f,0.9f),
                Actions.scaleTo(0.9f,0.9f,0.9f),
                Actions.scaleTo(0.6f,0.6f,0.9f),

                new Action() {
            @Override
            public boolean act(float delta) {
                setVisible(false);

                return true;
            }
        });
    }

}
