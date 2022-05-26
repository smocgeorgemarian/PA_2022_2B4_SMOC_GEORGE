package client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import services.MyService;

public class Client {

    public MyService service;

    public Client(){
        service = new MyService(new RestTemplateBuilder());
    }

    public int getCount(){
        return service.getCount();
    }

}
