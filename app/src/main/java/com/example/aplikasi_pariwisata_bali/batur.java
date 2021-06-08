package com.example.aplikasi_pariwisata_bali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class batur extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batur);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvAlamat = findViewById(R.id.tv_alamat_batur);
        tvAlamat.setText("Lokasi : " + getString(R.string.alamat_batur));

        tvAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

        Button btnShare = findViewById(R.id.button_share_batur);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setShare();
            }
        });
    }

    private void setShare(){
        Bitmap bitmapDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.batur);
        String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmapDrawable, null, null);
        Uri imgUri = Uri.parse(bitmapPath);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.desc_batur));
        intent.putExtra(Intent.EXTRA_STREAM, imgUri);
        intent.setType("*/*");
        try {
            startActivity(Intent.createChooser(intent, "Share Via"));
        } catch (Exception e) {
            Toast.makeText(this, "WhatsApp is not exist", Toast.LENGTH_SHORT).show();
        }
    }

    private void openMap() {
        Uri addressUri = Uri.parse("geo:0,0?q=" + Uri.encode(getString(R.string.alamat_batur)));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, addressUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}