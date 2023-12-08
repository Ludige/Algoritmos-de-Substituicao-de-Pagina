public class Fork {
    boolean isUsed;

    public void useFork() {
        this.isUsed = true;
    }

    public void returnFork() {
        this.isUsed = false;
    }

    public Fork() {
        this.isUsed = false;
    }

}
