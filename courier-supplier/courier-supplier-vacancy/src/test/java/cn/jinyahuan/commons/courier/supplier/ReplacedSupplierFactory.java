package cn.jinyahuan.commons.courier.supplier;

import cn.jinyahuan.commons.courier.supplier.vacancy.DefaultVacancySupplierFactory;
import cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierFactory;
import cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierSupplier;

import java.util.function.Predicate;

/**
 * @author Yahuan Jin
 */
public class ReplacedSupplierFactory implements CourierSupplierFactory {
    @Override
    public CourierSupplier getSupplier() {
        return new VacancyCourierSupplier(VacancyCourierFactory.IMMUTABLE_THROWABLE_COURIER);
    }

    @Override
    public Predicate<CourierSupplierFactory> getExcludedPredicate() {
        return p -> p instanceof DefaultVacancySupplierFactory;
    }
}
