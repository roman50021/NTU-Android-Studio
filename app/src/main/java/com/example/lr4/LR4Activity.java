package com.example.lr4;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.lr1.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LR4Activity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr4);

        ViewPager2 pager = findViewById(R.id.viewPager);
        TabLayout   tabs  = findViewById(R.id.tabLayout);

        pager.setAdapter(new LR4PagerAdapter(this));

        new TabLayoutMediator(tabs, pager,
                (tab, pos) -> tab.setText(pos == 0 ? "Точки" : "Графік")
        ).attach();
    }
}
