package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import persistance.Person;
import repository.PersonsRepository;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private static final String PASSWORD = "password";
    @Autowired
    private PersonsRepository personsRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Person user = personsRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), PASSWORD,
                new ArrayList<>());
    }

    @Bean
    JwtUserDetailsService jwtUserDetailsService() {
        return new JwtUserDetailsService();
    }

}
