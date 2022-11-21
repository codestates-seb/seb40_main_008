package main008.BED.uploadClass.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The Class is not found.")
public class UploadClassNotFoundException extends RuntimeException {
}
