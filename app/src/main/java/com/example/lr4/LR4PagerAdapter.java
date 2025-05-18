package com.example.lr4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class LR4PagerAdapter extends FragmentStateAdapter {

    public LR4PagerAdapter(@NonNull AppCompatActivity act) { super(act); }

    @NonNull @Override
    public Fragment createFragment(int pos) {
        return pos == 0 ? new ValuesFragment() : new GraphFragment();
    }

    @Override public int getItemCount() { return 2; }
}
