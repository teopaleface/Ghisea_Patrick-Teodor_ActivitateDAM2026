package ro.ase.ghisea.laborator23;

import android.os.Bundle;
import android.util.Log;

/** Toate cele trei activități moștenesc logarea; tagul comun permite filtrarea în Logcat. */
public abstract class LifecycleActivity extends ScreenActivity {
  private static final String TAG = "LifecycleDemo";

  private void record(String event) {
    String message = getClass().getSimpleName() + " · " + event;
    // Nivelurile sunt demonstrate intenționat; E și W de aici nu indică o eroare reală.
    Log.e(TAG, message + " [Error demo]");
    Log.w(TAG, message + " [Warning demo]");
    Log.d(TAG, message + " [Debug]");
    Log.i(TAG, message + " [Info]");
    Log.v(TAG, message + " [Verbose]");
  }

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    record("onCreate");
  }

  // Activitatea devine vizibilă utilizatorului.
  @Override
  protected void onStart() {
    super.onStart();
    record("onStart");
  }

  // Activitatea ajunge în prim-plan, pregătită pentru interacțiune.
  @Override
  protected void onResume() {
    super.onResume();
    record("onResume");
  }

  // Activitatea pierde prim-planul; aici se opresc operațiile care cer interacțiune activă.
  @Override
  protected void onPause() {
    record("onPause");
    super.onPause();
  }

  // Activitatea nu mai este vizibilă; se eliberează resursele legate de vizibilitate.
  @Override
  protected void onStop() {
    record("onStop");
    super.onStop();
  }
}
