package com.example.androidapp.ui.fragmentactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidapp.MainActivity;
import com.example.androidapp.R;
import com.example.androidapp.ui.fragmentactivity.Fragment1;
import com.example.androidapp.ui.fragmentactivity.Fragment2;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity); // Assuming you have a layout for FragmentActivity

        // Find the back button and set an OnClickListener
        Button backToMainButton = findViewById(R.id.btn_back_to_main);
        backToMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateBackToMainActivity();
            }
        });

        // Get the fragment to load from the intent
        String fragmentToLoad = getIntent().getStringExtra("fragment_to_load");

        if (fragmentToLoad != null) {
            loadFragment(fragmentToLoad); // Load the appropriate fragment
        }
    }
    // Find the back button and set an OnClickListener

    // Method to navigate back to MainActivity
    private void navigateBackToMainActivity() {
        Intent intent = new Intent(FragmentActivity.this, MainActivity.class);
        startActivity(intent);
        finish();  // Close the current FragmentActivity
    }

    // Method to load the fragment based on the name passed
    private void loadFragment(String fragmentName) {
        Fragment fragment = null;

        if (fragmentName.equals("fragment1")) {
            fragment = new Fragment1(); // Create instance of Fragment1
        } else if (fragmentName.equals("fragment2")) {
            fragment = new Fragment2(); // Create instance of Fragment2
        }

        // Replace the fragment_container with the selected fragment
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, fragment); // Assuming fragment_container is in the layout
            transaction.commit();
        }
    }
}
