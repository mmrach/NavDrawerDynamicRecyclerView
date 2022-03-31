package com.amm.navdrawerdynamicrecyclerview.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amm.navdrawerdynamicrecyclerview.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class IngredientesViewModel extends ViewModel {

    private MutableLiveData<List<Ingrediente>> _theList;

    public IngredientesViewModel() {
    }

    public void initList(String[] arrayIngredientes) {
        List<Ingrediente> ingredienteList = new ArrayList<Ingrediente>();
        for (int i = 0; i < arrayIngredientes.length; i++) {
            ingredienteList.add(new Ingrediente(arrayIngredientes[i]));
        }
        if (_theList == null) {
            _theList = new MutableLiveData<>();
        }
        _theList.setValue(ingredienteList);
        _theList.getValue().sort(Ingrediente::compareTo);
    }

    public LiveData<List<Ingrediente>> getList() {
        if (_theList == null) {
            _theList = new MutableLiveData<>();
        }
        return _theList;
    }

    public void deleteIngrediente(int position) {
        if (_theList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_theList.getValue());
            ingredienteList.remove(position);
            _theList.setValue(ingredienteList);
        }
    }

    public void addIngrediente(Ingrediente ingrediente) {
        if (_theList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_theList.getValue());
            ingredienteList.add(ingrediente);
            ingredienteList.sort(Ingrediente::compareTo);
            _theList.setValue(ingredienteList);
        }
    }

    public void updateIngrediente(Ingrediente newIngrediente, int position) {
        if (_theList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_theList.getValue());
            ingredienteList.remove(position);
            ingredienteList.add(position, newIngrediente);
            _theList.setValue(ingredienteList);
        }
    }

    public boolean findIngredienteByName(String ingredienteName) {
        boolean retVal=false;
        if (_theList.getValue() != null) {
            for (Ingrediente ingrediente : _theList.getValue()) {
                if (ingrediente.toString().equals(ingredienteName)){
                    retVal = true;
                    break;
                }
            }
        }
        else {
            retVal=false;
        }
        return retVal;
    }
    //-------------------------------

}
