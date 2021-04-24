package com.codecademy.ent_test.log_reg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.codecademy.ent_test.R;
import com.codecademy.ent_test.main_work;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class regis_page extends AppCompatActivity implements View.OnClickListener {

    ImageView backLog, helpReg;
    EditText fullname, nickname, phone_num, email_reg, password_reg, passwordConfirm_reg;
    Button reg_btn;

    User user_profile;
    String fullname_info, nickname_info, phone_info, email_info, password_info, passwordConfirm_info;

    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_page);
        initId();

        backLog.setOnClickListener(this);
        helpReg.setOnClickListener(this);
        reg_btn.setOnClickListener(this);
    }

    private void initId() {
        backLog = findViewById(R.id.back_login);
        helpReg = findViewById(R.id.btn_help);

        fullname = findViewById(R.id.reg_fullname);
        nickname = findViewById(R.id.reg_nickname);
        phone_num = findViewById(R.id.reg_phone);
        email_reg = findViewById(R.id.reg_email);
        password_reg = findViewById(R.id.reg_password);
        passwordConfirm_reg = findViewById(R.id.reg_confirm_password);

        reg_btn = findViewById(R.id.btn_reg);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_reg){
            if (TextUtils.isEmpty(fullname.getText())) {
                fullname.setError("Толық толтырыңыз");
                return;
            }
            if (TextUtils.isEmpty(nickname.getText())) {
                nickname.setError("Толық толтырыңыз");
                return;
            }
            if (TextUtils.isEmpty(phone_num.getText())) {
                phone_num.setError("Толық толтырыңыз");
                return;
            }
            if (TextUtils.isEmpty(email_reg.getText())) {
                email_reg.setError("Толық толтырыңыз");
                return;
            }
            if (TextUtils.isEmpty(password_reg.getText())) {
                password_reg.setError("Толық толтырыңыз");
                return;
            }
            if (TextUtils.isEmpty(passwordConfirm_reg.getText())) {
                passwordConfirm_reg.setError("Толық толтырыңыз");
                return;
            }

            String email_fire = email_reg.getText().toString();
            String password_fire = password_reg.getText().toString();
            String passwordConfirm_fire = passwordConfirm_reg.getText().toString();

            if (!passwordConfirm_fire.equals(password_fire)){
                passwordConfirm_reg.setError("Пароль сәйкес келмейді");
                return;
            }else {
                createAccount(email_fire, password_fire);
            }


        }
        else if (v.getId() == R.id.back_login){
            Intent intent = new Intent(regis_page.this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void createAccount(String email_fire, String password_fire) {
        firebaseAuth.createUserWithEmailAndPassword(email_fire, password_fire).addOnCompleteListener(regis_page.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    infoUser();
                    Toast.makeText(regis_page.this, "Қолданушы тіркелді!" + email_info , Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(regis_page.this, main_work.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(regis_page.this, "Тіркелмеді қайта тіркеліңіз" + task.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public  void infoUser(){
        fullname_info = fullname.getText().toString();
        nickname_info = nickname.getText().toString();
        phone_info = phone_num.getText().toString();
        email_info = email_reg.getText().toString();
        password_info = password_reg.getText().toString();
        passwordConfirm_info = passwordConfirm_reg.getText().toString();

        user_profile = new User(fullname_info, nickname_info, phone_info, email_info);
        databaseReference.child("Users").child(String.valueOf(GenerateRandom())).setValue(user_profile);
    }

    public static int GenerateRandom() {
        int randomaizer = (int) (1000000 * Math.random());
        return randomaizer;
    }

}