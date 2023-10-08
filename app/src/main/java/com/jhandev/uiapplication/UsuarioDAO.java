package com.jhandev.uiapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class UsuarioDAO {

    private Context ct;
    private Usuario us;
    private ArrayList<Usuario> lista;
    private SQLiteDatabase sql;
    private String db = "Biblioteca";
    private String tabla =
            "create table if not exists usuarios(id integer primary key autoincrement," +
                    " nombre text," +
                    " apellido text," +
                    " correo text," +
                    " password text )";

    public UsuarioDAO(Context ct) {
        this.ct = ct;
        sql = ct.openOrCreateDatabase(db, ct.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        us = new Usuario();
    }

    public boolean insertUsuario(Usuario us){
        if (buscar(us.getCorreo()) == 0) {
            ContentValues cv = new ContentValues();
            cv.put("nombre", us.getNombre());
            cv.put("apellido", us.getApellido());
            cv.put("correo", us.getCorreo());
            cv.put("password", us.getPassword());
            return (sql.insert("usuarios",null,cv) > 0);
        }else{
            return false;
        }
    }

    public int buscar(String u) {

        int numU = 0;
        lista = selectUsuarios();
        for (Usuario us:lista) {
            if (us.getCorreo().equals(u)) {
                numU++;
            }
        }
        return numU;
    }

    public ArrayList<Usuario> selectUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuarios",null);

        if (cr != null && cr.moveToFirst()) {
            do {
                Usuario us = new Usuario();
                us.setId(cr.getInt(0));
                us.setNombre(cr.getString(1));
                us.setApellido(cr.getString(2));
                us.setCorreo(cr.getString(3));
                us.setPassword(cr.getString(4));
                lista.add(us);
            }
            while(cr.moveToNext());
        }
        return lista;
    }

    public  int login(String us, String crr){

        int lg = 0;
        Cursor cr = sql.rawQuery("select * from usuarios",null);
        if (cr != null && cr.moveToFirst()) {
            do {
                if (cr.getString(3).equals(us) && cr.getString(4).equals(crr)) {
                    lg++;
                }
            }
            while(cr.moveToNext());
        }
        return lg;
    }

    public Usuario getUsuario(String usr, String passwd) {
        lista = selectUsuarios();

        for (Usuario us:lista) {
            if (us.getCorreo().equals(usr) && us.getPassword().equals(passwd)){
                return us;
            }
        }
        return null;
    }

    public Usuario getUsuarioById(int id){
        lista = selectUsuarios();

        for (Usuario us:lista) {
            if (us.getId() == id) {
                return us;
            }
        }
        return null;
    }
}
