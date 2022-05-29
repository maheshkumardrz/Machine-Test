package com.mahesh.mechinetest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mahesh.mechinetest.R;
import com.mahesh.mechinetest.ui.DataModel;

import java.util.ArrayList;


public class BirthDaysAdapter extends RecyclerView.Adapter<BirthDaysAdapter.ViewHolder> {

    ArrayList<DataModel> mValues;
    Context mContext;
    protected ItemListener mListener;

    public BirthDaysAdapter(Context context, ArrayList<DataModel> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name,type;
        public ImageView user_image;
        DataModel item;

        public ViewHolder(View v) {

            super(v);

            v.setOnClickListener(this);
            name = (TextView) v.findViewById(R.id.name);
            user_image = (ImageView) v.findViewById(R.id.user_image);
            type = (TextView) v.findViewById(R.id.type);




        }

        public void setData(DataModel item) {
            this.item = item;

            name.setText(item.text);
            user_image.setImageResource(item.drawable);
            type.setText(item.type);


        }
        @Override
        public void onClick(View view) {
//            if (mListener != null) {
//                mListener.onItemClick(item);
//            }
        }
    }

    @Override
    public BirthDaysAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.birthdays_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener
    {
        void onItemClick(DataModel item);

    }
}