package com.amm.navdrawerdynamicrecyclerview.main;

import androidx.core.util.Pair;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.amm.navdrawerdynamicrecyclerview.Ingrediente;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends ViewModel {

    private MutableLiveData<Pair<String ,Integer>> _color;
    private MutableLiveData<String> _ingrediente;
    private MutableLiveData<List<Ingrediente>> _ingredienteList;

    //Constructor sin parametros para que podamos usar la default Application Factory.
    public SharedViewModel() {
    }

    //-----_ingredienteList set-get
    public void initIngredienteList(String[] arrayIngredientes) {
        List<Ingrediente> ingredienteList = new ArrayList<Ingrediente>();
        for (int i = 0; i < arrayIngredientes.length; i++) {
            ingredienteList.add(new Ingrediente(arrayIngredientes[i]));
        }
        if (_ingredienteList == null) {
            _ingredienteList = new MutableLiveData<>();
        }
        _ingredienteList.setValue(ingredienteList);
        _ingredienteList.getValue().sort(Ingrediente::compareTo);
    }

    public LiveData<List<Ingrediente>> getIngredienteList() {
        if (_ingredienteList == null) {
            _ingredienteList = new MutableLiveData<>();
        }
        return _ingredienteList;
    }

    public void deleteIngrediente(int position) {
        if (_ingredienteList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_ingredienteList.getValue());
            ingredienteList.remove(position);
            _ingredienteList.setValue(ingredienteList);
        }
    }

    public void addIngrediente(Ingrediente ingrediente) {
        if (_ingredienteList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_ingredienteList.getValue());
            ingredienteList.add(ingrediente);
            ingredienteList.sort(Ingrediente::compareTo);
            _ingredienteList.setValue(ingredienteList);
        }
    }

    public void updateIngrediente(Ingrediente newIngrediente, int position) {
        if (_ingredienteList.getValue() != null) {
            List<Ingrediente> ingredienteList = new ArrayList<>(_ingredienteList.getValue());
            ingredienteList.remove(position);
            ingredienteList.add(position, newIngrediente);
            _ingredienteList.setValue(ingredienteList);
        }
    }

    public boolean findIngredienteByName(String ingredienteName) {
        boolean retVal=false;
        if (_ingredienteList.getValue() != null) {
            for (Ingrediente ingrediente : _ingredienteList.getValue()) {
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

    //-----_color set-get
    public LiveData<Pair<String, Integer>> getSelectedColor() {
        if (_color == null){
            _color = new MutableLiveData<Pair<String,Integer>>();
        }
        return _color;
    }

    public void setSelectedColor(Pair<String, Integer> selectedColor) {
        this._color.setValue(selectedColor);
    }
    //-----------------------------

    //-----Ingrediente set-get
    public LiveData<String> getIngrediente() {
        if (_ingrediente == null){
            _ingrediente = new MutableLiveData<String>();
        }
        return _ingrediente;
    }

    public void setSelectedColor(String ingrediente) {
        this._ingrediente.setValue(ingrediente);
    }
    //------------------------
}
