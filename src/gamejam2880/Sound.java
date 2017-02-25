/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

import javax.sound.sampled.*;
import java.io.*;
import javax.swing.*;
/**
 *
 * @author kylechapman
 */
Sound as1 = AudioSystem.getAudioInputStream(new java.io.FileInputStream("chickenDance.wav"));
               AudioFormat af = as1.getFormat();
               Clip clip1 = AudioSystem.getClip();
               DataLine.Info info = new DataLine.Info(Clip.class, af);

               Line line1 = AudioSystem.getLine(info);

               if ( ! line1.isOpen() )
               {
                clip1.open(as1);
                clip1.loop(Clip.LOOP_CONTINUOUSLY);
                clip1.start();
               }
    /**    
    ZAP("zap.wav"),
    PEW("pew.wav"),
    KAPLOW("kaplow.wav"),
    BANG("bang.wav"),
    ZEW("zew.wav"),
    FLAME("flame.wav");
*/
  
}
