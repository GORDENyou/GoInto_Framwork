package p.gordenyou.goui.tab.common;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 1. 首先这是一个接口，定义了实现方法，约束了相同类型控件的方法。
 *
 */

// Tab 为我们自定义的 Tab 类型， D为我们的数据类型
public interface IGoTabLayout<Tab extends ViewGroup, D> {
    // 通过自定义的数据定位 Tab
    Tab findTab(@NotNull D data);

    // 自定义选中切换监听事件
    void addTabSelectedChangeListener(OnTabSelectedListener<D> listener);

    // 设置默认选中，这里是通过自定义数据来定位？
    void defaultSelected(@NonNull D defaultInfo);

    // 数据列表
    void inflateInfo(@NonNull List<D> infoList);

    // 内部接口  todo prevIfo 和 nextInfo 是用来干嘛的？
    interface OnTabSelectedListener<D> {
        void onTabSelectedChange(int index, @Nullable D prevInfo, @NonNull D nextInfo);
    }
}
