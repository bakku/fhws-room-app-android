package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.FreeRoomsAdapter;
import de.fhws.fiw.mobile.applications.roommodule.adapter.PagerAdapter;
import de.fhws.fiw.mobile.applications.roommodule.adapter.UsedRoomsAdapter;
import de.fhws.fiw.mobile.applications.roommodule.fragments.FreeRoomsFragment;
import de.fhws.fiw.mobile.applications.roommodule.fragments.UsedRoomsFragment;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import de.fhws.fiw.mobile.applications.roommodule.transformer.DepthPageTransformer;


/**
 * Created by Patrick Müller on 09.06.2016.
 */
public class OverviewActivity extends AppCompatActivity{

    public SwipeRefreshLayout swipeContainer;

    private FreeRoomsFragment freeRoomsFragment;

    private UsedRoomsFragment usedRoomsFragment;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabview_with_pull_to_update);


        initViewPagerAndTabs();

        final FreeRoomsAdapter freeRoomsAdapter = this.freeRoomsFragment.getFreeRoomsAdapter();
        final UsedRoomsAdapter usedRoomsAdapter = this.usedRoomsFragment.getUsedRoomsAdapter();

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeColors(R.color.colorPrimary, R.color.colorAccent);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                freeRoomsAdapter.clear();
                usedRoomsAdapter.clear();

                freeRoomsAdapter.addAllFreeRooms(RoomData.getInstance().getFreeRooms());
                usedRoomsAdapter.addAllUsedRooms(RoomData.getInstance().getUsedRooms());

                swipeContainer.setRefreshing(false); //signal complete referesh
            }
        });
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
        ViewPager viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewPagerOverview);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        this.freeRoomsFragment = new FreeRoomsFragment();
        this.usedRoomsFragment = new UsedRoomsFragment();
        pagerAdapter.addFragment(this.freeRoomsFragment, "Frei");
        pagerAdapter.addFragment(this.usedRoomsFragment, "Belegt");
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutOverview);
        tabLayout.setupWithViewPager(viewPager);
    }

}