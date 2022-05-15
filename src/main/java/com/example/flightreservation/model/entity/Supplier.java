package com.example.flightreservation.model.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@ToString
@NoArgsConstructor
@Table(name = "tb_suppliers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier extends BaseEntity {

    @Column(name = "supplier_name", unique = true, nullable = false)
    String supplierName;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    List<Plane> planeList;

}
