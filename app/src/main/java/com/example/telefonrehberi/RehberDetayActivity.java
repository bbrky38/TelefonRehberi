package com.example.telefonrehberi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RehberDetayActivity extends AppCompatActivity {

    SQLiteDatabase database;
    EditText editTextAd, editTextTelefon, editTextEPosta;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehber_detay);

        database = openOrCreateDatabase("Rehber", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS rehberler (id INTEGER PRIMARY KEY, ad TEXT, telefon TEXT, eposta TEXT)");

        editTextAd = findViewById(R.id.editTextAd);
        editTextTelefon = findViewById(R.id.editTextTelefon);
        editTextEPosta = findViewById(R.id.editTextEPosta);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        editTextAd.setText(intent.getStringExtra("ad"));
        editTextTelefon.setText(intent.getStringExtra("telefon"));
        editTextEPosta.setText(intent.getStringExtra("eposta"));
    }


    public void buttonDuzenle(View view) {
        Button button = (Button) view;
        if(button.getText().toString().equals("Düzenle")) {
            editTextAd.setEnabled(true);
            editTextTelefon.setEnabled(true);
            editTextEPosta.setEnabled(true);
            button.setText("Kaydet");
        } else {
            SQLiteStatement sorgu = database.compileStatement("UPDATE rehberler SET ad=?, telefon=?, eposta=? WHERE id=?");
            sorgu.bindString(1, editTextAd.getText().toString());
            sorgu.bindString(2, editTextTelefon.getText().toString());
            sorgu.bindString(3, editTextEPosta.getText().toString());
            sorgu.bindLong(4, id);
            sorgu.execute();
            Intent intent = new Intent(this, RehberActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Düzenlendi", Toast.LENGTH_SHORT).show();
        }
    }

    public void buttonSil(View view) {
        SQLiteStatement sorgu = database.compileStatement("DELETE FROM rehberler WHERE id=?");
        sorgu.bindLong(1, id);
        sorgu.execute();
        Intent intent = new Intent(this, RehberActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Silindi", Toast.LENGTH_SHORT).show();
    }
}