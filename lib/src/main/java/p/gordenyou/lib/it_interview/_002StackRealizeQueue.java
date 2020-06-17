package p.gordenyou.lib.it_interview;

import java.util.Stack;

/**
 * 【题目】
 * 编写一个类，用两个栈实现队列，支持队列的基本操作（add、poll 轮询、peek）。
 *
 * 【分析】
 *  栈是先进后出，队列是先进先出。我们将数据入栈后，再将出栈数据在另一个栈入栈，即可还原原来数据顺序
 */
public class _002StackRealizeQueue {
    private Stack<Integer> stackIn;
    private Stack<Integer> stackOut;

    public _002StackRealizeQueue(){
        this.stackIn = new Stack<>();
        this.stackOut = new Stack<>();
    }

    public void add(int num){
        stackIn.push(num);
    }

    /**
     * 取出队首元素，并删除。
     */
    public int poll(){
        if(stackIn.isEmpty() && stackOut.isEmpty()) throw new RuntimeException("the queue is empty");
        if(!stackOut.isEmpty()){
            return stackOut.pop();
        }else{
            /*int length = stackIn.size();
            for (int i = 0; i < length; i++) {
                stackOut.push(stackIn.pop());
            }*/
            while(!stackIn.isEmpty()){
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.pop();
    }

    public int peek(){
        if(stackIn.isEmpty() && stackOut.isEmpty()) throw new RuntimeException("the queue is empty");
        if(!stackOut.isEmpty()){
            return stackOut.peek();
        }else{
            int length = stackIn.size();
            for (int i = 0; i < length; i++) {
                stackOut.push(stackIn.pop());
            }
        }
        return stackOut.peek();
    }

    @Override
    public String toString() {
        return "_002StackRealizeQueue{" +
                "stackIn=" + stackIn +
                ", stackOut=" + stackOut +
                '}';
    }

    public static void main(String[] args) {
        _002StackRealizeQueue queue = new _002StackRealizeQueue();
        queue.add(3);
        queue.add(1);
        queue.add(7);
        queue.add(4);
        queue.add(9);

        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.toString());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.toString());
        queue.add(12);
        queue.add(19);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.toString());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.toString());


        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.toString());

        queue.add(100);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.toString());

        /* Result:
        3
        3
        _002StackRealizeQueue{stackIn=[], stackOut=[9, 4, 7, 1]}
        1
        1
        _002StackRealizeQueue{stackIn=[], stackOut=[9, 4, 7]}
        7
        7
        _002StackRealizeQueue{stackIn=[12, 19], stackOut=[9, 4]}
        4
        4
        _002StackRealizeQueue{stackIn=[12, 19], stackOut=[9]}
        9
        9
        _002StackRealizeQueue{stackIn=[12, 19], stackOut=[]}
        12
        12
        _002StackRealizeQueue{stackIn=[], stackOut=[100, 19]}*/
    }
}
