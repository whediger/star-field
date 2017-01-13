package com.HEDgearSoftWare.app;

import java.util.Random;

public class ThemeMusic implements Runnable {

  AudioFilePlayer player;
  Random rand = new Random();

  public ThemeMusic(){
    player = new AudioFilePlayer();
  }

  @Override
  public void run(){
    int songnum = rand.nextInt(18);

    if(  songnum == 0
      || songnum == 1
      || songnum == 2
      || songnum == 3
      || songnum == 4
      )
      player.play("target/classes/com/HEDgearSoftWare/app/resources/audio/sountracks/soundtrack-"+rand.nextInt(4)+".ogg");
    else
      player.play("target/classes/com/HEDgearSoftWare/app/resources/audio/sountracks/soundtrack-"+songnum+".mp3");
    run();
  }
}
