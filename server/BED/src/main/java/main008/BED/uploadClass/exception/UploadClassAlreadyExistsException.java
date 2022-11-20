package main008.BED.uploadClass.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The Class with this name already exists.")
public class UploadClassAlreadyExistsException extends RuntimeException {
}
