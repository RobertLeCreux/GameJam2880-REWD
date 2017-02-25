
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import java.io.*;
import sun.audio.*;

/**
 *
 * @author kylechapman
 */
public Sound {
<<<<<<< HEAD
    //ZAP("zap.aif"),
    //PEW("pew.aif"),
    //KAPLOW("kaplow.aif"),
    //BANG("bang.aif"),
    //ZEW("zew.aif"),
    //FLAME("flame.aif");
=======

    ZAP("zap.wav"),
    PEW("pew.wav"),
    KAPLOW("kaplow.wav"),
    BANG("bang.wav"),
    ZEW("zew.wav"),
    FLAME("flame.wav");
>>>>>>> origin/master

  public static void main(String[] args) 
  throws Exception
  {
    // open the sound file as a Java input stream
    String gongFile = "";
    InputStream in = new FileInputStream(gongFile);

    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  }
}