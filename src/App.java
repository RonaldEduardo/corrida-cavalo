import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        final String VALORCARTEIRA = "Favor digite o valor inicial da sua carteira:";
        final String VALORAPOSTA = "Qual o valor apostado?";
        final String APOSTAPERGUNTA = "Quer apostar em uma corrida de cavalos? S/N";
        final String CONTINUARAPOSTA = "Quer continuar apostando? S/N";
        final String ESCOLHACAVALO = "Qual cavalo quer apostar? \nVerde\nVermelho\nRoxo\nAzul";
        final String FRASECAMPEAO = "Parabéns, o cavalo %S é o campeão!!!\nO valor da aposta foi duplicado para %.2f\n";
        final String FRASEPERDEDOR = "Cavalo apostado: %S\nVocê perdeu, o cavalo campeão é o %S\n";
        final String SOLICITARADICIONARDINHEIRO = "Deseja adicionar mais dinheiro em sua carteira?";
        final String ADICIONARVALORCARTEIRA = "Digite o valor para adiconar na carteira";
        final int TAMANHOPISTA = 100; // Tamanho da pista
        String opcaoEscolha;// escolha do usuario se quiser apostar
        boolean condicao;// se fo sim, o programa roda senão encerra. É a condição de escolha
        float valorCarteira = 0;
        String escolhaAdicionarDinheiro;
        boolean validcaoAdicionarDinheiro;

        System.out.println(VALORCARTEIRA);
        valorCarteira = scan.nextFloat();
        scan.nextLine();

        System.out.println(APOSTAPERGUNTA);
        opcaoEscolha = scan.nextLine();
        condicao = opcaoEscolha.toUpperCase().equals("S") || opcaoEscolha.toUpperCase().equals("N") ? true : false;
        while (!condicao) {// loop para a pesso aceita ou não, no caso S/N se digitar outra letra fica no
                           // loop infinito
            System.out.println("Digite S/N\n" + APOSTAPERGUNTA);
            opcaoEscolha = scan.nextLine();
            condicao = opcaoEscolha.toUpperCase().equals("S") || opcaoEscolha.toUpperCase().equals("N") ? true : false;
        }
        condicao = condicao && opcaoEscolha.toUpperCase().equals("S") ? true : false;
        // opcao de aposta, caso não queira aposta o programa acaba aqui!!!

        while (condicao) {
            float valorAposta;// valor da aposta
            String cavaloCampeao = "Branco";// cavalo campeão, o branco vem por padrão mas não existe
            String escolhaCavalo;// escolha do cavalo do usuario
            int[] posicoesIniciais = { 0, 1, 2, 3 }; // Posições iniciais para cada cavalo, de onde cada cavalo ira
                                                     // partir
            String[] cavalos = { "sA=AP", "sA=AP", "sA=AP", "sA=AP" }; // Símbolos para se mover(cavalos)
            String[] corCavalo = { "VERDE", "VERMELHO", "ROXO", "AZUL" };// cores dos cavalos
            boolean chegada = false;// chegada, quaso o um cavalo ganhe a chegada é marcada como true e acaba a
                                    // corrida, caso dois ou mais cavalos chegem nao tem ganhador
            boolean validacaoValorAposta = false;

            System.out.println(VALORAPOSTA);
            valorAposta = scan.nextFloat();
            scan.nextLine();
            // se o valor da aposta for maior que o valor da carteira fala que nao tem esse
            // dinheiro
            if (valorAposta > valorCarteira + 1) {
                while (!validacaoValorAposta) {
                    System.out.println("Valor indisponivel, " + SOLICITARADICIONARDINHEIRO);
                    escolhaAdicionarDinheiro = scan.nextLine();
                    validcaoAdicionarDinheiro = escolhaAdicionarDinheiro.toUpperCase().equals("S")
                            || escolhaAdicionarDinheiro.toUpperCase().equals("N") ? true : false;
                    while (!validcaoAdicionarDinheiro) {// loop para a pesso aceita ou não, no caso S/N se digitar outra
                                                        // letra fica no loop infinito
                        System.out.println("Digite S/N\n" + SOLICITARADICIONARDINHEIRO);
                        escolhaAdicionarDinheiro = scan.nextLine();
                        validcaoAdicionarDinheiro = escolhaAdicionarDinheiro.toUpperCase().equals("S")
                                || escolhaAdicionarDinheiro.toUpperCase().equals("N") ? true : false;
                    }
                    if (escolhaAdicionarDinheiro.toUpperCase().equals("N")) {
                        System.out.println("Valor da aposta não pode ser maior que o valor da carteira");
                        System.out.println(VALORAPOSTA + " Carteira: " + valorCarteira);
                        valorAposta = scan.nextFloat();
                        scan.nextLine();
                        validacaoValorAposta = valorAposta <= valorCarteira ? true : false;
                    }else{
                        System.out.println(ADICIONARVALORCARTEIRA);
                        valorCarteira += scan.nextFloat();// adiciona dinheiro a carteira
                        scan.nextLine();
                        validcaoAdicionarDinheiro = validcaoAdicionarDinheiro && escolhaAdicionarDinheiro.toUpperCase().equals("S") ? true : false;
                        System.out.println(VALORAPOSTA + " Carteira: " + valorCarteira);
                        valorAposta = scan.nextFloat();
                        scan.nextLine();
                        validacaoValorAposta = valorAposta <= valorCarteira ? true : false;
                    }
                }
            }
            valorCarteira -= valorAposta;
            // valor da aposta que pode ser duplicado, caso ganhe

            System.out.println(ESCOLHACAVALO);
            escolhaCavalo = scan.nextLine();
            // perguta qual cavalo o usuario escolhe, sendo 4 opções: verde, vermelho,roxo e
            // azul

            boolean condicaoCavalo = escolhaCavalo.toUpperCase().equals(corCavalo[0])
                    || escolhaCavalo.toUpperCase().equals(corCavalo[1])
                    || escolhaCavalo.toUpperCase().equals(corCavalo[2])
                    || escolhaCavalo.toUpperCase().equals(corCavalo[3]) ? true : false;
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

                // esse é o for que percorre os cavalos o i é o cavalo atual
                for (int i = 0; i < cavalos.length; i++) {
                    // verifica se a cor do cavalo é a que o usurio escolheu, se sim acrescenta o
                    // valor apostado
                    if (escolhaCavalo.toUpperCase().equals(corCavalo[i])) {
                        System.out.print(corCavalo[i] + " R$" + valorAposta + ":\n\n");
                    } else {
                        System.out.print(corCavalo[i] + ":\n\n");
                    }
                    // nesse for vai printar onde o cavalo esta de acordo com as posições, caso nao
                    // seja um cavalo ele vai printa a pista "_"
                    for (int j = 0; j < TAMANHOPISTA; j++) {
                        if (j == posicoesIniciais[i]) {
                            System.out.print(cavalos[i]);
                        } else {
                            System.out.print("_");
                        }
                    }
                    System.out.println(); // Isso vai imprimir uma nova linha após a pista de cada cavalo
                    if (posicoesIniciais[i] >= TAMANHOPISTA) {// Verificar se o cavalo vai cruzar a linha de chegada
                        chegada = true; // Marcar a chegada para sair do loop
                        posicoesIniciais[i] = TAMANHOPISTA - 1; // Ajustar posição para exatamente no fim da pista
                        // identifica o cavalo da chegada, e aplica a string da cor
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
                        velocidadeCavalos[i] = random.nextInt(2, 6);
                        // Atualizar posição se a corrida não terminou
                        posicoesIniciais[i] += velocidadeCavalos[i];
                    }
                } // FOR I
                if (!chegada) {
                    Thread.sleep(400); // Só dorme se a corrida não tiver terminado
                }
            } // WHILE
              // se o cavalo campeão for igual o escolhido a aposta é duplicada
            boolean cavaloEscolhaCampeao = cavaloCampeao.toUpperCase().equals(escolhaCavalo.toUpperCase()) ? true
                    : false;
            if (cavaloEscolhaCampeao) {
                valorAposta = valorAposta * 2;
                valorCarteira += valorAposta;
                System.out.printf(FRASECAMPEAO, escolhaCavalo, valorAposta);
            } else {
                // caso não seja campeão
                System.out.printf(FRASEPERDEDOR, escolhaCavalo, cavaloCampeao);
            }
            // se o dinheiro da carteira acabou entra aqui, para adicionar mais, ou não
            if (valorCarteira <= 0.0) {
                final String SEMDINHEIRO = "Seu dinheiro da carteira acabou!!!";
                System.out.println(SEMDINHEIRO);
                System.out.println(SOLICITARADICIONARDINHEIRO);
                escolhaAdicionarDinheiro = scan.nextLine();
                validcaoAdicionarDinheiro = escolhaAdicionarDinheiro.toUpperCase().equals("S")
                        || escolhaAdicionarDinheiro.toUpperCase().equals("N") ? true : false;
                while (!validcaoAdicionarDinheiro) {// loop para a pesso aceita ou não, no caso S/N se digitar outra
                                                    // letra fica no loop infinito
                    System.out.println("Digite S/N\n" + SOLICITARADICIONARDINHEIRO);
                    escolhaAdicionarDinheiro = scan.nextLine();
                    validcaoAdicionarDinheiro = escolhaAdicionarDinheiro.toUpperCase().equals("S")
                            || escolhaAdicionarDinheiro.toUpperCase().equals("N") ? true : false;
                }
                validcaoAdicionarDinheiro = validcaoAdicionarDinheiro
                        && escolhaAdicionarDinheiro.toUpperCase().equals("S") ? true : false;
                if (validcaoAdicionarDinheiro) {
                    System.out.println(ADICIONARVALORCARTEIRA);
                    valorCarteira += scan.nextFloat();// adiciona dinheiro a carteira
                    scan.nextLine();
                    // pergunta se o usuario deseja continuar apostando
                    System.out.println(CONTINUARAPOSTA);
                    opcaoEscolha = scan.nextLine();
                    condicao = opcaoEscolha.toUpperCase().equals("S") || opcaoEscolha.toUpperCase().equals("N") ? true
                            : false;
                    while (!condicao) {// loop para a pesso aceita ou não, no caso S/N se digitar outra letra fica no
                                       // loop infinito
                        System.out.println("Digite S/N\n" + CONTINUARAPOSTA);
                        opcaoEscolha = scan.nextLine();
                        condicao = opcaoEscolha.toUpperCase().equals("S") || opcaoEscolha.toUpperCase().equals("N")
                                ? true
                                : false;
                    }
                    condicao = condicao && opcaoEscolha.toUpperCase().equals("S") ? true : false;
                } else {
                    break;
                }
            } else {
                // pergunta se o usuario deje continuar apostando
                System.out.println(CONTINUARAPOSTA);
                opcaoEscolha = scan.nextLine();
                condicao = opcaoEscolha.toUpperCase().equals("S") || opcaoEscolha.toUpperCase().equals("N") ? true
                        : false;
                while (!condicao) {// loop para a pesso aceita ou não, no caso S/N se digitar outra letra fica no
                                   // loop infinito
                    System.out.println("Digite S/N\n" + CONTINUARAPOSTA);
                    opcaoEscolha = scan.nextLine();
                    condicao = opcaoEscolha.toUpperCase().equals("S") || opcaoEscolha.toUpperCase().equals("N")
                            ? true
                            : false;
                }
                condicao = condicao && opcaoEscolha.toUpperCase().equals("S") ? true : false;
            }
        }
        scan.close();// fecha o Scanner
    }
}
