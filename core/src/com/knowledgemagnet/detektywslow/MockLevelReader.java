package com.knowledgemagnet.detektywslow;

import com.knowledgemagnet.detektywslow.game_objects.BrickInArray;

/**
 * Created by Igor on 09.01.2019.
 */

public class MockLevelReader implements ILeverReader {

    char[][]board;
    @Override
    public char[][] getBoardLevel(int lvl) {
        char[][]board=new char[10][10];
        char c1='1';
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                char c=c1++;
                board[j][i]=c;
            }
        }        //return new char[0][];
        return board;
    }
}