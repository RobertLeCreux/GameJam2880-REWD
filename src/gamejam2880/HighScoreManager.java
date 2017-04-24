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
import java.io.*;
import java.util.*;

public class HighScoreManager {
    
    private ArrayList<Score> scores;
    private static final String HIGHSCORE_FILE = "scores.dat";
    
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;
    
    public HighScoreManager(Player player) throws IOException{
        scores = new ArrayList<Score>();
    }
    
    public ArrayList<Score> getScores(){
        loadScoreFile();
        sort();
        return scores;
    }
    
    public void sort(){
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }
    
    public void addScore(String name, int score){
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }
    
    public void loadScoreFile(){
        try{
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e){
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }

    }
    
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
    
    public String getHighscoreString() {
        String highscoreString = "";
	int max = 10;

        ArrayList<Score> scores;
        scores = getScores();

        int i = 0;
        int x = scores.size();
        if (x > max) {
            x = max;
        }
        while (i < x) {
            highscoreString += String.format("%1$-5s %2$3s %3$-20s %4$-30s",(i + 1) + "."," ",scores.get(i).getName(),scores.get(i).getScore() + "\n");
            //                               "%1$-9s %2$-14s %3$-7s %4$-1s", "Seat A", seatA.getSeatsSold(), "$" + seatA.getSeatPrice(), getSeatA()
            i++;
        }
        return highscoreString;
}
}
