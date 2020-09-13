package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DBHelper db;
    EditText fname, lname, email, phone, pass, cpass;
    TextView lg;
    Button r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(this);

        fname = (EditText) findViewById(R.id.rg_firstN);
        lname = (EditText) findViewById(R.id.rg_lastN);
        email = (EditText) findViewById(R.id.rg_email);
        phone = (EditText) findViewById(R.id.rg_phone);
        pass = (EditText) findViewById(R.id.rg_pass);
        cpass = (EditText) findViewById(R.id.rg_cpass);

        lg = (TextView) findViewById(R.id.txt_rg_lg);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_login);
            }
        });

        r = (Button) findViewById(R.id.rgt);

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();

                String fn = fname.getText().toString();
                String ln = lname.getText().toString();
                String em = email.getText().toString();
                String ph = phone.getText().toString();
                String pss = pass.getText().toString();
                String cpss = cpass.getText().toString();

                if (fn.equals("")||ln.equals("")||em.equals("")||ph.equals("")||pss.equals("")||cpss.equals("")){
                    Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (pss.equals(cpss)){
                        boolean chkemail = db.chkemail(em);
                        if (chkemail==true){
                            boolean insert = db.insert(fn, ln, em, ph, pss);
                            if (insert==true){
                                Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                setContentView(R.layout.activity_display_albums);
                            }
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Email Already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });


    }
}
