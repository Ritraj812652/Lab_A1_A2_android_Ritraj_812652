package com.example.lab_a1_a2_android_ritraj_812652.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.lab_a1_a2_android_ritraj_812652.Fragments.ProductsFragment;
import com.example.lab_a1_a2_android_ritraj_812652.Fragments.ProviderFragment;


public class MViewPagerAdapter extends FragmentStateAdapter {

    private Context myContext;
    int totalTabs;

    public MViewPagerAdapter(Context context, FragmentManager fm, Lifecycle lifecycle, int totalTabs) {
        super(fm,lifecycle);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0)
            return new ProductsFragment();
        if(position==1)
            return new ProviderFragment();
        return null;
    }

    @Override
    public int getItemCount() {
        return totalTabs;
    }
}