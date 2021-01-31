package com.example.chat.UI.HomeUi;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chat.R;
import com.example.chat.UI.ConnectUi.Connect_UsActivity;
import com.example.chat.UI.Location.GPSLocationActivity;
import com.example.chat.UI.Message.StarredMessagesActivity;
import com.example.chat.UI.ReplaceActivity;
import com.example.chat.UI.SettingUi.chatting.NotesActivity;
import com.example.chat.model.Users;
import com.example.chat.model.adapter.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFragment extends Fragment {

    private RecyclerView recyclerView ;
    private UserAdapter userAdapter ;
    private List<Users> mUsers;
    FirebaseUser fuser;
    DatabaseReference reference ;
    private List<String> usersList;

    public ChattingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_chatting, container, false);
        setHasOptionsMenu(true);
        recyclerView = inflate.findViewById(R.id.RecyclerView_Chat);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



//////////////////////////////////////////////////////////////////////
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        usersList = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    ChatM chat = dataSnapshot.getValue(ChatM.class);
//                    if (chat.getSender().equals(fuser.getUid())){
//                        usersList.add(chat.getReceiver());
//                    }
//                    if (chat.getReceiver().equals(fuser.getUid())){
//                        usersList.add(chat.getSender());
//                    }
                }
                readChat();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//////////////////////////////////////////////////
       updateToken(FirebaseInstanceId.getInstance().getToken());

        return inflate;
    }


    ////////////////////////////notification
    private void updateToken(String token){
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
//        Token token1 = new Token();
//        reference.child(fuser.getUid()).setValue(token1);

    }
    ///////////////////

    private void readChat() {
        mUsers = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsers.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Users users = dataSnapshot.getValue(Users.class);
                    for (String id : usersList) {
                        if (users.getId().equals(id)) {
                            if (mUsers.size() != 0) {
                                for (Users users1 : mUsers) {
                                    if (!users.getId().equals(users1.getId())) {
                                        mUsers.add(users);

                                    }
                                }

                            } else
                                {
                                mUsers.add(users);
                            }
                        }
                    }

                }
                userAdapter = new UserAdapter(getContext(),mUsers,true);
                recyclerView.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.chatmenu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getActivity(), "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {

            case R.id.new_group_item:
                Intent ii = new Intent(getActivity(), NotesActivity.class);
                startActivity(ii);
                getActivity().finish();
                // Not implemented here
                return true;
            case R.id.private_message_item:
                Intent in = new Intent(getActivity(), StarredMessagesActivity.class);
                startActivity(in);
                getActivity().finish();
                // Do Fragment menu item stuff here
                return true;
            case R.id.connect_us_item:
                Intent intent = new Intent(getActivity(), Connect_UsActivity.class);
                startActivity(intent);
                getActivity().finish();
                return true;
            case R.id.upload_item:
                startActivity(new Intent(getActivity(), ReplaceActivity.class));
                getActivity().finish();
                // do your code
                return true;
            case R.id.location_item:
                Intent i = new Intent(getActivity(), GPSLocationActivity.class);
                  startActivity(i);
                getActivity().finish();
                // do your code
                return true;

            default:
                break;
        }

        return false;
    }



}



///////////////////////////////////////////////////////////////
//    private void chatList() {
//        mUsers = new ArrayList<>();
//        reference = FirebaseDatabase.getInstance().getReference("Users");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                mUsers.clear();
//                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
//                    Users users = dataSnapshot.getValue(Users.class);
//                    for (Chatlist chatlist :usersList){
//                        if (users.getId().equals(chatlist.getId())){
//                            mUsers.add(users);
//                        }
//                    }
//                }
//                userAdapter  = new UserAdapter(getContext(),mUsers,true);
//                recyclerView.setAdapter(userAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
