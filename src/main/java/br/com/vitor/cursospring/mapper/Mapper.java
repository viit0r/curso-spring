package br.com.vitor.cursospring.mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class Mapper {

    private static ModelMapper mapper = new ModelMapper();

    public static <O,D> D parseObject(O origem, Class<D> destino) {
        return mapper.map(origem, destino);
    }

    public static <O,D> List<D> parseListsObjects(List<O> origem, Class<D> destino) {

        List<D> listDestino = new ArrayList<>();

        for (O or: origem) {
            listDestino.add(mapper.map(or, destino));
        }

        return listDestino;
    }
}
