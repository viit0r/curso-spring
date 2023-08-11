package com.viit0r.cursospring.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <Origem, Destino> Destino parseObject(Origem origem, Class<Destino> destino) {
        return mapper.map(origem, destino);
    }

    public static <Origem, Destino> List<Destino> parseListObjects(List<Origem> listOrigens, Class<Destino> destino) {
        List<Destino> listObjetosDestino = new ArrayList<Destino>();

        for (Origem origem: listOrigens) {
            listObjetosDestino.add(mapper.map(origem, destino));
        }
        return listObjetosDestino;
    }
}
