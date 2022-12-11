package com.spring.batch.springbatchexample.repo;

import com.spring.batch.springbatchexample.model.TransformedLiquid;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LiquidRepo extends JpaRepository<TransformedLiquid,Integer> {
}
