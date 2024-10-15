package com.example.androidapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.androidapp.databinding.ActivityMainBinding;
import com.example.androidapp.ui.more.MoreFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Setting up AppBarConfiguration for top-level destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard,
                R.id.navigation_notifications, R.id.navigation_news)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Handle bottom navigation item selections
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_more) {
                    // Display MoreFragment as a bottom sheet
                    showMoreFragment();
                    return true;
                } else {
                    // Check if fragment_container is currently displayed
                    if (isFragmentContainerVisible()) {
                        // Hide fragment_container and show nav_host_fragment_activity_main
                        hideFragmentContainer();
                    }
                    // Let NavigationUI handle the rest of the items
                    return NavigationUI.onNavDestinationSelected(item, navController);
                }
            }
        });
    }

    // Method to show MoreFragment as a BottomSheet
    private void showMoreFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        MoreFragment moreFragment = new MoreFragment();
        moreFragment.show(fragmentManager, moreFragment.getTag());
        hideNavHostFragment();
    }

    // Method to check if fragment_container is visible
    private boolean isFragmentContainerVisible() {
        return findViewById(R.id.fragment_container).getVisibility() == View.VISIBLE;
    }

    // Method to hide the fragment_container and show the nav_host_fragment_activity_main
    private void hideFragmentContainer() {
        findViewById(R.id.fragment_container).setVisibility(View.GONE);
        findViewById(R.id.nav_host_fragment_activity_main).setVisibility(View.VISIBLE);
    }

    // Method to hide the nav_host_fragment when showing the MoreFragment
    private void hideNavHostFragment() {
        findViewById(R.id.nav_host_fragment_activity_main).setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        // Check if there are any fragments in the back stack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }

        // Check if we are returning to the main navigation flow
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            hideFragmentContainer();
        }
    }
}
