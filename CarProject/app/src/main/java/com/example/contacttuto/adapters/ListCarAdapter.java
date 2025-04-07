package com.example.contacttuto.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacttuto.Car;
import com.example.contacttuto.R;
import com.example.contacttuto.VisualizeActivity;

import java.util.ArrayList;

public class ListCarAdapter extends RecyclerView.Adapter<ListCarAdapter.CarViewHolder> {

    ArrayList<Car> listCars;

    public ListCarAdapter(ArrayList<Car> listCars){

        this.listCars = listCars;

    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_car,null,false);

        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {

        holder.viewBrand.setText(listCars.get(position).getBrand());
        holder.viewModel.setText(listCars.get(position).getModel());
        holder.viewTypeOfFuel.setText(listCars.get(position).getTypeOfFuel());
        holder.viewYear.setText(String.valueOf(listCars.get(position).getYear()));
        holder.viewHp.setText(String.valueOf(listCars.get(position).getHp()));

    }

    @Override
    public int getItemCount() {
        return listCars.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

        TextView viewBrand, viewModel, viewTypeOfFuel, viewYear, viewHp;



        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            viewBrand = itemView.findViewById(R.id.viewBrand);
            viewModel = itemView.findViewById(R.id.viewModel);
            viewTypeOfFuel = itemView.findViewById(R.id.viewTypeOfFuel);
            viewYear = itemView.findViewById(R.id.viewYear);
            viewHp = itemView.findViewById(R.id.viewHp);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VisualizeActivity.class);
                    intent.putExtra("ID", listCars.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
