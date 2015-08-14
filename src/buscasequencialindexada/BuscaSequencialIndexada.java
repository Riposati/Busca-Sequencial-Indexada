package buscasequencialindexada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gurip_000
 */
public class BuscaSequencialIndexada {

    public static void main(String[] args) {

        List<Indice> indices = new ArrayList<>();
        List<Dados> dados = new ArrayList<>();
        int tamTabelaDeDados;
        int tamIndice;
        Scanner r = new Scanner(System.in);

        System.out.println("informe o tamanho da tabela de dados");
        tamTabelaDeDados = r.nextInt();

        System.out.println("Informe agora os dados");
        montarTabelaDados(tamTabelaDeDados, r, dados);

        System.out.println("Tabela dos dados em ordem\n");
        ordenaOsDadosParticao(dados);

        System.out.println("\ninforme o tamanho das partições desejadas");
        tamIndice = r.nextInt();

        /*
         aqui caso a pessoa queira um indice maior que o tamanho da tabela de dados não mostra nada ou zero
         */
        if (tamIndice > 0 && tamIndice <= dados.size()) {
            mostraDadosParticionados(dados, tamIndice);
            montarEMostrarIndice(dados, tamIndice, indices, r);
        }
    }

    private static void montarTabelaDados(int tamTabelaDeDados, Scanner r, List<Dados> dados) {
        Dados dado;
        for (int i = 0; i < tamTabelaDeDados; i++) {
            dado = new Dados();
            dado.setChave(r.nextInt());
            dados.add(dado);
        }
    }

    private static void montarEMostrarIndice(List<Dados> dados, int tamIndice, List<Indice> indices, Scanner r) {

        Indice indice;
        System.out.println("\nIndices\n******************************************************************");

        // Aqui monta a tabela de indices baseada na tabela de dados
        int cont = 0;
        for (int i = 0; i < (dados.size() / tamIndice); i++) {
            cont += tamIndice;
            indice = new Indice();
            indice.setChave(cont - 1);
            indice.setEntrada(dados.get(cont - 1).getChave());
            indices.add(indice);
        }

        if (dados.size() % tamIndice != 0) {
            // esse código abaixo é para montar a ultima linha do indices
            indice = new Indice();
            int v = dados.size() - 1;
            indice.setChave(v);
            indice.setEntrada(dados.get(v).getChave());
            indices.add(indice);
        }

        for (int i = 0; i < indices.size(); i++) {
            System.out.println("indices chaves = " + indices.get(i).getChave() + " entradas nos dados = " + indices.get(i).getEntrada());
        }
        System.out.println("******************************************************************");

        encontraParticaoCertaEBuscaChave(r, indices, tamIndice, dados);
    }

    private static void encontraParticaoCertaEBuscaChave(Scanner r, List<Indice> indices, int tamParticao, List<Dados> dados) {
        
        System.out.println("\ndigite uma chave a ser buscada nesses dados\n");

        int chaveQueBusco = r.nextInt();
        List<Integer> vetorAuxiliar = new ArrayList<>();
        boolean flag = false;
        boolean testaSeEPrimeiraParticao = false;

        for (int j = 0; j < indices.size() && (flag == false); j++) {
            //System.out.println("chave que busco = " + chaveQueBusco + " indices.get(j).getEntrada() = " + indices.get(j).getEntrada());
            if (chaveQueBusco <= indices.get(j).getEntrada()) {
                int sub = 0;
                if (j > 0 && indices.get(j).getChave() > 0) {
                    sub = (int) (indices.get(j).getChave() - indices.get(j - 1).getChave());
                } else {

                    testaSeEPrimeiraParticao = true;
                }

                if (!testaSeEPrimeiraParticao) {
                    int x = indices.get(j).getChave();
                    System.out.println("tamanho dessa partição = " + sub);
                    for (int y = sub; y > 0; y--) {
                        vetorAuxiliar.add(dados.get(x).getChave());
                        x--;
                    }
                }

                if (testaSeEPrimeiraParticao) {

                    for (int y = 0; y < tamParticao; y++) {
                        vetorAuxiliar.add(dados.get(y).getChave());
                    }
                }

                //System.out.println("chave que busco = " + chaveQueBusco + " indices.get(j).getEntrada() = " + indices.get(j).getEntrada());

                flag = true;
            }
        }

        Collections.sort(vetorAuxiliar);
        System.out.println("\nPartição que iremos buscar na tabela de dados");

        int t = 0;
        for (int chave : vetorAuxiliar) {
            if (t % tamParticao == 0) {
                System.out.println("-----------------------------");
            }
            System.out.println(chave);

            t++;
        }

        int respostaDaBusca = Collections.binarySearch(vetorAuxiliar, chaveQueBusco);

        if (respostaDaBusca > -1) {
            System.out.println("Achamos o valor ele está na posição = " + respostaDaBusca);
        } else {
            System.out.println("Não encontramos o valor nessa partição");
        }
    }

    private static void mostraDadosParticionados(List<Dados> dados, int tamIndice) {
        int cont = 0;

        System.out.println("\nTabela de Dados\n");
        for (int t = 0; t < dados.size(); t++) {
            
            if (t % tamIndice == 0) {

                System.out.println("--------------------------------------------------------------------------");
            }

            System.out.println(cont + " Tamanho partição = " + dados.get(t).getChave());
            cont++;
        }
        System.out.println("--------------------------------------------------------------------------");
    }

    private static void ordenaOsDadosParticao(List<Dados> dados) {

        for (int j = 0; j < dados.size() - 1; j++) {
            for (int i = 0; i < dados.size() - 1; i++) {

                Dados dadoAux = dados.get(i);
                Dados dadoAux2 = dados.get(i + 1);

                if (dadoAux.getChave() > dadoAux2.getChave()) {

                    dados.remove(dadoAux);
                    dados.remove(dadoAux2);

                    dados.add(i, dadoAux2);
                    dados.add(i + 1, dadoAux);
                }
            }
        }
        int mostraIndice = 0;

        for (Dados d : dados) {

            System.out.print("Indice = " + mostraIndice + " Chave = " + d.getChave() + "\n");
            mostraIndice++;
        }
    }
}
