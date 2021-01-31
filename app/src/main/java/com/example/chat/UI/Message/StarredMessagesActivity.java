package com.example.chat.UI.Message;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.chat.MainActivity;
import com.example.chat.R;

public class StarredMessagesActivity extends AppCompatActivity {

    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starred_messages);
        frameLayout = findViewById(R.id.Frame_Started_message);
        Toolbar toolbarStarted = findViewById(R.id.toolbar_Started_message1);

        toolbarStarted.setTitle(getResources().getString(R.string.Started_Message_name));
        setSupportActionBar(toolbarStarted);
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
