package ro.ase.ghisea.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends ScreenActivity {
  private String savedResult = "Niciun pachet adăugat încă.";

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    showScreen(R.layout.activity_main);
    if (state != null) savedResult = state.getString("result", savedResult);
    ((TextView) findViewById(R.id.result)).setText(savedResult);
    findViewById(R.id.manual)
        .setOnClickListener(v -> startActivityForResult(new Intent(this, AddActivity.class), 4));
    findViewById(R.id.ai)
        .setOnClickListener(v -> startActivityForResult(new Intent(this, AddAiActivity.class), 4));
  }

  @Override
  protected void onActivityResult(int request, int result, Intent data) {
    super.onActivityResult(request, result, data);
    if (request == 4 && result == RESULT_OK && data != null && data.hasExtra("pachet")) {
      PachetFotografic p = PachetFotografic.fromBundle(data.getBundleExtra("pachet"));
      savedResult = p.toString();
      ((TextView) findViewById(R.id.result)).setText(savedResult);
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle out) {
    out.putString("result", savedResult);
    super.onSaveInstanceState(out);
  }
}
