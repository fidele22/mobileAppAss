package com.example.androidapp.ui.networkactivity;

import com.example.androidapp.R;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.tabs.TabLayout;
import android.widget.FrameLayout;
import androidx.fragment.app.Fragment;
public class NetworkActivity extends AppCompatActivity {

    private TabLayout tabLayout;

    private FrameLayout frameContainer;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_network);


        tabLayout = findViewById(R.id.tab_layout);

        frameContainer = findViewById(R.id.frame_container);


        setupTabLayout();

    }


    private void setupTabLayout() {

        tabLayout.addTab(tabLayout.newTab().setText("Form"));

        tabLayout.addTab(tabLayout.newTab().setText("Data"));


        // Load the first fragment by default

        loadFragment(new FragmentForm());


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override

            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {

                    case 0:

                        loadFragment(new FragmentForm());

                        break;

                    case 1:

                        loadFragment(new DataFragment());

                        break;

                }

            }


            @Override

            public void onTabUnselected(TabLayout.Tab tab) {}


            @Override

            public void onTabReselected (TabLayout.Tab tab) {}

        });

    }


    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frame_container, fragment);

        fragmentTransaction.commit();

    }

}