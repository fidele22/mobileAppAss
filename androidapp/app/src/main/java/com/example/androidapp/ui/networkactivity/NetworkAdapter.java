package com.example.androidapp.ui.networkactivity;
import android.util.Log;
import com.example.androidapp.R;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class NetworkAdapter extends RecyclerView.Adapter<NetworkAdapter.ViewHolder> {

    private ArrayList<CarObject> carList;

    public NetworkAdapter(ArrayList<CarObject> carList) {
        this.carList = carList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarObject car = carList.get(position);
        Log.d("NetworkAdapter", "CarObject at position " + position + ": " + car);
        holder.carNameTextView.setText("Car Name: " + car.getCarName());
        holder.carPlaqueTextView.setText("Car Plaque: " + car.getCarPlaque());
        holder.deliveredYearTextView.setText(String.valueOf("Delivered on: " + car.getDeliveredYear()));
        holder.isElectricTextView.setText("Is it Electric: " + (car.isElectric() ? "Yes" : "No"));
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView carNameTextView, carPlaqueTextView, deliveredYearTextView, isElectricTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            carNameTextView = itemView.findViewById(R.id.carNameTextView);
            carPlaqueTextView = itemView.findViewById(R.id.carPlaqueTextView);
            deliveredYearTextView = itemView.findViewById(R.id.deliveredYearTextView);
            isElectricTextView = itemView.findViewById(R.id.isElectricTextView);
        }
    }
}