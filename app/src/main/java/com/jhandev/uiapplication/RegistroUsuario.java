package com.jhandev.uiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroUsuario extends AppCompatActivity {
    private Button registro, login;
    private EditText nombre, apellido, correo, password;

    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        registro = findViewById(R.id.btnRegistrar);
        login = findViewById(R.id.btnLogin);

        nombre = findViewById(R.id.textNombre);
        apellido = findViewById(R.id.txtApellido);
        correo = findViewById(R.id.txtEmailRegistro);
        password = findViewById(R.id.txtPassword);
        dao = new UsuarioDAO(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroUsuario.this,Login.class);
                startActivity(intent);
            }
        });

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario us = new Usuario();
                us.setNombre(nombre.getText().toString());
                us.setApellido(apellido.getText().toString());
                us.setCorreo(correo.getText().toString());
                us.setPassword(password.getText().toString());
                if (!us.isNull()) {
                    Toast.makeText(RegistroUsuario.this,"Error: campos vacios",Toast.LENGTH_SHORT).show();
                } else if (dao.insertUsuario(us)) {
                    Toast.makeText(RegistroUsuario.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistroUsuario.this,Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegistroUsuario.this,"Usuario registrado",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}