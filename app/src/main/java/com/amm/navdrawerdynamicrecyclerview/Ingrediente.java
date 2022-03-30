package com.amm.navdrawerdynamicrecyclerview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Ingrediente {
    private String ingredienteName;

    public Ingrediente(String ingredienteName) {
        this.ingredienteName = ingredienteName;
    }

    public String getIngredienteName() {
        return ingredienteName;
    }

    public void setIngredienteName(String ingredienteName) {
        this.ingredienteName = ingredienteName;
    }

    public static DiffUtil.ItemCallback<Ingrediente> ingedienteDiffCallback = new DiffUtil.ItemCallback<Ingrediente>() {
        @Override
        public boolean areItemsTheSame(@NonNull Ingrediente oldItem, @NonNull Ingrediente newItem) {
            return oldItem.getIngredienteName().equals(newItem.getIngredienteName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Ingrediente oldItem, @NonNull Ingrediente newItem) {
            return oldItem.getIngredienteName().equals(newItem.getIngredienteName());
        }
    };
}
