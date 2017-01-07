package com.HEDgearSoftWare.app;

public enum ScreenSize {
    HEIGHT(480), WIDTH(640);

    private int value;

    ScreenSize(int n){
      value = (int)(n * 1.5);
    }

    int getValue(){
      return value;
    }
}
