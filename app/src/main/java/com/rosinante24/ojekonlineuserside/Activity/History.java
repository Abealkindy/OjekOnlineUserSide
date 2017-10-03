package com.rosinante24.ojekonlineuserside.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.rosinante24.ojekonlineuserside.Adapter.CustomAdapter;
import com.rosinante24.ojekonlineuserside.R;

public class History extends AppCompatActivity {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        tablayout.addTab(tablayout.newTab().setText("Proses"));
        tablayout.addTab(tablayout.newTab().setText("Selesai"));
        CustomAdapter adapter = new CustomAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
//
//    private void addTabs(ViewPager pager) {
//
//        adapter.addFrag(new ProsesFragment(), "Proses");
//        adapter.addFrag(new Selesai(), "Proses");
//
//
//    }
}
