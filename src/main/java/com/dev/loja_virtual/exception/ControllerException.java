package com.dev.loja_virtual.exception;

import java.net.http.HttpHeaders;
import java.sql.SQLException;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dev.loja_virtual.dto.ObjectErrorDTO;

@RestControllerAdvice
@ControllerAdvice
public class ControllerException extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ExceptionMsg.class})
	public ResponseEntity<Object> handleExceptionCustomEntity(ExceptionMsg ex) {

		ObjectErrorDTO objectErrorDTO = new ObjectErrorDTO();

		objectErrorDTO.setError(ex.getMessage());
		objectErrorDTO.setCode(HttpStatus.OK.toString());

		return new ResponseEntity<Object>(objectErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/* Captura Exceções do Projeto */
	@ExceptionHandler({Exception.class, RuntimeException.class, Throwable.class})
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		ObjectErrorDTO objectErrorDTO = new ObjectErrorDTO();

		String msg = "";

		if (ex instanceof MethodArgumentNotValidException) {
			List<ObjectError> list = ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors();

			for (ObjectError objectError : list) {
				msg += objectError.getDefaultMessage() + "\n";
			}
		} 
		else {
			msg = ex.getMessage();
		}

		objectErrorDTO.setError(msg);
		objectErrorDTO.setCode(status.value() + "===>" + status.getReasonPhrase());
		
		ex.printStackTrace();

		return new ResponseEntity<Object>(objectErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

	/* Captura erro de Banco de Dados */
	@ExceptionHandler({DataIntegrityViolationException.class, ConstraintViolationException.class, SQLException.class})
	protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex) {

		ObjectErrorDTO objectErrorDTO = new ObjectErrorDTO();

		String msg = "";

		if (ex instanceof DataIntegrityViolationException) {
			msg = "Erro de integridade no banco: "
					+ ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof ConstraintViolationException) {
			msg = "Erro de chave estrangeira: "
					+ ((ConstraintViolationException) ex).getCause().getCause().getMessage();
		} else if (ex instanceof SQLException) {
			msg = "Erro de SQL do Banco" + ((SQLException) ex).getCause().getCause().getMessage();
		} else {
			msg = ex.getMessage();
		}
		
		objectErrorDTO.setError(msg);
		objectErrorDTO.setCode(msg);
		
		ex.printStackTrace();
		
		
		return new ResponseEntity<Object>(objectErrorDTO, HttpStatus.INTERNAL_SERVER_ERROR); 
	}
}
