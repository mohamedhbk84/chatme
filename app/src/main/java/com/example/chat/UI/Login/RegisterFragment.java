package com.example.chat.UI.Login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.chat.MainActivity;
import com.example.chat.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private EditText emailText,passText,confirmPassText,nameText;
    private FirebaseAuth mAuth;
    DatabaseReference reference;
    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        emailText = view.findViewById(R.id.editText);
        passText = view.findViewById(R.id.editText2);
        confirmPassText = view.findViewById(R.id.editText3);
       nameText = view.findViewById(R.id.editPhone);
        Button btn = view.findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString().trim();
                String pass = passText.getText().toString().trim();
                String cPass = confirmPassText.getText().toString().trim();
                String name = nameText.getText().toString().trim();

                if(!TextUtils.isEmpty(email) &&!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(pass) && TextUtils.equals(pass,cPass)){
                    signUp(email,name,pass);
                }
            }
        });
        TextView textView = view.findViewById(R.id.textView2);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity activity = (LoginActivity) getActivity();
                activity.startLoginFragment();
            }
        });
        return view;
    }

    private void signUp(String email, final String name , String pass) {
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        FirebaseUser currentUser = mAuth.getCurrentUser();
                        assert currentUser != null;
                        String userId = currentUser.getUid();
                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId);
                        HashMap<String ,String> hashMap = new HashMap<>();
                        hashMap.put("id",userId);
                        hashMap.put("username",name);
                        hashMap.put("imageURl","default");
                        hashMap.put("status","offline");
                        hashMap.put("search",name.toLowerCase());


                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }
                        });



                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

