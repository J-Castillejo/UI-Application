package com.jhandev.uiapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    private EditText correo, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button registro = findViewById(R.id.btnRegistrar);
        Button login = findViewById(R.id.btnIngresar);

        correo = findViewById(R.id.txtEmailRegistro);
        password = findViewById(R.id.txtPassword);

        registro.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, RegistroUsuario.class);
            startActivity(intent);
        });

        login.setOnClickListener(view -> {
            String u = correo.getText().toString();
            String p = password.getText().toString();

            if (u.equals("") && p.equals("")) {
                Toast.makeText(Login.this, "Error: campos vacios", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Login.this, "Error: usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
    }}
