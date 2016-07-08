package de.fhws.fiw.mobile.applications.roommodule.activities;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import de.fhws.fiw.mobile.applications.roommodule.R;
import de.fhws.fiw.mobile.applications.roommodule.adapter.FreeRoomsAdapter;
import de.fhws.fiw.mobile.applications.roommodule.adapter.PagerAdapter;
import de.fhws.fiw.mobile.applications.roommodule.adapter.UsedRoomsAdapter;
import de.fhws.fiw.mobile.applications.roommodule.fragments.FreeRoomsFragment;
import de.fhws.fiw.mobile.applications.roommodule.fragments.SearchFragment;
import de.fhws.fiw.mobile.applications.roommodule.fragments.UsedRoomsFragment;
import de.fhws.fiw.mobile.applications.roommodule.models.Room;
import de.fhws.fiw.mobile.applications.roommodule.models.RoomData;
import de.fhws.fiw.mobile.applications.roommodule.network.DownloadListener;
import de.fhws.fiw.mobile.applications.roommodule.transformer.DepthPageTransformer;


/**
 * Created by Patrick Müller on 09.06.2016.
 */
public class OverviewActivity extends AppCompatActivity implements MenuItemCompat.OnActionExpandListener, DownloadListener {

    public SwipeRefreshLayout swipeContainer;

    private FreeRoomsFragment freeRoomsFragment;

    private UsedRoomsFragment usedRoomsFragment;

    private SearchFragment searchFragment;

    private SearchView searchView;

    private FreeRoomsAdapter freeRoomsAdapter;

    private UsedRoomsAdapter usedRoomsAdapter;

    private ProgressBar progressBar;

    private boolean swipeUpdate = false;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabview_with_pull_to_update);

        progressBar = (ProgressBar) findViewById(R.id.overviewProgressBar);
        initViewPagerAndTabs();
        setBehaviourOfPullToUpdate();
    }

    private void setBehaviourOfPullToUpdate(){
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setColorSchemeColors(R.color.colorPrimary, R.color.colorAccent);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                freeRoomsAdapter.clear();
                usedRoomsAdapter.clear();

                swipeUpdate = true;

                RoomData.updateData(OverviewActivity.this, true);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_search);
        this.searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        this.searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        MenuItemCompat.setOnActionExpandListener(menuItem, this);

        return true;
    }

    private void initViewPagerAndTabs() {
        ViewPager viewPager = (android.support.v4.view.ViewPager) findViewById(R.id.viewPagerOverview);
        viewPager.setPageTransformer(true, new DepthPageTransformer());

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        this.freeRoomsFragment = new FreeRoomsFragment();
        this.freeRoomsAdapter = new FreeRoomsAdapter(RoomData.getInstance(this, false).getFreeRooms(), R.layout.room_list_entry);
        this.freeRoomsFragment.setAdapter(freeRoomsAdapter);

        this.usedRoomsFragment = new UsedRoomsFragment();
        this.usedRoomsAdapter = new UsedRoomsAdapter(RoomData.getInstance(this, false).getUsedRooms(), R.layout.room_list_entry);
        this.usedRoomsFragment.setAdapter(usedRoomsAdapter);

        pagerAdapter.addFragment(this.freeRoomsFragment, "Frei");
        pagerAdapter.addFragment(this.usedRoomsFragment, "Belegt");
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutOverview);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        this.searchFragment = new SearchFragment();
        searchFragment.setSearchView(this.searchView);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.overview_root_layout, this.searchFragment)
                .commit();

        swipeContainer.setVisibility(View.GONE);

        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        swipeContainer.setVisibility(View.VISIBLE);

        getSupportFragmentManager()
                .beginTransaction()
                .remove(this.searchFragment)
                .commit();

        this.searchFragment = null;

        return true;
    }

    @Override
    public void onDownloadStarted() {
        if (swipeUpdate == false) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onOneRoomFinished(Room room) {
        if (room.roomIsFree()) {
            freeRoomsAdapter.addFreeRoom(room);
        }
        else {
            usedRoomsAdapter.addUsedRoom(room);
        }
    }

    @Override
    public void onDownloadSuccess() {
        if (swipeUpdate) {
            swipeContainer.setRefreshing(false);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDownloadError() {
        if (swipeUpdate) {
            swipeContainer.setRefreshing(false);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }
}