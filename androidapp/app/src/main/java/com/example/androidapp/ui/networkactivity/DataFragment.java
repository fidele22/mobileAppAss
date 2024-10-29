package com.example.androidapp.ui.networkactivity;



import com.example.androidapp.R;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public class DataFragment extends Fragment {

    private RecyclerView recyclerView;
    private NetworkAdapter myAdapter;
    private ArrayList<CarObject> carList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        recyclerView = view.findViewById(R.id.web_recycler_view);
        carList = new ArrayList<>();
        myAdapter = new NetworkAdapter(carList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);

        fetchDataFromMongoDB();

        return view;
    }

    private void fetchDataFromMongoDB() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String url = "http://172.31.152.50:5000/api/cars/display"; // Use the correct URL

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            carList.clear(); // Clear the existing list
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject carObject = response.getJSONObject(i);
                                String carName = carObject.getString("carName");
                                String carPlaque = carObject.getString("carPlaque");
                                int deliveredYear = carObject.getInt("deliveredYear");
                                boolean isElectric = carObject.getBoolean("isElectric");

                                // Create a new CarObject and add it to the list
                                CarObject car = new CarObject(carName, carPlaque, deliveredYear, isElectric);
                                carList.add(car);
                            }
                            myAdapter.notifyDataSetChanged(); // Notify the adapter of data changes
                        } catch (Exception e) {
                            Log.e("DataFragment", "Error parsing JSON data", e);
                            Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("DataFragment", "Error fetching data", error);
                        Toast.makeText(getContext(), "Error fetching data", Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(jsonArrayRequest);
    }
}