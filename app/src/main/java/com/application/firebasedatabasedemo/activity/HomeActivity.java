package com.application.firebasedatabasedemo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.application.firebasedatabasedemo.R;
import com.application.firebasedatabasedemo.model.User;
import com.application.firebasedatabasedemo.adapter.UserListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference ref;
    ListView listview;
    ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        userList = new ArrayList<>();
        listview = (ListView)findViewById(R.id.activity_home_listview);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        ref = mFirebaseDatabase.getReference("USER");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                 for (DataSnapshot child : dataSnapshot.getChildren()) {

//                    for (DataSnapshot childd : child.getChildren()) {
                        Log.i("HomeActivity", "database value is" + child.getValue());

                        User user = new User();
                        user.setFirstname(child.child("firstname").getValue().toString());
                        user.setLastName(child.child("lastName").getValue().toString());
                        user.setUsername(child.child("username").getValue().toString());
                        user.setEmail(child.child("email").getValue().toString());
                        user.setUserId(child.child("userId").getValue().toString());
                        user.setTableId(child.getKey());

                        userList.add(user);
//                    }


                }
                UserListAdapter userListAdapter = new UserListAdapter(HomeActivity.this,userList);
                listview.setAdapter(userListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HomeActivity.this,DetailActivity.class);
                intent.putExtra("USER_SELECTED_POSITION",userList);
                startActivity(intent);
                finish();
            }
        });


    }


    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
        dialog.setCancelable(false);
        dialog.setMessage("Are you sure you want to exit from App");
        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();

            }
        });

        dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
