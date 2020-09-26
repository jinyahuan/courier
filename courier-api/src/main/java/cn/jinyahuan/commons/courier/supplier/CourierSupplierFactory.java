package cn.jinyahuan.commons.courier.supplier;

import java.util.function.Predicate;

/**
 * 创建信使服务商的工厂类。
 *
 * @author Yahuan Jin
 */
public interface CourierSupplierFactory {
    /**
     * 获取信使服务商。
     *
     * @return default value is {@code null}
     */
    default CourierSupplier getSupplier() {
        return null;
    }

    /**
     * 获取需要移除的工厂的 Predicate。
     *
     * <pre>
     * 可能由于某些原因需要移除已经加载的工厂，比如：
     *     1. 将已经发布版本甚至已经集成到项目中的固定CourierSupplierFactory替换为新的CourierSupplierFactory
     *     2. 启动时更换信使服务商，这时就需要把老的CourierSupplierFactory移除掉
     * </pre>
     *
     * @return default value is {@code null}
     */
    default Predicate<CourierSupplierFactory> getExcludedPredicate() {
        return null;
    }
}
