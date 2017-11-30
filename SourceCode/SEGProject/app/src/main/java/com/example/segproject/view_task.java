package com.example.segproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class view_task extends AppCompatActivity {
    Task T;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        T = new Task("",1,"",1);//blank to not cause errors we still need to figure out how to pass an object to this page
    }
    public void CompleteClick(View view){
        completeTask(T);
    }
    public void DeclineClick(View view){
        declineTask(T);
    }
    public void ReturnClick(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void completeTask(Task compTask){

        int scoreToAdd = compTask.getReward();
        //Profile.set_Score(Profile.get_score + scoreToAdd);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void declineTask(Task delTask){
        //Profile.RemoveTask(delTask);
        //ProfileUnassigned.AddTask(delTask);
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
