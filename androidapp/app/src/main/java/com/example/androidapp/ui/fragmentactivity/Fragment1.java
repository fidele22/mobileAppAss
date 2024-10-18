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
import android.widget.Toast; // Import Toast
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.example.androidapp.R;

public class Fragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        // Find the button and set an OnClickListener for navigating to Fragment2
        Button visitListButton = view.findViewById(R.id.btn_visit_list);
        visitListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment2();
            }
        });

        Button sendButton = view.findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText carNameEditText = view.findViewById(R.id.edit_text_car_name);
                EditText carYearEditText = view.findViewById(R.id.edit_text_car_year);
//                EditText carPlaqueEditText = view.findViewById(R.id.edit_text_car_plaque);
                CheckBox isElectricCheckBox = view.findViewById(R.id.checkbox_is_electric);

                String carName = carNameEditText.getText().toString();
                int carYear = Integer.parseInt(carYearEditText.getText().toString());
//                String carPlaque = carPlaqueEditText.getText().toString();
                boolean isElectric = isElectricCheckBox.isChecked();

                int carId = 0; // or generate it based on your logic
                Car car = new Car(carId, carName, isElectric, carYear);
                CarDAO carDAO = new CarDAO(getContext());
                carDAO.saveCar(car); // Save the car data

                // Show success message
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
        transaction.addToBackStack(null);  // Add the transaction to the back stack so the user can navigate back
        transaction.commit();
    }
}