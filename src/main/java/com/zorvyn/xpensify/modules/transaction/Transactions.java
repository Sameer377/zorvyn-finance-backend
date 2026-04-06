package com.zorvyn.xpensify.modules.transaction;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

import com.zorvyn.xpensify.core.BaseEntity;
import com.zorvyn.xpensify.core.enums.TransactionStatus;
import com.zorvyn.xpensify.core.enums.TransactionType;
import com.zorvyn.xpensify.modules.categories.Category;
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

    @Column(name = "from_account_number")
    private String fromAccountNumber;

    @Column(name = "to_account_number")
    private String toAccountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "record_date", nullable = false)
    private LocalDate recordDate;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
