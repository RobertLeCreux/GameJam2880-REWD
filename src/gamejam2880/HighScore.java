/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamejam2880;

/**
 *
 * @author rober
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {
    public int score;
    
    public HighScore(Player player) throws IOException{
        reader();
        // score = player.getFurthestReached() + player.getMonsterAKilled() + player.getMonsterBKilled() + player.getMonsterCKilled();
        
    }
    
    public static void reader() throws IOException {

        FileReader inputStream = null;
        FileWriter outputStream = null;

        try {
            inputStream = new FileReader("highscore.txt");
            outputStream = new FileWriter("highscore.txt");

            int c;
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }
}
