package com.example.aplikasi_pariwisata_bali;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.aplikasi_pariwisata_bali.model.Items;
import com.example.aplikasi_pariwisata_bali.model.WisataModel;

import java.util.ArrayList;

public class MenuUtama extends AppCompatActivity {
    Button button1, button2, button3, button4, button5, button6, btnSearch;
    AutoCompleteTextView edSearch;
    String searchResult;
    String[] nameAll = Items.nameAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        btnSearch = findViewById(R.id.btn_search);

        edSearch = findViewById(R.id.ed_search);

        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getPlace(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchResult = editable.toString();
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToDetail(searchResult);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, WisataAlam.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, WisataEdukasi.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, WisataReligi.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, WisataKuliner.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuUtama.this, About.class);
                startActivity(intent);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                moveTaskToBack(true);

                //membuat method tombol keluar dari aplikasi

            }
        });
    }

    private void goToDetail(String searchResult) {
        for (int i = 0; i < nameAll.length; i++) {
            if (nameAll[i].contains(searchResult)) {
                WisataModel data = new WisataModel(
                        Items.nameAll[i],
                        Items.locationAll[i],
                        Items.descAll[i],
                        Items.imgAll[i]
                );
                Intent intent = new Intent(this, DetailActivity.class);
                intent.putExtra("data", data);
                startActivity(intent);
                break;
            }
        }
    }

    private void getPlace(String s) {
        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < nameAll.length; i++) {
            if (nameAll[i].toLowerCase().contains(s)) {
                result.add(nameAll[i]);
                ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, result);
                adapter.notifyDataSetChanged();
                edSearch.setAdapter(adapter);
            }
        }
    }
}