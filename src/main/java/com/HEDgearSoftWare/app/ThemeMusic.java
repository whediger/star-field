package com.HEDgearSoftWare.app;

public class ThemeMusic implements Runnable {

  AudioFilePlayer player;

  public ThemeMusic(){
    player = new AudioFilePlayer();

  }

  public void run(){
    player.play("target/classes/com/HEDgearSoftWare/app/resources/audio/sountracks/spacelifeNo14.ogg");
  }


}
