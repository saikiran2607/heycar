package com.heycar.heycardealerslisting.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heycar.heycardealerslisting.domain.entity.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
