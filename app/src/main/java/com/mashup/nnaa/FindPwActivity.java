package com.mashup.nnaa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FindPwActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pw);

        EditText editFind = findViewById(R.id.edit_find);
        ImageView findClose = findViewById(R.id.img_find_close);
        Button btnFind = findViewById(R.id.btn_find);
        ProgressDialog progressDialog = new ProgressDialog(this);

        findClose.setOnClickListener(view -> {
            Intent intent = new Intent(FindPwActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        String email = editFind.getText().toString();

        btnFind.setOnClickListener(view -> {
            if (editFind.getText().toString().isEmpty()) {
                Toast.makeText(FindPwActivity.this, "가입하신 이메일을 입력해주세요!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                intent.putExtra(Intent.EXTRA_SUBJECT, "NNAA 비밀번호 찾기메일입니다.");
                intent.putExtra(Intent.EXTRA_TEXT, "비밀번호");
                startActivity(intent);
                Log.v("@@@@@@@@@@@", "mail");
                btnFind.setBackgroundColor(Color.BLUE);

                progressDialog.setMessage("메일을 보내는중입니다..");
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
                progressDialog.show();

                Toast.makeText(FindPwActivity.this, "이메일로 비밀번호를 보냈습니다!", Toast.LENGTH_SHORT).show();
            }

            // progressDialog.dismiss();
        });

        TextView textView = findViewById(R.id.text_pw);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            String test = intent.getStringExtra("edit");
            String t = intent.getStringExtra("btn");

            textView.setText(test);
            textView.append(t);
        }
    }
}
