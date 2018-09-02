package me.panpf.androidx.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class TestSupportActivity extends FragmentActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.at_test);
        this.getSupportFragmentManager().beginTransaction().replace(R.id.testAt_frame, new Fragment()).commit();
    }
}