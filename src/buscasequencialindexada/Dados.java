package buscasequencialindexada;

import java.util.List;
import java.util.Scanner;

public class Dados{
    
    private int chave;
    
    public Dados(){}
    
    public int getChave() {
        return chave;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public void montarTabelaDados(int tamTabelaDeDados, Scanner r, List<Dados> dados) {
        Dados dado;
        for (int i = 0; i < tamTabelaDeDados; i++) {
            dado = new Dados();
            dado.setChave(r.nextInt());
            dados.add(dado);
        }
    }
    
    public void mostraDadosParticionados(List<Dados> dados, int tamIndice) {
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
    
    public void ordenaOsDadosParticao(List<Dados> dados) {

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
