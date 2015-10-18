package com.rippleworks.letsmeet;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String name,branch;
    public static final String USER_NAME="name";
    public static final String USER_BRANCH="branch";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HomeFragment homefragment;
    private int TabIcons[] = { R.mipmap.ic_home, R.mipmap.ic_profile, R.mipmap.ic_leaderboard};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager=(ViewPager)findViewById(R.id.viewpager);

        Intent intent=this.getIntent();
        if(intent!=null)
        name=intent.getStringExtra("name");
        //branch=intent.getStringExtra("branch");

        Bundle bundle=new Bundle();
        bundle.putString(USER_NAME,name);
        homefragment=new HomeFragment();
        homefragment.setArguments(bundle);

        tabLayout=(TabLayout)findViewById(R.id.tabs);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        for(int i=0; i<tabLayout.getTabCount(); ++i)
        {
            tabLayout.getTabAt(i).setIcon(TabIcons[i]);
        }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
          }
        });
        */
    }
    private  void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(homefragment, "Home");
        viewPagerAdapter.addFragment(new ProfileFragment(), "Profile");
        viewPagerAdapter.addFragment(new LeaderboardFragment(), "Leaderboard");
        viewPager.setAdapter(viewPagerAdapter);

    }

  class ViewPagerAdapter extends FragmentPagerAdapter
  {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String string) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(string);
    }

    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);

    }
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else if(id == R.id.action_facebook)
        {
            startActivity(new Intent(this, FacebookLoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
