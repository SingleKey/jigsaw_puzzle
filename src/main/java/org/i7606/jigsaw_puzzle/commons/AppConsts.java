package org.i7606.jigsaw_puzzle.commons;

/**
 * @Author SingleKey
 * @Date 2023/9/16 18:45
 */
public interface AppConsts {

    int DEFAULT_SIZE = 550;
    int WINDOW_WIDTH  = DEFAULT_SIZE;
    int WINDOW_HEIGHT = DEFAULT_SIZE;

    int BORDER_WIDTH  = DEFAULT_SIZE;
    int BORDER_HEIGHT = DEFAULT_SIZE;

    int BORDER_START_X = (AppConsts.WINDOW_WIDTH  - AppConsts.BORDER_SIZE) >> 1;
    int BORDER_START_Y = (AppConsts.WINDOW_HEIGHT - AppConsts.BORDER_SIZE) >> 1;

    /**
     * 边框大小
     */
    int BORDER_SIZE = 299;
    /**
     * 边框厚度
     */
    int BORDER_WEIGHT = 6;
    /**
     * 小正方形大小
     */
//    int CARD_SIZE = 95;
}
