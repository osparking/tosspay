package com.bumsoap.tosspay.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmPaymentRequest {
  private String paymentKey;
  private String orderId;
  private BigDecimal amount;
}
