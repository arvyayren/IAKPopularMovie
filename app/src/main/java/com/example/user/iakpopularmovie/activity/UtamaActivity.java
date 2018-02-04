package com.example.user.iakpopularmovie.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.user.iakpopularmovie.R;
import com.example.user.iakpopularmovie.fragment.FavoriteFragment;
import com.example.user.iakpopularmovie.fragment.NowPlayingFragment;
import com.example.user.iakpopularmovie.fragment.PopularFragment;
import com.example.user.iakpopularmovie.fragment.TopRateFragment;

public class UtamaActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Bundle b;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (b==null) {
                switch (item.getItemId()) {
                    case R.id.Now_Playing:
                        getSupportFragmentManager().beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.layout_untuk_fragment, new NowPlayingFragment())
                                .commit();
                        return true;
                    case R.id.Popular:
                        getSupportFragmentManager().beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.layout_untuk_fragment, new PopularFragment())
                                .commit();
                        return true;
                    case R.id.Up_Coming:
                        getSupportFragmentManager().beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.layout_untuk_fragment, new TopRateFragment())
                                .commit();
                        return true;
                    case R.id.Favorite:
                        getSupportFragmentManager().beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                                .replace(R.id.layout_untuk_fragment, new FavoriteFragment())
                                .commit();
                        return true;
                }
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utama);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .add(R.id.layout_untuk_fragment, new NowPlayingFragment())
                    .commit();
        }

    }
}
