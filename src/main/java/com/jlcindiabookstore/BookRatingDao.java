package com.jlcindiabookstore;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRatingDao extends JpaRepository<BookRating,Integer>{
	public BookRating findBookRatingByBookId(Integer bookId);
	

}
