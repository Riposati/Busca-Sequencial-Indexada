package buscasequencialindexada;

import java.util.ArrayList;
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
        int tamParticoesDesejadas;
        Indice indice = new Indice();
        Dados dado = new Dados();
        Scanner r = new Scanner(System.in);

        System.out.println("informe o tamanho da tabela de dados");
        tamTabelaDeDados = r.nextInt();

        if (tamTabelaDeDados > 0) {

            System.out.println("Informe agora os dados");
            dado.montarTabelaDados(tamTabelaDeDados, r, dados);

            System.out.println("Tabela dos dados em ordem\n");
            dado.ordenaOsDadosParticao(dados);

            System.out.println("\ninforme o tamanho das partições desejadas");
            tamParticoesDesejadas = r.nextInt();

            /*
             aqui caso a pessoa queira um indice maior que o tamanho da tabela de dados não mostra nada ou zero
             */
            if (tamParticoesDesejadas > 0 && tamParticoesDesejadas <= dados.size()) {
                dado.mostraDadosParticionados(dados, tamParticoesDesejadas);
                indice.montarEMostrarIndice(dados, tamParticoesDesejadas, indices, r);
            }
        }
    }
}
