package com.example.androidapp.ui.fragmentactivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        // Find the button and set an OnClickListener
        Button visitListButton = view.findViewById(R.id.btn_visit_list);
        visitListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToFragment2();
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
