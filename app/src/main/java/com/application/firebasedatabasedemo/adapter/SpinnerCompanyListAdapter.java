package com.application.firebasedatabasedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.application.firebasedatabasedemo.R;
import com.application.firebasedatabasedemo.adapter.UserListAdapter;

import java.util.ArrayList;

/**
 * Created by Mindbowser on 6/1/2018.
 */

public class SpinnerCompanyListAdapter extends BaseAdapter{
    Context context;
    ArrayList<String> post;
    protected LayoutInflater inflater;


    public SpinnerCompanyListAdapter(Context context, ArrayList<String> post) {
        this.context = context;
        this.post = post;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return post.size();
    }

    @Override
    public Object getItem(int position) {
        return post.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = inflater.inflate(R.layout.spinner_company_row, parent, false);
            ViewHolder holder = new ViewHolder(row);
            row.setTag(holder);
        }

        final ViewHolder holder = (ViewHolder) row.getTag();
        holder.tvFirstName.setText(post.get(position));



        return row;
    }

    class ViewHolder {

        protected TextView tvFirstName;

        public ViewHolder(View view) {
            tvFirstName = (TextView) view.findViewById(R.id.spinner_company_row_tv);


        }
    }
}
