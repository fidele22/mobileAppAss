package com.example.androidapp.ui.fragmentactivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.androidapp.R;
import java.util.ArrayList;

public class Fragment2 extends Fragment {
    private ListView listView;
    private ArrayList<String> stringArrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        listView = view.findViewById(R.id.list_view_fragment_2);

        // Sample data for ArrayList
        stringArrayList = new ArrayList<>();
        stringArrayList.add("Item 1");
        stringArrayList.add("Item 2");
        stringArrayList.add("Item 3");

        // Setup the ArrayAdapter and attach it to the ListView
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(arrayAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh array list data if needed
        arrayAdapter.notifyDataSetChanged();
    }
}
