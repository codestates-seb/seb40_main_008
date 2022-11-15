package main008.BED.docs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "The document with this name already exists")
public class DocsAlreadyExistsException extends RuntimeException {
}
