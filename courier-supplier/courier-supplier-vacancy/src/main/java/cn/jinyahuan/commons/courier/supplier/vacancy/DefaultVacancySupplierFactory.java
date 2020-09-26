package cn.jinyahuan.commons.courier.supplier.vacancy;

import cn.jinyahuan.commons.courier.supplier.CourierSupplier;
import cn.jinyahuan.commons.courier.supplier.CourierSupplierFactory;

/**
 * 默认的空缺信使服务商工厂。
 *
 * @author Yahuan Jin
 */
public class DefaultVacancySupplierFactory implements CourierSupplierFactory {
    /**
     * @return 一个空缺信使服务商实例，该实例内部构造一个服务不可用的信使实例，
     * 该信使实例所有的方法都会返回“服务不可用状态”的响应对象
     */
    @Override
    public CourierSupplier getSupplier() {
        return new VacancyCourierSupplier();
    }
}
