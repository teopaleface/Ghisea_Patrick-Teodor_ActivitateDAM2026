package ro.ase.ghisea.laborator23;

import android.os.Bundle;

public class MainActivity extends LifecycleActivity {
  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    showScreen(R.layout.activity_main);
    findViewById(R.id.back).setOnClickListener(v -> finish());
  }
}
