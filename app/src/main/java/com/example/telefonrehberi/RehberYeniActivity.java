package com.example.telefonrehberi;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RehberYeniActivity extends AppCompatActivity {

    SQLiteDatabase database;
    EditText editTextAd, editTextTelefon, editTextEPosta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehber_yeni);

        database = openOrCreateDatabase("Rehber", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS rehberler (id INTEGER PRIMARY KEY, ad TEXT, telefon TEXT, eposta TEXT)");

        editTextAd = findViewById(R.id.editTextAd);
        editTextTelefon = findViewById(R.id.editTextTelefon);
        editTextEPosta = findViewById(R.id.editTextEPosta);
    }

    public void buttonKaydet(View view) {
        String ad = editTextAd.getText().toString(),
               telefon = editTextTelefon.getText().toString(),
               eposta = editTextEPosta.getText().toString();

        SQLiteStatement sorgu = database.compileStatement("INSERT INTO rehberler (ad, telefon, eposta) VALUES (?, ?, ?)");
        sorgu.bindString(1, ad);
        sorgu.bindString(2, telefon);
        sorgu.bindString(3, eposta);
        sorgu.execute();
        Toast.makeText(this, "Kaydedildi", Toast.LENGTH_SHORT).show();
        finish();
    }
}