package com.spring.batch.springbatchexample.mapper;

import com.spring.batch.springbatchexample.model.Mandate;
import com.spring.batch.springbatchexample.model.TransformedMandate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MandateToMandateTransformedMapper {
    TransformedMandate mandateToTransformedMandate(Mandate mandate);
}
