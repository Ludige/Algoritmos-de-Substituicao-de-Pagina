import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
    String name;
    Plate plate;
    boolean isInLeftHand;
    boolean isInRightHand;
    String textColor;
    ReentrantLock mutex = new ReentrantLock();

    public Philosopher(String name, String textColor, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.textColor = textColor;
        this.plate = new Plate(leftFork, rightFork);
        this.isInLeftHand = false;
        this.isInRightHand = false;
    }

    @Override
    public void run() {
        while (true) {
            takeForks();
            if (this.isInLeftHand && this.isInRightHand) {
                try {
                    System.out.println(textColor + this.name + " está comendo \u001B[0m");
                    Thread.sleep(2500);
                    returnForks();
                    System.out.println(textColor + this.name + " está pensando\u001B[0m");
                    Thread.sleep(5000);
                    System.out.println(textColor + this.name + " pensou demais e está faminto! \u001B[0m");
                } catch (InterruptedException error) {
                    System.out.println(error);
                }
            }
        }
    }

    public void takeForks() {
        try {
            mutex.lock();
            if (this.isInLeftHand == false && !this.plate.getLeftFork().isUsed) {
                System.out.println(textColor + this.name + " pegou o garfo à sua esquerda \u001B[0m");
                this.plate.leftFork.useFork();
                this.isInLeftHand = true;
            }

            if (this.isInRightHand == false && !this.plate.getRightFork().isUsed) {
                System.out.println(textColor + this.name + " pegou o garfo à sua direita \u001B[0m");
                this.plate.rightFork.useFork();
                this.isInRightHand = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mutex.unlock();
        }
    }

    public void returnForks() {
        try {
            mutex.lock();
            System.out.println(textColor + this.name + " devolveu os garfos \u001B[0m");
            this.plate.getLeftFork().returnFork();
            this.plate.getRightFork().returnFork();
            this.isInLeftHand = false;
            this.isInRightHand = false;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            mutex.unlock();
        }
    }
}