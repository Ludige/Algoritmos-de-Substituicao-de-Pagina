import java.util.Random;

public class NRU {
    public static void main(String[] args) {
        Random randomizer = new Random();

        String[][] matriz_A = new String[1][6];
        matriz_A[0][0] = "N";
        matriz_A[0][1] = "I";
        matriz_A[0][2] = "D";
        matriz_A[0][3] = "R";
        matriz_A[0][4] = "M";
        matriz_A[0][5] = "T";


        //Criação da Matriz Swap
        Integer[][] matriz_swap = new Integer[100][6];  
        for(int x = 0; x < matriz_swap.length; x++){
            matriz_swap[x][0] = x; // N
            matriz_swap[x][1] = x+1; // I
            matriz_swap[x][2] = randomizer.nextInt(51); // D
            matriz_swap[x][3] = 0; // R (Linhas desnecessarias, coloqujei só pra organizar melhor)
            matriz_swap[x][4] = 0; // M
            matriz_swap[x][5] = randomizer.nextInt(9900) + 100; 
        }

        
        //Print da  Matriz Swap
        System.out.println("\t- - - MATRIZ SWAP ORIGINAL - - -");
        exibirMatriz(matriz_A);
        exibirMatriz(matriz_swap);

        // 
        //  RAM
        // 

        Integer[][] matriz_ram = new Integer[10][6];
        int pointer;
        int random;
        boolean is_in_ram = false;

        System.out.println("\n\t- - - MATRIZ RAM ORIGINAL - - -");
        exibirMatriz(matriz_A);

        for(int x = 0; x < matriz_ram.length; x++){
            pointer = randomizer.nextInt(100);
            for(int y = 0; y < matriz_ram[0].length; y++){ 
                matriz_ram[x] = matriz_swap[pointer]; //Copiar toda a coluna na posição do ponteiro
                System.out.printf("%9s", matriz_ram[x][y] + "  |  ");
            }
            System.out.println("");
        }

        for(int z = 0; z < 1000; z++){
            pointer = randomizer.nextInt(99)+1;
            for(int x = 0; x < matriz_ram.length; x++){
                if(matriz_ram[x][1] == pointer){//Modificar R e chances de modificar M
                    is_in_ram = true;
                    matriz_ram[x][3] = 1;

                    random = randomizer.nextInt(100)+1;
                    if(random <= 30){
                        matriz_ram[x][2] = matriz_ram[x][2] + 1;
                        matriz_ram[x][4] = 1;
                    }
                }else if(x == matriz_ram.length - 1 && is_in_ram == false){//Se na ultima posição ponteiro nao for encotrado
                    Integer[][] order_by_class = new Integer[10][6];
                    
                    for(int y = 0; y < matriz_ram.length; y++){
                        for(int a = 0; a < matriz_ram.length; a++){
                            if(matriz_ram[y][3] == 0 && matriz_ram[y][4] == 0){
                                order_by_class[a] = matriz_ram[y];
                            }else if(matriz_ram[y][3] == 0 && matriz_ram[y][4] == 1){
                                order_by_class[a] = matriz_ram[y];
                            }else if(matriz_ram[y][3] == 1 && matriz_ram[y][4] == 0){
                                order_by_class[a] = matriz_ram[y];
                            }else if(matriz_ram[y][3] == 1 && matriz_ram[y][4] == 1){
                                order_by_class[a] = matriz_ram[y];
                            }
                        }
                    }
                    
                    int atual = order_by_class[0][0];
                    
                    for(int a = 0; a < matriz_ram.length; a++){
                        if(matriz_ram[a][0] == atual){
                            matriz_ram[a][3] = 0;
                            matriz_ram[a][4] = 0;
                            matriz_swap[matriz_ram[a][0]] = matriz_ram[a];
                            matriz_ram[a] = matriz_swap[pointer];
                            break;
                        }
                    }
                    break;
                }else if(x == matriz_ram.length - 1 && is_in_ram == true){
                    is_in_ram = false;
                }
                
            }
           
            if(z % 10 == 0){//Resetar R para 0
                for(int y = 0; y < matriz_ram.length; y++){
                    matriz_ram[y][3] = 0;
                }

                for(int i = 0; i < matriz_swap.length; i++){
                    matriz_swap[i][4] = 0;
                }
            }
        }

        System.out.println("\n\t- - - MATRIZ SWAP MODIFICADA - - -");
        exibirMatriz(matriz_swap);

        System.out.println("\n\t- - - MATRIZ RAM MODIFICADA - - -");
        exibirMatriz(matriz_ram);
    }
    


    public static<X> void exibirMatriz(X[][]matriz){
        System.out.println();
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {                
                System.out.printf("%9s", matriz[x][y] + "  |  ");
            }
            System.out.println();
        }
    }
}


