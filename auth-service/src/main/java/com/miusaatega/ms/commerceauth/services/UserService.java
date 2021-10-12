package com.miusaatega.ms.commerceauth.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@PropertySource("classpath:application.properties")
public class UserService implements UserDetailsService {

    @Autowired        // NO LONGER auto-created by Spring Cloud (see below)
    @LoadBalanced     // Explicitly request the load-balanced template
    // with Ribbon built-in
    protected RestTemplate restTemplate;

    private String accountServiceUrl;

    @Value("${services.token}")
    private String accountServiceToken;


    final ObjectMapper objectMapper = new ObjectMapper();

    public UserService(@Value("${services.url.account}") String accountServiceUrl) {
        this.accountServiceUrl = accountServiceUrl;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth(accountServiceToken);
            String responseStr = restTemplate.exchange(accountServiceUrl
                + "/api/account/login/{email}",  HttpMethod.GET, new HttpEntity<>(headers), String.class, s)
                    .getBody();
            JsonNode root = objectMapper.readTree(responseStr);
            user = new User(root.path("email").asText(), root.path("password").asText(), new ArrayList<>() {
                {
                    add(new SimpleGrantedAuthority("USER"));
                }
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("error: " + e.getMessage());
        }
        return user;
    }
}