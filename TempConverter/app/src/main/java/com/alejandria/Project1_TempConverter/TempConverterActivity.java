package com.alejandria.Project1_TempConverter;

import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;

public class TempConverterActivity extends AppCompatActivity implements OnEditorActionListener{

    //variables for the widget
    private EditText mfarenheitED;
    private TextView mcelciusTV;

    //define SharedPreference object
    private SharedPreferences savedValues;

    //define an instance variable for the calculation
    private String tempAmountString="";
    private float ctemp= 15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);

        //Get reference to the widgets
        mfarenheitED = (EditText) findViewById(R.id.farenheitED);
        mcelciusTV = (TextView) findViewById(R.id.celciusTV);

        //set the listener
        mfarenheitED.setOnEditorActionListener(this);

        //get SharedPreference object
        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);
    }

    //Implement the listener
    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_DONE)
        {
            calculateAndDisplay();
        }
        return false;
    }

    @Override
    public void onPause() {
        //save the instance variable
        SharedPreferences.Editor editor = savedValues.edit();
        editor.putString("tempAmountString", tempAmountString);
        editor.commit();

        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

        //get the instance variable
        tempAmountString = savedValues.getString("tempAmountString","");
        
        //set the temp on its wideget
        mfarenheitED.setText(tempAmountString);

        //calculate and display
        calculateAndDisplay();
    }

    //get temp
    public void calculateAndDisplay(){
       //get temp
        tempAmountString = mfarenheitED.getText().toString();
        float tempAmount;
        if (tempAmountString.equals(" ")){
            tempAmount = 0;
        }
        else {
            tempAmount = Float.parseFloat(tempAmountString);
        }
        //calculate temp
        float totalTemp=((tempAmount-32)*5/9);

        //display result with formatting
        NumberFormat result = NumberFormat.getCurrencyInstance();
        mcelciusTV.setText(result.format(totalTemp));
        }
    }

