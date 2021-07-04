package com.heycar.heycardealerslisting.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "DEALER")
public class Dealer {
    @Id
    private Long id;

    @OneToMany(mappedBy = "dealer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Listing> listings;
}
