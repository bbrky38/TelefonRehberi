package com.example.telefonrehberi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class RehberActivity extends AppCompatActivity {
    SQLiteDatabase database;
    ListView listView;
    ArrayList<Rehber> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rehber);

        database = openOrCreateDatabase("Rehber", MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS rehberler (id INTEGER PRIMARY KEY, ad TEXT, telefon TEXT, eposta TEXT)");

        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        arrayList.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM rehberler",null);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") Integer id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String ad = cursor.getString(cursor.getColumnIndex("ad"));
            @SuppressLint("Range") String telefon = cursor.getString(cursor.getColumnIndex("telefon"));
            @SuppressLint("Range") String eposta = cursor.getString(cursor.getColumnIndex("eposta"));
            arrayList.add(new Rehber(id, ad, telefon, eposta));
        }
        cursor.close();
        RehberAdapter adapter = new RehberAdapter(arrayList, this);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

    }

    public void buttonYeni(View view) {
        Intent intent = new Intent(this, RehberYeniActivity.class);
        startActivity(intent);
    }
}