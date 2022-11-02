package com.jlcindiabookstore;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

@CrossOrigin // CORS
@RestController
public class RatingController {
	static Logger log = LoggerFactory.getLogger(RatingController.class);
	@Autowired
	RatingService ratingService;

	
	@GetMapping("/userRatings/{userId}")
	public List<UserRatingd> getUserRatingByUserId(@PathVariable String userId) {
		log.info("---RatingController---getUserRatingByUserId()-----");
		return ratingService.getUserRatingByUserId(userId);
	}

	@GetMapping("/bookRatings/{bookId}")
	public BookRating getBookRatingByBookId(@PathVariable String bookId) {
		log.info("---RatingController---getBookRatingByBookId()-----");
		return ratingService.getBookRatingByBookId(Integer.parseInt(bookId));
	}
}
