package com.example.androidapp.adapter;
import com.example.androidapp.R;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private Cursor cursor;
    private Context context;

    public CarAdapter(Cursor cursor) {
        this.cursor = cursor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        holder.carNameTextView.setText(cursor.getString(1));
        holder.yearTextView.setText(String.valueOf(cursor.getInt(3)));
        holder.isElectricTextView.setText(cursor.getInt(2) == 1 ? "Electric" : "Not Electric");
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView carNameTextView;
        public TextView yearTextView;
        public TextView isElectricTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            carNameTextView = itemView.findViewById(R.id.car_name_text_view);
            yearTextView = itemView.findViewById(R.id.year_text_view);
            isElectricTextView = itemView.findViewById(R.id.is_electric_text_view);
        }
    }
}