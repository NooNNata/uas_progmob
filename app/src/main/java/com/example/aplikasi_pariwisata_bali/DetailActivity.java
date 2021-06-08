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
import android.widget.Toast;

import com.example.aplikasi_pariwisata_bali.databinding.ActivityDetailBinding;
import com.example.aplikasi_pariwisata_bali.model.WisataModel;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding binding;
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WisataModel data = getIntent().getParcelableExtra("data");

        binding.tvName.setText(data.getName());
        binding.tvAlamat.setText(data.getLocation());
        binding.tvDesc.setText(data.getDescription());
        binding.ivImage.setImageResource(data.getImage());

        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setShare(data);
            }
        });

        binding.tvAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMap(data);
            }
        });
    }

    private void openMap(WisataModel data) {
        Uri addressUri = Uri.parse("geo:0,0?q=" + Uri.encode(data.getLocation()));
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, addressUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    // set untuk membagikan aplikasi
    private void setShare(WisataModel data)
    {
        Bitmap bitmapDrawable = BitmapFactory.decodeResource(getResources(), data.getImage());
        String bitmapPath = MediaStore.Images.Media.insertImage(getContentResolver(), bitmapDrawable, "Kuta", "Kuta desc");
        Uri imgUri = Uri.parse(bitmapPath);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, data.getDescription());
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