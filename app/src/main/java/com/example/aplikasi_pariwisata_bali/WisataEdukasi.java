package com.example.aplikasi_pariwisata_bali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.aplikasi_pariwisata_bali.model.Items;
import com.example.aplikasi_pariwisata_bali.model.WisataModel;

import java.util.ArrayList;

public class WisataEdukasi extends AppCompatActivity {
    private ListView listView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_edukasi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.list);
        String[] values = new String[]{"Monumen Bajrasandhi","Art Center", "The Soekarno Center",
                "Museum Bali",
                "Kapal Karam USS Liberty",
                "Museum Blanco Renaissance",
                "Tirta Gangga"};

        ArrayList<WisataModel> data = new ArrayList<>();

        for (int i = 0; i < Items.nameBelajar.length; i++) {
            data.add(new WisataModel(
                    Items.nameBelajar[i],
                    Items.locationBelajar[i],
                    Items.descBelajar[i],
                    Items.imgBelajar[i]
            ));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("data", data.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}