
/**
 * 
 * 
 * 
 */

import java.io.File;
import javax.sound.sampled.*;

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

    /**    
    ZAP("zap.wav"),
    PEW("pew.wav"),
    KAPLOW("kaplow.wav"),
    BANG("bang.wav"),
    ZEW("zew.wav"),
    FLAME("flame.wav");
    */
