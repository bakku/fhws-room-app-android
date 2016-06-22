package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.PagerAdapter;
import de.fhws.fiw.mobile.applications.roommodule.transformer.DepthPageTransformer;


/**
 * Created by Patrick Müller on 09.06.2016.
 */
public class OverviewActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    private void initViewPagerAndTabs() {
        ViewPager viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        //pagerAdapter.addFragment(new ProfessorFragment(), "Professors");
        //pagerAdapter.addFragment(new UniversityPersonalFragment(), "Ordinary");
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

}