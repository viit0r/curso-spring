package com.viit0r.cursospring.service;

import com.viit0r.cursospring.model.Person;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("Buscando uma pessoa...");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setPrimeiroNome("Vitor");
        person.setUltimoNome("Porto");
        person.setEndereco("São Paulo - SP - Brasil");
        person.setGenero("Masculino");

        return person;
    }
}
