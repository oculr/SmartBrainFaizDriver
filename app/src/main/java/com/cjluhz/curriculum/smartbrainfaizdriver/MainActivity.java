package com.cjluhz.curriculum.smartbrainfaizdriver;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.cjluhz.curriculum.smartbrainfaizdriver.ui.delta.DeltaFragment;
import com.cjluhz.curriculum.smartbrainfaizdriver.ui.faiz.FaizFragment;
import com.cjluhz.curriculum.smartbrainfaizdriver.ui.kaixa.KaixaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setSelectedItemId(R.id.navigation_faiz);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                int id = item.getItemId();
                switch (id){
                    case R.id.navigation_faiz:
                        fm.beginTransaction()
                                .replace(R.id.nav_host_fragment, new FaizFragment())
                                .commit();
                        break;
                    case R.id.navigation_kaixa:
                        fm.beginTransaction()
                                .replace(R.id.nav_host_fragment, new KaixaFragment())
                                .commit();
                        break;
                    case R.id.navigation_delta:
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.nav_host_fragment, new DeltaFragment())
                                .commit();
                        break;
                }
                return false;
            }
        });

    }

}