package com.thinkinfotech;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.thinkinfotech.fragments.HeadlinesFragment;

public class NewsActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment headLinesLisFragment ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(myToolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        headLinesLisFragment = new HeadlinesFragment();
        fragmentTransaction.replace(R.id.container, headLinesLisFragment, "HeadlinesList");
        fragmentTransaction.commit();
    }

    @Override
    public void onHeadlineSelected(int index) {

    }
}
