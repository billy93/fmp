package com.atibusinessgroup.fmp.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidPasswordException extends AbstractThrowableProblem {

	public InvalidPasswordException(String message) {
        super(ErrorConstants.INVALID_PASSWORD_TYPE, message, Status.BAD_REQUEST);
    }
}
