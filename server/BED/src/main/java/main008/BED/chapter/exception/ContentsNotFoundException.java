package main008.BED.chapter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "The Contents with this id is Not Found.")
public class ContentsNotFoundException extends RuntimeException {
}
