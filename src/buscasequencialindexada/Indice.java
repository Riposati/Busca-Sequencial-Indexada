package buscasequencialindexada;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gurip_000
 */
public class Indice {
    
    private int chave;
    private long entrada;
    
    public Indice(){}

    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public long getEntrada() {
        return entrada;
    }

    public void setEntrada(long entrada) {
        this.entrada = entrada;
    }
    
    public void montarEMostrarIndice(List<Dados> dados, int tamIndice, List<Indice> indices, Scanner r) {

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

        encontraParticaoCertaEBuscaChaveAtravesDoIndice(r, indices, tamIndice, dados);
    }
    
    private void encontraParticaoCertaEBuscaChaveAtravesDoIndice(Scanner r, List<Indice> indices, int tamParticao, List<Dados> dados) {
        
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
                flag = true;
            }
        }
        trazResultadoDaBusca(vetorAuxiliar, tamParticao, chaveQueBusco);
    }

    private void trazResultadoDaBusca(List<Integer> vetorAuxiliar, int tamParticao, int chaveQueBusco) {
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

}
