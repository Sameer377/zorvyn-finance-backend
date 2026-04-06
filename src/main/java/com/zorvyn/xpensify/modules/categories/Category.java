package com.zorvyn.xpensify.modules.categories;

/**
 * @author Sameer Shaikh
 * @date 05-04-2026
 * @description
 */

import com.zorvyn.xpensify.core.BaseEntity;
import com.zorvyn.xpensify.core.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "categories")
@SuperBuilder
public class Category extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @Column(name = "description")
    private String description;
}
