package ru.akm.testtask.animals.jetty.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.akm.testtask.animals.jetty.models.ErrorInfo;

/**
 *
 * @author akm
 */
@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint
        implements AuthenticationEntryPoint {

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws IOException {
        
        ObjectMapper mapper = new ObjectMapper(); 
        final ErrorInfo error = new ErrorInfo(666L, "Доступ запрещён",authException.getLocalizedMessage());
        mapper.writeValue(response.getOutputStream(), error);
    }
}
