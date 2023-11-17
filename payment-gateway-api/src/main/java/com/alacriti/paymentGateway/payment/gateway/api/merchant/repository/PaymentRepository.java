package com.alacriti.paymentGateway.payment.gateway.api.merchant.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alacriti.paymentGateway.payment.gateway.api.merchant.entity.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, UUID>{

}
