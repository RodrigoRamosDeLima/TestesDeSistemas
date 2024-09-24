package com.example;

public class GeometriaService {

    // Método para calcular a área do triângulo
    public double areaTriangulo(double base, double altura) {
        return (base * altura) / 2;
    }

    // Método para calcular a área do quadrado
    public double areaQuadrado(double lado) {
        return lado * lado;
    }

    // Método para calcular a área do retângulo
    public double areaRetangulo(double largura, double altura) {
        return largura * altura;
    }

    // Método para calcular a área da circunferência
    public double areaCirculo(double raio) {
        return Math.PI * raio * raio;
    }

    // Método para calcular o volume da esfera
    public double volumeEsfera(double raio) {
        return (4.0 / 3.0) * Math.PI * Math.pow(raio, 3);
    }

    // Método para calcular o volume do cubo
    public double volumeCubo(double lado) {
        return Math.pow(lado, 3);
    }

    // Método para calcular o volume do cilindro
    public double volumeCilindro(double raio, double altura) {
        return Math.PI * Math.pow(raio, 2) * altura;
    }
}
