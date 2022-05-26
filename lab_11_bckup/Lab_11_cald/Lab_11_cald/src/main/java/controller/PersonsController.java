package controller;

import io.swagger.annotations.Api;
import manager.FactoryManager;
import result_modeler.Friendship;
import result_modeler.NameModifier;
import result_modeler.NewPerson;
import result_modeler.TopCreator;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import persistance.Friend;
import persistance.FriendId;
import persistance.Person;
import repository.FriendRepository;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import java.util.List;

@Component
@Controller
@RequestMapping("/persons")
@Api
public class PersonsController {
    private final PersonsRepository personsRepository;
    private final FriendRepository friendRepository;
    private final TopCreator topCreator;

    public PersonsController() {
        EntityManagerFactory emf = FactoryManager.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        personsRepository = new PersonsRepository();
        friendRepository = new FriendRepository(em);
        topCreator = new TopCreator(personsRepository, friendRepository);
    }

    @GetMapping
    public @ResponseBody
    List<Person> getPersons() {
        return personsRepository.findAll();
    }

    @GetMapping("/count")
    public @ResponseBody
    int countPersons() {
        return (int) personsRepository.Count().longValue();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Person getPerson(@PathVariable("id") long id) {
        return personsRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Consumes("application/json")
    public @ResponseBody
    Person addPerson(@RequestBody NewPerson newPerson) {
        Person person = new Person(newPerson.name, Boolean.FALSE);
        personsRepository.persist(person);
        return person;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Person updatePerson(@RequestBody NameModifier nameModifier) {
        Person person = personsRepository.findById((long) nameModifier.id);
        personsRepository.begin();
        person.setName(nameModifier.newName);
        personsRepository.commit();
        return person;
    }

    @PostMapping("/friend")
    public @ResponseBody
    String addFriendship(@RequestBody Friendship friendship) {
        FriendId friendId = new FriendId(friendship.id1, friendship.id2);
        Friend friend = new Friend(friendId);
        friendRepository.persist(friend);
        return "{\"answer\": \"operation completed successfully\"}";
    }

    @GetMapping("/friend/{id}")
    public @ResponseBody
    List<Friend> getFriendsOfPerson(@PathVariable("id") long id) {
        return friendRepository.findById(id);
    }

    @GetMapping("/popular/{k}")
    public @ResponseBody
    List<Person> getMostPopular(@PathVariable("k") int k) {return topCreator.execute(k);}
}
