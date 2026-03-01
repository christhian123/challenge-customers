package io.github.tig666.challenge_customers_api.repository;

import io.github.tig666.challenge_customers_api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
