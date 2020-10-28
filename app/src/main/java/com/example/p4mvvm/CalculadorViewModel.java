package com.example.p4mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CalculadorViewModel extends AndroidViewModel {

    Executor executor;
    SimuladorCalculadora simuladorCalculadora;
    MutableLiveData<Double> precioBase = new MutableLiveData<>();
    MutableLiveData calculando = new MutableLiveData<>();
    static MutableLiveData<Integer> errorDistnacia = new MutableLiveData<>();
    static MutableLiveData<Double> errorPrecio = new MutableLiveData<>();

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
                       errorDistnacia.postValue(null);
                       errorPrecio.postValue(null);
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

                   @Override
                   public void cuandoHayaErrorDeDistancia(int distanciaMinima) {
                       errorDistnacia.postValue(distanciaMinima);
                   }

                   @Override
                   public void cuandoHayaErrorDePrecio(double precioMinimo) {
                        errorPrecio.postValue(precioMinimo);
                   }
               });
            }
        });
    }
}