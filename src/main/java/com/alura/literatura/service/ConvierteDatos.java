package com.alura.literatura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> Clase) {
           try{
               return objectMapper.readValue(json,Clase);
           }    catch (JsonProcessingException e){
               throw new RuntimeException(e);
           }

    }
}
