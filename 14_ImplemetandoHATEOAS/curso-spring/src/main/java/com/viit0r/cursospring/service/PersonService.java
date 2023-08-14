package com.viit0r.cursospring.service;

import com.viit0r.cursospring.controller.PersonController;
import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.exception.ResourceNotFoundException;
import com.viit0r.cursospring.mapper.Mapper;
import com.viit0r.cursospring.model.Person;
import com.viit0r.cursospring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;


@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public List<PersonDTO> findAll() {
        logger.info("Buscando todas as pessoas...");

        List<PersonDTO> personsDTO = Mapper.parseListObjects(personRepository.findAll(), PersonDTO.class);

        //Adicionando HATEOAS em cada item da lista
        personsDTO.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getIdPerson())).withSelfRel()));
        return personsDTO;
    }

    public PersonDTO findById(Long id) {
        logger.info("Buscando uma pessoa...");

        Person personRetornada = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        PersonDTO personDTO = Mapper.parseObject(personRetornada, PersonDTO.class);

        //Adicionando HATEOAS
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return personDTO;
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Criando uma pessoa...");
        Person personCriada = Mapper.parseObject(person, Person.class);

        PersonDTO personDTO = Mapper.parseObject(personRepository.save(personCriada), PersonDTO.class);

        //Adicionando HATEOAS
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getIdPerson())).withSelfRel());
        return personDTO;
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Atualizando uma pessoa...");

        Person personRecuperada = personRepository.findById(person.getIdPerson())
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        personRecuperada.setPrimeiroNome(person.getPrimeiroNome());
        personRecuperada.setUltimoNome(person.getUltimoNome());
        personRecuperada.setEndereco(person.getEndereco());
        personRecuperada.setGenero(person.getGenero());

        PersonDTO personDTO = Mapper.parseObject(personRepository.save(personRecuperada), PersonDTO.class);

        //Adicionando HATEOAS
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getIdPerson())).withSelfRel());
        return personDTO;
    }

    public void delete(Long id) {
        logger.info("Deletando uma pessoa...");

        Person personRecuperada = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        personRepository.delete(personRecuperada);
    }
}
