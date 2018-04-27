package com.atibusinessgroup.fmp.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class SystemParameterException extends AbstractThrowableProblem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SystemParameterException(String message) {
        super(ErrorConstants.INVALID_SYSTEM_PARAMETER_VALUE, message, Status.BAD_REQUEST);
    }
}
