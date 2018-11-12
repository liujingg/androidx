package me.panpf.androidx.test.app.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import me.panpf.androidx.test.app.fragment.TestOriginFragment;

/**
 * <P>Created by Vincent on 2018/11/9.</P>
 */
public class DialogxTestActivity extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getFragmentManager().beginTransaction()
        .replace(android.R.id.content, new TestOriginFragment())
        .commit();
  }

  public Dialog getDialog() {
    return new Dialog(this);
  }
}
