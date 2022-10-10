package com.example.mini_project.Activities.MiniApps.imc;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mini_project.R;

import java.util.List;

public class IMCAdapter extends RecyclerView.Adapter<IMCAdapter.MyViewHolder> {

    private List<imcDetails> imcList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dateTV, timeTV, imcTV,imcStatus;

        public MyViewHolder(View view) {
            super(view);
            dateTV = view.findViewById(R.id.dateTV);
            timeTV = view.findViewById(R.id.timeTV);
            imcTV = view.findViewById(R.id.imcTV);
            imcStatus = view.findViewById(R.id.imcStatusTV);
        }
    }


    public IMCAdapter(List<imcDetails> imcList) {
        this.imcList = imcList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.imc_item_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        imcDetails imc = imcList.get(position);
        holder.dateTV.setText(imc.getDate());
        holder.timeTV.setText(imc.getTime());
        holder.imcTV.setText(imc.getImcValue());
        holder.imcStatus.setText(imc.getImcStatus());
    }

    @Override
    public int getItemCount() {
        return imcList.size();
    }
}
