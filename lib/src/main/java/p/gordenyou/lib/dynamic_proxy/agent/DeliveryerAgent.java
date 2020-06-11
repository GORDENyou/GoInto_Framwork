package p.gordenyou.lib.dynamic_proxy.agent;

import p.gordenyou.lib.dynamic_proxy.bussiness.Delivery;
import p.gordenyou.lib.dynamic_proxy.staff.Deliveryer;

class DeliveryerAgent implements Delivery {
    private Deliveryer deliveryer;

    public DeliveryerAgent(Deliveryer deliveryer) {
        this.deliveryer = deliveryer;
    }

    @Override
    public void delivery() {
        System.out.println("我是配送主管，为您分配配送员");
        deliveryer.delivery();
        System.out.println("配送完成，祝您生活愉快！");
    }
}
