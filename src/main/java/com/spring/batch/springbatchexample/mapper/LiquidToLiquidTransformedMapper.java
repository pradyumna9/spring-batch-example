package com.spring.batch.springbatchexample.mapper;

import com.spring.batch.springbatchexample.model.Liquid;
import com.spring.batch.springbatchexample.model.TransformedLiquid;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LiquidToLiquidTransformedMapper {
    TransformedLiquid liquidToTransformedLiquid(Liquid liquid);
}
