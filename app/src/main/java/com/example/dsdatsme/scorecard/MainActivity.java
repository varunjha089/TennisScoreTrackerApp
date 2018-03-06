package com.example.dsdatsme.scorecard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //TODO: add share button
    private static final int[][] scoreboard = {
            {},
            {R.id.player_1_set_1, R.id.player_1_set_2, R.id.player_1_set_3, R.id.player_1_set_4, R.id.player_1_set_5, R.id.player_1_final},
            {R.id.player_2_set_1, R.id.player_2_set_2, R.id.player_2_set_3, R.id.player_2_set_4, R.id.player_2_set_5, R.id.player_2_final}
    };
    private static final int score[] = {0, 15, 30, 40, 45};
    TextView player1ScoreCardTextView;
    TextView player2ScoreCardTextView;
    TextView player1CurrentScoreTextView;
    TextView player2CurrentScoreTextView;
    Button player1ScoreButton;
    Button player2ScoreButton;
    ImageButton resetButton;
    TextView currentSetTextView;
    int[] totalPoins = {0, 0};
    private int currentPlayer1Score = 0;
    private int currentPlayer2Score = 0;
    private int currentSet = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1ScoreCardTextView = (TextView) findViewById(R.id.player_1_name_scoreboard_textview);
        player2ScoreCardTextView = (TextView) findViewById(R.id.player_2_name_scoreboard_textview);
        player1CurrentScoreTextView = (TextView) findViewById(R.id.player_1_score_textview);
        player2CurrentScoreTextView = (TextView) findViewById(R.id.player_2_score_textview);
        currentSetTextView = (TextView) findViewById(R.id.current_set_textview);
        player1ScoreButton = (Button) findViewById(R.id.player_1_score_button);
        player2ScoreButton = (Button) findViewById(R.id.player_2_score_button);
        resetButton = (ImageButton) findViewById(R.id.reset_button);

        ////////////////for player 1 button

        player1ScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPlayer1Score >= 3) {
                    setPoint(1);
                } else {
                    currentPlayer1Score++;

                }

                player1CurrentScoreTextView.setText(String.valueOf(score[currentPlayer1Score]));
            }
        });
////////////////for player 2 button
        player2ScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentPlayer2Score >= 3) {
                    setPoint(2);
                } else {
                    currentPlayer2Score++;

                }
                player2CurrentScoreTextView.setText(String.valueOf(score[currentPlayer2Score]));
            }
        });
    }///////////////////////End of onCreate()

    protected void setPoint(int player) {
        currentPlayer1Score = 0;
        currentPlayer2Score = 0;


        //for scoreboard
        TextView temp = (TextView) findViewById(scoreboard[player][currentSet]);
        int currentSetPoint = Integer.parseInt(temp.getText().toString());

        temp.setText(String.valueOf(++currentSetPoint)); //give setpoint to player and display


        if (currentSetPoint >= 6) {// if set is won move to next set
            currentSet++;
            //TODO: add increment to total list view & add setnumber text update
            currentSetTextView.setText(String.valueOf(currentSet + 1));
            totalPoins[player - 1]++;
            totalUpdate();
            Toast.makeText(getApplicationContext(), "Player " + player + " wins the set", Toast.LENGTH_LONG).show();

        } else if (currentSet == 4 && currentSetPoint >= 6) { ///winner declaration
            declareWinner();

        } else
            Toast.makeText(getApplicationContext(), "Set point awarded to player " + player, Toast.LENGTH_SHORT).show();


        //for current score


    }//end of se point

    protected void declareWinner() {
        setContentView(R.layout.winner_layout);
        TextView winner = (TextView) findViewById(R.id.winner_text_view);
        int op = 0;
        if (totalPoins[0] > totalPoins[1])
            op = 1;
        else
            op = 2;

        TextView finalScorePlayer1 = (TextView) findViewById(R.id.player_1_final_optput);
        finalScorePlayer1.setText(String.valueOf(totalPoins[0]));
        TextView finalScorePlayer2 = (TextView) findViewById(R.id.player_2_final_output);
        finalScorePlayer2.setText(String.valueOf(totalPoins[1]));
        winner.setText("Hurray!   Player " + op + " has Won!");
    }

    protected void totalUpdate() {
        TextView finalScorePlayer1 = (TextView) findViewById(scoreboard[1][5]);
        finalScorePlayer1.setText(String.valueOf(totalPoins[0]));
        TextView finalScorePlayer2 = (TextView) findViewById(scoreboard[2][5]);
        finalScorePlayer2.setText(String.valueOf(totalPoins[1]));
    }

    public void shareButton(View view) {
        Intent msgShare = new Intent();

    }
    public void resetButton(View view){

    }
}
