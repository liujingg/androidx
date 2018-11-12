package me.panpf.androidx.test.app.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import me.panpf.androidx.test.app.fragment.TestSupportFragment;

/**
 * <P>Created by Vincent on 2018/11/9.</P>
 */
public class DialogxTestFragmentActivity extends FragmentActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getSupportFragmentManager().beginTransaction()
        .replace(android.R.id.content, new TestSupportFragment())
        .commit();
  }

  public Dialog getDialog() {
    return new Dialog(this);
  }
}
