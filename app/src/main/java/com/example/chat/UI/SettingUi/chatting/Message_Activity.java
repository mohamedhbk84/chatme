package com.example.chat.UI.SettingUi.chatting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chat.MainActivity;
import com.example.chat.Notification.APIService;
import com.example.chat.Notification.Client;
import com.example.chat.Notification.Data;
import com.example.chat.Notification.MyResponse;
import com.example.chat.Notification.Sender;
import com.example.chat.Notification.Token;
import com.example.chat.R;
import com.example.chat.model.ChatM;
import com.example.chat.model.Users;
import com.example.chat.model.adapter.MessageAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Message_Activity extends AppCompatActivity {
  CircularImageView profile_Image;
  TextView username;
  FirebaseUser fuser;
  DatabaseReference reference ;
  Intent intent;


     MessageAdapter messageAdapter ;
     List<ChatM> mChat;
     RecyclerView recyclerView;
     ImageButton btnSend;
     EditText textSend;
     ValueEventListener seenListener;
     APIService apiService ;
     boolean notify = false ;
     /////// the problem
    private String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Message_Activity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
//                finish();
            }
        });
        apiService = Client.getClient("https:fcm.googleapis.com/").create(APIService.class);
        profile_Image = findViewById(R.id.ImageProfile1);
        username = findViewById(R.id.UserNameProfile1);
        intent = getIntent();
        final String userid = intent.getStringExtra("userid");
        fuser = FirebaseAuth.getInstance().getCurrentUser();

        ////////////////////////////////////////////////////////send message
        textSend = findViewById(R.id.textSend);
        btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notify = true;
                String msg = textSend.getText().toString();
                if (!msg.equals("")){
                    sendMessage(fuser.getUid(),userid,msg);
                }else {
                    Toast.makeText(Message_Activity.this, "you cannot send empty message", Toast.LENGTH_SHORT).show();
                }
                textSend.setText("");

            }
        });
        ///////////////////////////////////////////////
        recyclerView = findViewById(R.id.RecycleMessage);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        //////////////////////////////////////////////////////
        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                username.setText(user.getUsername());
                String url = user.getImageURL();
                if (url != null && url.equals("default")){
//                if (user.getImageURL().equals("default")){

                    profile_Image.setImageResource(R.mipmap.face);
                }else {
                    Glide.with(Message_Activity.this).load(user.getImageURL()).into(profile_Image);
                }
                readMessage(fuser.getUid(),userid,user.getImageURL());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        seenMessage(userid);



    }

    private void seenMessage(final String userid){

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        seenListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
//                    ChatM chat = dataSnapshot.getValue(ChatM.class);
//                    if (chat.getReceiver().equals(fuser.getUid()) && chat.getSender().equals(userid)){
//                        HashMap <String,Object> hashMap = new HashMap<>();
//                        hashMap.put("isseen",true);
//                        dataSnapshot.getRef().updateChildren(hashMap);
//
//                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

private  void sendMessage(String sender , final String receiver , String message) {
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    HashMap<String, Object> hashMap = new HashMap<>();
    hashMap.put("sender", sender);
    hashMap.put("receiver", receiver);
    hashMap.put("message", message);
    hashMap.put("isseen", false);
    reference.child("Chats").push().setValue(hashMap);

    /////////////////add user to chat fragment
    final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("chatlist")
            .child(fuser.getUid())
            .child(userid);
    chatRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            if (!snapshot.exists()){
                chatRef.child("id").setValue(userid);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });

/////////////////////////////////////////notification
    final String msg = message;
    DatabaseReference reference1 = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
    reference1.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            Users users = snapshot.getValue(Users.class);
            if (notify) {
                sendNotification(receiver, users.getUsername(), msg);
            }
            notify = false;

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });


}

    private void sendNotification(final String receiver, final String username, final String msg) {
        DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
        Query query = tokens.orderByKey().equalTo(receiver);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    Token token = dataSnapshot.getValue(Token.class);
                    Data data = new Data(fuser.getUid(),R.mipmap.ic_launcher_round, username +": "+msg,"New Message",
                            userid);
                    Sender sender = new Sender(data ,token.getToken());
                    apiService.sendNotification(sender)
                            .enqueue(new Callback<MyResponse>() {
                                @Override
                                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                                    if (response.code() == 200){
                                        if (response.body().success != 1){
                                            Toast.makeText(Message_Activity.this, "Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<MyResponse> call, Throwable t) {

                                }
                            });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private  void readMessage(final String myid , final String userid , final String imageurl){
        mChat = new ArrayList<>();
        reference =FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mChat.clear();
//              for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                  ChatM chat = dataSnapshot.getValue(ChatM.class);
//              if (chat.getReceiver().equals(myid) && chat.getSender().equals(userid) ||
//              chat.getReceiver().equals(userid)&& chat.getSender().equals(myid)){
//                 mChat.add(chat);
//
//              }
                messageAdapter = new MessageAdapter(Message_Activity.this, mChat, imageurl);
                recyclerView.setAdapter(messageAdapter);

//            }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
                startActivity(new Intent(this, MainActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void status (String status){
        reference = FirebaseDatabase.getInstance().getReference("Users").child(fuser.getUid());
        HashMap<String ,Object> hashMap = new HashMap<>();
        hashMap.put("status",status);
        reference.updateChildren(hashMap);

    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reference.removeEventListener(seenListener);
        status("offline");
    }
}
