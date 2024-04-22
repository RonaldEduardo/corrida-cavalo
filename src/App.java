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
        String opcaoEscolha;// escolha do usuario se quiser apostar
        boolean condicao;// se fo sim, o programa roda senão encerra. É a condição de escolha

        System.out.println(APOSTAPERGUNTA);
        opcaoEscolha = scan.nextLine();
        condicao = opcaoEscolha.toUpperCase().equals("S") ? true : false;
        // opcao de aposta, caso não queira aposta o programa acaba aqui!!!

        while (condicao) {
            float valorAposta;// valor da aposta
            String cavaloCampeao = "Branco";// cavalo campeão, o branco vem por padrão mas não existe
            String escolhaCavalo;//escolha do cavalo do usuario
            int[] posicoesIniciais = { 0, 1, 2, 3 }; // Posições iniciais para cada cavalo, de onde cada cavalo ira partir
            String[] cavalos = { "sA=AP", "sA=AP", "sA=AP", "sA=AP" }; // Símbolos para se mover(cavalos)
            String[] corCavalo = { "VERDE", "VERMELHO", "ROXO", "AZUL" };// cores dos cavalos
            boolean chegada = false;// chegada, quaso o um cavalo ganhe a chegada é marcada como true e acaba a corrida, caso dois ou mais cavalos chegem nao tem ganhador

            System.out.println(VALORAPOSTA);
            valorAposta = scan.nextFloat();
            scan.nextLine();
            //valor da aposta que pode ser duplicado, caso ganhe

            System.out.println(ESCOLHACAVALO);
            escolhaCavalo = scan.nextLine();
            // perguta qual cavalo o usuario escolhe, sendo 4 opções: verde, vermelho,roxo e azul

            boolean condicaoCavalo = escolhaCavalo.toUpperCase().equals(corCavalo[0]) || escolhaCavalo.toUpperCase().equals(corCavalo[1]) || escolhaCavalo.toUpperCase().equals(corCavalo[2]) || escolhaCavalo.toUpperCase().equals(corCavalo[3]) ? true : false;
            // caso a escolha cavalo esteja fora das 4 cores, é declarado como false
            if (condicaoCavalo) {
                System.out.println("Boa escolha");
            } else {
                System.out.println("Esse cavalo não existe");
                break;
            }
            int[] velocidadeCavalos = { 1, 1, 1, 1 }; // Velocidade dos cavalos

            while (!chegada) {
                System.out.print("\033[H\033[2J"); // Limpa a tela
                System.out.flush();// limpa o buffer de saida

                for (int i = 0; i < cavalos.length; i++) {// esse é o for que percorre os cavalos o i é o cavalo atual
                    // verifica se a cor do cavalo é a que o usurio escolheu, se sim acrescenta o valor apostado
                    if(escolhaCavalo.toUpperCase().equals(corCavalo[i])){
                    System.out.print(corCavalo[i]+" R$"+ valorAposta + ":\n\n");
                    }else{
                        System.out.print(corCavalo[i] + ":\n\n");
                    }

                    for (int j = 0; j < TAMANHOPISTA; j++) {// nesse for vai printar onde o cavalo esta de acordo com as posições, caso nao seja um cavalo ele vai printa a pista "_"
                        if (j == posicoesIniciais[i]) {
                          System.out.print(cavalos[i]);
                        } else {
                          System.out.print("_");
                        }
                    }
                    // Verificar se o cavalo vai cruzar a linha de chegada
                    if (posicoesIniciais[i] >= TAMANHOPISTA) {
                        chegada = true; // Marcar a chegada para sair do loop
                        posicoesIniciais[i] = TAMANHOPISTA - 1; // Ajustar posição para exatamente no fim da pista
                        //identifica o cavalo da chegada, e aplica a string da cor
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
                    } else {
                        // velocidade aleatoria dos cavalos, dando mais competividade
                        velocidadeCavalos[i]= random.nextInt(1,4);
                        // Atualizar posição se a corrida não terminou
                        posicoesIniciais[i] += velocidadeCavalos[i];
                    }
                }

                if (!chegada) {
                    Thread.sleep(400); // Só dorme se a corrida não tiver terminado
                }
            }
            // se o cavalo campeão for igual o escolhido a aposta é duplicada
            boolean cavaloEscolhaCampeao = cavaloCampeao.toUpperCase().equals(escolhaCavalo.toUpperCase()) ? true : false;
            if (cavaloEscolhaCampeao) {
                valorAposta = valorAposta * 2;
                System.out.printf(FRASECAMPEAO, escolhaCavalo, valorAposta);
            } else {
                //caso não seja campeão
                System.out.println(FRASEPERDEDOR + cavaloCampeao);
            }
            //pergunta se o usuario deje continuar apostando
            System.out.println(CONTINUARAPOSTA);
            opcaoEscolha = scan.nextLine();
            condicao = opcaoEscolha.toUpperCase().equals("S") ? true : false;

        }
        scan.close();//fecha o Scanner
    }
}
