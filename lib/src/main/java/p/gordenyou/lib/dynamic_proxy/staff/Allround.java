package p.gordenyou.lib.dynamic_proxy.staff;

import p.gordenyou.lib.dynamic_proxy.bussiness.Delivery;
import p.gordenyou.lib.dynamic_proxy.bussiness.Sell;

public class Allround implements Sell, Delivery {
    @Override
    public void delivery() {
        System.out.println("我是一名全能玩家，可以送货物");
    }

    @Override
    public void sell() {
        System.out.println("我是一名全能玩家，可以卖东西");
    }
}
