package com.amm.navdrawerdynamicrecyclerview.preferencias;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.amm.navdrawerdynamicrecyclerview.Ingrediente;
import com.amm.navdrawerdynamicrecyclerview.main.SharedViewModel;

import java.util.List;

import navdrawerdynamicrecyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IngredientesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IngredientesFragment extends Fragment {

    private RecyclerView rvIngredienes;
    private IngredientesAdapter ingredientesAdapter;
    private ImageButton ibRefresh;

    // Referencia a la default ViewModelFactory de la App, a usar cuando el ViewModel no recibe parámetros y se usa su constructor por defecto
    private ViewModelProvider.AndroidViewModelFactory theAppFactory;
    // Declaramos una referencia para el ViewModel de Preferencias.
    private SharedViewModel sharedViewModel;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public IngredientesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IngredientesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IngredientesFragment newInstance(String param1, String param2) {
        IngredientesFragment fragment = new IngredientesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        // Sin Factory, cogiendo la devault ViewMOdelFactory del objeto Application.
        theAppFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication());
        sharedViewModel = new ViewModelProvider( requireActivity(), (ViewModelProvider.Factory) theAppFactory).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredientes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ibRefresh = (ImageButton) getView().findViewById(R.id.ibRefresh);
        ibRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] theArray = getResources().getStringArray(R.array.ingredientes_array);
                sharedViewModel.initIngredienteList(theArray);
            }
        });

        rvIngredienes = (RecyclerView) getView().findViewById(R.id.rvIngredientes);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvIngredienes.setLayoutManager(layoutManager);
        ingredientesAdapter = new IngredientesAdapter(Ingrediente.ingedienteDiffCallback,getContext());
        rvIngredienes.setAdapter(ingredientesAdapter);

        sharedViewModel.getIngredienteList().observe(this, new Observer<List<Ingrediente>>() {
            @Override
            public void onChanged(List<Ingrediente> listaIngredientes) {
                ingredientesAdapter.submitList(listaIngredientes);
            }
        });
    }
}