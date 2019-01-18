package com.knowledgemagnet.detektywslow;

import java.util.List;

/**
 * Created by Igor on 09.01.2019.
 */

public interface  ILeverReader {

    char[][] getBoardLevel(int lvl);
    String getText();
    List<String>getWords();
    List<Character> getLettersToShootDown();
}
