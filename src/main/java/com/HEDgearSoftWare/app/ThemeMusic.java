package com.HEDgearSoftWare.app;

public class ThemeMusic {

  AudioFilePlayer player;

  public ThemeMusic(){
    player = new AudioFilePlayer();
    player.play("target/classes/com/HEDgearSoftWare/app/resources/audio/sountracks/spacelifeNo14.ogg");
  }


}
