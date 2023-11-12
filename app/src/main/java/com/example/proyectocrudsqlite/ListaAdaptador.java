package com.example.proyectocrudsqlite;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListaAdaptador extends RecyclerView.Adapter<ListaAdaptador.ViewHolder> {
    private List<list_Elements> mData;
    private LayoutInflater mInflater;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView nombre,ingredientes,precio;
        Button btnEditar, btnEliminar;
        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iconImageView);
            nombre = itemView.findViewById(R.id.nameTxtView);
            ingredientes = itemView.findViewById(R.id.ingreTxtView);
            precio = itemView.findViewById(R.id.precioTxtView);
            btnEditar = (Button) itemView.findViewById(R.id.btnEditar);
            btnEliminar = (Button) itemView.findViewById(R.id.btnEliminar);
        }
        void bindData(final list_Elements item){
            icon.setImageResource(item.getImagen());
            nombre.setText(item.getName());
            ingredientes.setText(item.getIngredientes());
            precio.setText(item.getPrecio());
        }
    }
    public ListaAdaptador(List<list_Elements> item, Context context){
        this.mData = item;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_elements,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListaAdaptador.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }
    public void setItems(List<list_Elements> items){
        mData = items;
    }
}
