package com.magentamause.drinkmanagerbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /** Reference to the drink at order time; nullable so a drink can later be deleted. */
    private Long drinkId;

    /** Snapshot of the drink name so the order stays readable even if the drink changes. */
    @Column(nullable = false)
    private String drinkName;

    @Column(nullable = false)
    private int quantity;

    /** True when the guest already has a glass and just needs a refill (no new glass needed). */
    @Column(name = "has_glass", nullable = false, columnDefinition = "boolean default false")
    private boolean hasGlass;

    public OrderItem(Long drinkId, String drinkName, int quantity, boolean hasGlass) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.hasGlass = hasGlass;
    }
}
