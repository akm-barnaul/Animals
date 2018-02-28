package ru.akm.testtask.animals.jetty.security;

/**
 *
 * @author akm
 */
public interface SecurityService {
    String findLoggedInUsername();

    void autologin(String username, String password);
}
