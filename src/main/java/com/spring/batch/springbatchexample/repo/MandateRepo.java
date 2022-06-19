package com.spring.batch.springbatchexample.repo;

import com.spring.batch.springbatchexample.model.TransformedMandate;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MandateRepo extends JpaRepository<TransformedMandate,Integer> {
}
