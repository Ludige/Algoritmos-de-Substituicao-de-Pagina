public class Plate {
    Fork leftFork;
    Fork rightFork;
    
    public Plate(Fork leftFork, Fork rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }


    public Fork getLeftFork() {
        return this.leftFork;
    }

    public void setLeftFork(Fork leftFork) {
        this.leftFork = leftFork;
    }

    public Fork getRightFork() {
        return this.rightFork;
    }

    public void setRightFork(Fork rightFork) {
        this.rightFork = rightFork;
    }
    
}
