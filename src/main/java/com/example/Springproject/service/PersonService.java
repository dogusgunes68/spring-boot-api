package com.example.Springproject.service;

import com.example.Springproject.dao.PersonDao;
import com.example.Springproject.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("personDao") PersonDao personDao){
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        return personDao.insertPerson(person);
    }

    public Optional<Person> getPersonById(UUID id){
        return  personDao.getPersonById(id);
    }

    public List<Person> getAllPersons(){
        return personDao.getAllPersons();
    }

    public int deletePersonById(UUID id){
       return personDao.deletePersonById(id);
    }

    public int updatePersonById(UUID id, Person person){
        return personDao.updatePersonById(id, person);
    }

}
