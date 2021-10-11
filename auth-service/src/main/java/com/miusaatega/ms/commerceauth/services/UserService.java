package com.miusaatega.ms.commerceauth.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class UserService implements UserDetailsService {

    @Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template
    // with Ribbon built-in
    protected RestTemplate restTemplate;

    private String accountServiceUrl;


    final ObjectMapper objectMapper = new ObjectMapper();

    public UserService(@Value("${services.url.accounts}") String accountServiceUrl) {
        this.accountServiceUrl = accountServiceUrl.startsWith("http") ?
                accountServiceUrl : "http://" + accountServiceUrl;
    }


    List<User> users = new ArrayList<>() {
        {
            add(new User("admin", "admin",
                    new ArrayList<>() {
                        {
                            add(new SimpleGrantedAuthority("ADMIN"));
                        }
                    }));
            add(new User("user", "user",
                    new ArrayList<>() {
                        {
                            add(new SimpleGrantedAuthority("USER"));
                        }
                    }));
        }
    };

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = null;
//        try {
//        String responseStr = restTemplate.getForObject(accountServiceUrl
//                + "/accounts/{username}", String.class, s);
//            JsonNode root = objectMapper.readTree(responseStr);
//            root.path("").asText();
//            user = new User(root.path("username").asText(), root.path("password").asText(), new ArrayList<>() {
//                {
//                    add(new SimpleGrantedAuthority("USER"));
//                }
//            });
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        return user;
        return users.stream().filter(u -> u.getUsername().equals(s)).findFirst().orElse(null);
    }
}