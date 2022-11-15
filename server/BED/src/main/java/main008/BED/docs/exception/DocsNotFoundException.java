package main008.BED.docs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The Docs with docs-id is not found.")
public class DocsNotFoundException extends RuntimeException {
}
