package com.magentamause.drinkmanagerbackend.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unguessable identifier handed to the guest so they can poll their own order. */
    @Column(nullable = false, unique = true, updatable = false)
    private String publicId;

    /** Human-friendly sequential number shown to guests and admins. */
    @Column(nullable = false, updatable = false)
    private Long queueNumber;

    @Column(nullable = false)
    private String guestName;

    /** Relative position on the garden map in the range [0, 1]. */
    @Column(nullable = false)
    private double locationX;

    @Column(nullable = false)
    private double locationY;

    @Column(length = 1000)
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status = OrderStatus.OPEN;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    private Instant doneAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();

    public Order(String publicId, Long queueNumber, String guestName, double locationX, double locationY, String note) {
        this.publicId = publicId;
        this.queueNumber = queueNumber;
        this.guestName = guestName;
        this.locationX = locationX;
        this.locationY = locationY;
        this.note = note;
        this.status = OrderStatus.OPEN;
        this.createdAt = Instant.now();
    }

    public void addItem(OrderItem item) {
        item.setOrder(this);
        this.items.add(item);
    }

    public void markDone() {
        this.status = OrderStatus.DONE;
        this.doneAt = Instant.now();
    }

    public void reopen() {
        this.status = OrderStatus.OPEN;
        this.doneAt = null;
    }
}
