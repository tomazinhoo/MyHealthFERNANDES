package com.example.thomas.myhealthfernandes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.thomas.myhealthfernandes.Helpers.PeopleHelper;
import com.example.thomas.myhealthfernandes.Models.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PeopleHelper peopleHelper = new PeopleHelper(this);
        peopleHelper.open();

        List<Person> people = peopleHelper.getPeople();

        peopleHelper.close();
    }
}
