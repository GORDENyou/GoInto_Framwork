package p.gordenyou.goui.tab.bottom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import p.gordenyou.goui.R;
import p.gordenyou.goui.tab.common.IGoTab;

public class GoTabBottom extends RelativeLayout implements IGoTab<GoTabBottomInfo<?>> {

    private GoTabBottomInfo<?> tabInfo;
    private ImageView tabImageView;
    private TextView tabIconView;
    private TextView tabNameView;

    public GoTabBottom(Context context) {
        this(context, null);
    }

    public GoTabBottom(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GoTabBottom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.go_tab_bottom, this);
        tabImageView = findViewById(R.id.iv_image);
        tabIconView = findViewById(R.id.tv_icon);
        tabNameView = findViewById(R.id.tv_name);
    }

    /**
     * 获取 Tab 设置的数据
     * @return tabInfo
     */
    public GoTabBottomInfo<?> getTabInfo() {
        return tabInfo;
    }

    public ImageView getTabImageView() {
        return tabImageView;
    }

    public TextView getTabIconView() {
        return tabIconView;
    }

    public TextView getTabNameView() {
        return tabNameView;
    }

    /**
     * 设置 Tab 对应数据
     * @param data 泛型数据
     */
    @Override
    public void setTabInfo(@NonNull GoTabBottomInfo<?> data) {
        this.tabInfo = data;
        inflateInfo(false, true);
    }

    /**
     * 初始化信息
     * @param selected 是否被选中
     * @param init 是否已经初始化
     */
    public void inflateInfo(boolean selected, boolean init){
        if(tabInfo.tabType == GoTabBottomInfo.TabType.ICON){
            if(init){
                // 如果已经初始化了
                tabImageView.setVisibility(GONE);
                tabIconView.setVisibility(VISIBLE);

                // 设置字体
                Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), tabInfo.iconFont);
                tabIconView.setTypeface(typeface);

                if(!TextUtils.isEmpty(tabInfo.name)){
                    tabNameView.setText(tabInfo.name);
                }
            }

            if(selected){
                tabIconView.setText(TextUtils.isEmpty(tabInfo.selectedIconName)? tabInfo.selectedIconName: tabInfo.defaultIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.tintColor));
                tabNameView.setTextColor(getTextColor(tabInfo.tintColor));
            }else{
                tabIconView.setText(tabInfo.defaultIconName);
                tabIconView.setTextColor(getTextColor(tabInfo.defaultColor));
                tabNameView.setTextColor(getTextColor(tabInfo.defaultColor));
            }
        } else if(tabInfo.tabType == GoTabBottomInfo.TabType.BITMAP){
            if(init){
                tabImageView.setVisibility(VISIBLE);
                tabIconView.setVisibility(GONE);
                if(!TextUtils.isEmpty(tabInfo.name)){
                    tabNameView.setText(tabInfo.name);
                }
            }

            if(selected){
                tabImageView.setImageBitmap(tabInfo.selectedBitmap);
            }else{
                tabImageView.setImageBitmap(tabInfo.defaultBitmap);
            }
        }
    }

    @Override
    public void resetHeight(int height) {
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.height = height;
        setLayoutParams(layoutParams);
        getTabNameView().setVisibility(GONE);
    }

    @Override
    public void onTabSelectedChange(int index, @Nullable GoTabBottomInfo<?> prevInfo, @NonNull GoTabBottomInfo<?> nextInfo) {
        if(prevInfo != tabInfo && nextInfo != tabInfo || prevInfo == nextInfo){
            return;
        }
        if(prevInfo == tabInfo){
            inflateInfo(false, false);
        }else{
            inflateInfo(true, false);
        }
    }

    @ColorInt
    private int getTextColor(Object color) {
        if (color instanceof String) {
            return Color.parseColor((String) color);
        } else {
            return (int) color;
        }
    }
}
