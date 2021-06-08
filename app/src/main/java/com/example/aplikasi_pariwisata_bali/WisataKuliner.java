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

public class WisataKuliner extends AppCompatActivity {
    private ListView listView;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wisata_kuliner);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.list);
        String[] values = new String[]{"Babi Guling","Lawar", "Bebek Bengil Ubud",
                "Ayam Betutu Men Tempeh",
                "Nasi Ayam Kedewetan Bu Mangku",
                "Babi Guling Pak Dobiel",
                "Kubu and Sawah Terrace",
                "Dedari Theory",
                "Bali Asli",
                "Pomegranate Café",
                "Kamandalu Resort Ubud",
                "Restoran Bebek Tepi Sawah Ubud",
                "Nasi Tekor",
                "Mentari Resataurant Bedugul"};

        ArrayList<WisataModel> data = new ArrayList<>();
        for (int i = 0; i < Items.nameKuliner.length; i++) {
            data.add(new WisataModel(
                    Items.nameKuliner[i],
                    Items.locationKuliner[i],
                    Items.descKuliner[i],
                    Items.imgKuliner[i]
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