package com.example.androidapp.ui.tabbedactivity;

import android.app.AlertDialog;
import android.database.Cursor;
import com.example.androidapp.ui.fragmentactivity.FragmentActivity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.androidapp.R;
import com.example.androidapp.adapter.CarAdapter;
import com.example.androidapp.crud.CarDAO;
import com.example.androidapp.ui.data.Car;

public class Fragment3 extends Fragment implements CarAdapter.OnCarActionListener {
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private CarDAO carDAO;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carDAO = new CarDAO(getContext()); // Initialize the DAO
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        // Initialize RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // Find the FAB and set a click listener
        // Inside Fragment3
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), FragmentActivity.class);
            intent.putExtra("fragment_to_load", "fragment1");  // Pass fragment1 to load in FragmentActivity
            startActivity(intent);
        });

        return view;
    }
//        return view;
//    }

    @Override
    public void onResume() {
        super.onResume();
        loadCars();  // Load cars when fragment resumes
    }

    private void loadCars() {
        Cursor cursor = carDAO.getAllCars();
        if (cursor != null) {
            carAdapter = new CarAdapter(cursor, this);
            recyclerView.setAdapter(carAdapter);  // Set adapter to RecyclerView
        } else {
            Toast.makeText(getContext(), "No cars available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onEdit(int carId) {
        Car car = carDAO.getCarById(carId);
        if (car == null) {
            Toast.makeText(getContext(), "Car not found!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Inflate the edit dialog layout
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View editView = inflater.inflate(R.layout.dialog_edit_car, null);

        // Populate the fields with current car data
        EditText carNameEditText = editView.findViewById(R.id.edit_car_name);
        EditText yearEditText = editView.findViewById(R.id.edit_car_year);
        CheckBox isElectricCheckBox = editView.findViewById(R.id.edit_car_electric);

        carNameEditText.setText(car.getCarName());
        yearEditText.setText(String.valueOf(car.getYear()));
        isElectricCheckBox.setChecked(car.isElectric());

        // Create a dialog for updating the car
        new AlertDialog.Builder(getContext())
                .setTitle("Edit Car")
                .setView(editView)
                .setPositiveButton("Update", (dialog, which) -> {
                    String updatedCarName = carNameEditText.getText().toString();
                    int updatedYear = Integer.parseInt(yearEditText.getText().toString());
                    boolean isElectric = isElectricCheckBox.isChecked();

                    car.setCarName(updatedCarName);
                    car.setYear(updatedYear);
                    car.setElectric(isElectric);

                    carDAO.updateCar(car);  // Update the car in the database
                    loadCars();  // Reload the updated data in RecyclerView
                    Toast.makeText(getContext(), "Car updated!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public void onDelete(int carId) {
        // Create a dialog for delete confirmation
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Car")
                .setMessage("Are you sure you want to delete this car?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    carDAO.deleteCar(carId);  // Delete the car from the database
                    loadCars();  // Reload the updated data in RecyclerView
                    Toast.makeText(getContext(), "Car deleted!", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("No", null)
                .show();
    }
}
