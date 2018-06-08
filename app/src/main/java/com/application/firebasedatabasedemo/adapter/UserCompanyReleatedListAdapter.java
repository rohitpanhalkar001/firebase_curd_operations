package com.application.firebasedatabasedemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.application.firebasedatabasedemo.R;
import com.application.firebasedatabasedemo.model.User;
import com.application.firebasedatabasedemo.activity.UserDetailEditActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Mindbowser on 6/1/2018.
 */

public class UserCompanyReleatedListAdapter extends BaseAdapter{
    Context context;
    ArrayList<User> userList;
    protected LayoutInflater inflater;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference refUser;

    public UserCompanyReleatedListAdapter(Context context, ArrayList<User> userList) {
        this.context =  context;
        this.userList = userList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inflater.inflate(R.layout.spinner_user_company_row, parent, false);
            ViewHolder holder = new ViewHolder(row);
            row.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) row.getTag();
        holder.tvFirstName.setText(userList.get(position).getFirstname());
        holder.tvLastName.setText(userList.get(position).getLastName());
        holder.tvEmail.setText(userList.get(position).getEmail());
        holder.tvUsername.setText(userList.get(position).getUsername());

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.child("USER").orderByChild("userId").equalTo(userList.get(position).getUserId());
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                            userList.remove(position);
                            notifyDataSetChanged();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("onCancelled", "onCancelled", databaseError.toException());
                    }
                });
            }
        });

        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context,UserDetailEditActivity.class);
                intent.putExtra("USER_EDIT_DETAIL",userList.get(position));
                context.startActivity(intent);
                ((Activity)context).finish();

            }
        });

        return row;
    }

    class ViewHolder {
        protected TextView tvFirstName;
        protected TextView tvLastName;
        protected TextView tvEmail;
        protected TextView tvUsername;
        protected Button deleteBtn;
        protected Button editBtn;

        public ViewHolder(View view) {
            tvEmail = (TextView) view.findViewById(R.id.spinner_user_company_email_tv);
            tvFirstName = (TextView) view.findViewById(R.id.spinner_user_company_firstname_tv);
            tvLastName = (TextView) view.findViewById(R.id.spinner_user_company_lastname_tv);
            tvUsername = (TextView) view.findViewById(R.id.spinner_user_company_userName);
            deleteBtn = (Button) view.findViewById(R.id.spinner_user_company_button_delete);
            editBtn = (Button) view.findViewById(R.id.spinner_user_company_button_edit);

        }
    }
}
