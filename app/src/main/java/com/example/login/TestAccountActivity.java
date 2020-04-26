package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.models.Member;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestAccountActivity extends AppCompatActivity {

    private EditText txt_name;
    private EditText txt_age;
    private EditText txt_phone;
    private EditText txt_height;
    private Button btn_send;
    private DatabaseReference reff;

    private Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_account);
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        txt_name = findViewById(R.id.txt_name);
        txt_age = findViewById(R.id.txt_age);
        txt_phone = findViewById(R.id.txt_phone);
        txt_height = findViewById(R.id.txt_height);
        btn_send = findViewById(R.id.btn_send);

        member = new Member();

        reff = FirebaseDatabase.getInstance().getReference().child("Member");

        btn_send.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String name = txt_name.getText().toString().trim();
                int age = Integer.parseInt(txt_age.getText().toString().trim());
                long phone = Long.parseLong(txt_phone.getText().toString().trim());
                float height = Float.parseFloat(txt_height.getText().toString().trim());

                member.setName(name);
                member.setAge(age);
                member.setPhone(phone);
                member.setHeight(height);

                reff.child("member1").setValue(member);
                Log.d("send", name + age + phone + height);
                Toast.makeText(TestAccountActivity.this, name + age + phone + height, Toast.LENGTH_SHORT).show();

                Toast.makeText(TestAccountActivity.this, "inserted Successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
