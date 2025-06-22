package com.alura.literatura.service;

public interface IConvierteDatos {
    default <T> T obtenerDatos(String json, Class<T> Clase) {
        return null;
    }
}
