package com.giftech.implicitintent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = (EditText) findViewById(R.id.et_website);
        mLocationEditText = (EditText) findViewById(R.id.et_location);
        mShareEditText = (EditText) findViewById(R.id.et_share);
    }

    public void openWebsite(View view) {
        String url = mWebsiteEditText.getText().toString();
        Uri webpage = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null){ //jika aplikasi yang membuka tersedia
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents","Cant handle this");
        }
    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents","Cant handle this");
        }
    }

    public void shareText(View view) {
        String txt = mShareEditText.getText().toString();
        String mimeType = "text/plain";

        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share kemana?")
                .setText(txt)
                .startChooser();
    }
}