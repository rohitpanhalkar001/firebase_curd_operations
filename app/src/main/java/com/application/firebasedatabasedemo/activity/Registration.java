package com.application.firebasedatabasedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.application.firebasedatabasedemo.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    TextView sumbitBtn;
    EditText edFirstName;
    EditText edLastName;
    EditText edEmail;
    EditText edPassword;

    String firstName;
    String lastName;
    String email;
    String userName;
    String password;

    public static String firebaseUserId;
    String userid;
    public static String ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initView();

    }

    private void initView() {
        edFirstName = (EditText) findViewById(R.id.activity_register_firstname);
        edLastName = (EditText) findViewById(R.id.activity_register_lastname);
        edEmail = (EditText) findViewById(R.id.activity_register_email);
        edPassword = (EditText) findViewById(R.id.activity_register_pwd);

        sumbitBtn = (TextView) findViewById(R.id.activity_photo_submitBtb);

        sumbitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                        .child("USER").push();
                firebaseUserId = databaseReference.getKey();

                userid = GetId();
                Map<String, Object> userDetails = new HashMap<>();
                ID = userid;
                userDetails.put("userId", ID);
                userDetails.put("username", edFirstName.getText().toString()+edLastName.getText().toString());
                userDetails.put("email", edEmail.getText().toString());
                userDetails.put("firstname", edFirstName.getText().toString());
                userDetails.put("lastName", edLastName.getText().toString());
                userDetails.put("password", edPassword.getText().toString());
                databaseReference.setValue(userDetails);
                Intent intent  = new Intent(Registration.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public static String GetId(){
        String[] chars = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","0","1","2","3","4","5","6","7","8","9"};
        StringBuilder s = new StringBuilder(10);

        for(int i = 0;i<10;i++){
            int pos = (int) (Math.random() * 62);
            String c = "";
            if (pos > chars.length-1){
                pos = pos - chars.length;
                c = chars[pos].toUpperCase();
            }else{
                c = chars[pos];
            }

            s.append(c);
        }

        return s.toString();
    }
}
