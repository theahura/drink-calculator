package com.diagraphictech.drinkscalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DrinksVariableSelection extends AppCompatActivity {

    public final static String AGE = "com.diagraphictech.helloworld.AGE";
    public final static String WEIGHT = "com.diagraphictech.helloworld.WEIGHT";
    public final static String GENDER = "com.diagraphictech.helloworld.GENDER";
    public final static String DRINK = "com.diagraphictech.helloworld.DRINK";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_variable_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drinks_variable_selection, menu);
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

    /** Called when the user clicks the Send button */
    public void calculateDrinks(View view) {
        Intent intent = new Intent(this, DrinksCalculation.class);

        String age_val = ((EditText) findViewById(R.id.age)).getText().toString();
        String weight_val = ((EditText) findViewById(R.id.weight)).getText().toString();

        RadioGroup rg_gender = (RadioGroup)findViewById(R.id.radio_gender);
        String gender_val = ((RadioButton)findViewById(rg_gender.getCheckedRadioButtonId())).getText().toString();
        RadioGroup rg_drink = (RadioGroup)findViewById(R.id.radio_drink);
        String drink_val = ((RadioButton)findViewById(rg_drink.getCheckedRadioButtonId())).getText().toString();

        intent.putExtra(AGE, age_val);
        intent.putExtra(WEIGHT, weight_val);
        intent.putExtra(GENDER, gender_val);
        intent.putExtra(DRINK, drink_val);
        startActivity(intent);
    }
}
