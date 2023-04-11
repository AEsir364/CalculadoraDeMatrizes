import java.util.Scanner;
public class CalculadoraMatrizes {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        /*
         * Discentes:
         * Alan do Nascimento Clímaco
         * Allinny da Silva Arias
         * Eliton de Souza Gobbi
         * Kayky Luka Ramos da Silva Guedes
         * Victor Eduardo Sousa Moraes
         * Wendel Gabriel de Souza Santos
         */
        // Lendo a ordem da matriz
        System.out.print("Digite a ordem da matriz: ");
        int numero = leitor.nextInt();
        // Lendo a matriz
        System.out.println("Digite os elementos da matriz, a cada "+numero+" números você estará na próxima coluna:");
        double[][] matriz = new double[numero][numero];
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                matriz[i][j] = leitor.nextDouble();
            }
        }
        // Calculando o determinante
        double det = calcularDeterminante(matriz);
        System.out.println("O determinante da matriz é: "+det);
        if (det == 0) {
            System.out.println("A matriz não possui inversa.");
            return;
        }
        // Calculando os cofatores
        double[][] cofatores = calcularCofatores(matriz);
        // Transpondo a matriz dos cofatores
        double[][] transposta = transporMatriz(cofatores);
        // Dividindo a matriz transposta dos cofatores pelo determinante
        double[][] inversa = new double[numero][numero];
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                inversa[i][j] = transposta[i][j] / det;
            }
        }
        // Imprimindo a matriz inversa
        System.out.println("A matriz inversa da matriz original é:");
        for (int i = 0; i < numero; i++) {
            for (int j = 0; j < numero; j++) {
                System.out.print(inversa[i][j] + "   ");
            }
            System.out.println();
        }
        leitor.close();
    }

    
    
    // Método para calcular o determinante da matriz
    public static double calcularDeterminante(double[][] matriz) {
        int n = matriz.length;
        double det = 0;
        if (n == 1) {
            det = matriz[0][0];
        } else if (n == 2) {
            det = matriz[0][0] * matriz[1][1] - matriz[0][1] * matriz[1][0];
        } else {
            for (int j = 0; j < n; j++) {
                det += matriz[0][j] * cofator(matriz, 0, j);
            }
        }
        return det;
    }

    
    
    // Método para calcular o cofator de um elemento da matriz
    public static double cofator(double[][] matriz, int linha, int coluna) {
        int n = matriz.length;
        double[][] submatriz = new double[n - 1][n - 1];
        int i_sub = 0, j_sub = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != linha && j != coluna) {
                    submatriz[i_sub][j_sub++] = matriz[i][j];
                    if (j_sub == n - 1) {
                        j_sub = 0;
                        i_sub++;
                    }
                }
            }
        }
        return Math.pow(-1, linha + coluna) * calcularDeterminante(submatriz);
    }
    public static double[][] calcularCofatores(double[][] matriz) {
        int n = matriz.length;
        double[][] cofatores = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cofatores[i][j] = cofator(matriz, i, j);
            }
        }
        return cofatores;
    }

    // Método para transpor uma matriz
    public static double[][] transporMatriz(double[][] matriz) {
        int n = matriz.length;
        double[][] transposta = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                transposta[j][i] = matriz[i][j];
            }
        }
        return transposta;
    }
}