package media.social.exceptions;

import media.social.dtos.ErrorDetailsDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // handler specific exceptions
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetailsDto> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest
            )
    {
            ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto();

            errorDetailsDto.setTimestamp(new Date());
            errorDetailsDto.setMessage(exception.getMessage());
            errorDetailsDto.setDetails(webRequest.getDescription(false));
            return new ResponseEntity<>(errorDetailsDto, HttpStatus.NOT_FOUND);
    }


    // handler specific exceptions
    @ExceptionHandler(BlogAPIsException.class)
    public ResponseEntity<ErrorDetailsDto> handleBlogAPIsException(
            BlogAPIsException exception, WebRequest webRequest
    )
    {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto();

        errorDetailsDto.setTimestamp(new Date());
        errorDetailsDto.setMessage(exception.getMessage());
        errorDetailsDto.setDetails(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.BAD_REQUEST);
    }

    // handler global exceptions

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetailsDto> handleGlobalException(
            Exception exception, WebRequest webRequest
    )
    {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto();

        errorDetailsDto.setTimestamp(new Date());
        errorDetailsDto.setMessage(exception.getMessage());
        errorDetailsDto.setDetails(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError)error).getField();
            String fieldMessage = error.getDefaultMessage();
            errors.put(fieldName,fieldMessage);
        });

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetailsDto> handleAccessDeniedException(
            AccessDeniedException exception, WebRequest webRequest
    )
    {
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto();

        errorDetailsDto.setTimestamp(new Date());
        errorDetailsDto.setMessage(exception.getMessage());
        errorDetailsDto.setDetails(webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.UNAUTHORIZED);
    }
}
