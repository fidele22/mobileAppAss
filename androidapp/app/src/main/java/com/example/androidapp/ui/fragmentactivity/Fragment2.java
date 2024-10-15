package com.example.androidapp.ui.fragmentactivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;

import java.util.ArrayList;

public class Fragment2 extends Fragment {

    private ListView listView;
    private ArrayList<Car> carList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);
        listView = view.findViewById(R.id.car_list_view);

        // Initialize sample car data
        carList = new ArrayList<>();
        carList.add(new Car("Toyota Camry", "A reliable sedan with great fuel economy."));
        carList.add(new Car("Honda Accord", "A spacious sedan known for its durability."));
        carList.add(new Car("Ford Mustang", "An iconic sports car with powerful performance."));
        carList.add(new Car("Chevrolet Malibu", "A comfortable midsize sedan with modern features."));
        carList.add(new Car("Nissan Altima", "A stylish sedan with advanced safety technology."));

        // Set up the adapter
        CarAdapter adapter = new CarAdapter(requireContext(), carList);
        listView.setAdapter(adapter);

        // Find the back button and set an OnClickListener
        Button backToMainButton = view.findViewById(R.id.btn_back_to_main);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMainActivity();
            }
        });

        return view;
    }

    // Method to navigate back to MainActivity
    private void navigateBackToMainActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();  // Optionally close the FragmentActivity if you want to remove it from the back stack
    }

    // Car data model
    private static class Car {
        String name;
        String description;

        Car(String name, String description) {
            this.name = name;
            this.description = description;
        }
    }

    // Custom adapter for the car list
    private static class CarAdapter extends ArrayAdapter<Car> {
        CarAdapter(@NonNull Context context, ArrayList<Car> cars) {
            super(context, 0, cars);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            }

            Car currentCar = getItem(position);
            if (currentCar != null) {
                TextView nameTextView = convertView.findViewById(android.R.id.text1);
                TextView descriptionTextView = convertView.findViewById(android.R.id.text2);

                nameTextView.setText(currentCar.name);
                descriptionTextView.setText(currentCar.description);
            }

            return convertView;
        }
    }
}
