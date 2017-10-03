package com.rosinante24.ojekonlineuserside.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rosinante24.ojekonlineuserside.Fragments.ProsesFragment;
import com.rosinante24.ojekonlineuserside.Fragments.Selesai;

/**
 * Created by KOCHOR on 9/15/2017.
 */

public class CustomAdapter extends FragmentStatePagerAdapter {

    public CustomAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new ProsesFragment();
        } else if (position == 1) {
            return new Selesai();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

}
