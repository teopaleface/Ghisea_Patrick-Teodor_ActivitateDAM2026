package ro.ase.ghisea.laborator23;

import android.app.Activity;
import android.view.View;

/** Aplică insets pentru barele sistemului inclusiv când targetSdk 35 impune edge-to-edge. */
public abstract class ScreenActivity extends Activity {
  protected void showScreen(int layout) {
    setContentView(layout);
    View root = findViewById(android.R.id.content);
    root.setOnApplyWindowInsetsListener(
        (v, insets) -> {
          v.setPadding(
              insets.getSystemWindowInsetLeft(),
              insets.getSystemWindowInsetTop(),
              insets.getSystemWindowInsetRight(),
              insets.getSystemWindowInsetBottom());
          return insets;
        });
    root.requestApplyInsets();
  }
}
