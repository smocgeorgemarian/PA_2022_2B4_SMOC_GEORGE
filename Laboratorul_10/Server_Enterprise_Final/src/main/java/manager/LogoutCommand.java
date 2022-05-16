package manager;

import exception.InvalidNumberOfArgs;
import exception.NoUserLoggedIn;
import persistance.Person;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class LogoutCommand extends Command{
    @Override
    boolean validate(List<String> commandArgs) {
        return commandArgs.isEmpty();
    }

    public void execute(List<String> commandArgs, EntityManager em, String lastLoggedInUser) throws Exception{
        if (!validate(commandArgs))
            throw new InvalidNumberOfArgs();
        if (lastLoggedInUser.isEmpty())
            throw new NoUserLoggedIn();
        PersonsRepository personsRepository = new PersonsRepository(em);
        Person person = personsRepository.findByName(lastLoggedInUser);
        personsRepository.begin();
        person.setLog(Boolean.FALSE);
        personsRepository.commit();
    }
}
