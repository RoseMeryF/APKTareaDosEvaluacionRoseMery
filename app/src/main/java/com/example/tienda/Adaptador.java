package com.example.tienda;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Adaptador extends BaseAdapter {
    ArrayList<Producto> lista;
    daoProducto dao;
    Producto c;
    Activity a;
    int id=0;

    public Adaptador(ArrayList<Producto> lista, daoProducto dao, Activity a) {
        this.lista = lista;
        this.dao = dao;
        this.a = a;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getCount(){
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        c=lista.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        c= lista.get(position);
        return c.getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater li = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = li.inflate(R.layout.item,null);
        }
        c=lista.get(position);
        TextView producto = v.findViewById(R.id.producto);
        TextView descripcion = v.findViewById(R.id.descripcion);
        TextView precio = v.findViewById(R.id.precio);
        Button editar = v.findViewById(R.id.btn_editar);
        Button eliminar = v.findViewById(R.id.btn_eliminar);
        producto.setText(c.getProducto());
        descripcion.setText(c.getDescripcion());
        precio.setText(c.getPrecio());
        editar.setTag(position);
        eliminar.setTag(position);
        editar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(convertView.getTag().toString());
                final Dialog dialog = new Dialog(a);
                dialog.setTitle("Editar Registro");
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.dialogo);
                dialog.show();
                final EditText producto = dialog.findViewById(R.id.et_producto);
                final EditText descripcion = dialog.findViewById(R.id.et_descripcion);
                final EditText precio = dialog.findViewById(R.id.et_precio);

                Button guardar = dialog.findViewById(R.id.btn_agregar);
                Button cancelar = dialog.findViewById(R.id.btn_cancelar);
                c=lista.get(pos);
                setId(c.getId());
                producto.setText(c.getProducto());
                descripcion.setText(c.getDescripcion());
                precio.setText(c.getPrecio());
                guardar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            c = new Producto(getId(),producto.getText().toString(),
                                    descripcion.getText().toString(),precio.getText().toString());
                            dao.editar(c);
                            lista = dao.verTodo();
                            notifyDataSetChanged();
                            dialog.dismiss();
                        }catch (Exception e){
                            Toast.makeText(a, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                cancelar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = Integer.parseInt(convertView.getTag().toString());
                        c = lista.get(position);
                        setId(c.getId());
                        AlertDialog.Builder del = new AlertDialog.Builder(a);
                        del.setMessage("Estas seguro de eliminar");
                        del.setCancelable(false);
                        del.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dao.eliminar(getId());
                                lista=dao.verTodo();
                                notifyDataSetChanged();
                            }
                        });
                        del.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                        del.show();
                    }
                });
            }
        });
        return v;
    }
}
