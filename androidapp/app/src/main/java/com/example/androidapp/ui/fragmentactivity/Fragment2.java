package com.example.androidapp.ui.fragmentactivity;

import android.database.Cursor;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androidapp.R;
import com.example.androidapp.adapter.CarAdapter;
import com.example.androidapp.crud.CarDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Fragment2 extends Fragment implements CarAdapter.OnCarActionListener {  // Implement the interface
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private CarDAO carDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        carDAO = new CarDAO(getContext());
        loadCars(); // Load initial data

        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load Fragment1 to add a new car
                Fragment1 fragment1 = new Fragment1();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment1)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    // Method to load cars from the database and set the adapter
    private void loadCars() {
        Cursor cursor = carDAO.getAllCars();
        carAdapter = new CarAdapter(cursor, this);  // Pass both the cursor and this fragment as the listener
        recyclerView.setAdapter(carAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCars(); // Refresh data when returning to Fragment2
    }

    @Override
    public void onEdit(int carId) {
        // Fetch car data from the database and pass it to Fragment1 for editing
        Bundle bundle = new Bundle();
        bundle.putInt("carId", carId);
        Fragment1 fragment1 = new Fragment1();
        fragment1.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment1)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDelete(int carId) {
        carDAO.deleteCar(carId); // Delete the car from the database
        loadCars(); // Refresh the list
    }
}
