package com.challenge.omurkumru.mvvm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.challenge.omurkumru.mvvm.ui.entrance.EntranceFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EntranceFragment homeFragment = new EntranceFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, homeFragment).commit();
    }
}
