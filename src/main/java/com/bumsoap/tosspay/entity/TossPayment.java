package com.bumsoap.tosspay.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayment {
  @Id
  byte[] paymentId;

  @Column(nullable = false, unique = true)
  String tossPaymentKey;

  // 토스내부에서 관리하는 별도의 orderId가 존재함
  @Column(nullable = false)
  String tossOrderId;

  @OneToOne
  @JoinColumn(name = "order_id", nullable = false)
  BsOrder order;
}
