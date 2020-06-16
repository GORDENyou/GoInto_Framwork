package p.gordenyou.lib.srp.test1;

/**
 * 这里是用来讨论SRP(Single Responsibility Principle)单一职责原则
 *
 * 这里是我们最常使用的模式。
 */
class UserInfo implements IUserInfo {
    @Override
    public void setUserName() {

    }

    @Override
    public void setPassword() {

    }

    @Override
    public void changePassword() {

    }

    @Override
    public void deleteUser() {

    }

    public static void main(String[] args) {

        /*
        这里 IUserInfo 继承了两个接口，我们后面可以强转为它们。

        todo 实际上这样子会破坏 SRP 原则，用户属性和用户的业务夹杂在了一起。属性变化或业务变化都会导致接口的变化。
         */
        UserInfo userInfo = new UserInfo();
        IUserBO iUserBO = userInfo;
        iUserBO.setPassword();

        IUserBiz iUserBiz = userInfo;
        iUserBiz.changePassword();
    }
}
