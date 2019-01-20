package com.knowledgemagnet.detektywslow.game_objects;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * Created by Igor on 20.01.2019.
 */

    public class BreakBrickAnimation extends Image
    {
        protected Animation animation = null;
        private float stateTime = 0;

        private BreakBrickAnimation(Animation animation) {
            super((TextureRegion) animation.getKeyFrame(0));
            this.animation = animation;

        }
        public static BreakBrickAnimation createAnimation(AssetManager assetManager){
            TextureAtlas textureAtlas=assetManager.get("brickbreak.txt",TextureAtlas.class);
            Animation animation=new Animation(1f/8f,textureAtlas.getRegions());
            return new BreakBrickAnimation(animation);
        }

        @Override
        public void act(float delta)
        {
            ((TextureRegionDrawable)getDrawable()).setRegion((TextureRegion) animation.getKeyFrame(stateTime+=delta, false));
            if(animation.isAnimationFinished(stateTime-0.2f)){
                this.setVisible(false);
            }
            super.act(delta);
        }

        public void play(){
            setVisible(true);
            stateTime=0;

        }
    }

