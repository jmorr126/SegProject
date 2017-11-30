package com.example.segproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class LoginPage extends AppCompatActivity {
    EditText username;
    EditText password;
    DatabaseReference databaseProfiles;
    List<Profile> profiles;
    String user;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_page);
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        databaseProfiles = FirebaseDatabase.getInstance().getReference("profiles");
        profiles = new ArrayList<>();


    }


    public void btnLoginClick(View view){
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        //TODO james implement this login method
        Profile USER = new Profile("","",1,"");// dummy profile
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("profiles").child(user);
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
               Profile USER= snap.getValue(Profile.class);
                String user = username.getText().toString().trim();
                String pass = username.getText().toString().trim();
               if(USER.get_name().equals(user) && USER.get_password()==pass){

                   //TOAST HERE
                   Intent intent = new Intent(getApplicationContext(),home_page.class);
                   intent.putExtra("Profile", USER);
                   startActivityForResult (intent,0);
               }
               else{
                   //TOAST HERE
               }

            }

            @Override public void onCancelled(DatabaseError error) { }
            });

        Intent intent = new Intent(getApplicationContext(),home_page.class);
        intent.putExtra("Profile", USER);
        startActivityForResult (intent,0);

    }

    public void btnCreateAccountClick(View view) {
        username = (EditText) findViewById(R.id.txtUser);
        password = (EditText) findViewById(R.id.txtPassword);
        String user = username.getText().toString().trim();
        String pass = username.getText().toString().trim();




        String type = "";
        //TODO jarod do this one



        
        //creating a Product Object
        Profile profile = new Profile(user, pass,0,type);

        //Saving the Product
        databaseProfiles.child(user).setValue(profile);


    }




}



