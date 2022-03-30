package com.amm.navdrawerdynamicrecyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Ingrediente implements Comparable<Ingrediente> {
    private String strIngrediente;

    public Ingrediente(String strIngrediente) {
        this.strIngrediente = strIngrediente;
    }

    @NonNull
    @Override
    public String toString() {

        return strIngrediente;
    }

    @Override
    public int compareTo(Ingrediente ingrediente) {
        return strIngrediente.compareTo(ingrediente.toString());
    }

    public void setName(String strIngrediente) {
        this.strIngrediente = strIngrediente;
    }

    public static DiffUtil.ItemCallback<Ingrediente> ingredienteDiffCallback = new DiffUtil.ItemCallback<Ingrediente>() {
        @Override
        public boolean areItemsTheSame(@NonNull Ingrediente oldItem, @NonNull Ingrediente newItem) {
            return oldItem.toString().equals(newItem.toString());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ingrediente oldItem, @NonNull Ingrediente newItem) {
            return oldItem.toString().equals(newItem.toString());
        }
    };

}
