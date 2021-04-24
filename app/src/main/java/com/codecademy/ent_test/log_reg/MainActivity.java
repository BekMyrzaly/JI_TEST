package com.codecademy.ent_test.log_reg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codecademy.ent_test.R;
import com.codecademy.ent_test.main_work;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button logIn;
    TextView regIn, forgotPassword;
    EditText email_log, password_log;
    FirebaseAuth firebaseAuth;

    String e_mail;
    String pass_word;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
        currentUser();
        logIn.setOnClickListener(this);
        regIn.setOnClickListener(this);
        forgotPassword.setOnClickListener(this);
    }

    private void currentUser() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(MainActivity.this, main_work.class);
            startActivity(intent);
        }
    }

    private void initId() {
        logIn = findViewById(R.id.btn_log);
        regIn = findViewById(R.id.back_reg);
        email_log = findViewById(R.id.log_email);
        password_log = findViewById(R.id.log_password);
        forgotPassword = findViewById(R.id.btn_forgot);

        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_log) {
            e_mail = email_log.getText().toString();
            pass_word = password_log.getText().toString();
            if (TextUtils.isEmpty(email_log.getText())) {
                email_log.setError("Толықтырыңыз");
                return;
            }
            if (TextUtils.isEmpty(password_log.getText())) {
                password_log.setError("Толықтырыңыз");
                return;
            }

            userLog(e_mail, pass_word);
        } else if (id == R.id.back_reg) {
            Intent intent1 = new Intent(MainActivity.this, regis_page.class);
            startActivity(intent1);
        }
    }

    private void userLog(String email_fire, String password_fire) {
        firebaseAuth.signInWithEmailAndPassword(email_fire, password_fire).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Қош келдіңіз!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, main_work.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Қолданушы табылмады", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}