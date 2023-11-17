package com.alacriti.paymentGateway.payment.gateway.api.merchant.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.alacriti.paymentGateway.payment.gateway.api.common.Currency;
import com.alacriti.paymentGateway.payment.gateway.api.common.Status;
import com.alacriti.paymentGateway.payment.gateway.api.merchant.entity.PaymentEntity;
import com.alacriti.paymentGateway.payment.gateway.api.merchant.model.Payment;
import com.alacriti.paymentGateway.payment.gateway.api.merchant.model.PaymentResponse;
import com.alacriti.paymentGateway.payment.gateway.api.merchant.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	public PaymentResponse requestPaymentStatus(Payment requestDetails) {

		PaymentEntity entity = new PaymentEntity();
		String message = "SUCESSFUL.....";
		

		try {
			validatecurrency(requestDetails);
			saveRequestData(requestDetails, Status.SUCESS.status, entity);

		} catch (Exception e) {
			saveRequestData(requestDetails, Status.FAIL.status, entity);
			message = e.getMessage();
		}
		
		

		PaymentResponse response = sendPaymentStatus(entity);

		return response;

	}

	public void validatecurrency(Payment payment) throws Exception {
		
		if (!payment.getCurrency().equals(Currency.USA.currency)) {
			
			throw new Exception("invalid currency");
		}
		
		if(payment.getMerchantId().equals(null)) {
			
			throw new Exception("No input, empty merchant id");
		}
		
		if(payment.getOrderId().equals(null)) {
			
			throw new Exception("No input, empty order id");
		}
		
		if(payment.getAmount() == 0) {
			
			throw new Exception("no input, enter amount");
		}
	}
	

	public PaymentResponse sendPaymentStatus(PaymentEntity entity) {

		PaymentResponse response = new PaymentResponse();

		response.setMerchantId(entity.getMerchantId());
		response.setOrderId(entity.getOrderId());
		response.setTransactionId(entity.getTransaction_id());
		response.setStatus(entity.getStatus());

		return response;

	}

	public void saveRequestData(Payment payment, String status, PaymentEntity paymentEntity) {

		paymentEntity.setMerchantId(payment.getMerchantId());
		paymentEntity.setAmount(payment.getAmount());
		paymentEntity.setCurrency(payment.getCurrency());
		paymentEntity.setOrderId(payment.getOrderId());
		paymentEntity.setTransaction_id("T-ID" + Math.random());
		paymentEntity.setStatus(status);

		paymentRepository.save(paymentEntity);

	}

	public List<PaymentResponse> returnTransactionStatus(String transactionID) {
		PaymentResponse response = new PaymentResponse();

		List<PaymentResponse> listOfResponse = new ArrayList<>();
		String sql = "select * from payment_info where transaction_id ='" + transactionID + "'";
		listOfResponse = jdbcTemplate.query(sql, new PaymentResponseMapper());
		return listOfResponse;

	}

	private class PaymentResponseMapper implements RowMapper<PaymentResponse> {

		@Override
		public PaymentResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
			PaymentResponse response = new PaymentResponse();
			String id = rs.getString("transaction_id");
			String status = rs.getString("status");
			String mId = rs.getString("merchant_id");
			String Oid = rs.getString("order_id");

			response.setTransactionId(id);
			response.setStatus(status);
			response.setMerchantId(mId);
			response.setOrderId(Oid);

			return response;

		}

	}

}