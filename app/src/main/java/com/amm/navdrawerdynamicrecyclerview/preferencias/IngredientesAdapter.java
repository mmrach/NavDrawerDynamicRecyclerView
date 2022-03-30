package com.amm.navdrawerdynamicrecyclerview.preferencias;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.amm.navdrawerdynamicrecyclerview.Ingrediente;
import com.amm.navdrawerdynamicrecyclerview.main.IngredientesViewModel;
import com.amm.navdrawerdynamicrecyclerview.main.SharedViewModel;

import java.util.List;

import navdrawerdynamicrecyclerview.R;

public class IngredientesAdapter extends ListAdapter<Ingrediente, IngredientesAdapter.IngredienteViewHolder> {

    // Referencia a la default ViewModelFactory de la App, a usar cuando el ViewModel no recibe parámetros y se usa su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;
    // Declaramos una referencia para el ViewModel de SharedViewModel.
    private IngredientesViewModel ingredientesViewModel;

    protected IngredientesAdapter(@NonNull DiffUtil.ItemCallback<Ingrediente> diffCallback, Context context) {
        super(diffCallback);
        // Sin Factory, cogiendo la Factory del objeto Application
        theAppFactory = ViewModelProvider.AndroidViewModelFactory.getInstance((Application) context.getApplicationContext());
        ingredientesViewModel = new ViewModelProvider((ViewModelStoreOwner) context, (ViewModelProvider.Factory) theAppFactory).get(IngredientesViewModel.class);
    }

    @NonNull
    @Override
    public IngredienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.ingrediente_row_layout, parent, false);
        return new IngredienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredienteViewHolder holder, int position) {
        Ingrediente ingrediente = getCurrentList().get(position);
        if (ingrediente != null){
            holder.bind(ingrediente.toString());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Seleccionado: " + ingrediente.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class IngredienteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvIngredienteItem;
        private ImageButton ibDelete;

         public IngredienteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIngredienteItem = itemView.findViewById(R.id.tvIngredienteItem);
            ibDelete = itemView.findViewById(R.id.ibDelete);
            ibDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ingredientesViewModel.deleteIngrediente(getLayoutPosition());
                }
            });
        }

        public void bind(String string) {
             tvIngredienteItem.setText(string);
        }
    }

}

