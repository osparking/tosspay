package com.bumsoap.tosspay.entity;

import com.bumsoap.tosspay.util.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BsOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false)
  private BigDecimal amount;

  @Column(name = "order_time", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  private LocalDateTime orderTime = LocalDateTime.now(); // 주문일시

  @Column(nullable = false)
  private OrderStatus orderStatus;

  @OneToOne(mappedBy = "order")
  private TossPayment payment;
}