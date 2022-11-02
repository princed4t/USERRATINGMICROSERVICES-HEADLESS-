package com.jlcindiabookstore;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jlcindiarabbitmq.BookRatingInfo;
import com.jlcindiarabbitmq.UserRatingInfo;

@Transactional
@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	BookRatingDao bookRatingDAO;
	@Autowired
	UserRatingDao userRatingDAO;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@RabbitListener(queues = "myuser.ratings.queue")
	public void addUserRating(UserRatingInfo urInfo) {

		UserRatingd userRating = new UserRatingd(urInfo.getBookId(), urInfo.getUserId(), urInfo.getRating(),
				urInfo.getReview());
//1.Add the User Rating
		userRatingDAO.save(userRating); // BookID

//2.Calculate the Avg rating for BookId
		int bookId = userRating.getBookId(); // 101
		List<UserRatingd> ratingList = this.getUserRatingByBookId(bookId);
		double sumRating = 0.0;
		for (UserRatingd ur : ratingList) {
			sumRating = sumRating + ur.getRating();
		}
		double avgRating = sumRating / ratingList.size();
//3.Update BookRating in UserRatingMS (Local)
		BookRating bookRating = bookRatingDAO.findById(bookId).get();
		bookRating.setAvgRating(avgRating);
		bookRatingDAO.save(bookRating);

//4.Update BookRating in BookSearchMS By Sending Message to RabbitMQ
		BookRatingInfo bookRatingInfo = new BookRatingInfo();
		bookRatingInfo.setBookId(bookRating.getBookId());
		bookRatingInfo.setAvgRating(bookRating.getAvgRating());
		bookRatingInfo.setNumberOfSearches(bookRating.getNumberOfSearches());
		rabbitTemplate.convertAndSend("mybook.search.exchange", "mybook.ratings.key", bookRatingInfo);
	}

	@Override
	public List<UserRatingd> getUserRatingByBookId(Integer bookId) {
		return userRatingDAO.findUserRatingByBookId(bookId);
	}

	@Override
	public List<UserRatingd> getUserRatingByUserId(String userId) {
		return userRatingDAO.findUserRatingByUserId(userId);
	}

	@Override
	public BookRating getBookRatingByBookId(Integer bookId) {
		BookRating bookRating = bookRatingDAO.findBookRatingByBookId(bookId);
		return bookRating;
	}
}