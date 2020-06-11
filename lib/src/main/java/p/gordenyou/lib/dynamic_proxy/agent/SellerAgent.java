package p.gordenyou.lib.dynamic_proxy.agent;

import p.gordenyou.lib.dynamic_proxy.bussiness.Sell;

public class SellerAgent implements Sell {
    private Sell seller;

    public SellerAgent(Sell seller) {
        this.seller = seller;
    }

    @Override
    public void sell() {
        // 我们可以增加前置和后置服务
        System.out.println("我是销售主管，我现在帮您匹配销售员。");
        seller.sell();
        System.out.println("祝您购物愉快！");
    }
}
