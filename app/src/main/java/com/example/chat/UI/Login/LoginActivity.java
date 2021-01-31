package com.example.chat.UI.Login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chat.R;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        ////////// check if user = null
//        if ( firebaseUser != null){
//
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//            finish();
//        }
//
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportFragmentManager().beginTransaction().replace(R.id.Container,new LoginFragment()).commit();
    }

    public void startRegisterFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.Container,new RegisterFragment()).commit();

    }

    public void startLoginFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.Container,new LoginFragment()).commit();

    }
}
