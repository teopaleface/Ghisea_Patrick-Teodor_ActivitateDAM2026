package ro.ase.ghisea.laborator4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;

/** Același contract de date și aceeași validare pentru cele două variante de formular. */
public abstract class FormActivity extends ScreenActivity {
  protected void initialize(int layout) {
    showScreen(layout);
    Spinner category = findViewById(R.id.category);
    ArrayAdapter<String> adapter =
        new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            new String[] {"Portret", "Eveniment", "Produs", "Peisaj"});
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    category.setAdapter(adapter);
    findViewById(R.id.save).setOnClickListener(v -> save());
    findViewById(R.id.cancel).setOnClickListener(v -> finish());
  }

  private void save() {
    EditText name = findViewById(R.id.name),
        price = findViewById(R.id.price),
        duration = findViewById(R.id.duration);
    String n = name.getText().toString().trim();
    if (n.isEmpty()) {
      name.setError("Completează denumirea");
      return;
    }
    double p;
    int d;
    try {
      p = Double.parseDouble(price.getText().toString().trim().replace(',', '.'));
      if (!Double.isFinite(p) || p <= 0) throw new NumberFormatException();
    } catch (NumberFormatException e) {
      price.setError("Prețul trebuie să fie pozitiv");
      return;
    }
    try {
      d = Integer.parseInt(duration.getText().toString().trim());
      if (d <= 0) throw new NumberFormatException();
    } catch (NumberFormatException e) {
      duration.setError("Durata trebuie să fie un număr întreg pozitiv");
      return;
    }
    RadioGroup location = findViewById(R.id.location);
    int selected = location.getCheckedRadioButtonId();
    String l = ((RadioButton) findViewById(selected)).getText().toString();
    PachetFotografic object =
        new PachetFotografic(
            n,
            p,
            d,
            ((Spinner) findViewById(R.id.category)).getSelectedItem().toString(),
            l,
            ((CheckBox) findViewById(R.id.album)).isChecked(),
            ((Switch) findViewById(R.id.editing)).isChecked(),
            ((RatingBar) findViewById(R.id.rating)).getRating());
    Bundle bundle = new Bundle();
    bundle.putBundle("pachet", object.toBundle());
    Intent result = new Intent();
    result.putExtras(bundle);
    setResult(RESULT_OK, result);
    finish();
  }
}
