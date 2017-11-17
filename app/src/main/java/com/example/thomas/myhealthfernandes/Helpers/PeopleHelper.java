package com.example.thomas.myhealthfernandes.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.thomas.myhealthfernandes.Models.Person;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 17/11/2017.
 */

public class PeopleHelper {
    private static final String TABLE_PERSON = "Person";
    private static final String COL_ID = "idPerson";
    private static final int NUM_COL_ID = 0;
    private static final String COL_LASTNAME = "lastnamePerson";
    private static final int NUM_COL_LASTNAME = 1;
    private static final String COL_FIRSTNAME = "firstnamePerson";
    private static final int NUM_COL_FIRSTNAME = 2;
    private static final String COL_AGE = "agePerson";
    private static final int NUM_COL_AGE = 3;
    private static final String COL_WEIGHT = "weightPerson";
    private static final int NUM_COL_WEIGHT = 4;
    private static final String COL_UPDATE_DATE = "updateDatePerson";
    private static final int NUM_COL_UPDATE_DATE = 5;
    private static final String COL_LOGIN = "loginPerson";
    private static final int NUM_COL_LOGIN = 6;

    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;

    public PeopleHelper(Context context) {
        // On crée la BDD et sa table
        dbHelper = new DataBaseHelper(context);
    }

    public void open() {
        // On ouvre la BDD en écriture
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        // On ferme l'accès à la BDD
        db.close();
    }

    public SQLiteDatabase getBDD() {
        return db;
    }

    public long insertPerson(Person person) {
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_LASTNAME, person.getLastname());
        values.put(COL_FIRSTNAME, person.getFirstname());
        values.put(COL_AGE, person.getAge());
        values.put(COL_WEIGHT, person.getWeight());
        values.put(COL_UPDATE_DATE, String.valueOf(person.getUpdateDate()));
        values.put(COL_LOGIN, person.getLogin());

        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_PERSON, null, values);
    }

    public long updatePerson(Person person) {
        Date currentTime = new Date();

        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();

        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_LASTNAME, person.getLastname());
        values.put(COL_FIRSTNAME, person.getFirstname());
        values.put(COL_AGE, person.getAge());
        values.put(COL_WEIGHT, person.getWeight());
        values.put(COL_UPDATE_DATE, String.valueOf(currentTime));
        values.put(COL_LOGIN, person.getLogin());

        //on met à jour l'objet dans la BDD via le ContentValues
        return db.update(TABLE_PERSON, values, "idPerson=" + person.getId() , null);
    }

    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from " + TABLE_PERSON, null);

        while (cursor.moveToNext()) {
            Person person = cursorToPerson(cursor);
            people.add(person);
        }

        cursor.close();
        return people;
    }

    public Person getPersonById(int idPerson) {
        Cursor cursor = db.rawQuery("select * from " + TABLE_PERSON + " where idPerson=" + idPerson, null);
        cursor.moveToFirst();

        Person person = cursorToPerson(cursor);
        return person;
    }

    public Person getPersonByLogin(String loginPerson) {
        Cursor cursor = db.rawQuery("select * from " + TABLE_PERSON + " where loginPerson=" + loginPerson, null);
        cursor.moveToFirst();

        Person person = cursorToPerson(cursor);
        return person;
    }

    //Cette méthode permet de convertir un cursor en une personne
    private Person cursorToPerson(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //On créé une personne
        Person person = new Person();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        person.setId(c.getInt(NUM_COL_ID));
        person.setLastname(c.getString(NUM_COL_LASTNAME));
        person.setFirstname(c.getString(NUM_COL_FIRSTNAME));
        person.setAge(c.getInt(NUM_COL_AGE));
        person.setWeight(c.getFloat(NUM_COL_WEIGHT));
        person.setUpdateDate(new Date(c.getLong(NUM_COL_UPDATE_DATE)));
        person.setLogin(c.getString(NUM_COL_LOGIN));

        //On retourne la personne
        return person;
    }
}
