package com.example.gausesum;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.io.Serializable;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener{

    StoreValue storeValue;
    private TextView _preNum1,_preNum2,_preNum3,_preNum4,_preNum5;

    Animation errorAnim;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_result);

        //init store value
        storeValue = (StoreValue) getIntent().getSerializableExtra("storeValue");

        //anim init
        errorAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);


        //init prediction
        _preNum1 = findViewById(R.id.tvPreNum1);
        _preNum2 = findViewById(R.id.tvPreNum2);
        _preNum3 = findViewById(R.id.tvPreNum3);
        _preNum4 = findViewById(R.id.tvPreNum4);
        _preNum5 = findViewById(R.id.tvPreNum5);

        _preNum1.setOnClickListener(this);
        _preNum2.setOnClickListener(this);
        _preNum3.setOnClickListener(this);
        _preNum4.setOnClickListener(this);
        _preNum5.setOnClickListener(this);

        //set if already visit some digit
        showDigitAlreadyVisit();

        findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storeValue.getResultDigitShow() != 5){
                    ((TextView)findViewById(R.id.tvPreNumShowHints)).startAnimation(errorAnim);
                }else {
                    startActivity(new Intent(getApplicationContext(), QuestionActivity1.class));
                    finish();
                }
            }
        });


        ((TextView)findViewById(R.id.tvNum11)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum1(),1)));
        ((TextView)findViewById(R.id.tvNum12)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum1(),2)));
        ((TextView)findViewById(R.id.tvNum13)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum1(),3)));
        ((TextView)findViewById(R.id.tvNum14)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum1(),4)));

        ((TextView)findViewById(R.id.tvNum21)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum2(),1)));
        ((TextView)findViewById(R.id.tvNum22)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum2(),2)));
        ((TextView)findViewById(R.id.tvNum23)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum2(),3)));
        ((TextView)findViewById(R.id.tvNum24)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum2(),4)));

        ((TextView)findViewById(R.id.tvNum31)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum3(),1)));
        ((TextView)findViewById(R.id.tvNum32)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum3(),2)));
        ((TextView)findViewById(R.id.tvNum33)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum3(),3)));
        ((TextView)findViewById(R.id.tvNum34)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum3(),4)));

        ((TextView)findViewById(R.id.tvNum41)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum4(),1)));
        ((TextView)findViewById(R.id.tvNum42)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum4(),2)));
        ((TextView)findViewById(R.id.tvNum43)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum4(),3)));
        ((TextView)findViewById(R.id.tvNum44)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum4(),4)));

        ((TextView)findViewById(R.id.tvNum51)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum5(),1)));
        ((TextView)findViewById(R.id.tvNum52)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum5(),2)));
        ((TextView)findViewById(R.id.tvNum53)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum5(),3)));
        ((TextView)findViewById(R.id.tvNum54)).setText(String.valueOf(storeValue.preDigit(storeValue.getNum5(),4)));

        int sum = storeValue.getNum1()+storeValue.getNum2()+storeValue.getNum3()+storeValue.getNum4()+storeValue.getNum5();

        if (storeValue.getPredictNum() != sum){
            ShowToast.shortToast(this,"Sorry! Try again");
        }


        ((TextView)findViewById(R.id.tvAns)).setText("Summation: "+String.valueOf(sum)+"\nCalculate and check");

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){

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

            if (storeValue.getResultDigitShow() == 5){
                ((TextView)findViewById(R.id.tvPreNumShowHints)).setText("");
                ShowToast.shortToast(this,"WOW! That's Correct");
            }
        }
    }

    private void visitColorChange(TextView textView){
        //change tv bg and font color
        textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        textView.setTextColor(Color.parseColor("#34D539"));
    }

}
