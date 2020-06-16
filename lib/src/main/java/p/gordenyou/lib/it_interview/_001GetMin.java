package p.gordenyou.lib.it_interview;

import java.util.Arrays;
import java.util.Stack;

/**
 * 【题目】
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作。
 *
 * 【要求】
 * 1.pop、push、getMin操作的时间复杂度都是O (1)。
 * 2．设计的栈类型可以使用现成的栈结构。
 *
 * 分析：这里实现的难点是返回栈的最小元素，而且时间复杂度为 O(1)。 思路还是一样，空间换时间。
 *      但是还有一个问题：我们用什么空间来存储这个最小值？
 *      因为我们每次出栈一个元素，栈里面的最小值都会改变。分析到这里，如果没有思路，直接看代码吧。
 */
public class _001GetMin {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public _001GetMin() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    public int pop(){
        if(stackData.empty())
            throw new RuntimeException("Stack is empty!");
        if(stackMin.peek().equals(stackData.peek())){
            stackData.pop();
            return stackMin.pop();
        }
        return stackData.pop();
    }

    public void push(int data){
        stackData.push(data);
        if(stackMin.isEmpty()) stackMin.push(data);
        else{
            if(stackMin.peek() >= data) stackMin.push(data);
        }
    }

    public int getMin(){
        if(stackMin.empty())
            throw new RuntimeException("Stack is empty!");
        return stackMin.peek();
    }

    public static void main(String[] args) {
        _001GetMin test = new _001GetMin();
        test.push(5);
        test.push(2);
        test.push(1);
        test.push(3);
        test.push(2);
        test.push(5);

        System.out.println("Min:  " + test.getMin()); //1
        System.out.println(Arrays.toString(test.stackMin.toArray()));
        System.out.println("Pop:  " + test.pop()); //5
        System.out.println("Min:  " + test.getMin());
        System.out.println("Pop:  " + test.pop());
        System.out.println("Min:  " + test.getMin());
        System.out.println("Pop:  " + test.pop());
        System.out.println("Min:  " + test.getMin());
        System.out.println("Pop:  " + test.pop());
        System.out.println("Min:  " + test.getMin());
        System.out.println("Pop:  " + test.pop());
        System.out.println("Min:  " + test.getMin());
        System.out.println("Pop:  " + test.pop());
        System.out.println("Min:  " + test.getMin());
        System.out.println("Pop:  " + test.pop());
        System.out.println("Min:  " + test.getMin());
        System.out.println("Pop:  " + test.pop());
        System.out.println("Min:  " + test.getMin());
        System.out.println(Arrays.toString(test.stackMin.toArray()));
        System.out.println("Pop:  " + test.pop());

    }
}
