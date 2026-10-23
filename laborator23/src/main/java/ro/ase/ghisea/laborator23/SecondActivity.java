package ro.ase.ghisea.laborator23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activitatea 2 este launcher; activitatea 1 rămâne accesibilă pentru demonstrarea lifecycle-ului.
 */
public class SecondActivity extends LifecycleActivity {
  private static final int REQUEST_SUM = 23;

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    showScreen(R.layout.activity_second);
    findViewById(R.id.open_main)
        .setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    findViewById(R.id.send).setOnClickListener(v -> send());
  }

  private void send() {
    EditText first = findViewById(R.id.first), second = findViewById(R.id.second);
    Integer a = read(first), b = read(second);
    if (a == null || b == null) return;
    String message = ((EditText) findViewById(R.id.message)).getText().toString().trim();
    if (message.isEmpty()) {
      ((EditText) findViewById(R.id.message)).setError("Introdu un mesaj");
      return;
    }
    Bundle data = new Bundle();
    data.putString("message", message);
    data.putInt("first", a);
    data.putInt("second", b);
    Intent intent = new Intent(this, ThirdActivity.class);
    intent.putExtras(data);
    // API-ul clasic este păstrat aici pentru ilustrarea mecanismului predat în laborator.
    startActivityForResult(intent, REQUEST_SUM);
  }

  private Integer read(EditText field) {
    try {
      return Integer.parseInt(field.getText().toString().trim());
    } catch (NumberFormatException e) {
      field.setError("Introdu un număr întreg pe 32 de biți");
      return null;
    }
  }

  @Override
  protected void onActivityResult(int request, int result, Intent data) {
    super.onActivityResult(request, result, data);
    if (request == REQUEST_SUM && result == RESULT_OK && data != null) {
      String message = data.getStringExtra("reply");
      long sum = data.getLongExtra("sum", 0);
      String text = message + "\nRezultat: " + sum;
      ((TextView) findViewById(R.id.result)).setText(text);
      Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }
  }
}
