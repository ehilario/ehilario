package com.challenge.Exceptions;

import com.challenge.Model.CustomException;
import com.challenge.Model.ExceptionResponse;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Created by ehilario on 4/7/2017.
 */
@ControllerAdvice
//@EnableWebMvc
public class ExceptionHandlerController {
    public static final Logger LOGGER = Logger.getLogger(ExceptionHandlerController.class);
  @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> generalException(Exception exception) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionResponse.setDescription(exception.getLocalizedMessage());
        LOGGER.error(exception.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ExceptionResponse> RequestException(CustomException customException) throws Exception {
        ExceptionResponse exceptionResponse = new ExceptionResponse();
        exceptionResponse.setCode(HttpStatus.BAD_REQUEST.value());
        exceptionResponse.setDescription(customException.getMessage());
        LOGGER.error(customException.getMessage());
        return new ResponseEntity<ExceptionResponse>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

}
