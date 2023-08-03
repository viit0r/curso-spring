package com.viit0r.cursospring.service;

import com.viit0r.cursospring.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public List<Person> findAll() {
        List<Person> persons = new ArrayList<>();
        logger.info("Buscando todas as pessoas...");

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(i);
            persons.add(person);
        }

        return persons;
    }

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

    private Person mockPerson(int indice) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setPrimeiroNome("Person name " + indice);
        person.setUltimoNome("Ultimo nome " + indice);
        person.setEndereco("Qualquer endereço no Brasil " + indice);
        person.setGenero("Masculino");

        return person;
    }
}
