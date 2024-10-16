package com.example.androidapp.ui.more;
import com.example.androidapp.MainActivity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.androidapp.R;
import com.example.androidapp.ui.tabbedactivity.TabbedActivity;

import com.example.androidapp.ui.fragmentactivity.FragmentActivity;

public class MoreFragment extends BottomSheetDialogFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_more, container, false);

        // Finding the TextView links
        TextView link1 = view.findViewById(R.id.link1);
        TextView link2 = view.findViewById(R.id.link2);
        TextView link3 = view.findViewById(R.id.link3);
        TextView link4 = view.findViewById(R.id.link4);

        // Set onClickListener for link1 to navigate to Fragment1
        link1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFragmentActivity("fragment1"); // Launch FragmentActivity with fragment1
            }
        });

        // Set onClickListener for link2 to navigate to Fragment2
        link2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFragmentActivity("fragment2"); // Launch FragmentActivity with fragment2
            }
        });


        // Set onClickListener for link3 to navigate to TabbedActivity
        link3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TabbedActivity.class);
                startActivity(intent);
            }
        });
        // Set onClickListener for link4 to navigate to MainActivity
        link4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    // Method to launch FragmentActivity with a specific fragment
    private void launchFragmentActivity(String fragment) {
        Intent intent = new Intent(getActivity(), FragmentActivity.class);
        intent.putExtra("fragment_to_load", fragment); // Pass the fragment name as an extra
        startActivity(intent);
    }

    // Method to launch TabbedActivity
    private void launchTabbedActivity() {
        Intent intent = new Intent(getActivity(), TabbedActivity.class); // Launch TabbedActivity
        startActivity(intent);
    }
}
