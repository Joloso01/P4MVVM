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
    }

    interface Callback{
        void cuandoCalculadoTerminado(double precio);
        void cuandoEmpiezeElCalculo();
        void cuandoFinaliceElCalculo();
        void cuandoHayaErrorDeDistancia(int distanciaMinima);
        void cuandoHayaErrorDePrecio(double precioMinimo);
    }

    public void calcular(Solicitud solicitud, Callback callback){
        int distanciaMinima = 0;
        double precioMinimo = 0;

        callback.cuandoEmpiezeElCalculo();
        try {
            Thread.sleep(3000);
            distanciaMinima = 1;
            precioMinimo = 0.1f;
        } catch (InterruptedException e) {}

        boolean error = false;
        if (solicitud.distancia < distanciaMinima) {
            callback.cuandoHayaErrorDeDistancia(distanciaMinima);
            error = true;
        }

        if (solicitud.precioCombustible < precioMinimo) {
            callback.cuandoHayaErrorDePrecio(precioMinimo);
            error = true;
        }

        if(!error) {
            callback.cuandoCalculadoTerminado((((solicitud.precioCombustible*solicitud.distancia)/100)*solicitud.consumo));
        }

        callback.cuandoFinaliceElCalculo();
    }
}