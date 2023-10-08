package com.jhandev.uiapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    private EditText correo, password;

    private Button registro, login;

    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        registro = findViewById(R.id.btnRegistrar);
        login = findViewById(R.id.btnIngresar);

        correo = findViewById(R.id.txtEmailRegistro);
        password = findViewById(R.id.txtPassword);
        dao = new UsuarioDAO(this);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, RegistroUsuario.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = correo.getText().toString();
                String passwd = password.getText().toString();

                if (email.equals("") && passwd.equals("")) {
                    Toast.makeText(Login.this, "Error: campos vacios", Toast.LENGTH_SHORT).show();

                } else if (dao.login(email, passwd) == 1) {
                    Usuario us = dao.getUsuario(email, passwd);
                    Toast.makeText(Login.this, "Datos correctos", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, InicioReg.class);
                    intent.putExtra("id", us.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, "Error: usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
