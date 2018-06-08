package com.application.firebasedatabasedemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.firebasedatabasedemo.R;
import com.application.firebasedatabasedemo.adapter.UserCompanyReleatedListAdapter;
import com.application.firebasedatabasedemo.model.User;
import com.application.firebasedatabasedemo.adapter.SpinnerCompanyListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDetailActivity extends AppCompatActivity {

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference refUser;

    DatabaseReference ref;
    Spinner spinner_company;
    ListView lv1;
    ArrayList<User> user;
    ArrayList<User> userList;
    ArrayList<String> post = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        initView();

    }

    private void initView() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        ref = mFirebaseDatabase.getReference("Company");

        userList = new ArrayList<>();
        user = (ArrayList<User>) getIntent().getSerializableExtra("USER_SELECTED_POSITION");

        lv1 = (ListView) findViewById(R.id.detail_activity_lv);
        UserCompanyReleatedListAdapter userListAdapter = new UserCompanyReleatedListAdapter(UserDetailActivity.this,user);
        lv1.setAdapter(userListAdapter);


        spinner_company = (Spinner) findViewById(R.id.activity_detail_spinner);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Log.i("DetailActivity", "database value is" + child.getValue());
                    post.add(child.getValue().toString());
                }
                Log.i("DetailActivity", "database value is" + post.size());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });


        SpinnerCompanyListAdapter spinnerCompanyListAdapter = new SpinnerCompanyListAdapter(UserDetailActivity.this,post);
        spinner_company.setAdapter(spinnerCompanyListAdapter);
        spinner_company.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                Toast.makeText(UserDetailActivity.this, "derp"+post.get(position), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                Toast.makeText(UserDetailActivity.this, "herf", Toast.LENGTH_LONG).show();
            }
        });





//        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                refUser = mFirebaseDatabase.getReference("USER");
//                refUser.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        for (DataSnapshot child : dataSnapshot.getChildren()) {
//                            if (user.get(position).getEmail().equals(child.child("email").getValue().toString())) {
//                                dataSnapshot.getRef().removeValue();
//                            }
//
//                        }
//                        Log.i("DetailActivity", "database value is" + post.size());
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        System.out.println("The read failed: " + databaseError.getCode());
//                    }
//                });
//            }
//        });


    }
}
