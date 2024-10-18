package com.example.androidapp.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {
    private Cursor cursor;
    private OnCarActionListener carActionListener;

    // Constructor accepting the cursor and action listener
    public CarAdapter(Cursor cursor, OnCarActionListener carActionListener) {
        this.cursor = cursor;
        this.carActionListener = carActionListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cursor.moveToPosition(position);
        int id = cursor.getInt(0);  // Assuming the first column is car_id
        holder.carNameTextView.setText(cursor.getString(1)); // Assuming the second column is car_name
        holder.yearTextView.setText(String.valueOf(cursor.getInt(3))); // Assuming the fourth column is year
        holder.isElectricTextView.setText(cursor.getInt(2) == 1 ? "Electric" : "Not Electric"); // Third column is is_electric

        // Edit Button Click
        holder.editButton.setOnClickListener(v -> carActionListener.onEdit(id));

        // Delete Button Click
        holder.deleteButton.setOnClickListener(v -> carActionListener.onDelete(id));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView carNameTextView;
        public TextView yearTextView;
        public TextView isElectricTextView;
        public Button editButton;
        public Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            carNameTextView = itemView.findViewById(R.id.car_name_text_view);
            yearTextView = itemView.findViewById(R.id.year_text_view);
            isElectricTextView = itemView.findViewById(R.id.is_electric_text_view);
            editButton = itemView.findViewById(R.id.edit_button);   // Button to edit the car
            deleteButton = itemView.findViewById(R.id.delete_button);  // Button to delete the car
        }
    }

    // Interface for callbacks
    public interface OnCarActionListener {
        void onEdit(int carId);
        void onDelete(int carId);
    }
}
