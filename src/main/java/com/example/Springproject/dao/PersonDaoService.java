package com.example.Springproject.dao;

import com.example.Springproject.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("personDao")
public class PersonDaoService implements PersonDao{

    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Person> getAllPersons() {
        return DB;
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person = getPersonById(id);
        if (person.isEmpty()){
            return 0;
        }
        DB.remove(person.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return getPersonById(id)
                .map(p -> {
                    int indexOfPersonToDelete = DB.indexOf(p);
                    if (indexOfPersonToDelete >= 0){
                        DB.set(indexOfPersonToDelete,new Person(id,person.getName()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
