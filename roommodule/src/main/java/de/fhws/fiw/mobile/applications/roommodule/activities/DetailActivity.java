package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.PagerAdapter;
import de.fhws.fiw.mobile.applications.roommodule.fragments.DetailInformationFragment;
import de.fhws.fiw.mobile.applications.roommodule.fragments.UsedRoomsFragment;
import de.fhws.fiw.mobile.applications.roommodule.transformer.DepthPageTransformer;

/**
 * Created by student on 09.06.16.
 */
public class DetailActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabview);

        initViewPagerAndTabs();

        // TODO: ueberpruefen ob OK
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Room Detail");

    }

    private void initViewPagerAndTabs() {
        ViewPager viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewPager);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new DetailInformationFragment(), "RoomDetail");
        //pagerAdapter.addFragment(new TimetableFragment(), "Timetable");
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}
