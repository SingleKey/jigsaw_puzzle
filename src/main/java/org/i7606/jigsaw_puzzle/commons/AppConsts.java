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
    int HOME_ICON_SIZE = 90;
    int HOME_ICON_LOCATION_X = (WINDOW_WIDTH  - HOME_ICON_SIZE) >> 1;
    int HOME_ICON_LOCATION_Y = 30;

    int HOME_BUTTON_WIDTH = 150;
    int HOME_BUTTON_HEIGHT = 37;
    int HOME_BUTTON_LOCATION_X = (WINDOW_WIDTH  - HOME_BUTTON_WIDTH) >> 1;
    int HOME_BUTTON_LOCATION_Y = 170;

    /**
     * 图片按钮大小
     */
    int IMAGE_BUTTON_SIZE = 25;

    //f3ae46
    //fe9800
}
