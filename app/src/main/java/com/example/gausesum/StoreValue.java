package com.example.gausesum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class StoreValue implements Serializable {
    private int num1,num2,num3,num4,num5;
    private int predictNum;
    private int resultDigitShow;

    private ArrayList<Integer> digitView = new ArrayList<>();

    public int getNum1() {
        return num1;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public int getNum2() {
        return num2;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public int getNum3() {
        return num3;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public int getNum4() {
        return num4;
    }

    public void setNum4(int num4) {
        this.num4 = num4;
    }

    public int getNum5() {
        return num5;
    }

    public void setNum5(int num5) {
        this.num5 = num5;
    }

    public int getPredictNum() {
        return predictNum;
    }

    public void setPredictNum(int predictNum) {
        this.predictNum = predictNum;
    }

    public int getResultDigitShow() {
        return resultDigitShow;
    }

    public void setResultDigitShow(int resultDigitShow) {
        this.resultDigitShow = resultDigitShow;
    }

    public int preDigit(int num, int position){
        String number = String.valueOf(num);
        return Integer.parseInt(Character.toString(number.charAt(position-1)));
    }

    public static int random(int min, int max){
        Random r = new Random();
        return r.nextInt(max - min + 1) + min;
    }

    public void setViewDigit(int position){
        digitView.add(position);
    }

    public boolean getViewDigit(int position){
        if (digitView.contains(position)){
            return true;
        }

        return false;
    }
}
