package ro.ase.ghisea.laborator23;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ThirdActivity extends LifecycleActivity {
  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    showScreen(R.layout.activity_third);
    Bundle data = getIntent().getExtras();
    if (data == null || !data.containsKey("first") || !data.containsKey("second")) {
      Toast.makeText(this, "Lipsesc datele de intrare", Toast.LENGTH_LONG).show();
      finish();
      return;
    }
    int a = data.getInt("first"), b = data.getInt("second");
    String message = data.getString("message", "");
    String received = "Mesaj: " + message + "\nValoarea 1: " + a + "\nValoarea 2: " + b;
    ((TextView) findViewById(R.id.received)).setText(received);
    Toast.makeText(this, message + "\nValori: " + a + ", " + b, Toast.LENGTH_LONG).show();
    // Conversia la long înainte de adunare evită overflow pentru două valori int valide.
    long sum = (long) a + b;
    ((TextView) findViewById(R.id.sum)).setText(a + " + " + b + " = " + sum);
    findViewById(R.id.return_result)
        .setOnClickListener(
            v -> {
              Bundle reply = new Bundle();
              reply.putString("reply", "Calcul efectuat cu succes!");
              reply.putLong("sum", sum);
              Intent result = new Intent();
              result.putExtras(reply);
              setResult(RESULT_OK, result);
              finish();
            });
    findViewById(R.id.cancel).setOnClickListener(v -> finish());
  }
}
