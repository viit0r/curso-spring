package com.viit0r.cursospring.service;

import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.dto.v2.PersonDTOV2;
import com.viit0r.cursospring.exception.ResourceNotFoundException;
import com.viit0r.cursospring.mapper.Mapper;
import com.viit0r.cursospring.mapper.custom.PersonMapper;
import com.viit0r.cursospring.model.Person;
import com.viit0r.cursospring.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;


@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    @Autowired
    PersonMapper mapper;

    public List<PersonDTO> findAll() {
        logger.info("Buscando todas as pessoas...");

        return Mapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO findById(Long id) {
        logger.info("Buscando uma pessoa...");

        Person personRetornada = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        return Mapper.parseObject(personRetornada, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Criando uma pessoa...");
        Person personCriada = Mapper.parseObject(person, Person.class);
        return Mapper.parseObject(personRepository.save(personCriada), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Criando uma pessoa com a versão 2...");
        Person personCriada = mapper.convertDTOToEntity(person);
        return mapper.convertEntityToDTO(personRepository.save(personCriada));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Atualizando uma pessoa...");

        Person personRecuperada = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        personRecuperada.setPrimeiroNome(person.getPrimeiroNome());
        personRecuperada.setUltimoNome(person.getUltimoNome());
        personRecuperada.setEndereco(person.getEndereco());
        personRecuperada.setGenero(person.getGenero());

        return Mapper.parseObject(personRepository.save(personRecuperada), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deletando uma pessoa...");

        Person personRecuperada = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foram encontrados registros para este ID!"));

        personRepository.delete(personRecuperada);
    }
}
