package com.example.p4mvvm;

public class SimuladorCalculadora {

    public static class Solicitud{

        public int distancia;
        public double precioCombustible;

        public Solicitud(int distancia, double precioCombustible) {
            this.distancia = distancia;
            this.precioCombustible = precioCombustible;
        }

        public double precioCalculado=((precioCombustible*distancia)/100);
    }

    interface Callback{
        void empezarCalculo();
        void finalizarCalculo();
        void cuandoCalculadoTerminado(double precio);
    }

    public double calcular(Solicitud solicitud, Callback callback){
        double consumo = 0;
        CombustibleFragment combustibleFragment = new CombustibleFragment();
        if (combustibleFragment.binding.radioGasolina.isChecked()){
            consumo=solicitud.precioCalculado*1.05;
        }else if (combustibleFragment.binding.radioDiesel.isChecked()){
            consumo=solicitud.precioCalculado*0.95;
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            return solicitud.precioCalculado;
        }
        return consumo;
    }


}
