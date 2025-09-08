package com.bumsoap.tosspay.controller;

import com.bumsoap.tosspay.request.SaveAmountRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
  /**
   * 결제의 금액을 세션에 임시저장
   * 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도
   */
  @PostMapping("/saveAmount")
  public ResponseEntity<?> temporarilySaveAmount(
      HttpSession session,
      @RequestBody SaveAmountRequest saveAmountRequest) {

    session.setAttribute(saveAmountRequest.getOrderId(),
        saveAmountRequest.getAmount());
    return ResponseEntity.ok("orderId 를 키에 amount 금액 저장!");
  }
}
