package com.jlcindiabookstore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import io.swagger.annotations.ApiModel;

@Entity
@Table(name = "myuserratings", schema = "jlcratingsdb")
@ApiModel("UserRating contains details of Rating given by User")
public class UserRatingd {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
	@SequenceGenerator(name = "idgen", sequenceName = "ratingis", initialValue = 6, allocationSize = 1)
	@Column(name = "rating_id")
	private Integer ratingId;
	@Column(name = "book_id")
	private Integer bookId;
	@Column(name = "user_id")
	private String userId;
	@Column(name = "rating")
	private double rating;
	@Column(name = "review")
	private String review;

	public UserRatingd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRatingd(Integer bookId, String userId, double rating, String review) {
		super();
		this.bookId = bookId;
		this.userId = userId;
		this.rating = rating;
		this.review = review;
	}

	public UserRatingd(Integer ratingId, Integer bookId, String userId, double rating, String review) {
		super();
		this.ratingId = ratingId;
		this.bookId = bookId;
		this.userId = userId;
		this.rating = rating;
		this.review = review;
	}

	public Integer getRatingId() {
		return ratingId;
	}

	public void setRatingId(Integer ratingId) {
		this.ratingId = ratingId;
	}

	public Integer getBookId() {
		return bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	@Override
	public String toString() {
		return "UserRatingd [ratingId=" + ratingId + ", bookId=" + bookId + ", userId=" + userId + ", rating=" + rating
				+ ", review=" + review + "]";
	}

}
