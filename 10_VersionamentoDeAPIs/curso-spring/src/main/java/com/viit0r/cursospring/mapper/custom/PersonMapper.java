package com.viit0r.cursospring.mapper.custom;

import com.viit0r.cursospring.dto.v2.PersonDTOV2;
import com.viit0r.cursospring.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person) {
        PersonDTOV2 personDTO = new PersonDTOV2();
        personDTO.setId(person.getId());
        personDTO.setPrimeiroNome(person.getPrimeiroNome());
        personDTO.setUltimoNome(person.getUltimoNome());
        personDTO.setEndereco(person.getEndereco());
        personDTO.setGenero(person.getGenero());
        personDTO.setDataAniversario(new Date());
        return personDTO;
    }

    public Person convertDTOToEntity(PersonDTOV2 personDTO) {
        Person person = new Person();
        person.setId(personDTO.getId());
        person.setPrimeiroNome(personDTO.getPrimeiroNome());
        person.setUltimoNome(personDTO.getUltimoNome());
        person.setEndereco(personDTO.getEndereco());
        person.setGenero(personDTO.getGenero());
        return person;
    }
}
