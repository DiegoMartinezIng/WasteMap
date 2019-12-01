package com.example.wastecontrol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.wastecontrol.R;
import com.example.wastecontrol.fragmen.videosfragmen.MediaItemFragment;
import com.example.wastecontrol.fragmen.videosfragmen.dummy.MediaContent;

public class AprenderActivity extends AppCompatActivity implements MediaItemFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aprender);
    }

    @Override
    public void onListFragmentInteraction(MediaContent.MediaItem item) {
        Uri webpage = Uri.parse(item.details);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
