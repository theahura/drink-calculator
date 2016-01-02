package com.diagraphictech.drinkscalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DrinksCalculation extends AppCompatActivity {
    //Gender
    private static int MALE_VALUE = 3;
    private static int FEMALE_VALUE = 0;

    //Age
    private static int YOUTH = 0;
    private static int TEEN = 2;
    private static int EARLYADULT = 3;
    private static int LATERADULT = 1;

    //Weight
    private static int LWEIGHT = 0;
    private static int LMWEIGHT = 2;
    private static int MWEIGHT = 4;
    private static int HMWEIGHT = 6;
    private static int HWEIGHT = 8;

    //Food
    private static int STARVING = 0;
    private static int HUNGRY = 1;
    private static int CONTENT = 2;
    private static int FULL_CONTENT = 3;
    private static int FULL = 4;

    //Class scores
    private static int LSCORE = 6;
    private static int LMSCORE = 9;
    private static int MSCORE = 11;
    private static int HMSCORE = 14;

    private static int[] LIGHT = new int[]{0,1,2,4,5};
    private static int[] LIGHT_MID = new int[]{0,2,3,4,6};
    private static int[] MID = new int[]{0,3,4,6,8};
    private static int[] HEAVY_MID = new int[]{0,3,5,7,9};
    private static int[] HEAVY = new int[]{0,4,6,8,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_calculation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //get variables
        Intent intent = getIntent();
        String age_string = intent.getStringExtra(DrinksVariableSelection.AGE);
        String weight_string = intent.getStringExtra(DrinksVariableSelection.WEIGHT);
        String gender_string = intent.getStringExtra(DrinksVariableSelection.GENDER);
        String food_string = intent.getStringExtra(DrinksVariableSelection.FOOD);
        String drink_string = intent.getStringExtra(DrinksVariableSelection.DRINK);

        TextView soberView = (TextView)findViewById(R.id.soberval);
        TextView tipsyView = (TextView)findViewById(R.id.tipsyval);
        TextView drunkView = (TextView)findViewById(R.id.drunkval);
        TextView wastedView = (TextView)findViewById(R.id.wastedval);
        TextView blackoutView = (TextView)findViewById(R.id.blackoutval);

        int[] scale = calculateDrinkScale(age_string, weight_string, food_string, gender_string, drink_string);

        soberView.setText(String.valueOf(scale[0]));
        tipsyView.setText(String.valueOf(scale[1]));
        drunkView.setText(String.valueOf(scale[2]));
        wastedView.setText(String.valueOf(scale[3]));
        blackoutView.setText(String.valueOf(scale[4]));
    }

    private int[] calculateDrinkScale(String age_string, String weight_string, String food_string, String gender_string, String drink_string) {
        //set input vars to integer equivalents
        int age_val = getAgeIntValue(Integer.parseInt(age_string));
        int weight_val = getWeightIntValue(Integer.parseInt(weight_string));
        int gender_val = getGenderIntValue(gender_string);
        int food_val = getFoodIntValue(Integer.parseInt(food_string));
        int drink_val = getDrinkIntValue(Integer.parseInt(drink_string));

        int classification = age_val + weight_val + gender_val + drink_val;

        if(classification < LSCORE) {
            return LIGHT;
        } else if (classification < LMSCORE) {
            return LIGHT_MID;
        } else if (classification < MSCORE) {
            return MID;
        } else if (classification < HMSCORE) {
            return HEAVY_MID;
        } else {
            return HEAVY;
        }
    }

    private int getFoodIntValue(int food_hours) {

        if(food_hours < 1) {
            return FULL;
        } else if (food_hours < 2) {
            return FULL_CONTENT;
        } else if (food_hours < 4) {
            return CONTENT;
        } else if (food_hours < 6) {
            return HUNGRY;
        } else {
            return STARVING;
        }
    }

    private int getGenderIntValue(String gender) {
        //Males can drink more, generally
        if(gender.equals("Male")) {
            return MALE_VALUE;
        } else {
            return FEMALE_VALUE;
        }
    }

    private int getDrinkIntValue(int drink) {
        //Can drink more beers than wine, and wine than vodka
        return 0;
    }

    private int getAgeIntValue(int age) {
        if(age < 14) {
            return YOUTH;
        } else if (age < 21) {
            return TEEN;
        } else if (age < 27){
            return EARLYADULT;
        } else {
            return LATERADULT;
        }
    }

    private int getWeightIntValue(int weight) {
        if(weight < 100) {
            return LWEIGHT;
        } else if(weight < 125) {
            return LMWEIGHT;
        } else if(weight < 150) {
            return MWEIGHT;
        } else if(weight < 200) {
            return HMWEIGHT;
        } else {
            return HWEIGHT;
        }
    }

}
