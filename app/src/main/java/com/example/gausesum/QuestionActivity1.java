package com.example.gausesum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionActivity1 extends AppCompatActivity implements View.OnClickListener {

    private TextView _in1,_in2,_in3,_in4;
    private TextView _key1,_key2,_key3,_key4,_key5,_key6,_key7,_key8,_key9;
    private TextView _next;
    private TextView _ques;
    private ImageView _backPress;

    private int inputCount = 0;
    private int inputNumber1;


    Animation keyAnim,inputAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //hide prediction layout
        //coz we don't it until one number not enter
        findViewById(R.id.llPredictionPart).setVisibility(View.GONE);




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



        //set question
        _ques.setText("Enter a number");


        //anim init
        keyAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        inputAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);


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
                    Intent goToQuestionActivity2 = new Intent(this,QuestionActivity2.class);
                    goToQuestionActivity2.putExtra("num1",inputNumber1);
                    startActivity(goToQuestionActivity2);
                    finish();
                }else{
                    ShowToast.shortToast(this,"Enter "+ String.valueOf(4 - inputCount) +" more number");
                }
                break;
        }
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
        inputNumber1 = Integer.parseInt(_in1.getText().toString());
        inputNumber1 = (inputNumber1 * 10 ) + Integer.parseInt(_in2.getText().toString());
        inputNumber1 = (inputNumber1 * 10 ) + Integer.parseInt(_in3.getText().toString());
        inputNumber1 = (inputNumber1 * 10 ) + Integer.parseInt(_in4.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.help:
                showHelp();
                return true;
            case R.id.info:
                startActivity(new Intent(this,AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showHelp() {
        AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                QuestionActivity1.this);

        alertDialog2.setCancelable(false);

        // Setting Dialog Title
        alertDialog2.setTitle("HELP");

        // Setting Dialog Message
        alertDialog2.setMessage("Hey! This is a simple math tricks.\n\n " +
                "We input five number and calculate summation.But interesting thing is i guess your summation before insert five numbers\n\n\nHAVE FUN!");


        // Setting Positive "Yes" Btn
        alertDialog2.setPositiveButton("DONE",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });


        // Showing Alert Dialog
        alertDialog2.show();
    }
}
