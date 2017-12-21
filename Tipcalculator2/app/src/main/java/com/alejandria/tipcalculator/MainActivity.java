package com.alejandria.tipcalculator;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.TextView.OnEditorActionListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements OnEditorActionListener, OnClickListener, OnSeekBarChangeListener, View.OnKeyListener {

//define variables; something to represent the data within the widget
    private EditText mbill_amount_EditText;
    private TextView mtip_percent_Amount;
    private TextView mtip_Amount;
    private TextView mtotal_tip_Amount;
    private Button mpercentUpButton;
    private Button mpercentDownButton;
    private SeekBar mseekBar;

//define SharedPreference Object
    private SharedPreferences savedValues;

    private String billAmountString = "";
    private float tipPercent = 15f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//get reference to the widgets; getting the info from the widgets
        mbill_amount_EditText = (EditText) findViewById(R.id.bill_amount_EditText);
        mtip_percent_Amount = (TextView) findViewById(R.id.tip_percent_Amount);
        mtip_Amount = (TextView) findViewById(R.id.tip_Amount);
        mtotal_tip_Amount = (TextView) findViewById(R.id.tip_total_Amount);
        mpercentDownButton = (Button) findViewById(R.id.percentDownButton);
        mpercentUpButton = (Button) findViewById(R.id.percentUpButton);
        mseekBar = (SeekBar) findViewById(R.id.seekBar);

//set the listeners; what are the actions made on the widget by clicking, editing etc.
        mbill_amount_EditText.setOnEditorActionListener(this);
        mpercentDownButton.setOnClickListener(this);
        mpercentUpButton.setOnClickListener(this);
        mseekBar.setOnSeekBarChangeListener(this);
        mseekBar.setOnKeyListener(this);
        //get SharedPreference object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }
    @Override
    public void onPause(){
        //save the instance variables
        Editor editor = savedValues.edit();
        editor.putString("billAmountString", billAmountString);
        editor.putFloat("tipPercent", tipPercent);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();

        //get the instance variables
        billAmountString = savedValues.getString("billAmountString", "");
        tipPercent = savedValues.getFloat("tipPercent", 0.15f);

        //set the bill amount on its widget
        mbill_amount_EditText.setText(billAmountString);
        int progress = Math.round(tipPercent * 100);
        mseekBar.setProgress(progress);

        //calculate and display
        calculateAndDisplay();

    }

    public void calculateAndDisplay() {

        //get the bill amount
        billAmountString = mbill_amount_EditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")){
            billAmount=0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }
        int progress = Math.round(tipPercent * 100);
        mseekBar.setProgress(progress);

        //calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        //display the other results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        mtip_Amount.setText(currency.format(tipAmount));
        mtotal_tip_Amount.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        mtip_percent_Amount.setText(percent.format(tipPercent));
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE)
        {
            calculateAndDisplay();
        }
        return false;
    }
     @Override
    public void onClick(View v){
         switch (v.getId()){
             case R.id.percentUpButton:
                 tipPercent=tipPercent + 0.01f;
                 calculateAndDisplay();
                 break;

             case R.id.percentDownButton:
                 tipPercent=tipPercent - 0.01f;
                 calculateAndDisplay();
                 break;
         }
     }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        mtip_percent_Amount.setText(progress + "%");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        calculateAndDisplay();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        calculateAndDisplay();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        return false;
    }
}
