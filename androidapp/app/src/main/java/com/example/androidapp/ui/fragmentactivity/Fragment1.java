package com.example.androidapp.ui.fragmentactivity;

import com.example.androidapp.ui.data.Car;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.example.androidapp.crud.CarDAO;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.androidapp.R;

public class Fragment1 extends Fragment {

    private EditText carNameEditText;
    private EditText carYearEditText;
    private CheckBox isElectricCheckBox;
    private CarDAO carDAO;
    private Car car; // This will hold the car if editing

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        carNameEditText = view.findViewById(R.id.edit_text_car_name);
        carYearEditText = view.findViewById(R.id.edit_text_car_year);
        isElectricCheckBox = view.findViewById(R.id.checkbox_is_electric);

        carDAO = new CarDAO(getContext());

        // Check if editing a car
        if (getArguments() != null) {
            int carId = getArguments().getInt("carId", -1);
            if (carId != -1) {
                car = carDAO.getCarById(carId); // Fetch the car from database
                if (car != null) {
                    // Prepopulate fields if editing
                    carNameEditText.setText(car.getCarName());
                    carYearEditText.setText(String.valueOf(car.getYear()));
                    isElectricCheckBox.setChecked(car.isElectric());
                }
            }
        }

        // Visit list button navigation
        Button visitListButton = view.findViewById(R.id.btn_visit_list);
        visitListButton.setOnClickListener(v -> navigateToFragment2());

        // Send or update button logic
        Button sendButton = view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(v -> {
            String carName = carNameEditText.getText().toString();
            int carYear = Integer.parseInt(carYearEditText.getText().toString());
            boolean isElectric = isElectricCheckBox.isChecked();

            if (car != null) {
                // Updating existing car
                car.setCarName(carName);
                car.setYear(carYear);
                car.setElectric(isElectric);
                carDAO.updateCar(car); // Update the car in the database
                Toast.makeText(getContext(), "Car updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                // Creating a new car
                car = new Car(0, carName, isElectric, carYear);
                carDAO.saveCar(car); // Save the new car in the database
                Toast.makeText(getContext(), "Data sent successfully", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    // Method to navigate to Fragment2
    private void navigateToFragment2() {
        Fragment2 fragment2 = new Fragment2();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment2);
        transaction.addToBackStack(null);  // Add to the back stack so the user can navigate back
        transaction.commit();
    }
}