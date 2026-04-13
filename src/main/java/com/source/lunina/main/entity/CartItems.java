package com.source.lunina.main.entity;

import com.source.lunina.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

@Entity
@Data
@NoArgsConstructor
@Table(name = "CartItems")
@EqualsAndHashCode(callSuper = true)
public class CartItems extends BaseEntity {
    @ManyToOne
    private User user;

    @ManyToOne
    private Products product;

    @ManyToOne
    @JoinColumn(name = "variant_id")
    private ProductVariants variant;

    private Integer quantity;

}
