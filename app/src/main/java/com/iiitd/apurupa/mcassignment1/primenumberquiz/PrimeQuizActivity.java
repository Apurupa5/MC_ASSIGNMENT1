package com.iiitd.apurupa.mcassignment1.primenumberquiz;


import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Random;

public class PrimeQuizActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mYesButton;
    private Button mNoButton;
    private Button mNextButton;
    private TextView mQuestionView;
    private TextView mScoreView;
    private int num,score=0,questioncount=0;
    private final int totalquestions=10;






    private static final String TAG = "PrimeQuizActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_quiz);
        init();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onRestoreInstance");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "Inside onSaveInstance");
    }
    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG, "Inside OnStart");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"Inside OnPause");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"Inside OnResume");

    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG, "Inside OnStop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside OnDestroy");
    }


//Method initializing all buttons and views on the screen.
    private void init()
    {
        mYesButton=(Button)findViewById(R.id.YesButton);
        mNoButton=(Button)findViewById(R.id.NoButton);
        mNextButton=(Button)findViewById(R.id.NextButton);
        mQuestionView=(TextView)findViewById(R.id.textViewer);
        mScoreView=(TextView)findViewById(R.id.ScoretextView);
        mYesButton.setOnClickListener(this);
        mNoButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        generateQuestion();
    }

    //Method to Generate a random prime number and display the question on the screen
    private void generateQuestion()
    {
        num=new Random().nextInt(1000)+1;
        questioncount+=1;
        if(questioncount==totalquestions+1) {
            Disable(mNextButton);
            Disable(mYesButton);
            Disable(mNoButton);
            DoneMessage();
        return;
        }
        String  mquestion=String.format(getString(R.string.question),num);
        mQuestionView.setText(mquestion);
        setScore(score,mScoreView);
    }

    //Method to check if a number is a Prime Number or Not.
    private int checkAnswer(int number)
    {
        for(int i=2;i<=number/2;i++) if(number%i==0) return 0;
        return 1;
    }


    // Method to Set Score on The Screen
    private void setScore(int score,TextView tview)
    {

        String text= String.format(getString(R.string.ScoreInfo),score,totalquestions);
                //tview.setText(R.string.Score+String.valueOf(score)+R.string.divSymbol+String.valueOf(totalquestions));
        tview.setText(text);
    }

    //Method to Display a score at the end of question set and start all over again
    private void DoneMessage() {

        AlertDialog.Builder done_popup = new AlertDialog.Builder(this);
        done_popup.setTitle("Completed!!!");
        done_popup.setMessage(getString(R.string.ScoreMessage)+String.valueOf(score));
        done_popup.setPositiveButton("START OVER",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                       score=0;questioncount=0;
                       Enable(mNextButton);
                       Enable(mYesButton);
                        Enable(mNoButton);
                        generateQuestion();
                    }
                });


        AlertDialog doneDialog = done_popup.create();
        doneDialog.show();
    }

    // Method for disabling buttons
    private void Disable(Button bt)
    {
        bt.setAlpha(.5f);
        bt.setClickable(false);
    }

    //Method for Enabling Buttons
    private void Enable(Button bt)
    {
        bt.setAlpha(1);
        bt.setClickable(true);
    }
    //Method Handling on click events of yes,no and next buttons.
    @Override
    public void onClick(View view) {
        int res;
        int duration=Toast.LENGTH_SHORT;
      switch(view.getId())
      {
          case R.id.YesButton:

              Disable(mNoButton);

              res=checkAnswer(num);
              if(res==1) {
                  Toast.makeText(this,R.string.sucessMessage,duration).show();
                  score+=1;}
              else Toast.makeText(this,R.string.failureMessage,duration).show();
              break;
          case R.id.NoButton:
              Disable(mYesButton);
              res=checkAnswer(num);
              if(res==0){ Toast.makeText(this,R.string.sucessMessage,duration).show();score+=1;}
              else Toast.makeText(this,R.string.failureMessage,duration).show();
              break;
          case R.id.NextButton:
              Enable(mYesButton);
              Enable(mNoButton);
              generateQuestion();
              break;
      }

    }



}
