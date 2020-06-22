package p.gordenyou.goui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.devio.hi.library.util.HiDisplayUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import p.gordenyou.goui.R;
import p.gordenyou.goui.tab.common.IGoTabLayout;

/**
 * Created by GORDENyou on 2020/6/20.
 * mailbox:1193688859@qq.com
 * have nothing but……
 */

public class GoTabBottomLayout extends FrameLayout implements IGoTabLayout<GoTabBottom, GoTabBottomInfo<?>> {

    private static final String TAG_TAB_BOTTOM = "TAG_TAB_BOTTOM";
    // 保存监听器的列表
    private List<OnTabSelectedListener<GoTabBottomInfo<?>>> tabSelectedChangeListeners = new ArrayList<>();
    // 当前被选中 Tab 的数据
    private GoTabBottomInfo<?> selectedInfo;
    // 底部导航栏的透明度
    private float bottomAlpha = 1f;
    //TabBottom高度
    private static float tabBottomHeight = 50;
    //TabBottom的头部线条高度
    private float bottomLineHeight = 0.5f;
    //TabBottom的头部线条颜色
    private String bottomLineColor = "#dfe0e1";
    // Tab 信息列表
    private List<GoTabBottomInfo<?>> infoList;

    public GoTabBottomLayout(@NonNull Context context) {
        this(context, null);
    }

    public GoTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoTabBottomLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 寻找选中的Tab
     * @param info
     * @return
     */
    @Override
    public GoTabBottom findTab(@NotNull GoTabBottomInfo<?> info) {
        ViewGroup viewGroup = findViewWithTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if(child instanceof GoTabBottom){
                GoTabBottom tab = (GoTabBottom) child;
                if(tab.getTabInfo() == info){
                    return tab;
                }
            }
        }
        return null;
    }

    @Override
    public void addTabSelectedChangeListener(OnTabSelectedListener<GoTabBottomInfo<?>> listener) {
        tabSelectedChangeListeners.add(listener);
    }

    @Override
    public void defaultSelected(@NonNull GoTabBottomInfo<?> defaultInfo) {
        onSelected(defaultInfo);
    }

    /**
     * 列表信息初始化
     * @param infoList 底部列表信息
     */
    @Override
    public void inflateInfo(@NonNull List<GoTabBottomInfo<?>> infoList) {
        if(infoList.isEmpty()){
            return;
        }
        this.infoList = infoList;

        //我们需要移除之前添加的View todo 注意这里的函数是 getChildCount()
        // 这里为什么 > 0 ? i = 0 代表的是中间的显示的列表。而我们要移除的是下面的 GoTabBottom
        for(int i = getChildCount() - 1; i > 0; i--){
            removeViewAt(i);
        }

        // 清除之前的监听器
        tabSelectedChangeListeners.clear();

        selectedInfo = null;

        setBackground();

        int height = HiDisplayUtil.dp2px(tabBottomHeight, getResources());
        FrameLayout frameLayout = new FrameLayout(getContext());
        // 计算每个 Tab 对应的宽度。
        int width = HiDisplayUtil.getDisplayWidthInPx(getContext()) / infoList.size();
        // 设置 FrameLayout 的 Tag 方便我们寻找 Tab 的时候，找回对应的 FrameLayout
        frameLayout.setTag(TAG_TAB_BOTTOM);
        for (int i = 0; i < infoList.size(); i++) {
            final GoTabBottomInfo<?> info = infoList.get(i);
            LayoutParams params = new LayoutParams(width, height);
            params.gravity = Gravity.BOTTOM;
            params.leftMargin = i * width;

            GoTabBottom tabBottom = new GoTabBottom(getContext());
            tabSelectedChangeListeners.add(tabBottom);
            tabBottom.setTabInfo(info);
            frameLayout.addView(tabBottom, params);
            tabBottom.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSelected(info);
                }
            });
        }
        LayoutParams flParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        flParams.gravity = Gravity.BOTTOM;
        addBottomLine();
        addView(frameLayout, flParams);
    }

    private void addBottomLine() {
        View bottomLine = new View(getContext());
        bottomLine.setBackgroundColor(Color.parseColor(bottomLineColor));

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HiDisplayUtil.dp2px(bottomLineHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        params.bottomMargin = HiDisplayUtil.dp2px(tabBottomHeight - bottomLineHeight, getResources());
        addView(bottomLine, params);
        bottomLine.setAlpha(bottomAlpha);
    }

    private void setBackground(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.go_bottom_layout_bg, null);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, HiDisplayUtil.dp2px(tabBottomHeight, getResources()));
        params.gravity = Gravity.BOTTOM;
        addView(view, params);
        // 设置底部透明度
        view.setAlpha(bottomAlpha);
    }

    private void onSelected(GoTabBottomInfo<?> nexInfo){
        for(OnTabSelectedListener<GoTabBottomInfo<?>> listener : tabSelectedChangeListeners){
            listener.onTabSelectedChange(infoList.indexOf(nexInfo), selectedInfo, nexInfo);
        }
    }

    public void setTabSelectedChangeListener(List<OnTabSelectedListener<GoTabBottomInfo<?>>> listeners) {
        this.tabSelectedChangeListeners = listeners;
    }

    public void setTabAlpha(float alpha) {
        this.bottomAlpha = alpha;
    }

    public static void setTabHeight(float tabHeight) {
        GoTabBottomLayout.tabBottomHeight = tabHeight;
    }

    public void setBottomLineHeight(float bottomLineHeight) {
        this.bottomLineHeight = bottomLineHeight;
    }

    public void setBottomLineColor(String bottomLineColor) {
        this.bottomLineColor = bottomLineColor;
    }
}
