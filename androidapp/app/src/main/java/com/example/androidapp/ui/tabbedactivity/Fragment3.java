package com.example.androidapp.ui.fragmentactivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import android.view.ViewGroup;
import java.util.ArrayList;
import com.example.androidapp.R;

public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private ArrayList<Car> carList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);
        recyclerView = view.findViewById(R.id.car_recycler_view);

        // Initialize car list
        carList = new ArrayList<>();
        carList.add(new Car("Toyota Camry", "A reliable sedan."));
        carList.add(new Car("Honda Accord", "A spacious sedan."));
        carList.add(new Car("Ford Mustang", "A powerful sports car."));
        carList.add(new Car("Chevrolet Malibu", "A comfortable midsize sedan."));
        carList.add(new Car("Nissan Altima", "A stylish sedan."));
        carList.add(new Car("BMW 3 Series", "A luxury sports sedan."));
        carList.add(new Car("Tesla Model S", "An electric car."));
        carList.add(new Car("Audi A4", "A compact executive car."));
        carList.add(new Car("Mercedes-Benz C-Class", "A premium sedan."));
        carList.add(new Car("Hyundai Sonata", "A midsize sedan."));
        carList.add(new Car("Kia Optima", "An affordable midsize sedan."));

        // Set up RecyclerView
        carAdapter = new CarAdapter(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(carAdapter);

        return view;
    }

    // Car model class
    private static class Car {
        String name;
        String description;

        Car(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    // Adapter for RecyclerView
    private static class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

        private ArrayList<Car> carList;

        CarAdapter(ArrayList<Car> carList) {
            this.carList = carList;
        }

        @NonNull
        @Override
        public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            return new CarViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
            Car car = carList.get(position);
            holder.bind(car);
        }

        @Override
        public int getItemCount() {
            return carList.size();
        }

        static class CarViewHolder extends RecyclerView.ViewHolder {

            TextView nameTextView;
            TextView descriptionTextView;

            CarViewHolder(@NonNull View itemView) {
                super(itemView);
                nameTextView = itemView.findViewById(android.R.id.text1);
                descriptionTextView = itemView.findViewById(android.R.id.text2);
            }

            void bind(Car car) {
                nameTextView.setText(car.name);
                descriptionTextView.setText(car.description);
            }
        }
    }
}
