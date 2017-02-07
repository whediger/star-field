package com.HEDgearSoftWare.app;

class DamageAudio implements Runnable {

  // audio vars +===}========>
  AudioFilePlayer player;

  public DamageAudio(){
    player = new AudioFilePlayer();
  }

  public void run(){
    player.play("/Users/whediger/Documents/java/playground/bitsNpieces/star-field/target/classes/com/HEDgearSoftWare/app/resources/audio/soundFX/explosion1.ogg");
    
  }
}
