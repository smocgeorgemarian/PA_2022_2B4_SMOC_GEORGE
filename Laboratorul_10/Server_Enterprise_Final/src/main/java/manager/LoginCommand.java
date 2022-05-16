package manager;

import exception.InvalidNumberOfArgs;
import exception.InvalidUser;
import exception.UserAlreadyLoggedIn;
import persistance.Person;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class LoginCommand extends Command{
    @Override
    public boolean validate(List<String> commandArgs) {
        return commandArgs.size() == 1;
    }

    void execute(List<String> commandArgs, EntityManager em) throws InvalidNumberOfArgs, InvalidUser, UserAlreadyLoggedIn {
        if (!validate(commandArgs))
            throw new InvalidNumberOfArgs();
        String username = commandArgs.get(0);
        PersonsRepository personsRepository = new PersonsRepository(em);
        Person person;
        try {
            person = personsRepository.findByName(username);
        }
        catch (Exception e) {
            throw new InvalidUser();
        }
        if (person.getLog())
            throw new UserAlreadyLoggedIn();
        personsRepository.begin();
        person.setLog(Boolean.TRUE);
        personsRepository.commit();
   }
}