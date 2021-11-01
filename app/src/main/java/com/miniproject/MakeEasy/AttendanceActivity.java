package com.miniproject.MakeEasy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AttendanceActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MainAdapter adapter;
    String[] tabs={"Mark","View"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);


        tabLayout=findViewById(R.id.tab_layout);
        viewPager2=findViewById(R.id.ViewPager);

        adapter=new MainAdapter(this);
        adapter.AddFragment(new MarkAttendanceFragment(),"Mark");
        adapter.AddFragment(new ViewAttendanceFragment(),"View");

        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout,viewPager2,(tab, position) -> tab.setText(tabs[position])).attach();

    }
    private class MainAdapter extends FragmentStateAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> StringArrayList = new ArrayList<>();

        public MainAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        public void AddFragment(Fragment fragment, String s){
            fragmentArrayList.add(fragment);
            StringArrayList.add(s);
        }


        @NonNull
        @NotNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentArrayList.size();
        }
    }

}