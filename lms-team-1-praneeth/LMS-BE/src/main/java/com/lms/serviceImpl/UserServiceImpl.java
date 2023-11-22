package com.lms.serviceImpl;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lms.dto.UserVerifyDto;
import com.lms.entity.User;
import com.lms.exception.details.CustomException;
import com.lms.exception.details.EmailNotFoundException;
import com.lms.repository.OtpRepo;
import com.lms.repository.UserRepo;
import com.lms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo lur;

	@Autowired
	private PasswordEncoder pe;

	@Autowired
	private OtpRepo or;

	@Override
	public User saveLU(User lu) {

		User lu1 = new User();

		lu1.setName(lu.getName());
		lu1.setEmail(lu.getEmail());
		lu1.setPassword(pe.encode(lu.getPassword()));
		lu1.setRoles(lu.getRoles());

		if (getByemail(lu1)) {
			return null;
		} else {
			return lur.save(lu1);
		}
	}

	@Override
	public Boolean getByemail(User lu) {
		boolean findByName = lur.existsByemail(lu.getEmail());
		return findByName;
	}

	@Override
	public Optional<User> fingbyemail(String email) {

		Optional<User> findByemail;
		try {
			findByemail = lur.findByemail(email);
			return findByemail;
		} catch (Exception e) {
			throw new EmailNotFoundException("Email Not Found");
		}
	}

	@Override
	public List<User> getLU(long id) {
		return null;
	}

	@Override
	public User updateLU(User lu) {
		return null;
	}

	@Override
	public void deleteLU(long id) {
		return;
	}

	@Override
	public ResponseEntity<?> getby(User lu) {

		if (lur.findByemail(lu.getEmail()).isEmpty()) {
			throw new EmailNotFoundException("Email Not Found");
		} else {
			return new ResponseEntity<Object>(lur.findByemail(lu.getEmail()).get(), HttpStatus.OK);
		}
	}

	@Override
	public String saveImg(MultipartFile mp, String email) throws Exception {

		User op = lur.findByemail(email).orElseThrow(() -> new EmailNotFoundException("Email Not Found"));
		try {
			op.setImg(mp.getBytes());
			lur.save(op);
			return "Image File Uploaded Successfully :" + mp.getOriginalFilename().toLowerCase();
		} catch (IOException e) {
			throw new CustomException("Incorrect Image File");
		}

	}

	@Override
	public byte[] downloadImage(String email) throws IOException, DataFormatException {

		User img = lur.findByemail(email).orElseThrow(() -> new EmailNotFoundException("Email Not Found"));

		byte[] img2 = img.getImg();

		return img2;
	}

	@Override
	public User Luupdate(User lu) {

		User lu1;
		if (lu.getEmail() == null && lu.getImg() == null && lu.getName() == null && lu.getPassword() == null) {
			throw new CustomException("Empty Details Failed To Update:");

		} else {
			lu1 = lur.findByemail(lu.getEmail()).orElseThrow(() -> new EmailNotFoundException("Email Not Found"));
		}

		if (lu.getEmail() != null && !lu.getEmail().isEmpty()) {
			lu1.setEmail(lu.getEmail());
		}
		if (lu.getPassword() != null && !lu.getPassword().isEmpty()) {
			lu1.setPassword(pe.encode(lu.getPassword()));
		}
		if (lu.getName() != null && !lu.getName().isEmpty()) {
			lu1.setName(lu.getName());
		}
		if (lu.getImg() != null && lu.getImg().length != 0) {
			lu1.setImg(lu.getImg());
		}

		return lur.save(lu1);

	}

	@Override
	public boolean saveotp(UserVerifyDto uvt) {

		if (!or.save(uvt).equals(null)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean verifyAccount(String email, String otp) {

		Optional<UserVerifyDto> findByemail;
		try {
			findByemail = or.findByemail(email);

			if (findByemail.get().getOtp().equals(otp) && Duration
					.between(findByemail.get().getOtpGeneratedTime(), LocalDateTime.now()).getSeconds() < (1 * 60)) {

				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			throw new EmailNotFoundException("Email Not Found");
		}
	}

	@Override
	public boolean resetPassword(String password, String verifypassword, long id) {

		User findById = lur.findById(id).orElseThrow(() -> new CustomException("Invalid Id"));
		if (password.equals(verifypassword)) {
			findById.setPassword(pe.encode(verifypassword));
			return true;
		}
		return false;
	}

}
