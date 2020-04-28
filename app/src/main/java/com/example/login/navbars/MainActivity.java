package com.example.login.navbars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.login.FavoriteFragment;
import com.example.login.HomeFragment;
import com.example.login.PetFragment;
import com.example.login.R;
import com.example.login.SearchFragment;
import com.example.login.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNav);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();

        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.Home:
                        fragment = new HomeFragment();
                        break;
                    case R.id.Favorite:
                        fragment = new FavoriteFragment();
                        break;
                    case R.id.Search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.Pet:
                        fragment = new PetFragment();
                        break;
                    case R.id.Shop:
                        fragment = new ShopFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });
    }
}
