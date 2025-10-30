package com.logwin.graphql.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof BookNotFoundException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())  // ← uses the message from your BookNotFoundException
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }

        // Generic fallback for unexpected errors
        return GraphqlErrorBuilder.newError(env)
                .message("Internal Server Error")
                .errorType(ErrorType.INTERNAL_ERROR)
                .build();
    }
}
