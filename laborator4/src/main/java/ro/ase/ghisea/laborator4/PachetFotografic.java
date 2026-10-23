package ro.ase.ghisea.laborator4;

import android.os.Bundle;
import java.util.Locale;

/** Obiect real: un pachet fotografic. Denumirea conține inițialele G, P și T. */
public final class PachetFotografic {
  public final String nume, categorie, locatie;
  public final double pret;
  public final int durata;
  public final boolean album, editare;
  public final float rating;

  public PachetFotografic(
      String n, double p, int d, String c, String l, boolean a, boolean e, float r) {
    nume = n;
    pret = p;
    durata = d;
    categorie = c;
    locatie = l;
    album = a;
    editare = e;
    rating = r;
  }

  public Bundle toBundle() {
    Bundle b = new Bundle();
    b.putString("nume", nume);
    b.putDouble("pret", pret);
    b.putInt("durata", durata);
    b.putString("categorie", categorie);
    b.putString("locatie", locatie);
    b.putBoolean("album", album);
    b.putBoolean("editare", editare);
    b.putFloat("rating", rating);
    return b;
  }

  public static PachetFotografic fromBundle(Bundle b) {
    return new PachetFotografic(
        b.getString("nume", ""),
        b.getDouble("pret"),
        b.getInt("durata"),
        b.getString("categorie", ""),
        b.getString("locatie", ""),
        b.getBoolean("album"),
        b.getBoolean("editare"),
        b.getFloat("rating"));
  }

  @Override
  public String toString() {
    return String.format(
        Locale.forLanguageTag("ro-RO"),
        "%s\n%s · %s · %d minute\n%.2f lei · %.1f / 5 stele\nAlbum: %s · Editare: %s",
        nume,
        categorie,
        locatie,
        durata,
        pret,
        rating,
        album ? "da" : "nu",
        editare ? "da" : "nu");
  }
}
