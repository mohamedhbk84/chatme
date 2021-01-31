package com.example.chat.UI.SettingUi.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chat.MainActivity;
import com.example.chat.R;
import com.example.chat.UI.SettingUi.chatting.Note.DisplayNotesActivity;
import com.example.chat.model.Storage.SQlite.DatabaseHandler;
import com.example.chat.model.model.MyNotes;

public class NotesActivity extends AppCompatActivity {
    private EditText title;
    private EditText content;
    private ImageView imageView_back;
    private Button saveButton;
    private Button showNotes;
    private DatabaseHandler dba;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        dba = new DatabaseHandler(NotesActivity.this);

        title =  findViewById(R.id.titleEditText);
        content =  findViewById(R.id.wishEditText);
        saveButton =  findViewById(R.id.saveButton);
        showNotes =  findViewById(R.id.Show_Notes);
        imageView_back =  findViewById(R.id.imageView_back);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
       showNotes.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(NotesActivity.this, DisplayNotesActivity.class));
           }
       });
        imageView_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NotesActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    private void saveToDB() {

        MyNotes wish = new MyNotes();
        wish.setTitle(title.getText().toString().trim());
        wish.setContent(content.getText().toString().trim());


        dba.addWishes(wish);
        dba.close();

        //clear
        title.setText("");
        content.setText("");

        Intent i = new Intent(NotesActivity.this, DisplayNotesActivity.class);
        startActivity(i);



    }


}
