package com.application.firebasedatabasedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.application.firebasedatabasedemo.R;
import com.application.firebasedatabasedemo.model.User;

import java.util.ArrayList;

/**
 * Created by Mindbowser on 5/29/2018.
 */

public class UserListAdapter extends BaseAdapter {
    Context context;
    ArrayList<User> userList;
    protected LayoutInflater inflater;


    public UserListAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        if (row == null) {
            row = inflater.inflate(R.layout.user_list_row, parent, false);
            ViewHolder holder = new ViewHolder(row);
            row.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) row.getTag();
        holder.tvFirstName.setText(userList.get(position).getFirstname());
        holder.tvLastName.setText(userList.get(position).getLastName());
        holder.tvEmail.setText(userList.get(position).getEmail());
        holder.tvUsername.setText(userList.get(position).getUsername());

        return row;
    }


    class ViewHolder {

        protected TextView tvFirstName;
        protected TextView tvLastName;
        protected TextView tvEmail;
        protected TextView tvUsername;

        public ViewHolder(View view) {
            tvEmail = (TextView) view.findViewById(R.id.email_tv);
            tvFirstName = (TextView) view.findViewById(R.id.firstname_tv);
            tvLastName = (TextView) view.findViewById(R.id.lastname_tv);
            tvUsername = (TextView) view.findViewById(R.id.userName);

        }
    }

    }
