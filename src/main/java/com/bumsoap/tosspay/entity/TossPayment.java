package com.bumsoap.tosspay.entity;

import com.bumsoap.tosspay.util.TossPaymentMethod;
import com.bumsoap.tosspay.util.TossPaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TossPayment {
  @Id
  private byte[] paymentId;

  @Column(nullable = false, unique = true)
  private String tossPaymentKey;

  // 토스내부에서 관리하는 별도의 orderId가 존재함
  @Column(nullable = false)
  private String tossOrderId;

  @OneToOne
  @JoinColumn(name = "order_id", nullable = false)
  private BsOrder order;

  private long totalAmount;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private TossPaymentMethod tossPaymentMethod;

  @Enumerated(value = EnumType.STRING)
  @Column(nullable = false)
  private TossPaymentStatus tossPaymentStatus;

  @Column(nullable = false)
  private LocalDateTime requestedAt;

  private LocalDateTime approvedAt;

  // 기타 메서드 생략..
}
