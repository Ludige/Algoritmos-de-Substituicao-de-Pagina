public class Main {
    public static void main(String[] args) {
        
        Fork garfo_1 = new Fork();
        Fork garfo_2 = new Fork();
        Fork garfo_3 = new Fork();
        Fork garfo_4 = new Fork();
        Fork garfo_5 = new Fork();

        //Nomes errados das cores kkkkkkkk 
        String azul = "\u001B[34m";
        String roxo = "\u001B[35m";
        String amarelo = "\u001B[33m";
        String verde = "\u001B[32m";
        String branco = "\u001B[00m";

        
        Philosopher filofo_1 = new Philosopher("Al Haitam", azul, garfo_1, garfo_2);
        Philosopher filofo_2 = new Philosopher("Kaveh",amarelo,garfo_2, garfo_3);
        Philosopher filofo_3 = new Philosopher("Mahamatra Cyno",roxo, garfo_3,garfo_4);
        Philosopher filofo_4 = new Philosopher("Tighnari",branco, garfo_4, garfo_5);
        Philosopher filofo_5 = new Philosopher("Collei",verde, garfo_5,garfo_1);
       
        filofo_1.start();
        filofo_2.start();
        filofo_3.start();
        filofo_4.start();
        filofo_5.start(); 
    }
}