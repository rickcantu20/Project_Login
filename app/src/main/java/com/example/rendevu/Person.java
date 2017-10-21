package com.example.rendevu;
/*
    Ricardo Cantu
    This class creates a 'Person' Object that is used by the card view.
 */
public class Person {
    String name;
    String age;
    int photoID;

    Person(String name, String age, int photoID){
        this.name = name;
        this.age = age;
        this.photoID = photoID;
    }
}