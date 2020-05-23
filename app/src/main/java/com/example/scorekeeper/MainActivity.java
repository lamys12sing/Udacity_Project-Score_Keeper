package com.example.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView_scorceOfTeamA, textView_scorceOfTeamB, textView_foulOfTeamA, textView_foulOfTeamB, textView_showTime;
    private Button btn_increaseScorceOfTeamA, btn_decreaseScorceOfTeamA, btn_increaseScorceOfTeamB, btn_decreaseScorceOfTeamB,
            btn_increaseFoulOfTeamA, btn_decreaseFoulOfTeamA,  btn_increaseFoulOfTeamB, btn_decreaseFoulOfTeamB, btn_reset, btn_countDown, btn_resetTime;
    private EditText editText_setTime;

    private ScoreAndFoul teamA = new ScoreAndFoul();
    private ScoreAndFoul teamB = new ScoreAndFoul();
    private CountDownTimer countDownTimer;
    private boolean isCounting;
    private long timeLeftInMillisecond = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getView();
        setListener();
    }

    /**
     * Get the view from xml.
     */
    private void getView(){
        textView_scorceOfTeamA = (TextView) findViewById(R.id.textView_scorceOfTeamA);
        textView_foulOfTeamA = (TextView) findViewById(R.id.textView_foulOfTeamA);
        textView_scorceOfTeamB = (TextView) findViewById(R.id.textView_scorceOfTeamB);
        textView_foulOfTeamB = (TextView) findViewById(R.id.textView_foulOfTeamB);
        textView_showTime = (TextView) findViewById(R.id.textView_showTime);

        btn_increaseScorceOfTeamA = (Button) findViewById(R.id.btn_increaseScorceOfTeamA);
        btn_decreaseScorceOfTeamA = (Button) findViewById(R.id.btn_decreaseScorceOfTeamA);
        btn_increaseFoulOfTeamA = (Button) findViewById(R.id.btn_increaseFoulOfTeamA);
        btn_decreaseFoulOfTeamA = (Button) findViewById(R.id.btn_decreaseFoulOfTeamA);
        btn_increaseScorceOfTeamB = (Button) findViewById(R.id.btn_increaseScorceOfTeamB);
        btn_decreaseScorceOfTeamB = (Button) findViewById(R.id.btn_decreaseScorceOfTeamB);
        btn_increaseFoulOfTeamB = (Button) findViewById(R.id.btn_increaseFoulOfTeamB);
        btn_decreaseFoulOfTeamB = (Button) findViewById(R.id.btn_decreaseFoulOfTeamB);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_countDown = (Button) findViewById(R.id.btn_countDown);
        btn_resetTime = (Button) findViewById(R.id.btn_resetTime);

        editText_setTime = (EditText) findViewById(R.id.editText_setTime);
    }

    /**
     * Set the listener for each button.
     */
    private void setListener(){
        btn_increaseScorceOfTeamA.setOnClickListener(teamAListener);
        btn_decreaseScorceOfTeamA.setOnClickListener(teamAListener);
        btn_increaseFoulOfTeamA.setOnClickListener(teamAListener);
        btn_decreaseFoulOfTeamA.setOnClickListener(teamAListener);

        btn_increaseScorceOfTeamB.setOnClickListener(teamBListener);
        btn_decreaseScorceOfTeamB.setOnClickListener(teamBListener);
        btn_increaseFoulOfTeamB.setOnClickListener(teamBListener);
        btn_decreaseFoulOfTeamB.setOnClickListener(teamBListener);

        btn_reset.setOnClickListener(resetListener);

        btn_countDown.setOnClickListener(countDownListener);
        btn_resetTime.setOnClickListener(resetTimeListener);
    }

    /**
     * Increase 1 to scorce or foul for team A when the button is clicked and then display it.
     */
    private Button.OnClickListener teamAListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_increaseScorceOfTeamA:
                    teamA.increaseScore();
                    teamA.displayScorce(textView_scorceOfTeamA);
                    break;

                case R.id.btn_decreaseScorceOfTeamA:
                    teamA.decreaseScore();
                    teamA.displayScorce(textView_scorceOfTeamA);
                    break;

                case R.id.btn_increaseFoulOfTeamA:
                    teamA.increaseFoul();
                    teamA.displayFoul(textView_foulOfTeamA);
                    break;

                case R.id.btn_decreaseFoulOfTeamA:
                    teamA.decreaseFoul();
                    teamA.displayFoul(textView_foulOfTeamA);
                    break;
            }
        }
    };

    /**
     * Increase 1 to scorce or foul for team B when the button is clicked and then display it.
     */
    private Button.OnClickListener teamBListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_increaseScorceOfTeamB:
                    teamB.increaseScore();
                    teamB.displayScorce(textView_scorceOfTeamB);
                    break;

                case R.id.btn_decreaseScorceOfTeamB:
                    teamB.decreaseScore();
                    teamB.displayScorce(textView_scorceOfTeamB);
                    break;

                case R.id.btn_increaseFoulOfTeamB:
                    teamB.increaseFoul();
                    teamB.displayFoul(textView_foulOfTeamB);
                    break;

                case R.id.btn_decreaseFoulOfTeamB:
                    teamB.decreaseFoul();
                    teamB.displayFoul(textView_foulOfTeamB);
                    break;
            }
        }
    };

    /**
     * Reset the scorce and foul for both team when the button is clicked.
     */
    private Button.OnClickListener resetListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            teamA.reset();
            teamA.displayScorce(textView_scorceOfTeamA);
            teamA.displayFoul(textView_foulOfTeamA);

            teamB.reset();
            teamB.displayScorce(textView_scorceOfTeamB);
            teamB.displayFoul(textView_foulOfTeamB);
        }
    };

    /**
     * If the timer is counting down, stop the timer. Otherwise, start the timer.
     */
    private Button.OnClickListener countDownListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            if (isCounting){
                stopCount();
                btn_countDown.setText(R.string.count_down);
                btn_resetTime.setVisibility(View.GONE);
            }
            else {
                startCount();
                btn_countDown.setText(R.string.pause);
                editText_setTime.setText(""); //Set "" to editText_setTime so that the count down can continue after pause
                editText_setTime.setVisibility(View.GONE); //Make the input box disappear
                btn_resetTime.setVisibility(View.VISIBLE);
            }
        }
    };

    /**
     *  Start count down
     */
    private void startCount(){
        String inputMinutes = editText_setTime.getText().toString();
        try {
            timeLeftInMillisecond = Long.parseLong(inputMinutes) * 60000; // 1 mintue = 60,000 millisecond
        } catch (NumberFormatException e){
            Log.e("MainActivity", "error message : " + e.getMessage());
        }


        countDownTimer = new CountDownTimer(timeLeftInMillisecond, 1000) { // 1,000 is the count down interval in millisecond
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisecond = millisUntilFinished;
                displayTimeLeft();
            }

            @Override
            public void onFinish() {
                textView_showTime.setText("00:00"); // Display 00:00 when the count down is finished.
            }
        }.start();
        isCounting = true;
    }

    /**
     *  Stop count down
     */
    private void stopCount(){
        countDownTimer.cancel();
        isCounting = false;
    }

    /**
     *  Display time left for the count down.
     */
    private void displayTimeLeft(){
        int minutes = (int) timeLeftInMillisecond / 60000; // 60,000 come from 60 sec x 1,000 millisecond
        int seconds = (int) timeLeftInMillisecond % 60000 / 1000; // find the millisecond left and /1000 is converting it to second
        String timeLeft = "";

        if (minutes < 10){
            timeLeft += "0"; // add a 0 so that the minutes will not shown in one digit only
        }

        timeLeft += minutes + ":";

        if (seconds < 10){
            timeLeft += "0"; // add a 0 so that the seconds will not shown in one digit only
        }
        timeLeft += seconds;

        textView_showTime.setText(timeLeft);
    }

    /**
     *  Show the input box so that the user can input the time
     */
    private Button.OnClickListener resetTimeListener = new Button.OnClickListener(){
        @Override
        public void onClick(View v) {
            stopCount(); // Stop the count down when press button
            editText_setTime.setVisibility(View.VISIBLE); // Show the input box
            btn_countDown.setText(R.string.count_down); // Show the start count down button
            btn_resetTime.setVisibility(View.GONE); // Make reset time button disappear
        }
    };
}