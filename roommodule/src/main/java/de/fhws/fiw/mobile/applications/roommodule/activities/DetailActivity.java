package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.PagerAdapter;
import de.fhws.fiw.mobile.applications.roommodule.fragments.DetailInformationFragment;
import de.fhws.fiw.mobile.applications.roommodule.fragments.TimetableFragment;
import de.fhws.fiw.mobile.applications.roommodule.transformer.DepthPageTransformer;

/**
 * Created by student on 09.06.16.
 */
public class DetailActivity extends AppCompatActivity {

    private String nameOfRoom;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabview);

        Intent intent = getIntent();

        this.nameOfRoom = intent.getExtras().getString("nameOfRoom");

        initViewPagerAndTabs();


        // TODO: ueberpruefen ob OK
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Room Detail");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    private void initViewPagerAndTabs() {
        ViewPager viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewPager_detail);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new DetailInformationFragment(), "RoomDetail");
        pagerAdapter.addFragment(TimetableFragment.newInstance(this.nameOfRoom), "Timetable");
        viewPager.setAdapter(pagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout_detail);
        tabLayout.setupWithViewPager(viewPager);
    }
}
