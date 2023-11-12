import java.util.Random;

public class WsClock {
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
        int clock_pointer = 0;
        int aux_clock = 0;

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

        for(int z = 0; z < 10000; z++){
            pointer = randomizer.nextInt(99)+1;
            aux_clock = clock_pointer - 1;
            if(aux_clock < 0){
                aux_clock = 9;
            }

            for(int x = 0; x < matriz_ram.length; x++){
                if(matriz_ram[x][1] == pointer){
                    is_in_ram = true;
                    matriz_ram[x][3] = 1;

                    random = randomizer.nextInt(100)+1;
                    if(random <= 30){
                        matriz_ram[x][2] = matriz_ram[x][2] + 1;
                        matriz_ram[x][4] = 1;
                    }
                }
            }
            
            if(is_in_ram == false){//Substituição
                random = randomizer.nextInt(9900) + 100;
                do{
                    if(matriz_ram[clock_pointer][3] == 1){// R == 1
                        matriz_ram[clock_pointer][3] = 0;
                        clock_pointer++;
                    }
                    
                    if(matriz_ram[clock_pointer][3] == 0){// R == 0
                        if(random < matriz_ram[clock_pointer][5]){// Tick > Tempo
                            if(matriz_ram[clock_pointer][4] == 0){// M == 0
                                matriz_ram[clock_pointer] = matriz_swap[pointer];
                                is_in_ram = false;
                            }
                            if(matriz_ram[clock_pointer][4] == 1){// M == 1
                                int atual = matriz_ram[clock_pointer][0];
                                matriz_swap[atual] = matriz_ram[clock_pointer];
                                matriz_ram[clock_pointer] = matriz_swap[pointer];
                                is_in_ram = false;
                                clock_pointer++;
                            }
                        }else if(random > matriz_ram[clock_pointer][5]){// Tick < T
                            clock_pointer++;
                        }
                        
                    }else if(matriz_ram[clock_pointer][3] == 1){// R == 1
                        clock_pointer++;
                    }
                    
                    if(clock_pointer == matriz_ram.length){
                        clock_pointer = 0;
                    }
                }while(is_in_ram == true);                
            }

            for(int i = 0; i < matriz_swap.length; i++){
                matriz_swap[i][4] = 0;
            }

            if(z % 10 == 0){//Resetar R para 0
                for(int y = 0; y < matriz_ram.length; y++){
                    matriz_ram[y][3] = 0;
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


