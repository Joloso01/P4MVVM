package com.example.p4mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CalculadorViewModel extends AndroidViewModel {

    Executor executor;
    SimuladorCalculadora simuladorCalculadora;
    MutableLiveData<Double> precioBase = new MutableLiveData<>();
    MutableLiveData calculando = new MutableLiveData<>();

    public CalculadorViewModel(@NonNull Application application){
        super(application);
        executor = Executors.newSingleThreadExecutor();
        simuladorCalculadora = new SimuladorCalculadora();
    }

    public void calcular(int distancia, double precioCombustible, double tipoCombustible){

        final SimuladorCalculadora.Solicitud solicitud= new SimuladorCalculadora.Solicitud(distancia,precioCombustible,tipoCombustible);

        executor.execute(new Runnable() {
            @Override
            public void run() {
               simuladorCalculadora.calcular(solicitud, new SimuladorCalculadora.Callback() {

                   @Override
                   public void cuandoCalculadoTerminado(double precio) {
                       precioBase.postValue(precio);
                   }

                   @Override
                   public void cuandoEmpiezeElCalculo() {
                       calculando.postValue(true);
                   }

                   @Override
                   public void cuandoFinaliceElCalculo() {
                       calculando.postValue(false);
                   }
               });
            }
        });
    }
}