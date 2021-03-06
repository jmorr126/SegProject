package com.example.segproject;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


public class create_task extends AppCompatActivity {
    EditText TaskName;
    EditText TaskDate;
    EditText TaskPriority;
    EditText TaskReward;
    EditText TaskUser;
    Profile USER;
    Profile CHILD;
    DatePicker datePicker;
    Calendar calendar;
    private int year, month, day;
    DatabaseReference databaseProfiles;
    DatabaseReference databaseTasks;
    Task T;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
        TaskName = (EditText) findViewById(R.id.txtName);
        TaskDate = (EditText) findViewById(R.id.txtDate);
        TaskPriority = (EditText) findViewById(R.id.txtPriority);
        TaskReward = (EditText) findViewById(R.id.txtReward);
        TaskUser = (EditText) findViewById(R.id.txtAssignUser);
        USER = (Profile) getIntent().getSerializableExtra("Profile");

        TaskDate.setInputType(0);


        if (!USER.isParent()) {
            TaskUser.setInputType(0); //Disables TaskUser if user is not a parent.
            TaskUser.setVisibility(View.INVISIBLE);
        }
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }


    public void onClickDate(View view) {
            showDialog(999);
        }

            @Override
            protected Dialog onCreateDialog(int id) {
                if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        TaskDate.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }


    public void CreateTaskClick(View view){
        if (TaskName.getText().toString().equals("")||TaskPriority.getText().toString().equals("")||TaskDate.getText().toString().equals("")||TaskReward.getText().toString().equals("")){
            Toast.makeText(this, "Empty Field, please fill all fields.", Toast.LENGTH_SHORT).show();
        return;}
        if(!TaskUser.getText().toString().trim().equals("")) {
            createTask(TaskName.getText().toString().trim(), TaskDate.getText().toString().trim(), Integer.parseInt(TaskPriority.getText().toString().trim()), Integer.parseInt(TaskReward.getText().toString().trim()), TaskUser.getText().toString().trim());
        }
        else{
            createTask(TaskName.getText().toString().trim(), TaskDate.getText().toString().trim(), Integer.parseInt(TaskPriority.getText().toString().trim()), Integer.parseInt(TaskReward.getText().toString().trim()), "unassigned");
        }
    }

    private void createTask(String name, String Date, int priority, int reward, String user){
        databaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        T = new Task(name,reward,Date,priority,user);
        databaseTasks.child(user).child(name).setValue(T);
        Toast.makeText(this, "Task Created.", Toast.LENGTH_SHORT).show();
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    private void AssignNewTask(String name, String Date, int priority, int reward, String user) {
        databaseTasks = FirebaseDatabase.getInstance().getReference("tasks");
        T = new Task(name, reward, Date, priority,user);
        databaseTasks.child(user).child(name).setValue(T);
        /*try{
            databaseProfiles = FirebaseDatabase.getInstance().getReference("profiles");
            DatabaseReference dR = FirebaseDatabase.getInstance().getReference("profiles").child(user);
            dR.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snap) {
                    Profile CHILD = snap.getValue(Profile.class);
                    CHILD.addTask(T);

                }

                @Override public void onCancelled(DatabaseError error) { }
            });}
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Invalid User", Toast.LENGTH_LONG).show();
            //toast asking for proper name
        }*/
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
