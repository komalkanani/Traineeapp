package com.softices.trainee.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.softices.traineeapp.R;

public class ProgramingAdapter extends RecyclerView.Adapter<ProgramingAdapter.PogramingViewholder>{
    private String[] data;
    public ProgramingAdapter (String[] data) {
        this.data = data;

    }

    @Override
    public PogramingViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View View = inflater.inflate(R.layout.list_item_layout,parent,false);
        return new PogramingViewholder(View);
    }

    @Override
    public void onBindViewHolder(PogramingViewholder holder, int position) {
        String title = data [position];
        holder.textTitle.setText(title);

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class PogramingViewholder extends RecyclerView.ViewHolder {
        TextView textTitle;
        public PogramingViewholder(View itemView){
            super (itemView);
            textTitle = (TextView) itemView.findViewById(R.id.textTitle);
        }
    }
}

