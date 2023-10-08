package com.jhandev.uiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InicioReg extends AppCompatActivity {

        private Button guardar, editar, eliminar, listar, salir;
        private EditText nombreLibro, Serial, nombreAutor;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_inicio_reg);

            guardar = findViewById(R.id.btnGuardar);
            editar = findViewById(R.id.btnEditar);
            eliminar = findViewById(R.id.btnEliminar);
            listar = findViewById(R.id.btnListar);
            salir = findViewById(R.id.btnSalir);
            nombreLibro = findViewById(R.id.txtLibro);
            Serial = findViewById(R.id.txtSerial);
            nombreAutor = findViewById(R.id.txtNomAutor);

            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                    SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                    if (Serial.getText().toString().trim().equals("")) {
                        Serial.setError("Error: Debe completar este campo");
                    } else if (nombreAutor.getText().toString().trim().equals("")) {
                        nombreAutor.setError("Error: Debe completar este campo");
                    } else if (nombreLibro.getText().toString().trim().equals("")) {
                        nombreLibro.setError("Error: Debe completar este campo");
                    } else {

                        int clave =  Integer.parseInt(Serial.getText().toString());
                        int clave2 = 0;
                        Cursor fila = db.rawQuery("select * from libros where Serial="+clave,null);
                        if(fila.moveToFirst()) {
                            clave2 = fila.getInt(0);
                        }
                        if(clave2 == clave) {
                            Toast.makeText(InicioReg.this,"El libro ya existe",Toast.LENGTH_SHORT).show();
                            db.close();
                        } else {
                            String nomAut = nombreAutor.getText().toString();
                            String nomLb = nombreLibro.getText().toString();
                            ContentValues datos = new ContentValues();
                            datos.put("Serial", clave);
                            datos.put("nombreAutor", nomAut);
                            datos.put("nombreLibro", nomLb);

                            db.insert("libros",null,datos);
                            db.close();
                            nombreAutor.setText("");
                            nombreLibro.setText("");
                            Serial.setText("");
                        }
                    }
                }
            });

            listar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                    SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                    if (Serial.getText().toString().trim().equals("")) {
                        Serial.setError("Error: Debe completar este campo");
                    } else {
                        int clave = Integer.parseInt(Serial.getText().toString());

                        Cursor fila = db.rawQuery("select * from libros where Serial="+clave,null);
                        if (fila.moveToFirst()) {
                            nombreAutor.setText(fila.getString(1));
                            nombreLibro.setText(fila.getString(2));
                        }else {
                            Toast.makeText(InicioReg.this,"El libro no existe",Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                    }
                }
            });

            editar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                    SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                    if (Serial.getText().toString().trim().equals("")) {
                        Serial.setError("Error: Debe completar este campo");
                    } else if (nombreAutor.getText().toString().trim().equals("")) {
                        nombreAutor.setError("Error: Debe completar este campo");
                    } else if (nombreLibro.getText().toString().trim().equals("")) {
                        nombreLibro.setError("Error: Debe completar este campo");
                    } else {
                        int clave = Integer.parseInt(Serial.getText().toString());

                        Cursor fila = db.rawQuery("select * from libros where Serial=" + clave, null);

                        String nomAut = nombreAutor.getText().toString();
                        String nomLb = nombreLibro.getText().toString();


                        ContentValues datos = new ContentValues();
                        datos.put("nombreAutor", nomAut);
                        datos.put("nombreLibro", nomLb);

                        if (fila.moveToFirst()) {
                            db.update("libros", datos, "Serial=" + clave, null);
                            Toast.makeText(InicioReg.this, "Libro Actualizado", Toast.LENGTH_SHORT).show();
                            nombreAutor.setText("");
                            nombreLibro.setText("");
                            Serial.setText("");
                        } else {
                            Toast.makeText(InicioReg.this, "El libro no existe", Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                    }
                }
            });

            eliminar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InicioSQLite inicioSQLite = new InicioSQLite(getApplicationContext(), "Biblioteca",null,1);
                    SQLiteDatabase db = inicioSQLite.getWritableDatabase();

                    if (Serial.getText().toString().trim().equals("")) {
                        Serial.setText("Error: Debe completar este campo");
                    } else {

                        int clave = Integer.parseInt(Serial.getText().toString());

                        Cursor fila = db.rawQuery("select * from libros where Serial=" + clave, null);

                        if (fila.moveToFirst()) {

                            db.execSQL("DELETE FROM libros  WHERE Serial=" + clave);
                            Toast.makeText(InicioReg.this, "Libro Eliminado", Toast.LENGTH_SHORT).show();
                            nombreAutor.setText("");
                            nombreLibro.setText("");
                            Serial.setText("");
                        } else {
                            Toast.makeText(InicioReg.this, "El libro no existe", Toast.LENGTH_SHORT).show();
                        }
                        db.close();
                    }
                }
            });

            salir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(InicioReg.this,Login.class);
                    startActivity(intent);
                }
            });
    }
}