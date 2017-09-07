package com.thinkinfotech;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.thinkinfotech.fragments.ArticleDetailsFragment;
import com.thinkinfotech.fragments.HeadlinesFragment;
import com.thinkinfotech.models.Article;

public class NewsActivity extends FragmentActivity implements HeadlinesFragment.OnHeadlineSelectedListener{
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment headLinesFragment ;
    private Fragment articeDetailsFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(myToolbar);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        headLinesFragment = new HeadlinesFragment();
        fragmentTransaction.replace(R.id.container, headLinesFragment, "HeadlinesList");
        fragmentTransaction.commit();
    }

    @Override
    public void onHeadlineSelected(Article article) {
        if(article != null) {
            articeDetailsFragment = ArticleDetailsFragment.newInstance(article.getUrl());
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.container, articeDetailsFragment, "ArticleDetailsView");
            fragmentTransaction.addToBackStack("HeadlinesList");
            fragmentTransaction.commit();

        }

    }
}
