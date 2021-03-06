package pl.tu.kielce.pizza.be.order.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.tu.kielce.pizza.be.common.jpa.AuditableEntity;
import pl.tu.kielce.pizza.be.department.model.jpa.Department;
import pl.tu.kielce.pizza.be.security.model.jpa.User;
import pl.tu.kielce.pizza.common.common.enums.OrderStatus;
import pl.tu.kielce.pizza.common.common.enums.OrderType;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ORDER_TABLE")
@Entity
public class Order extends AuditableEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private User buyer;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<BoughtItem> boughtItems;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<BoughtPizza> boughtPizzas;

    @OneToOne//(mappedBy = "order", cascade = CascadeType.ALL)
    private Department department;

//    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private Double totalPrice;

    @Embedded
    private DeliveryInfo deliveryInfo;
//    private String note;
}
