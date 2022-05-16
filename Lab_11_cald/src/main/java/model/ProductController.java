package model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import persistance.Person;
import repository.PersonsRepository;
import manager.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigInteger;
import java.util.List;

@Controller
@RequestMapping("/persons")
public class ProductController {
    private PersonsRepository personsRepository;
    public ProductController() {
        EntityManagerFactory emf = FactoryManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        personsRepository = new PersonsRepository(em);
    }
    @GetMapping
    public @ResponseBody List<Person> getPersons() {
        return personsRepository.findAll();
    }
    @GetMapping("/count")
    public @ResponseBody int countProducts() {
        return (int) personsRepository.Count().longValue();
    }
    @GetMapping("/{id}")
    public @ResponseBody Person getPerson(@PathVariable("id") int id) {
        return personsRepository.findById(BigInteger.valueOf(id));
    }
}
