package cn.jinyahuan.commons.courier.supplier;

import cn.jinyahuan.commons.courier.supplier.vacancy.DefaultVacancySupplierFactory;

import java.util.function.Predicate;

/**
 * @author Yahuan Jin
 */
public class ExcludedSupplierFactory implements CourierSupplierFactory {
    @Override
    public Predicate<CourierSupplierFactory> getExcludedPredicate() {
        return p -> p instanceof DefaultVacancySupplierFactory;
    }
}
