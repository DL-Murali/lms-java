package com.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(DbException.class)
	public ResponseEntity<DbDetails> handleResourceNotFound(DbException db) {

		DbDetails db1 = new DbDetails();

		db1.setMessage(db.getMessage());

		return new ResponseEntity<>(db1, HttpStatus.NOT_FOUND);
	}

}
