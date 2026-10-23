package ro.ase.ghisea.laborator4;

import android.os.Bundle;

/**
 * Varianta cu design asistat de AI; păstrează toate controalele și contractul variantei de bază.
 */
public class AddAiActivity extends FormActivity {
  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    initialize(R.layout.activity_add_ai);
  }
}
