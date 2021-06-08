package com.example.aplikasi_pariwisata_bali;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.io.File;

public class PantaiKuta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantai_kuta);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tvAlamat = findViewById(R.id.tv_alamat);
        tvAlamat.setText("Lokasi : " + getString(R.string.alamat_kuta));

        tvAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap();
            }
        });

        // cari id button share
        Button MY_BTN = (Button)findViewById(R.id.button_share);
        // buat aksi jika tombol di tekan
        MY_BTN.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                // panggil fungsi share yang telah di buat
                setShare();
            }
        });
    }

    //method to open map according to the address
    private void openMap() {
        Uri addressUri = Uri.parse("geo:0,0?q=" + Uri.encode(getString(R.string.alamat_kuta)));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, addressUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    // set untuk membagikan aplikasi
    private void setShare()
    {
        Bitmap bitmapDrawable = BitmapFactory.decodeResource(getResources(), R.drawable.kuta);
        String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmapDrawable, "Kuta", "Kuta desc");
        Uri imgUri = Uri.parse(bitmapPath);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.desc_kuta));
        intent.putExtra(Intent.EXTRA_STREAM, imgUri);
        intent.setType("*/*");
        try {
            startActivity(Intent.createChooser(intent, "Share Via"));
        } catch (Exception e) {
            Toast.makeText(this, "WhatsApp is not exist", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}

