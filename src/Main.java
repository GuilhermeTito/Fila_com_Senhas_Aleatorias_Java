import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    // Constantes
    public static final int
        TAMANHO = 10,
        OP_NAO_SELECIONADO = 0,
        OP_GERAR_SENHA = 1,
        OP_ATENDER_CLIENTE = 2,
        OP_SAIR = 3;

    // Variáveis
    public static int[] fila = new int[TAMANHO];
    public static int
            inicio = 0,
            fim = 0,
            opcao = OP_NAO_SELECIONADO;
    public static boolean
        vazia = true,
        deuCerto;

    public static void main(String[] args)
    {
        while(opcao != OP_SAIR)
        {
            opcao = menu();

            switch (opcao) {
                case OP_GERAR_SENHA -> {
                    deuCerto = gerarSenha();
                    if (!deuCerto) {
                        System.out.println("Fila cheia!");
                    }
                    imprimir();
                }

                case OP_ATENDER_CLIENTE -> {
                    deuCerto = atenderCliente();
                    if (!deuCerto) {
                        System.out.println("Fila vazia!");
                    }
                    imprimir();
                }

                case OP_SAIR -> System.exit(0);

                default -> System.out.println("Opção inválida.");
            }
        }
    }

    public static int entradaInteira()
    {
        Scanner leitor = new Scanner(System.in);
        boolean ime = true; //InputMismatchException
        while(ime)
        {
            if(leitor.hasNextInt())
            {
                ime = false;
            }
            else
            {
                System.out.println("Inválido. Digite novamente.");
                leitor.nextLine();
            }
        }
        return leitor.nextInt();
    }

    public static int menu(){
        System.out.println("Fila do Banco");
        System.out.println("1 - Gerar senha");
        System.out.println("2 - Atender cliente");
        System.out.println("3 - Sair");
        System.out.println("Digite sua opção:");

        return entradaInteira();
    }

    public static boolean gerarSenha()
    {
        int senha;
        boolean numeroRepetido;
        int i;

        if(vazia)
        {
            senha = ThreadLocalRandom.current().nextInt(0, 10000);

            fila[fim] = senha;
            fim++;

            if(fim == TAMANHO)
            {
                fim = 0;
            }

            vazia = false;
        }
        else
        {
            if(inicio == fim)
            {
                return false;
            }

            do
            {
                senha = ThreadLocalRandom.current().nextInt(0, 10000);
                numeroRepetido = false;
                i = inicio;

                do
                {
                    if (senha == fila[i])
                    {
                        numeroRepetido = true;
                        break;
                    }

                    i++;

                    if(i == TAMANHO)
                    {
                        i = 0;
                    }
                }while(i != fim);
            }while(numeroRepetido);

            fila[fim] = senha;
            fim++;

            if(fim == TAMANHO)
            {
                fim = 0;
            }
        }
        System.out.println("Senha gerada: " + senha);
        return true;
    }

    public static boolean atenderCliente()
    {
        if(vazia)
        {
            return false;
        }

        System.out.println("Senha " + fila[inicio] + " atendida!");
        inicio++;

        if(inicio == TAMANHO)
        {
            inicio = 0;
        }

        if(inicio == fim)
        {
            vazia = true;
        }

        return true;
    }

    public static void imprimir()
    {
        if(!vazia)
        {
            int i = inicio;
            do
            {
                System.out.print("[" + fila[i] + "] ");
                i++;
                if(i == TAMANHO)
                {
                    i = 0;
                }
            }while (i != fim);
            System.out.println();
        }
    }
}