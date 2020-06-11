package p.gordenyou.lib.dynamic_proxy.staff;

import p.gordenyou.lib.dynamic_proxy.bussiness.Delivery;

public class Deliveryer implements Delivery {
    @Override
    public void delivery() {
        System.out.println("我是一名送货员，负责送货");
    }
}
