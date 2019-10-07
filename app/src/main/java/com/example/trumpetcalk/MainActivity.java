package com.example.trumpetcalk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewProgName;
    private TextView textEnterParam;

    private TextView textTranshHeight;
    private TextView textTranshWidth;
    private TextView textTranshLenght;



    private TextView textTrumpetSquare;
    private TextView textRocksLvl;
    private TextView textSandsLvl;
    private TextView textSoilsLvl;

    private EditText txtTrHeight01;
    private EditText txtTrHeight02;
    private EditText txtTrWidth01;
    private EditText txtTrWidth02;
    private EditText txtTrLenght;

    private EditText txtTrumpetSquare;
    private EditText txtRocksLvl;
    private EditText txtSandsLvl;
    private EditText txtSoilsLvl;

    private CheckBox squareCheckBox;
    private CheckBox trumpetCheckBox;
    private CheckBox transhBackFill;

    private Button btnNext;
    private Button btnCalk;
    private Button btn2;

    private TextView lblOutput;
    private TextView lblOutput1;
    private TextView lblOutput2;
    private TextView lblOutput3;


    public void checkParametrTransh(){
        String transhHeightOnStart = txtTrHeight01.getText().toString();
        String transhHeightOnFinish = txtTrHeight02.getText().toString();
        String transhWidthOnStart = txtTrWidth01.getText().toString();
        String transhWidthOnFinish = txtTrWidth02.getText().toString();
        String transhLenght = txtTrLenght.getText().toString();
        String message = "";
        String messageOpal = "";
        try {
                double transhHeight01 = Double.valueOf(transhHeightOnStart);
                double transhHeight02 = Double.valueOf(transhHeightOnFinish);
                double transhHeightMiddle = (transhHeight01 + transhHeight02) / 2;
                double transhWidth01 = Double.valueOf(transhWidthOnStart);
                double transhWidth02 = Double.valueOf(transhWidthOnFinish);
                double transhWidthMiddle = (transhWidth01 + transhWidth02) / 2;
                double transhLenghtForCalk = Double.valueOf(transhLenght);
                double volumeOfGrunt = transhHeightMiddle * transhWidthMiddle * transhLenghtForCalk;
                double transhOpal = transhHeightMiddle * transhLenghtForCalk *2;
                volumeOfGrunt = (double)Math.round(volumeOfGrunt * 1000d) / 1000d;
                transhOpal = (double)Math.round(transhOpal * 1000d) / 1000d;
                if (volumeOfGrunt <= 0) {
                    message = volumeOfGrunt + "Неверные значения!!!";
                } else {
                    message = volumeOfGrunt + "м3 " + " Объем разрабатываемого грунта.";
                    messageOpal = transhOpal + "м2 " + " Объем щитов для раскрепления откосов.";
                }
            double vTrumpet = 0;
            if (trumpetCheckBox.isChecked()) {
                String trumpetSquare = txtTrumpetSquare.getText().toString();
                String messageTrumpet = "";
                try {
                    double trumpetS = Double.valueOf(trumpetSquare);
                    double volumeOfTrumpet = trumpetS * transhLenghtForCalk;
                    vTrumpet = volumeOfTrumpet;
                    volumeOfTrumpet = (double)Math.round(volumeOfTrumpet * 100000d) / 1000d;
                    if (volumeOfTrumpet <= 0) {
                        messageTrumpet = volumeOfTrumpet + "Неверное значение!!!";
                    } else {
                        messageTrumpet = volumeOfTrumpet + "м3 " + " Вычесленный объем трубы.";
                    }
                } catch (Exception e) {
                    messageTrumpet = "Enter correct numbers on meters like:(1.0) (0.1) (2.4).";
                } finally {
                    lblOutput2.setText(messageTrumpet);
                    txtTrumpetSquare.requestFocus();
                    txtTrumpetSquare.selectAll();
                }
            } else {

            }
            if (transhBackFill.isChecked()) {
                String transhRocks = txtRocksLvl.getText().toString();
                String transhSands = txtSandsLvl.getText().toString();
                String transhSoils = txtSoilsLvl.getText().toString();
                String messageBackFill = "";
                try {
                    double transhRock = Integer.parseInt(transhRocks);
                    double transhSand = Integer.parseInt(transhSands);
                    double transhSoil = Integer.parseInt(transhSoils);
                    double volumeOfRock = (transhRock * transhWidthMiddle * transhLenghtForCalk)/100;
                    double volumeOfSoil = (transhSoil * transhWidthMiddle * transhLenghtForCalk)/100;
                    volumeOfRock = (double)Math.round(volumeOfRock * 1000d) / 1000d;
                    volumeOfSoil = (double)Math.round(volumeOfSoil * 1000d) / 1000d;
                    double volumeOfSand;
                    if (trumpetCheckBox.isChecked()) {
                        volumeOfSand = ((transhSand * transhWidthMiddle * transhLenghtForCalk)/100) - vTrumpet;
                        volumeOfSand = (double)Math.round(volumeOfSand * 1000d) / 1000d;
                    } else {
                        volumeOfSand = (transhSand * transhWidthMiddle * transhLenghtForCalk)/100;
                        volumeOfSand = (double)Math.round(volumeOfSand * 1000d) / 1000d;
                    }
                    if (volumeOfRock <= 0 && volumeOfSoil <= 0 && volumeOfSand <= 0) {
                        messageBackFill = "Неверное значение!!!";
                    } else {
                        messageBackFill = volumeOfRock + "м3 " + "объем щебня " + volumeOfSand + "м3 " + "объем песка " + volumeOfSoil + "м3 " + "объем грунта.";
                    }
                } catch (Exception e) {
                    messageBackFill = "Enter correct numbers on meters like:(1.0) (0.1) (2.4).";
                } finally {
                    lblOutput3.setText(messageBackFill);
                    txtRocksLvl.requestFocus();
                    txtRocksLvl.selectAll();
                    txtSandsLvl.requestFocus();
                    txtSandsLvl.selectAll();
                    txtSoilsLvl.requestFocus();
                    txtSoilsLvl.selectAll();

                }
            } else {

            }
        } catch (Exception e) {
            message = "Enter correct numbers on meters like:(1.0) (0.1) (2.4).";
        } finally {
            lblOutput.setText(message);
            lblOutput1.setText(messageOpal);
            txtTrHeight01.requestFocus();
            txtTrHeight01.selectAll();
            txtTrHeight02.requestFocus();
            txtTrHeight02.selectAll();
            txtTrWidth01.requestFocus();
            txtTrWidth01.selectAll();
            txtTrWidth02.requestFocus();
            txtTrWidth02.selectAll();
            txtTrLenght.requestFocus();
            txtTrLenght.selectAll();;
        }

    }
    public void newCalculation () {
        txtTrHeight01.getText().clear();
        txtTrHeight02.getText().clear();
        txtTrWidth01.getText().clear();
        txtTrWidth02.getText().clear();
        txtTrLenght.getText().clear();

        txtTrumpetSquare.getText().clear();
        txtRocksLvl.getText().clear();
        txtSandsLvl.getText().clear();
        txtSoilsLvl.getText().clear();

        lblOutput.setText(" ");
        lblOutput1.setText(" ");
        lblOutput2.setText(" ");
        lblOutput3.setText(" ");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        textViewProgName = (TextView) findViewById(R.id.textViewProgName);
        textEnterParam = (TextView) findViewById(R.id.textEnterParam);

        textTranshHeight = (TextView) findViewById(R.id.textTranshHeight);
        textTranshWidth = (TextView) findViewById(R.id.textTranshWidth);
        textTranshLenght = (TextView) findViewById(R.id.textTranshLenght);

        textTrumpetSquare = (TextView) findViewById(R.id.textTrumpetSquare);
        textRocksLvl = (TextView) findViewById(R.id.textRocksLvl);
        textSandsLvl = (TextView) findViewById(R.id.textSandsLvl);
        textSoilsLvl = (TextView) findViewById(R.id.textSoilLvl);

        txtTrHeight01 = (EditText) findViewById(R.id.txtTrHeight01);
        txtTrHeight02 = (EditText) findViewById(R.id.txtTrHeight02);
        txtTrWidth01 = (EditText) findViewById(R.id.txtTrWidth01);
        txtTrWidth02 = (EditText) findViewById(R.id.txtTrWidth02);
        txtTrLenght = (EditText) findViewById(R.id.txtTrLenght);

        txtTrumpetSquare = (EditText) findViewById(R.id.txtTrumpetSquare);
        txtRocksLvl = (EditText) findViewById(R.id.txtRocksLvl);
        txtSandsLvl = (EditText) findViewById(R.id.txtSandsLvl);
        txtSoilsLvl = (EditText) findViewById(R.id.txtSoilsLvl);

        squareCheckBox = (CheckBox) findViewById(R.id.squareCheckBox);
        trumpetCheckBox = (CheckBox) findViewById(R.id.trumpetCheckBox);
        transhBackFill = (CheckBox) findViewById(R.id.transhBackFill);

        btnNext = (Button) findViewById(R.id.btnNext);
        btnCalk = (Button) findViewById(R.id.btnCalc);
        btn2 = (Button) findViewById(R.id.btn2);

        lblOutput = (TextView) findViewById(R.id.lblOutput);
        lblOutput1 = (TextView) findViewById(R.id.lblOutput1);
        lblOutput2 = (TextView) findViewById(R.id.lblOutput2);
        lblOutput3 = (TextView) findViewById(R.id.lblOutput3);

        btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                checkParametrTransh();
            }
        });
        btnCalk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newCalculation ();
            }
        });




        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });




        Button mJopaButton = (Button) findViewById(R.id.squareCheckBox);
        mJopaButton.setOnClickListener(new View.OnClickListener() {  //check Box
            @Override
            public void onClick(View v) {
                if(squareCheckBox.isChecked()) {
                    textTranshHeight.setTextColor(Color.RED);
                    textTranshWidth.setTextColor(Color.RED);
                    textTranshLenght.setTextColor(Color.RED);
                } else {
                    textTranshHeight.setTextColor(Color.GREEN);
                    textTranshWidth.setTextColor(Color.GREEN);
                    textTranshLenght.setTextColor(Color.GREEN);
                }
            }
        });
        Button trumpetCheckButton = (Button) findViewById(R.id.trumpetCheckBox);
        trumpetCheckButton.setOnClickListener(new View.OnClickListener() {  //trumpet check Box
            @Override
            public void onClick(View v) {
                if(trumpetCheckBox.isChecked()) {
                    textTrumpetSquare.setVisibility(View.VISIBLE);
                    txtTrumpetSquare.setVisibility(View.VISIBLE);
                    lblOutput2.setVisibility(View.VISIBLE);
                } else {
                    textTrumpetSquare.setVisibility(View.INVISIBLE);
                    txtTrumpetSquare.setVisibility(View.INVISIBLE);
                    lblOutput2.setVisibility(View.INVISIBLE);
                }
            }
        });
        Button transhBackFillCheckButton = (Button) findViewById(R.id.transhBackFill);
        transhBackFillCheckButton.setOnClickListener(new View.OnClickListener() {  //transh check Box
            @Override
            public void onClick(View v) {
                if(transhBackFill.isChecked()) {
                    textRocksLvl.setVisibility(View.VISIBLE);
                    textSandsLvl.setVisibility(View.VISIBLE);
                    textSoilsLvl.setVisibility(View.VISIBLE);
                    txtRocksLvl.setVisibility(View.VISIBLE);
                    txtSandsLvl.setVisibility(View.VISIBLE);
                    txtSoilsLvl.setVisibility(View.VISIBLE);
                    lblOutput3.setVisibility(View.VISIBLE);
                } else {
                    textRocksLvl.setVisibility(View.INVISIBLE);
                    textSandsLvl.setVisibility(View.INVISIBLE);
                    textSoilsLvl.setVisibility(View.INVISIBLE);
                    txtRocksLvl.setVisibility(View.INVISIBLE);
                    txtSandsLvl.setVisibility(View.INVISIBLE);
                    txtSoilsLvl.setVisibility(View.INVISIBLE);
                    lblOutput3.setVisibility(View.INVISIBLE);
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
