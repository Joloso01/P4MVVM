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
        void cuandoEmpiezeElCalculo();
        void cuandoFinaliceElCalculo();
        void cuandoCalculadoTerminado(double precio);
    }

    public void calcular(Solicitud solicitud, Callback callback){
        double consumo = 0;
        CombustibleFragment combustibleFragment = new CombustibleFragment();

        if (combustibleFragment.botonGasolina){
            consumo=solicitud.precioCalculado*1.05;
        }else if (combustibleFragment.botonDiesel){
            consumo=solicitud.precioCalculado*0.95;
        }

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            callback.cuandoCalculadoTerminado(consumo);
        }
    }


}
