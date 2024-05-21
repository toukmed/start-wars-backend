package com.cnexia.starwarsbackend.exceptions;

import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @GraphQlExceptionHandler(BusinessException.class)
    public GraphQLError handle(BusinessException ex) {
        log.warn("Person already bookmarked in the database");
        Map<String, Object> extensions = new HashMap<>();
        extensions.put("statusCode", HttpStatus.BAD_REQUEST.value());
        return GraphQLError
                .newError()
                .errorType(ErrorType.BAD_REQUEST)
                .extensions(extensions)
                .message(ex.getMessage())
                .build();
    }

}
