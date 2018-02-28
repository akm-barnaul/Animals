package ru.akm.testtask.animals.jetty.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.akm.testtask.animals.jetty.models.User;

/**
 * Фильтр для обработки аутентификации с при передаче учётных данных в JSON
 * 
 * @author akm
 */
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    public CustomUsernamePasswordAuthenticationFilter() {
        super();
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    public CustomUsernamePasswordAuthenticationFilter(AuthenticationManager am) {
        super();
        this.setAuthenticationManager(am);
        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        User u = null;
        try {
            BufferedReader reader = request.getReader();
            StringBuffer sb = new StringBuffer();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String parsedReq = sb.toString();
            if (parsedReq != null) {
                ObjectMapper mapper = new ObjectMapper();
                u = mapper.readValue(parsedReq, User.class);
            }
        } catch (IOException ex) {
            u = null;
            Logger.getLogger(CustomUsernamePasswordAuthenticationFilter.class.getName()).log(Level.SEVERE, null, ex);
            throw new InternalAuthenticationServiceException("Ошибка чтения авторизационных данных");
        }
        if (u != null) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(u.getUserName(), u.getUserPwd());
            if (userDetailsService != null) {
                token.setDetails(userDetailsService.loadUserByUsername(u.getUserName()));
            }
            return this.getAuthenticationManager().authenticate(token);
        }
        return null;
    }
}
