package manager;

import exception.InvalidNumberOfArgs;
import persistance.Person;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import java.util.List;

public class RegisterCommand extends Command{
    @Override
    public boolean validate(List<String> commandArgs) {
        return commandArgs.size() <= 1;
    }

    @Override
    public void execute(List<String> commandArgs, EntityManager em) throws InvalidNumberOfArgs{
        if (!validate(commandArgs))
            throw new InvalidNumberOfArgs();

        PersonsRepository personsRepository = new PersonsRepository(em);
        String username = commandArgs.get(0);
        Person person = new Person(username, Boolean.TRUE);

        personsRepository.begin();
        personsRepository.persist(person);
        personsRepository.commit();
    }
}
