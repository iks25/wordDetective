package com.knowledgemagnet.detektywslow;

import com.badlogic.gdx.math.Vector2;
import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;

import java.util.List;

/**
 * Created by Igor on 26.01.2019.
 */

public interface IHelpulInformation {

    List<Vector2> getFakeBrickPositions();
    BrickInArray[][] getBricksArray();
    List<String> getNotDiscoverWords();

}
