package unittest.mapper;

import com.viit0r.cursospring.dto.v1.PersonDTO;
import com.viit0r.cursospring.mapper.Mapper;
import com.viit0r.cursospring.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import unittest.mapper.mock.MockPerson;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DozerConverterTest {
    
    MockPerson inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockPerson();
    }

    @Test
    public void parseEntityToDTOTest() {
        PersonDTO output = Mapper.parseObject(inputObject.mockEntity(), PersonDTO.class);
        assertEquals(Long.valueOf(0L), output.getIdPerson());
        assertEquals("First Name Test0", output.getPrimeiroNome());
        assertEquals("Last Name Test0", output.getUltimoNome());
        assertEquals("Addres Test0", output.getEndereco());
        assertEquals("Male", output.getGenero());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        List<PersonDTO> outputList = Mapper.parseListObjects(inputObject.mockEntityList(), PersonDTO.class);
        PersonDTO outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getIdPerson());
        assertEquals("First Name Test0", outputZero.getPrimeiroNome());
        assertEquals("Last Name Test0", outputZero.getUltimoNome());
        assertEquals("Addres Test0", outputZero.getEndereco());
        assertEquals("Male", outputZero.getGenero());
        
        PersonDTO outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getIdPerson());
        assertEquals("First Name Test7", outputSeven.getPrimeiroNome());
        assertEquals("Last Name Test7", outputSeven.getUltimoNome());
        assertEquals("Addres Test7", outputSeven.getEndereco());
        assertEquals("Female", outputSeven.getGenero());
        
        PersonDTO outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getIdPerson());
        assertEquals("First Name Test12", outputTwelve.getPrimeiroNome());
        assertEquals("Last Name Test12", outputTwelve.getUltimoNome());
        assertEquals("Addres Test12", outputTwelve.getEndereco());
        assertEquals("Male", outputTwelve.getGenero());
    }

    @Test
    public void parseDTOToEntityTest() {
        Person output = Mapper.parseObject(inputObject.mockDTO(), Person.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("First Name Test0", output.getPrimeiroNome());
        assertEquals("Last Name Test0", output.getUltimoNome());
        assertEquals("Addres Test0", output.getEndereco());
        assertEquals("Male", output.getGenero());
    }

    @Test
    public void parserDTOListToEntityListTest() {
        List<Person> outputList = Mapper.parseListObjects(inputObject.mockDTOList(), Person.class);
        Person outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("First Name Test0", outputZero.getPrimeiroNome());
        assertEquals("Last Name Test0", outputZero.getUltimoNome());
        assertEquals("Addres Test0", outputZero.getEndereco());
        assertEquals("Male", outputZero.getGenero());
        
        Person outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("First Name Test7", outputSeven.getPrimeiroNome());
        assertEquals("Last Name Test7", outputSeven.getUltimoNome());
        assertEquals("Addres Test7", outputSeven.getEndereco());
        assertEquals("Female", outputSeven.getGenero());
        
        Person outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("First Name Test12", outputTwelve.getPrimeiroNome());
        assertEquals("Last Name Test12", outputTwelve.getUltimoNome());
        assertEquals("Addres Test12", outputTwelve.getEndereco());
        assertEquals("Male", outputTwelve.getGenero());
    }
}
