package com.example.chat.UI.ConnectUi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.chat.MainActivity;
import com.example.chat.R;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class Connect_UsActivity extends AppCompatActivity {
    private FrameLayout frameConnect;
    private TextView tx_phone;

    private Button btn_call;
    private ImageView im_twitter, im_face, im_google, im_youtube, im_insta, im_whats;
    private String[] permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect__us);
        frameConnect = findViewById(R.id.Frame_Connect_Us);
        Toolbar toolbarConnect = findViewById(R.id.toolbar_Connect_Us);
        toolbarConnect.setTitle(getResources().getString(R.string.Connect_name));

        setSupportActionBar(toolbarConnect);

        CircularImageView circularImageView = findViewById(R.id.imageView110);
// Set Color
        circularImageView.setCircleColor(Color.WHITE);
// or with gradient
        circularImageView.setCircleColorStart(Color.BLACK);
        circularImageView.setCircleColorEnd(Color.RED);
        circularImageView.setCircleColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Set Border
        circularImageView.setBorderWidth(15f);
        circularImageView.setBorderColor(Color.BLACK);
// or with gradient
        circularImageView.setBorderColorStart(Color.BLACK);
        circularImageView.setBorderColorEnd(Color.RED);
        circularImageView.setBorderColorDirection(CircularImageView.GradientDirection.TOP_TO_BOTTOM);

// Add Shadow with default param
        circularImageView.setShadowEnable(true);
// or with custom param
        circularImageView.setShadowRadius(7f);
        circularImageView.setShadowColor(Color.RED);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);




        toolbarConnect.setTitle(getResources().getString(R.string.Connect_name));
        setSupportActionBar(toolbarConnect);
        tx_phone = findViewById(R.id.contact_num);
        im_face = findViewById(R.id.contact_face);
        im_google = findViewById(R.id.contact_google);
        im_insta = findViewById(R.id.contact_insta);
        im_twitter = findViewById(R.id.contact_twitter);
        im_whats = findViewById(R.id.contact_whats);
        im_youtube =findViewById(R.id.contact_youtube);
        btn_call = findViewById(R.id.contact_call);

        im_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullUrl = "https://m.facebook.com";
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setPackage("com.facebook.katana");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
                    startActivity(sharingIntent);

                } catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(fullUrl));
                    startActivity(i);


                }
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl));
//                startActivity(browserIntent);
            }
        });

        im_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullUrl = "https://m.twitter.com";
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setPackage("com.twitter.katana");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
                    startActivity(sharingIntent);

                } catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(fullUrl));
                    startActivity(i);


                }
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl));
//                startActivity(browserIntent);
            }
        });

        im_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullUrl = "https://m.youtube.com";
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setPackage("com.youtube");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
                    startActivity(sharingIntent);

                } catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(fullUrl));
                    startActivity(i);


                }
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
//                startActivity(browserIntent);
            }
        });

        im_insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullUrl = "https://m.instagram.com";
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setPackage("com.instagram.katana");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
                    startActivity(sharingIntent);

                } catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(fullUrl));
                    startActivity(i);


                }
//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramUrl));
//                startActivity(browserIntent);
            }
        });
        im_whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PackageManager pm = getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");

                    PackageInfo info = pm.getPackageInfo("com.whatsapp",
                            PackageManager.GET_META_DATA);

                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, "تطبيق شات مى");
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {

                }

            }
        });

        im_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullUrl = "https://m.google.com";
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setPackage("com.google.katana");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "your title text");
                    startActivity(sharingIntent);

                } catch (Exception e) {
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(fullUrl));
                    startActivity(i);


                }

//                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleUrl));
//                startActivity(browserIntent);
            }
        });
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (isPermissionGranted()) {
                    call_action();
                }
            }
        });
        permissions = new String[]{Manifest.permission.CALL_PHONE};

    }

    private boolean isPermissionGranted() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
            }
            return;
        }
    }


    public void call_action() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel: 01559606577" ));
        startActivity(callIntent);
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settingmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.back_item:
                startActivity(new Intent(this, MainActivity.class));

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
