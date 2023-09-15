package com.jhandev.uiapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class RegistroUsuario extends Activity {
    private Button registro, login;
    private EditText nombre, apellido, correo, password;

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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroUsuario.this,Login.class);
                startActivity(intent);
            }
        });

        registro.setOnClickListener(View -> {
            String nombreText = nombre.getText().toString();
            String apellidoText = apellido.getText().toString();
            String correoText = correo.getText().toString();
            String passwordText = password.getText().toString();

            // Validar Nombre
            if (nombreText.equals("")) {
                nombre.setError("Ingrese su nombre");
                nombre.requestFocus();
                return; // Detiene la validación si falta el nombre
            } else if (nombreText.length() < 3) {
                nombre.setError("Nombre minimo 3 caracteres");
                nombre.requestFocus();
                return; // Detiene la validación si nombre es demasiado corto
            }

            // Validar Apellido
            if (apellidoText.equals("")) {
                apellido.setError("Ingrese su apellido");
                apellido.requestFocus();
                return; // Detiene la validación si falta apellido
            } else if (apellidoText.length() < 3) {
                apellido.setError("Apellido minimo 3 caracteres");
                apellido.requestFocus();
                return; // Detiene la validación si apellido es demasiado corto
            }

            // Regex para validar Email
            Pattern pattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9._]*@(gmail\\.com|hotmail\\.com|outlook\\.com|yahoo\\.com)$");
            // Validar Email
            if (!pattern.matcher(correoText).matches()) {
                correo.setError("El email ingresado es inválido.");
                correo.requestFocus();
                return; // Detiene la validación si el email es inválido
            }

            // Validar Contraseña
            if (passwordText.equals("")) {
                password.setError("Ingrese su contraseña");
                password.requestFocus();
            } else if (passwordText.length() < 6) {
                password.setError("Contraseña minimo 6 caracteres");
                password.requestFocus();
            }
        });
    }
}