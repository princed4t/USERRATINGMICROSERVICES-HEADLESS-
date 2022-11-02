package com.jlcindiabookstore;

import java.util.List;

public interface RatingService {
public List<UserRatingd> getUserRatingByBookId(Integer bookId);
public List<UserRatingd> getUserRatingByUserId(String userId);
public BookRating getBookRatingByBookId(Integer bookId);
}