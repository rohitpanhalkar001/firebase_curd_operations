package com.application.firebasedatabasedemo.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.application.firebasedatabasedemo.R;
import com.application.firebasedatabasedemo.model.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UserDetailEditActivity extends AppCompatActivity {

    EditText edFirstName;
    EditText edLastName;
    EditText edEmail;
    TextView submitBtnTv;
    FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_edit);

        edFirstName = (EditText) findViewById(R.id.activity_user_detail_edit_firstname);
        edLastName = (EditText) findViewById(R.id.activity_user_detail_edit_lastname);
        edEmail = (EditText) findViewById(R.id.activity_user_detail_edit_email);

        submitBtnTv = (TextView) findViewById(R.id.activity_user_detail_edit_submitBtb);



        final User user = (User) getIntent().getSerializableExtra("USER_EDIT_DETAIL");

        edFirstName.setText(user.getFirstname().toString());
        edLastName.setText(user.getLastName().toString());
        edEmail.setText(user.getEmail().toString());

        submitBtnTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseDatabase = FirebaseDatabase.getInstance();
                mDatabase = mFirebaseDatabase.getReference("USER");
                Map<String, Object> userDetails = new HashMap<>();
                userDetails.put("username", edFirstName.getText().toString()+edLastName.getText().toString());
                userDetails.put("email", edEmail.getText().toString());
                userDetails.put("firstname", edFirstName.getText().toString());
                userDetails.put("lastName", edLastName.getText().toString());
                mDatabase.child(user.getTableId()).updateChildren(userDetails);

                Intent intent  = new Intent(UserDetailEditActivity.this,HomeActivity.class);
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
