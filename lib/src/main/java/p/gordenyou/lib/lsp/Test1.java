package p.gordenyou.lib.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Test1 {
    static class Father{
        public Collection doSomething(HashMap hashMap){
            System.out.println("父类被执行");
            return hashMap.values();
        }
    }

    static class Son extends Father{
        public Collection doSomething(Map map){
            System.out.println("子类被执行");
            return map.values();
        }
    }

    public static void main(String[] args) {
        /*
        运行结果：
        父类被执行
        父类被执行

        原因分析：Son#doSomething 重写（Overload）了方法，并没有覆盖(Override) Father 的方法。
         */
        test1();
    }

    private static void test1() {
        Father father = new Father();
        HashMap hashMap = new HashMap();
        father.doSomething(hashMap);

        Son son = new Son();
        son.doSomething(hashMap);
    }
}
