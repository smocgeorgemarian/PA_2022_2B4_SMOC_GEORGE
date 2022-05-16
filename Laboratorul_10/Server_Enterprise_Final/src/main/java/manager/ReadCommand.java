package manager;

import exception.InvalidNumberOfArgs;
import persistance.Message;
import repository.MessageRepository;
import repository.PersonsRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.SimpleTimeZone;

public class ReadCommand extends Command{
    @Override
    public boolean validate(List<String> commandArgs) {
        return commandArgs.isEmpty();
    }

    public String execute(List<String> commandArgs, EntityManager em, String lastLoggedInUser) throws InvalidNumberOfArgs {
        if (!validate(commandArgs))
            throw new InvalidNumberOfArgs();
        PersonsRepository personsRepository = new PersonsRepository(em);
        Long lastId = personsRepository.findByName(lastLoggedInUser).getId();
        MessageRepository messageRepository = new MessageRepository(em);

        StringBuilder stringBuilder = new StringBuilder();
        List<Message> messageList = messageRepository.findById(lastId);
        messageRepository.begin();
        for (int i = 0; i < messageList.size(); i++) {
            stringBuilder.append("Message with index: ").append(i).append(" ");
            stringBuilder.append("[").append(messageList.get(i).getMessage()).append("] ");
            System.out.println(stringBuilder.toString());
            messageList.get(i).setIsRead(Boolean.TRUE);
        }
        messageRepository.commit();

        if (stringBuilder.isEmpty())
            return "Command executed. No new messages";
        System.out.println("Final value: " + stringBuilder.toString());
        return stringBuilder.toString();
    }
}
