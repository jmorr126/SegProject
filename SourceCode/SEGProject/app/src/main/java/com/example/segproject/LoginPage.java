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
        Login(username.getText().toString().trim(),password.getText().toString().trim());

    }

    private void Login(String user, String pass){
        //TODO james implement this login method
        Profile USER = new Profile("","","",1,"");// dummy profile
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("profiles").child(user);
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                System.out.println();
            }

            @Override public void onCancelled() { }
        });

        Intent intent = new Intent(getApplicationContext(),home_page.class);
        intent.putExtra("Profile", USER);
        startActivityForResult (intent,0);

    }

}
