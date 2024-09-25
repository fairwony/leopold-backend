package com.team2.leopold.dto;

public class ResponseFaqDto {
	private Integer uid;
	private String question;
	private String answer;
	private String imageUrl;
	private String faqCategoryName;

	public ResponseFaqDto(Integer uid, String question, String answer, String imageUrl, String faqCategoryName) {
		this.uid = uid;
		this.question = question;
		this.answer = answer;
		this.imageUrl = imageUrl;
		this.faqCategoryName = faqCategoryName;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFaqCategoryName() {
		return faqCategoryName;
	}

	public void setFaqCategoryName(String faqCategoryName) {
		this.faqCategoryName = faqCategoryName;
	}
}