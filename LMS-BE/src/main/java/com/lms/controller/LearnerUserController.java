package com.lms.controller;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.entity.LearnerUser;
import com.lms.exception.DbException;

@RestController
@RequestMapping("/user")
public class LearnerUserController {

	@Autowired
	private DataSource ds;

	@PostMapping("/save")
	public LearnerUser save(@RequestBody LearnerUser lu) {

		return lu;

	}

	@GetMapping("/testdb")
	public ResponseEntity<String> testdb() throws SQLException {

		try (Connection c = ds.getConnection()) {
			throw new DbException("Db Not Connected");
		} catch (Exception e) {
			return ResponseEntity.ok("Db Connected");

		}

	}

	@GetMapping("/login")
	public String login() {
		return "Connected To Back-End";
		
	}

}
