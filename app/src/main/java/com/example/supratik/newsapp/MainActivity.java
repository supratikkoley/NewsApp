package com.example.supratik.newsapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.androidnetworking.AndroidNetworking;
import com.example.supratik.newsapp.Fragments.BuissnessFragment;
import com.example.supratik.newsapp.Fragments.EntertainmentFragment;
import com.example.supratik.newsapp.Fragments.IndiaFragment;
import com.example.supratik.newsapp.Fragments.ScienceFragment;
import com.example.supratik.newsapp.Fragments.SportsFragment;
import com.example.supratik.newsapp.Fragments.TechFragment;
import com.example.supratik.newsapp.Fragments.WorldFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        //viewPager = (ViewPager) findViewById(R.id.viewpager);
        AndroidNetworking.initialize(getApplicationContext());
        setupViewPager(viewPager);

        //ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //viewPager.setAdapter(adapter);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFrag(new IndiaFragment(), "India");
        viewPagerAdapter.addFrag(new WorldFragment(), "World");
        viewPagerAdapter.addFrag(new BuissnessFragment(), "Buissness");
        viewPagerAdapter.addFrag(new TechFragment(), "Technology");
        viewPagerAdapter.addFrag(new SportsFragment(), "Sports");
        viewPagerAdapter.addFrag(new ScienceFragment(), "Science");
        viewPagerAdapter.addFrag(new EntertainmentFragment(), "Entertainment");

        viewPager.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}