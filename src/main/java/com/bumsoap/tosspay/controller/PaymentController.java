package com.bumsoap.tosspay.controller;

import com.bumsoap.tosspay.request.ApiResponse;
import com.bumsoap.tosspay.request.SaveAmountRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
  /**
   * 결제 금액을 검증
   */
  @PostMapping("/verifyAmount")
  public ResponseEntity<ApiResponse> verifyAmount(
      HttpSession session,
      @RequestBody SaveAmountRequest saveAmountRequest) {

    String amount = (String) session.getAttribute(
        saveAmountRequest.getOrderId());
    // 결제 전의 금액과 결제 후의 금액이 같은지 검증
    if (amount == null ||
        !amount.equals(saveAmountRequest.getAmount().toString()))
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          new ApiResponse("결제 금액이 불일치합니다.", null));

    // 검증에 사용했던 세션은 삭제
    session.removeAttribute(saveAmountRequest.getOrderId());

    return ResponseEntity.ok(new ApiResponse("지불 금액이 일치합니다.", null));
  }

  /**
   * 결제의 금액을 세션에 임시저장
   * 결제 과정에서 악의적으로 결제 금액이 바뀌는 것을 확인하는 용도
   */
  @PostMapping("/saveAmount")
  public ResponseEntity<?> temporarilySaveAmount(
      HttpSession session,
      @RequestBody SaveAmountRequest saveAmountRequest) {

    session.setAttribute(saveAmountRequest.getOrderId(),
        saveAmountRequest.getAmount().toString());
    return ResponseEntity.ok("orderId 를 키에 amount 금액 저장!");
  }
}
