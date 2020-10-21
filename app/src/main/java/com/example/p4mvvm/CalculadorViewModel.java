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
    MutableLiveData<Double> precio = new MutableLiveData<>();

    public CalculadorViewModel(@NonNull Application application){
        super(application);

        executor = Executors.newSingleThreadExecutor();
        simuladorCalculadora = new SimuladorCalculadora();
    }
}