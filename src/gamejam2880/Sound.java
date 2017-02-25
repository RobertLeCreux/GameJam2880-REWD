
/**
 * 
 * 
 * 
 */

import java.io.File;
import javax.sound.sampled.*;

<<<<<<< HEAD
public class Sound {
    public static void main(String[] args) {        
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Beats.wav"));
            Clip test = AudioSystem.getClip();  

            test.open(ais);
            test.start();

            while (!test.isRunning())
                Thread.sleep(10);
            while (test.isRunning())
                Thread.sleep(10);

            test.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
=======
               Line line1 = AudioSystem.getLine(info);
=======
public Sound {
<<<<<<< HEAD
    //ZAP("zap.aif"),
    //PEW("pew.aif"),
    //KAPLOW("kaplow.aif"),
    //BANG("bang.aif"),
    //ZEW("zew.aif"),
    //FLAME("flame.aif");

>>>>>>> origin/master

    /**    
    ZAP("zap.wav"),
    PEW("pew.wav"),
    KAPLOW("kaplow.wav"),
    BANG("bang.wav"),
    ZEW("zew.wav"),
    FLAME("flame.wav");
<<<<<<< HEAD
    */
=======
<<<<<<< HEAD
*/
  
}

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
>>>>>>> origin/master
