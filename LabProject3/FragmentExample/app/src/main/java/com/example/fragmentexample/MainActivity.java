package com.example.fragmentexample;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Dynamically add MyFragment
        MyFragment myFragment = new MyFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, myFragment);
        transaction.commit();
    }

    public void sendDataToFragment2(String message) {
        Fragment2 fragment2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment2 != null) {
            fragment2.updateData(message);
        }
    }
}
