package p.gordenyou.gointoframwork.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 测量
     * @param widthMeasureSpec 来自父亲的参考测量数据
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        // 父布局的宽高
        int selfWidth = MeasureSpec.getSize(widthMeasureSpec);
        int selfHeight = MeasureSpec.getSize(heightMeasureSpec);


        /*
        todo 我们需要学习一种自顶向下的编码方式！
         */

        // 1. 先度量孩子，再度量自己
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);

            /*
            度量孩子其实就是解析 XML 文件中的属性，将其转化为屏幕识别 dp, px 等属性。
             */
            // 获取孩子的 Layout 属性。
            LayoutParams childParams = childView.getLayoutParams();

            // 将 LayoutParams 转变为 measureSpec todo 面试题： LayoutParams 和 MeasureSpec 有什么联系？
            int childWidthSpec = getChildMeasureSpec(widthMeasureSpec, paddingLeft + paddingRight, childParams.width);
            int childHeightSpec = getChildMeasureSpec(heightMeasureSpec, paddingTop + paddingBottom, childParams.height);
            // 从上面代码我们可以看出，我们需要通过父亲 View 的宽高、模式，以及子 View 自己的 Padding 、
            // 模式来同时决定。 todo 其实这里的 childParams.width 指的就是子 View 的模式（match_parent, wrap_parent, exactly）

            childView.measure(widthMeasureSpec, heightMeasureSpec);
        }

        // 2. 再度量自己
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
