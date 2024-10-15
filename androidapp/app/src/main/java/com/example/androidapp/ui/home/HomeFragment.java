package com.example.androidapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.androidapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        // Set click listeners for each CardView
        binding.card1.setOnClickListener(v -> {
            // Handle click for card1 (open or display additional info)
            handleCardClick("Card 1 clicked");
        });

        binding.card2.setOnClickListener(v -> {
            handleCardClick("Card 2 clicked");
        });

        binding.card3.setOnClickListener(v -> {
            handleCardClick("Card 3 clicked");
        });

        binding.card4.setOnClickListener(v -> {
            handleCardClick("Card 4 clicked");
        });

        binding.card5.setOnClickListener(v -> {
            handleCardClick("Card 5 clicked");
        });

        binding.card6.setOnClickListener(v -> {
            handleCardClick("Card 6 clicked");
        });

        return root;
    }

    // Method to handle card clicks
    private void handleCardClick(String message) {
        // You can show a Toast, open a new Fragment, or perform other actions
        // For example, you can use Toast to show the message
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

        // You can also replace the fragment or open a new activity if needed
        // For example:
        // Fragment newFragment = new DetailFragment();
        // getActivity().getSupportFragmentManager().beginTransaction()
        //        .replace(R.id.fragment_container, newFragment)
        //        .addToBackStack(null)
        //        .commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}