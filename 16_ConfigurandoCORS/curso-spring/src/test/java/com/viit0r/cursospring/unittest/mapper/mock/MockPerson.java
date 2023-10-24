package com.viit0r.cursospring.unittest.mapper.mock;

import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonDTO mockDTO() {
        return mockDTO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDTO> mockDTOList() {
        List<PersonDTO> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setEndereco("Addres Test" + number);
        person.setPrimeiroNome("First Name Test" + number);
        person.setGenero(((number % 2)==0) ? "Male" : "Female");
        person.setId(number.longValue());
        person.setUltimoNome("Last Name Test" + number);
        return person;
    }

    public PersonDTO mockDTO(Integer number) {
        PersonDTO person = new PersonDTO();
        person.setEndereco("Addres Test" + number);
        person.setPrimeiroNome("First Name Test" + number);
        person.setGenero(((number % 2)==0) ? "Male" : "Female");
        person.setIdPerson(number.longValue());
        person.setUltimoNome("Last Name Test" + number);
        return person;
    }

}
