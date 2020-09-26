package cn.jinyahuan.commons.courier.supplier;

import cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierFactory;
import cn.jinyahuan.commons.courier.supplier.vacancy.VacancyCourierSupplier;

/**
 * @author Yahuan Jin
 */
public class ManualSupplierFactory implements CourierSupplierFactory {
    @Override
    public CourierSupplier getSupplier() {
        return new VacancyCourierSupplier(VacancyCourierFactory.IMMUTABLE_SUCCESSFUL_STATE_COURIER);
    }
}
