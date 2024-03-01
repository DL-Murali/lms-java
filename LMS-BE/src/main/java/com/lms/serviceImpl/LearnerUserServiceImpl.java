package com.lms.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.entity.LearnerUser;
import com.lms.repository.LearnerUserRepo;
import com.lms.service.LearnerUserService;

@Service
public class LearnerUserServiceImpl implements LearnerUserService {

	@Autowired
	private LearnerUserRepo lur;

	@Override
	public LearnerUser saveLU(LearnerUser lu) {
		return lur.save(lu);
	}

	@Override
	public List<LearnerUser> getLU(long id) {
		return null;
	}

	@Override
	public LearnerUser updateLU(LearnerUser lu) {
		return null;
	}

	@Override
	public void deleteLU(long id) {

	}

}
