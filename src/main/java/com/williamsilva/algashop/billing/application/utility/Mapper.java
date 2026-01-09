package com.williamsilva.algashop.billing.application.utility;

public interface Mapper {

    <T> T convert(Object source, Class<T> targetClass);
}
