package com.example.p4mvvm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.p4mvvm.databinding.FragmentCombustibleBinding;

public class CombustibleFragment extends Fragment {
    public FragmentCombustibleBinding binding;
    float tipoCombustible=0.00f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentCombustibleBinding.inflate(inflater, container,false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final CalculadorViewModel calculadorViewModel=new ViewModelProvider(this).get(CalculadorViewModel.class);

        binding.calculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.radioGasolina.isChecked()){
                    tipoCombustible=1.05f;
                }else if (binding.radioDiesel.isChecked()){
                    tipoCombustible=0.95f;
                }

                int distancia= Integer.parseInt(binding.distanciaEt.getText().toString());
                double precio = Double.parseDouble(binding.precioEt.getText().toString());

                calculadorViewModel.calcular(distancia,precio,tipoCombustible);
            }
        });

        calculadorViewModel.precioBase.observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double precio) {
                binding.precioTV.setText(String.format("%.2f",precio));
            }
        });

        calculadorViewModel.calculando.observe(getViewLifecycleOwner(), new Observer() {

            @Override
            public void onChanged(Object o) {
                if (o.equals(true)) {
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.precioTV.setVisibility(View.GONE);
                } else {
                    binding.calculando.setVisibility(View.GONE);
                    binding.precioTV.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}