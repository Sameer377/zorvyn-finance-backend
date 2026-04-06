package com.zorvyn.xpensify.modules.transaction;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

import com.zorvyn.xpensify.core.BaseEntity;
import com.zorvyn.xpensify.core.enums.TransactionType;
import com.zorvyn.xpensify.modules.user.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "transactions")
@SuperBuilder
public class Transactions extends BaseEntity {

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
