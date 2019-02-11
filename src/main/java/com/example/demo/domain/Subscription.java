package com.example.demo.domain;

public class Subscription {
	
	private String email;
	
	private SubscriptionType subscriptionType = SubscriptionType.ALL_EMAILS;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SubscriptionType getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(SubscriptionType subscriptionType) {
		this.subscriptionType = subscriptionType;
	}
	
	
}
