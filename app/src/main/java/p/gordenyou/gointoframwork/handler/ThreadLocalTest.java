package p.gordenyou.gointoframwork.handler;

public class ThreadLocalTest {
    private static final ThreadLocal<String> sThreadLocal = new ThreadLocal<String>();
    public static void main(String[] args){
        sThreadLocal.set(" main ");
        System.out.println(Thread.currentThread().getName() +" ---->"+sThreadLocal.get());

        for (int i = 0; i < 2; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() +" ---->"+sThreadLocal.get());
                    sThreadLocal.set(" runnable "+ finalI);
                    System.out.println(Thread.currentThread().getName() +" ---->"+sThreadLocal.get());

                }
            }).start();
        }
    }
}
