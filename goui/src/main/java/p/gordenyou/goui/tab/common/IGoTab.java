package p.gordenyou.goui.tab.common;

import androidx.annotation.NonNull;
import androidx.annotation.Px;

/**
 * 单个 Tab 的接口，继承了 layout 中的监听接口
 * @param <D> 数据类型
 */
public interface IGoTab<D> extends IGoTabLayout.OnTabSelectedListener<D> {
    /**
     * 设置 Tab 的数据
     * @param data 泛型数据
     */
    void setHiTabInfo(@NonNull D data);

    /**
     * 动态修改某个item的大小
     * @param height 高度，用了标注规范单位（px）
     */
    void resetHeight(@Px int height);
}
