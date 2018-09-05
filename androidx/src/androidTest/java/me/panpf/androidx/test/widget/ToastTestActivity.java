package me.panpf.androidx.test.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

public class ToastTestActivity extends FragmentActivity {

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(me.panpf.androidx.test.R.layout.at_test);
        this.getSupportFragmentManager().beginTransaction().replace(me.panpf.androidx.test.R.id.testAt_frame, new Fragment()).commit();
    }
}