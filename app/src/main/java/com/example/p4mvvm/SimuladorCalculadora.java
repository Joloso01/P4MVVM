package com.example.p4mvvm;

public class SimuladorCalculadora {

    public static class Solicitud{

        public int distancia;
        public double precioCombustible;
        double consumo = 0;

        public Solicitud(int distancia, double precioCombustible, double tipoCombustible) {
            this.distancia = distancia;
            this.precioCombustible = precioCombustible;
            this.consumo = tipoCombustible;
        }

        public double precioCalculado=((precioCombustible*distancia)/100);
    }

    interface Callback{
        void cuandoCalculadoTerminado(double precio);
        void cuandoEmpiezeElCalculo();
        void cuandoFinaliceElCalculo();
    }

    public void calcular(Solicitud solicitud, Callback callback){

        callback.cuandoEmpiezeElCalculo();
        CombustibleFragment combustibleFragment = new CombustibleFragment();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {}
        callback.cuandoCalculadoTerminado((((solicitud.precioCombustible*solicitud.distancia)/100)*solicitud.consumo));
        callback.cuandoFinaliceElCalculo();
    }
}