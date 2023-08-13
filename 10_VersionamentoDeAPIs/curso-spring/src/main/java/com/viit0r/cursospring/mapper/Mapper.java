package com.viit0r.cursospring.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static <Origem, Destino> Destino parseObject(Origem origem, Class<Destino> destino) {
        return mapper.map(origem, destino);
    }

    public static <Origem, Destino> List<Destino> parseListObjects(List<Origem> listOrigens, Class<Destino> destino) {
        List<Destino> listObjetosDestino = new ArrayList<>();

        for (Origem origem: listOrigens) {
            listObjetosDestino.add(mapper.map(origem, destino));
        }
        return listObjetosDestino;
    }
}
