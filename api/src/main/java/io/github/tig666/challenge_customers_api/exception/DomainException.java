package io.github.tig666.challenge_customers_api.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DomainException extends RuntimeException {
    int statusCode;
    String message;
}
