package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GeometriaServiceTest {
    private GeometriaService geometriaService;

    @BeforeEach
    public void setUp() {
        geometriaService = new GeometriaService();
    }

    @Test
    public void testAreaTriangulo() {
        double area = geometriaService.areaTriangulo(2.0, 2.0);
        assertEquals(2.0, area, 0.01, "A área do triângulo deve ser 2.0");
    }

    @Test
    public void testAreaCirculo() {
        double area = geometriaService.areaCirculo(2.0);
        assertEquals(12.57, area, 0.1, "A área do círculo deve ser aproximadamente 12.57");
    }

    @Test
    public void testAreaQuadrado() {
        double area = geometriaService.areaQuadrado(2.0);
        assertEquals(4.0, area, 0.01, "A área do quadrado deve ser 4.0");
    }

    @Test
    public void testAreaRetangulo() {
        double area = geometriaService.areaRetangulo(2.0, 3.0);
        assertEquals(6.0, area, 0.01, "A área do retângulo deve ser 6.0");
    }

    @Test
    public void testVolumeCubo() {
        double volume = geometriaService.volumeCubo(2.0);
        assertEquals(8.0, volume, 0.01, "O volume do cubo deve ser 8.0");
    }

    @Test
    public void testVolumeCilindro() {
        double volume = geometriaService.volumeCilindro(2.0, 2.0);
        assertEquals(25.13, volume, 0.1, "O volume do cilindro deve ser aproximadamente 25.13");
    }

    @Test
    public void testVolumeEsfera() {
        double volume = geometriaService.volumeEsfera(2.0);
        assertEquals(33.51, volume, 0.1, "O volume da esfera deve ser aproximadamente 33.51");
    }
}
