package com.example.segproject;
import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by jamesmorrill on 2017-11-30.
 */

public class Profile implements Serializable{

    private String _name;
    private String _password;
    private int _score;
    private String _type;
    private String _img;


    public Profile(){

    }
    public Profile( String name, String password, int score, String type, String img){

        _name = name;
        _password = password;
        _score = score;
        _type = type;
        _img = img;



    }

    public int get_score() {
        return _score;
    }

    public String get_name() {
        return _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_img() {
        return _img;
    }

    public void set_img(String _img) {
        this._img = _img;
    }

    public boolean isParent(){
        return _type.equals("Parent");

    }

}
