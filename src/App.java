import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        final String VALORAPOSTA = "Qual o valor apostado?";
        final String APOSTAPERGUNTA = "Quer apostar? S/N";
        final String CONTINUARAPOSTA = "Quer continuar apostando? S/N";
        final String ESCOLHACAVALO = "Qual cavalo quer apostar? \nVerde\nVermelho\nRoxo\nAzul";
        final String FRASECAMPEAO = "Parabéns, o cavalo %S é o campeão!!!\nO valor da aposta foi duplicado para %.2f\n";
        final String FRASEPERDEDOR = "Você perdeu, o cavalo campeão é o ";
        final int TAMANHOPISTA = 100; // Tamanho da pista

        String opcao;
        boolean condicao;

        System.out.println(APOSTAPERGUNTA);
        opcao = scan.nextLine();
        condicao = opcao.toUpperCase().equals("S") ? true : false;

        while (condicao) {
            float valorAposta;
            String cavaloCampeao = "Branco";
            String escolhaCavalo;
            int[] posicoesIniciais = { 0, 1, 2, 3 }; // Posições iniciais para cada cavalo
            String[] cavalos = { "sA=AP", "sA=AP", "sA=AP", "sA=AP" }; // Símbolos para se mover(cavalos)
            String[] corCavalo = { "VERDE", "VERMELHO", "ROXO", "AZUL" };
            boolean chegada = false;

            System.out.println(VALORAPOSTA);
            valorAposta = scan.nextFloat();
            scan.nextLine();

            System.out.println(ESCOLHACAVALO);
            escolhaCavalo = scan.nextLine();

            if (escolhaCavalo.toUpperCase().equals("VERDE") || escolhaCavalo.toUpperCase().equals("VERMELHO") ||
                    escolhaCavalo.toUpperCase().equals("ROXO") || escolhaCavalo.toUpperCase().equals("AZUL")) {
                System.out.println("Boa escolha");
            } else {
                System.out.println("Esse cavalo não existe");
                break;
            }
            int[] velocidadeCavalos = { 1, 1, 1, 1 }; // Velocidade dos cavalos

                while (!chegada) {
                    System.out.print("\033[H\033[2J"); // Limpa a tela
                    System.out.flush();

                    for (int i = 0; i < cavalos.length; i++) {
                        if(escolhaCavalo.toUpperCase().equals(corCavalo[i])){
                        System.out.print(corCavalo[i]+" R$"+ valorAposta + ":\n\n");
                    }else{
                         System.out.print(corCavalo[i] + ":\n\n");
                    }


                        for (int j = 0; j < TAMANHOPISTA; j++) {
                            if (j == posicoesIniciais[i]) {
                                System.out.print(cavalos[i]);
                            } else {
                                System.out.print("_");
                            }
                        }
                        System.out.println();

                    // Verificar se o cavalo vai cruzar a linha de chegada na próxima atualização
                    if (posicoesIniciais[i] >= TAMANHOPISTA) {
                        chegada = true; // Marcar a chegada para sair do loop
                        posicoesIniciais[i] = TAMANHOPISTA - 1; // Ajustar posição para exatamente no fim da pista
                        switch (corCavalo[i]) {
                            case "VERDE":
                                cavaloCampeao = corCavalo[0];
                                break;
                            case "VERMELHO":
                                cavaloCampeao = corCavalo[1];
                                break;
                            case "ROXO":
                                cavaloCampeao = corCavalo[2];
                                break;
                            case "AZUL":
                                cavaloCampeao = corCavalo[3];
                                break;
                        }
                       // break; // Sair do for loop uma vez que um cavalo cruzou a linha de chegada
                    } else {
                        velocidadeCavalos[i]= random.nextInt(1,4);
                        // Atualizar posição se a corrida não terminou
                        posicoesIniciais[i] += velocidadeCavalos[i];
                    }
                }

                if (!chegada) {
                    Thread.sleep(400); // Só dorme se a corrida não tiver terminado
                }
            }

            if (cavaloCampeao.toUpperCase().equals(escolhaCavalo.toUpperCase())) {
                valorAposta = valorAposta * 2;
                System.out.printf(FRASECAMPEAO, escolhaCavalo, valorAposta);
            } else {
                System.out.println(FRASEPERDEDOR + cavaloCampeao);
            }

            System.out.println(CONTINUARAPOSTA);
            opcao = scan.nextLine();
            condicao = opcao.toUpperCase().equals("S") ? true : false;

        }
        scan.close();
    }
}
