package com.jlcindiabookstore;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRatingDao extends JpaRepository<UserRatingd,Integer>{
	public List<UserRatingd> findUserRatingByBookId(Integer bookId);
	public List<UserRatingd> findUserRatingByUserId(String userId);

}
