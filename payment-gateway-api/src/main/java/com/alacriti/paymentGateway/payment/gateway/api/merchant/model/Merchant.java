package com.alacriti.paymentGateway.payment.gateway.api.merchant.model;

public class Merchant {

	private String merchantId;
	private String merchantName;
	private String merchantEmail;
	private String merchantBusinesstype;
	private String merchantAddress;
	private long merchantPhone;

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getMerchantBusinesstype() {
		return merchantBusinesstype;
	}

	public void setMerchantBusinesstype(String merchantBusinesstype) {
		this.merchantBusinesstype = merchantBusinesstype;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public long getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(long merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

}
