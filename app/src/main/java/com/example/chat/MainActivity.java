package com.example.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.chat.UI.Login.LoginActivity;
import com.example.chat.model.Tab_Home_Adapter;
import com.example.chat.model.Users;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab;
    private ViewPager viewPager;
    private Tab_Home_Adapter tab_home_adapter;
    FirebaseUser firebaseUser ;
    DatabaseReference reference;
    CircularImageView imageProfile;
    TextView UserNameProfile;

    private List<Users> mUsers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageProfile = findViewById(R.id.ImageProfile);
        UserNameProfile = findViewById(R.id.UserNameProfile);


//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
////                child(firebaseUser.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Users users = snapshot.getValue(Users.class);
//                UserNameProfile.setText(users.getUsername());
//                if (users.getImageURL().equals("default")){
//                String url = users.getImageURL();
//                if (url != null && url.equals("default")){
//                imageProfile.setImageResource(R.mipmap.ic_launcher);
//                }else {
//                    Glide.with(MainActivity.this).load(users.getImageURL()).into(imageProfile);
//                }

//        }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });


        viewPager = findViewById(R.id.ViewPagerHome);
        Toolbar toolbar = findViewById(R.id.toolbar);
        tab_home_adapter = new Tab_Home_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(tab_home_adapter);
        tab = findViewById(R.id.TabLayout);
        tab.setupWithViewPager(viewPager);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        tab.getTabAt(1).select();
        tab.getTabAt(0).setIcon(R.drawable.ic_photo_cameraa_black_24dp);






//
        //////////////
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int unread = 0;
//                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
//                    ChatM chat = dataSnapshot.getValue(ChatM.class);
//                    if(chat.getReceiver().equals(firebaseUser.getUid())&& ! chat.isIsseen()){
//                        unread++;
//                    }
//                }
//                if (unread == 0 ){
//
//                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
////////////////////////////////////


//
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
//            case R.id.search_item:
//                // do your code
//                return true;
            case R.id.add_item:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                return true;
            case R.id.logOut:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
                ///
//                startActivity(new Intent(MainActivity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));

                /////////////////////////// intent
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void status (String status){
//        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//        HashMap<String ,Object> hashMap = new HashMap<>();
//        hashMap.put("status",status);
//        reference.updateChildren(hashMap);

    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }

}
