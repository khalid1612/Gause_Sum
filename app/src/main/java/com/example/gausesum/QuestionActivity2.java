package com.example.gausesum;

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

public class QuestionActivity2 extends AppCompatActivity implements View.OnClickListener{

    private TextView _in1,_in2,_in3,_in4;
    private TextView _key1,_key2,_key3,_key4,_key5,_key6,_key7,_key8,_key9;
    private TextView _next;
    private TextView _ques;
    private ImageView _backPress;

    private TextView _preNum1,_preNum2,_preNum3,_preNum4,_preNum5;
    private TextView _preHints;

    private int inputCount = 0;
    private int inputNumber2;

    private StoreValue storeValue;


    Animation keyAnim,inputAnim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        //init prediction
        _preNum1 = findViewById(R.id.tvPreNum1);
        _preNum2 = findViewById(R.id.tvPreNum2);
        _preNum3 = findViewById(R.id.tvPreNum3);
        _preNum4 = findViewById(R.id.tvPreNum4);
        _preNum5 = findViewById(R.id.tvPreNum5);


        //preHints
        _preHints = findViewById(R.id.tvPreNumShowHints);

        //anim init
        keyAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        inputAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);



        //init key
        _key1 = findViewById(R.id.key1);
        _key2 = findViewById(R.id.key2);
        _key3 = findViewById(R.id.key3);
        _key4 = findViewById(R.id.key4);
        _key5 = findViewById(R.id.key5);
        _key6 = findViewById(R.id.key6);
        _key7 = findViewById(R.id.key7);
        _key8 = findViewById(R.id.key8);
        _key9 = findViewById(R.id.key9);


        //init input
        _in1 = findViewById(R.id.tvInput1);
        _in2 = findViewById(R.id.tvInput2);
        _in3 = findViewById(R.id.tvInput3);
        _in4 = findViewById(R.id.tvInput4);

        //init next
        _next = findViewById(R.id.keyNext);

        //init back
        _backPress = findViewById(R.id.keyBackpress);

        //init ques
        _ques = findViewById(R.id.tvQues);

        //int store value
        storeValue = new StoreValue();


        //inti btn click listener
        _key1.setOnClickListener(this);
        _key2.setOnClickListener(this);
        _key3.setOnClickListener(this);
        _key4.setOnClickListener(this);
        _key5.setOnClickListener(this);
        _key6.setOnClickListener(this);
        _key7.setOnClickListener(this);
        _key8.setOnClickListener(this);
        _key9.setOnClickListener(this);
        _backPress.setOnClickListener(this);
        _next.setOnClickListener(this);
        _preNum1.setOnClickListener(this);
        _preNum2.setOnClickListener(this);
        _preNum3.setOnClickListener(this);
        _preNum4.setOnClickListener(this);
        _preNum5.setOnClickListener(this);



        //set question
        _ques.setText("Enter another number");

        //set prediction hint
        _preHints.setText("Here your final summation\nYou can see up to 3 digit");


        //store value
        setDefaultStoreValue();

        //set full number
        ((TextView)findViewById(R.id.fullNumber))
                .setText(
                        String.valueOf(storeValue.getNum1())
                );



    }

    private void setDefaultStoreValue() {
        storeValue.setNum1(getIntent().getIntExtra("num1",0000));
        storeValue.setPredictNum(storeValue.getNum1() - 2 + 20000);
        storeValue.setResultDigitShow(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.key1:
                writeInput(_key1,1);
                break;

            case R.id.key2:
                writeInput(_key2, 2);
                break;

            case R.id.key3:
                writeInput(_key3, 3);
                break;

            case R.id.key4:
                writeInput(_key4, 4);
                break;

            case R.id.key5:
                writeInput(_key5, 5);
                break;

            case R.id.key6:
                writeInput(_key6, 6);
                break;

            case R.id.key7:
                writeInput(_key7, 7);
                break;

            case R.id.key8:
                writeInput(_key8, 8);
                break;

            case R.id.key9:
                writeInput(_key9, 9);
                break;

            case R.id.keyBackpress:
                _backPress.startAnimation(keyAnim);
                removeInput();
                break;

            case R.id.keyNext:
                _next.startAnimation(keyAnim);
                if (inputCount == 4){
                    createNum();
                    storeValue.setNum2(inputNumber2);
                    Intent goToQuestionActivity3 = new Intent(this,QuestionActivity3.class);
                    goToQuestionActivity3.putExtra("storeValue", (Serializable) storeValue);
                    startActivity(goToQuestionActivity3);
                    finish();
                }else{
                    ShowToast.shortToast(this,"Enter "+ String.valueOf(4 - inputCount) +" more number");
                }
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

    private void visitColorChange(TextView textView){
        //change tv bg and font color
        textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        textView.setTextColor(Color.parseColor("#34D539"));
    }

    private void removeInput() {
        if (inputCount == 4){
            _in4.setText("*");
            inputCount = 3;
        }
        else if (inputCount == 3){
            _in3.setText("*");
            inputCount = 2;
        }
        else if (inputCount == 2){
            _in2.setText("*");
            inputCount = 1;
        }
        else if (inputCount == 1){
            _in1.setText("*");
            inputCount = 0;
        }
    }

    private void writeInput(TextView _key, int key) {
        _key.startAnimation(keyAnim);
        if (inputCount == 0){
            _in1.setText(String.valueOf(key));
            _in1.startAnimation(inputAnim);
            inputCount++;
        }
        else if (inputCount == 1){
            _in2.setText(String.valueOf(key));
            _in2.startAnimation(inputAnim);
            inputCount++;
        }
        else if (inputCount == 2){
            _in3.setText(String.valueOf(key));
            _in3.startAnimation(inputAnim);
            inputCount++;
        }
        else if (inputCount == 3){
            _in4.setText(String.valueOf(key));
            _in4.startAnimation(inputAnim);
            inputCount++;
        }
        else{
            return;
        }
    }

    private void createNum(){
        inputNumber2 = Integer.parseInt(_in1.getText().toString());
        inputNumber2 = (inputNumber2 * 10 ) + Integer.parseInt(_in2.getText().toString());
        inputNumber2 = (inputNumber2 * 10 ) + Integer.parseInt(_in3.getText().toString());
        inputNumber2 = (inputNumber2 * 10 ) + Integer.parseInt(_in4.getText().toString());
    }
}
