package com.example.justinkhull.gitreference;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by justinkhull on 2/28/18.
 */

public class GitReferenceAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<GitReference> mDataSource;

    public GitReferenceAdapter(Context context, ArrayList<GitReference> arrayList) {
        this.mContext = context;
        this.mDataSource = arrayList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //return null;
        View rowView = mInflater.inflate(R.layout.git_reference_layout, viewGroup, false);
        //View rowView = mInflater.inflate(android.R.layout.simple_expandable_list_item_2, viewGroup, false);

        TextView command = rowView.findViewById(R.id.command);
        //TextView command = rowView.findViewById(android.R.id.text1);
        TextView example = rowView.findViewById(R.id.example);
        //TextView example = rowView.findViewById(android.R.id.text2);

        GitReference gitReference = (GitReference) getItem(i);
        command.setText(gitReference.getCommand());
        example.setText(gitReference.getExample());
        return rowView;
    }

}
