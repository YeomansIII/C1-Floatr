package me.floatr.ui.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.floatr.R;
import me.floatr.ui.fragments.HomeFragment;
import me.floatr.ui.fragments.LoginFragment;
import me.floatr.ui.fragments.OffersFragment;
import me.floatr.util.FloatrApiInterface;
import me.floatr.util.PreferenceNames;
import me.floatr.util.ServiceGenerator;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String TAG = MainActivity.class.getSimpleName();
    public static final String BASE_URL = "http://api.floatr.me/";
    public FloatrApiInterface apiService;
    public SharedPreferences preferences;
    Toolbar toolbar;
    View navHeaderView;
    TextView navHeadEmailTextView;
    TextView navHeadNameTextView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = getSharedPreferences(PreferenceNames.MAIN_PREFS_NAME, 0);

        if (preferences.getString(PreferenceNames.PREF_USER_TOKEN, null) == null) {
            apiService = ServiceGenerator.createService(FloatrApiInterface.class);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).addToBackStack(null).commit();
        } else {
            apiService = ServiceGenerator.createService(FloatrApiInterface.class, preferences.getString(PreferenceNames.PREF_USER_TOKEN, ""));
            finishLogin();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Log.d(TAG, "Nav press: " + item.getTitle());
        if (id == R.id.nav_home) {
            Log.d(TAG, "Nav home");
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new OffersFragment()).addToBackStack(null).commit();
            // Handle the camera action
        } else if (id == R.id.nav_offers) {

        } else if (id == R.id.nav_loans) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_logout) {
            preferences.edit().clear().apply();
            getSupportFragmentManager().beginTransaction().replace(R.id.container, new LoginFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setUpNavigation() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navHeaderView = navigationView.getHeaderView(0);
        navHeadNameTextView = (TextView) navHeaderView.findViewById(R.id.navHeaderName);
        navHeadEmailTextView = (TextView) navHeaderView.findViewById(R.id.navHeaderEmail);
        navHeadNameTextView.setText(preferences.getString(PreferenceNames.PREF_USER_FIRST_NAME, "") + " " + preferences.getString(PreferenceNames.PREF_USER_LAST_NAME, ""));
        navHeadEmailTextView.setText(preferences.getString(PreferenceNames.PREF_USER_USERNAME, ""));
    }

    public void finishLogin() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(drawerLayout.getWindowToken(), 0);
        setUpNavigation();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }
}
