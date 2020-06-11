package p.gordenyou.lib.dynamic_proxy.staff;

import p.gordenyou.lib.dynamic_proxy.bussiness.Sell;

public class Seller implements Sell {
    @Override
    public void sell() {
        System.out.println("我是一名售货员，负责售货");
    }
}
