package com.example.androidapp.ui.tabbedactivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.androidapp.R;
import com.example.androidapp.ui.fragmentactivity.Fragment4;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabbedActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed);

        // Enable "Up" navigation
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        // Set up the ViewPager with the sections adapter.
        FragmentPagerAdapter pagerAdapter = new FragmentPagerAdapter(this);
        viewPager.setAdapter(pagerAdapter);


        // Link TabLayout with ViewPager and set custom tab names
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Car List");  // Custom title for the first tab
                            break;
                        case 1:
                            tab.setText("Others");  // Custom title for the second tab
                            break;
                    }
                }
        ).attach();
    }



    @Override
    public boolean onSupportNavigateUp() {
        // Navigate back to MainActivity when "Up" is pressed
        finish(); // Close this activity
        return true; // Indicates that we handled the event
    }

    // ViewPager Adapter to manage fragments
    private static class FragmentPagerAdapter extends FragmentStateAdapter {
        public FragmentPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new Fragment3(); // Fragment3 for car list
            } else {
                return new Fragment4(); // Fragment4 for other UI
            }
        }

        @Override
        public int getItemCount() {
            return 2; // We have 2 fragments
        }
    }
}
