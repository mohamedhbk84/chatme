package com.example.chat.UI.SettingUi.Profile;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chat.R;

public class SituationNewsActivity extends AppCompatActivity {

    private SharedPreferences sp;
    private TextView Text1111;
    private ProgressDialog mProgress ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situation_news);
                mProgress = new ProgressDialog(this);

        Toolbar toolbar = findViewById(R.id.toolbar11);
        toolbar.setTitle(getResources().getString(R.string.news));
        setSupportActionBar(toolbar);
         sp = PreferenceManager.getDefaultSharedPreferences(this.getBaseContext());
         Text1111 = findViewById(R.id.Text1111);
        Text1111.setText(sp.getString("News",""));
        TextView text1 = findViewById(R.id.Text1);
        TextView text2 = findViewById(R.id.Text2);
        TextView text3 = findViewById(R.id.Text3);
        TextView text4 = findViewById(R.id.Text1);
        TextView text5 = findViewById(R.id.Text4);
        TextView text6 = findViewById(R.id.Text6);
        TextView text7 = findViewById(R.id.Text7);
        TextView text8 = findViewById(R.id.Text8);
        TextView text9 = findViewById(R.id.Text9);
        TextView text10 = findViewById(R.id.Text10);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp.edit().putString("News","متوفر").commit();
                Text1111.setText(sp.getString("News","متوفر"));
                Toast.makeText(SituationNewsActivity.this, "متوفر", Toast.LENGTH_SHORT).show();
            }
        });
        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("News","مشغول").commit();
                Text1111.setText(sp.getString("News","متوفر"));
                Toast.makeText(SituationNewsActivity.this, "مشغول", Toast.LENGTH_SHORT).show();
            }
        });
        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().putString("News","فى الخارج").commit();
                Text1111.setText(sp.getString("News","متوفر"));
                Toast.makeText(SituationNewsActivity.this, "فى الخارج", Toast.LENGTH_SHORT).show();
            }
        });
  text4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          sp.edit().putString("News","فى المدرسة").commit();
          Text1111.setText(sp.getString("News","متوفر"));
          Toast.makeText(SituationNewsActivity.this, "فى المدرسة", Toast.LENGTH_SHORT).show();
      }
  });
  text5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          sp.edit().putString("News","فى العمل ").commit();
          Text1111.setText(sp.getString("News","متوفر"));
          Toast.makeText(SituationNewsActivity.this, "فى العمل", Toast.LENGTH_SHORT).show();
      }
  });
text6.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sp.edit().putString("News","فى إجتماع").commit();
        Text1111.setText(sp.getString("News","متوفر"));
        Toast.makeText(SituationNewsActivity.this, "فى إجتماع", Toast.LENGTH_SHORT).show();
    }
});
text7.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sp.edit().putString("News","فى النادى الرياضى").commit();
        Text1111.setText(sp.getString("News","متوفر"));
        Toast.makeText(SituationNewsActivity.this, "فى النادى الرياضى", Toast.LENGTH_SHORT).show();
    }
});
text8.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sp.edit().putString("News","نائم").commit();
        Text1111.setText(sp.getString("News","متوفر"));
        Toast.makeText(SituationNewsActivity.this, "نائم", Toast.LENGTH_SHORT).show();
    }
});
text9.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sp.edit().putString("News","المكالمات الطارئة فقط").commit();
        Text1111.setText(sp.getString("News","متوفر"));
        Toast.makeText(SituationNewsActivity.this, "المكالمات الطارئة فقط", Toast.LENGTH_SHORT).show();
    }
});
text10.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        sp.edit().putString("News","لا استطيع التحدث , واتساب فقط").commit();
        Text1111.setText(sp.getString("News","متوفر"));
        Toast.makeText(SituationNewsActivity.this, "لا استطيع التحدث , واتساب فقط", Toast.LENGTH_SHORT).show();
    }
});
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
                startActivity(new Intent(this, ProfileSettingActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
