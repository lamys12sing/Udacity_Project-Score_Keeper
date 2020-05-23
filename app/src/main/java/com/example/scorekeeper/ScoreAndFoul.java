package com.example.scorekeeper;

import android.widget.TextView;

public class ScoreAndFoul {
    private int score = 0;
    private int foul = 0;

    /**
     * Increase 1 to score
     */
    public void increaseScore(){
        score++;
    }

    /**
     * Decrease 1 to score
     */
    public void decreaseScore(){
        score--;
    }

    /**
     * Increase 1 to foul
     */
    public void increaseFoul(){
        foul++;
    }

    /**
     * Decrease 1 to foul
     */
    public void decreaseFoul(){
        foul--;
    }

    /**
     * Display scorce for the team.
     * @param displayView is the view for displaying scorce.
     */
    public void displayScorce(TextView displayView){
        displayView.setText(String.valueOf(score));
    }

    /**
     * Display foul for the team.
     * @param displayView is the view for displaying foul.
     */
    public void displayFoul(TextView displayView){
        displayView.setText(String.valueOf(foul));
    }

    /**
     * Reset both scorce and foul for the team.
     */
    public void reset(){
        score = 0;
        foul = 0;
    }
}
