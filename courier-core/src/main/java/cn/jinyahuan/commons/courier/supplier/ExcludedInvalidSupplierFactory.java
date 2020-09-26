package cn.jinyahuan.commons.courier.supplier;

import java.util.Objects;
import java.util.function.Predicate;

/**
 * 移除无效的信使服务商工厂。
 * <p>无效的定义为：不会生产信使服务商。
 *
 * @author Yahuan Jin
 */
public class ExcludedInvalidSupplierFactory implements CourierSupplierFactory {
    /**
     * @return 一个 Predicate 实例，该实例用于检测信使服务商工厂是否为无效的信使服务商工厂
     */
    @Override
    public Predicate<CourierSupplierFactory> getExcludedPredicate() {
        return p -> Objects.isNull(p.getSupplier());
    }
}
