package com.example.gausesum;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.Serializable;

public class QuestionActivity3 extends AppCompatActivity implements View.OnClickListener{

    private TextView _preNum1,_preNum2,_preNum3,_preNum4,_preNum5;
    private TextView _preHints;

    private TextView _in1,_in2,_in3,_in4;
    private TextView _ques;

    private TextView _next;

    private StoreValue storeValue;

    private int inputNumber3;

    Animation keyAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_question3);

        //init prediction
        _preNum1 = findViewById(R.id.tvPreNum1);
        _preNum2 = findViewById(R.id.tvPreNum2);
        _preNum3 = findViewById(R.id.tvPreNum3);
        _preNum4 = findViewById(R.id.tvPreNum4);
        _preNum5 = findViewById(R.id.tvPreNum5);


        //preHints
        _preHints = findViewById(R.id.tvPreNumShowHints);

        //init next
        _next = findViewById(R.id.keyNext);

        //anim init
        keyAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);

        //init input
        _in1 = findViewById(R.id.tvInput1);
        _in2 = findViewById(R.id.tvInput2);
        _in3 = findViewById(R.id.tvInput3);
        _in4 = findViewById(R.id.tvInput4);


        //init ques
        _ques = findViewById(R.id.tvQues);


        //init store value
        storeValue = (StoreValue) getIntent().getSerializableExtra("storeValue");

        //click init
        _preNum1.setOnClickListener(this);
        _preNum2.setOnClickListener(this);
        _preNum3.setOnClickListener(this);
        _preNum4.setOnClickListener(this);
        _preNum5.setOnClickListener(this);
        _next.setOnClickListener(this);



        //set question
        _ques.setText("Machine set a random number");

        //set prediction hint
        if (3 - storeValue.getResultDigitShow() == 0){
            _preHints.setText("you can show full number after finish");
        }else{
            _preHints.setText("Here your final result\nYou can see up to 3 digit");
        }



        //set if already visit some digit
        showDigitAlreadyVisit();

        //set full number
        ((TextView)findViewById(R.id.fullNumber))
                .setText(
                        String.valueOf(storeValue.getNum1()) + " + " +
                        String.valueOf(storeValue.getNum2())
                );

        //invisible keyboard
        findViewById(R.id.llKeyboard).setVisibility(View.INVISIBLE);

        //invisible back button
        findViewById(R.id.keyBackpress).setVisibility(View.INVISIBLE);

        //setRandom digit
        setRandomDigit();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.keyNext:
                _next.startAnimation(keyAnim);
                createNum();
                storeValue.setNum3(inputNumber3);
                Intent goToQuestionActivity4 = new Intent(this,QuestionActivity4.class);
                goToQuestionActivity4.putExtra("storeValue", (Serializable) storeValue);
                startActivity(goToQuestionActivity4);
                finish();
                break;

            case R.id.tvPreNum1:
                preAnsShow(_preNum1,1);
                break;

            case R.id.tvPreNum2:
                preAnsShow(_preNum2,2);
                break;

            case R.id.tvPreNum3:
                preAnsShow(_preNum3,3);
                break;

            case R.id.tvPreNum4:
                preAnsShow(_preNum4,4);
                break;

            case R.id.tvPreNum5:
                preAnsShow(_preNum5,5);
                break;

        }
    }

    private void preAnsShow(TextView textView, int digitNo) {
        if (storeValue.getResultDigitShow() < 3){

            //change tv bg and font color
            visitColorChange(textView);

            //set value in textView
            textView.setText(String.valueOf(storeValue.preDigit(storeValue.getPredictNum(),digitNo)));

            //if digit already not show then add and increase
            if (!storeValue.getViewDigit(digitNo)) {
                //save which position is visit
                storeValue.setViewDigit(digitNo);

                //increase digit view
                storeValue.setResultDigitShow(storeValue.getResultDigitShow() + 1);
            }
        }
    }

    private void showDigitAlreadyVisit() {
        if (storeValue.getResultDigitShow() > 0){
            if (storeValue.getViewDigit(1)){
                visitColorChange(_preNum1);
                _preNum1.setText(String.valueOf(storeValue.preDigit(storeValue.getPredictNum(),1)));
            }

            if (storeValue.getViewDigit(2)){
                visitColorChange(_preNum2);
                _preNum2.setText(String.valueOf(storeValue.preDigit(storeValue.getPredictNum(),2)));
            }

            if (storeValue.getViewDigit(3)){
                visitColorChange(_preNum3);
                _preNum3.setText(String.valueOf(storeValue.preDigit(storeValue.getPredictNum(),3)));
            }

            if (storeValue.getViewDigit(4)){
                visitColorChange(_preNum4);
                _preNum4.setText(String.valueOf(storeValue.preDigit(storeValue.getPredictNum(),4)));
            }

            if (storeValue.getViewDigit(5)){
                visitColorChange(_preNum5);
                _preNum5.setText(String.valueOf(storeValue.preDigit(storeValue.getPredictNum(),5)));
            }
        }
    }


    private void visitColorChange(TextView textView){
        //change tv bg and font color
        textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        textView.setTextColor(Color.parseColor("#34D539"));
    }

    private  void setRandomDigit(){
        //set random value
        animatorDigitSet(_in1,9 - storeValue.preDigit(storeValue.getNum2(),1),15,100);
        animatorDigitSet(_in2,9 - storeValue.preDigit(storeValue.getNum2(),2),17,100);
        animatorDigitSet(_in3,9 - storeValue.preDigit(storeValue.getNum2(),3),19,100);
        animatorDigitSet(_in4,9 - storeValue.preDigit(storeValue.getNum2(),4),21,100);
    }

    private void animatorDigitSet(final TextView textView,int end, int repeat, int time){
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, end);
        valueAnimator.setDuration(time);

        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                textView.setText(valueAnimator.getAnimatedValue().toString());

            }
        });
        valueAnimator.start();
        valueAnimator.setRepeatCount(repeat);
    }


    private void createNum(){
        inputNumber3 = Integer.parseInt(_in1.getText().toString());
        inputNumber3 = (inputNumber3 * 10 ) + Integer.parseInt(_in2.getText().toString());
        inputNumber3 = (inputNumber3 * 10 ) + Integer.parseInt(_in3.getText().toString());
        inputNumber3 = (inputNumber3 * 10 ) + Integer.parseInt(_in4.getText().toString());
    }
}
