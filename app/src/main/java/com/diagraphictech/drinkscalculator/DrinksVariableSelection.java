package com.diagraphictech.drinkscalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;

public class DrinksVariableSelection extends AppCompatActivity {

    public final static String AGE = "com.diagraphictech.helloworld.AGE";
    public final static String WEIGHT = "com.diagraphictech.helloworld.WEIGHT";
    public final static String GENDER = "com.diagraphictech.helloworld.GENDER";
    public final static String FOOD = "com.diagraphictech.helloworld.FOOD";
    public final static String DRINK = "com.diagraphictech.helloworld.DRINK";

    public static final String PREFS_NAME = "DefaultDrinkSettings";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_variable_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //loads previously loaded data points
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String age = settings.getString(AGE, "");
        String weight = settings.getString(WEIGHT, "");
        String gender = settings.getString(GENDER, "");
        String drinks = settings.getString(DRINK, "");

        TextView weightView = (TextView)findViewById(R.id.weight);
        TextView ageView = (TextView)findViewById(R.id.age);
        TextView drinkView = (TextView)findViewById(R.id.drink);
        RadioGroup genderView = (RadioGroup)findViewById(R.id.radio_gender);

        weightView.setText(weight);
        ageView.setText(age);
        drinkView.setText(drinks);

        if(gender.equals("Female")) {
            genderView.check(genderView.getChildAt(1).getId());
        }
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
        String food_val = ((EditText) findViewById(R.id.food)).getText().toString();
        String drink_val = ((EditText) findViewById(R.id.drink)).getText().toString();

        if(age_val.equals("") || weight_val.equals("") || food_val.equals("") || drink_val.equals("")) {

            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Please add values for each field.");
            builder1.setCancelable(false);

            builder1.setPositiveButton(
                    "Continue",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

            return;
        }

        RadioGroup rg_gender = (RadioGroup)findViewById(R.id.radio_gender);
        String gender_val = ((RadioButton)findViewById(rg_gender.getCheckedRadioButtonId())).getText().toString();

        intent.putExtra(AGE, age_val);
        intent.putExtra(WEIGHT, weight_val);
        intent.putExtra(FOOD, food_val);
        intent.putExtra(GENDER, gender_val);
        intent.putExtra(DRINK, drink_val);

        //loads for later use
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(AGE, age_val);
        editor.putString(WEIGHT, weight_val);
        editor.putString(DRINK, drink_val);
        editor.putString(GENDER, gender_val);
        editor.commit();

        startActivity(intent);
    }
}
