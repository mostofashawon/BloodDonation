package com.blooddonation.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blooddonation.Main.R;
import com.blooddonation.Model.Information;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {


    private Context Get_Context;
    private List<Information> Get_List;
    ClickListner clickListner;

    public interface ClickListner {

        void onItemListner(int postion, View v);
    }

    public void setOnClick(ClickListner clickListner){

        this.clickListner=clickListner;
    }

    public CustomAdapter(Context get_Context, List<Information> get_List) {
        Get_Context = get_Context;
        Get_List = get_List;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(Get_Context);

        View view = layoutInflater.inflate(R.layout.dummy_recyclerview,parent,false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        Information information = Get_List.get(position);

        holder.DonerName.setText(information.getDoner_Name());
        holder.DonerPhone.setText(information.getDoner_Phone());
        holder.DonerGroup.setText(information.getDoner_BloodGroup());
        holder.DonerDivision.setText(information.getDoner_Division());

        holder.Doner_Name.setImageResource(R.drawable.lg_doner_);
        holder.Doner_Phone.setImageResource(R.drawable.lg_phone);
        holder.Doner_Group.setImageResource(R.drawable.welcome_screen);
        holder.Doner_Division.setImageResource(R.drawable.lg_location);

    }

    @Override
    public int getItemCount() {
        return Get_List.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView  Doner_Name,Doner_Phone,Doner_Group,Doner_Division;
        TextView DonerName,DonerPhone,DonerGroup,DonerDivision;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            Doner_Name =(ImageView) itemView.findViewById(R.id.Dummy_Doner);
            Doner_Phone =(ImageView) itemView.findViewById(R.id.Dummy_Phone);
            Doner_Group =(ImageView) itemView.findViewById(R.id.Dummy_Blood);
            Doner_Division =(ImageView) itemView.findViewById(R.id.Dummy_Division);

            DonerName =(TextView) itemView.findViewById(R.id.Dummy_Doner_Name);
            DonerPhone =(TextView) itemView.findViewById(R.id.Dummy_DonerPhone_No);
            DonerGroup =(TextView) itemView.findViewById(R.id.Dummy_DonerBlood_Group);
            DonerDivision =(TextView) itemView.findViewById(R.id.Dummy_Doner_Division);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            clickListner.onItemListner(getAdapterPosition(),itemView);

        }
    }
}
