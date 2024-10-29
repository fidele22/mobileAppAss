package com.example.androidapp.ui.networkactivity;

import com.example.androidapp.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;
import java.util.ArrayList;

public class FragmentForm extends Fragment {

    private EditText carNameEditText, carPlaqueEditText, deliveredYearEditText;
    private CheckBox isElectricCheckBox;
    private Button submitButton;
    private RecyclerView recyclerView;
    private NetworkAdapter myAdapter;
    private ArrayList<CarObject> carList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);

        carNameEditText = view.findViewById(R.id.carNameEditText);
        carPlaqueEditText = view.findViewById(R.id.carPlaqueEditText);
        deliveredYearEditText = view.findViewById(R.id.deliveredYearEditText);
        isElectricCheckBox = view.findViewById(R.id.isElectricCheckBox);
        submitButton = view.findViewById(R.id.submitButton);
        recyclerView = view.findViewById(R.id.recyclerView);

        carList = new ArrayList<>();
        myAdapter = new NetworkAdapter(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitData();
            }
        });

        return view;
    }

  // Import this at the top of your file

    private void submitData() {
        String carName = carNameEditText.getText().toString();
        String carPlaque = carPlaqueEditText.getText().toString();
        String deliveredYear = deliveredYearEditText.getText().toString();
        boolean isElectric = isElectricCheckBox.isChecked();

        // Log the data being sent
        Log.d("FragmentForm", "Submitting data: carName=" + carName + ", carPlaque=" + carPlaque + ", deliveredYear=" + deliveredYear + ", isElectric=" + isElectric);

        int deliveredYearInt;
        try {
            deliveredYearInt = Integer.parseInt(deliveredYear); // Convert to int
        } catch (NumberFormatException e) {
            Log.e("FragmentForm", "Invalid delivered year: " + deliveredYear, e);
            Toast.makeText(getContext(), "Invalid delivered year", Toast.LENGTH_SHORT).show();
            return; // Exit if the year is invalid
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("carName", carName);
            jsonObject.put("carPlaque", carPlaque);
            jsonObject.put("deliveredYear", deliveredYearInt);
            jsonObject.put("isElectric", isElectric);
        } catch (Exception e) {
            Log.e("FragmentForm", "Error creating JSON object", e);
            return; // Exit if there's an error creating the JSON
        }

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://172.31.152.50:5000/api/cars"; // Use the correct URL
        Log.d("FragmentForm", "Sending request to URL: " + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("FragmentForm", "Response received: " + response.toString());
                Toast.makeText(getContext(), "Data submitted successfully", Toast.LENGTH_SHORT).show();
                fetchDataFromMongoDB();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String errorMessage = "Error submitting data";
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    errorMessage = new String(error.networkResponse.data);
                }
                Log.e("FragmentForm", "Error response: " + errorMessage, error);
                Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    private void fetchDataFromMongoDB() {
        // Implement logic to fetch data from MongoDB and update the RecyclerView
        // For demonstration purposes, assume we have a method to fetch data
        carList.clear();
        carList.addAll(fetchData()); // Replace with actual data fetching logic
        myAdapter.notifyDataSetChanged();
    }


    private ArrayList<CarObject> fetchData() {
        // This is a placeholder method. Implement actual logic to fetch data from MongoDB
        ArrayList<CarObject> cars = new ArrayList<>();
        // Add cars to the list
        return cars;
    }
}