package ru.akm.testtask.animals.jetty.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

/**
 * Сервис для ограничения 10-ю попытками в час
 * 
 * @author akm
 */
@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPT = 10;
    private LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        super();
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.HOURS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    /**
     * Обработка успешного логина
     * 
     * @param key Имя пользователя
     */
    public void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    /**
     * Обработка ошибочного логина
     * 
     * @param key Имя пользователя
     */
    public void loginFailed(String key) {
        int attempts = 0;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    /**
     * Проверка признака блокировки
     * 
     * @param key   Имя пользователя
     * @return      True, если блокирован
     */
    public boolean isBlocked(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException e) {
            return false;
        }
    }
}
