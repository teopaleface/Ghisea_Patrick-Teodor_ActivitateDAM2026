package ro.ase.ghisea.laborator4;

import android.os.Bundle;

/** Varianta de bază, cu controale standard și aranjare simplă. */
public class AddActivity extends FormActivity {
  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    initialize(R.layout.activity_add);
  }
}
