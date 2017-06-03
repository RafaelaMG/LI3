package GeraVendas;

import GeraVendas.Wrappers.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.text.*;

public class GereVendas {

    public static void tabelaEst(TreeMap<Integer, ArrayList<Venda>> comprasmes) {
        int count = 0;
        int i = 1;
        float valor = 0, f1 = 0, f2 = 0, f3 = 0;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        HashSet<String> clientes = new HashSet<>();
        System.out.println("Mês\tProdutos Comprados\tFacturacao Total\t\tFilial 1\t\t\tFilial 2\t\t\tFilial 3\t\tClientes que compraram");
        for (ArrayList<Venda> cena : comprasmes.values()) {
            for (Venda v : cena) {
                clientes.add(v.getCodC());
                if (v.getMes() == i) {
                    count++;
                    valor += v.getPreco();
                    switch (v.getFilial()) {
                        case 1:
                            f1 += v.getPreco();
                            break;
                        case 2:
                            f2 += v.getPreco();
                            break;
                        case 3:
                            f3 += v.getPreco();
                            break;
                    }
                } else {
                    System.out.println(i + "\t     " + count + "\t\t  " + df.format(valor) + "€\t\t        " + df.format(f1) + "€\t\t          " + df.format(f2) + "€\t\t         " + df.format(f3) + "€\t\t     " + clientes.size());
                    count = 1;
                    clientes = new HashSet<>();
                    i++;
                    valor += v.getPreco();
                    switch (v.getFilial()) {
                        case 1:
                            f1 += v.getPreco();
                            break;
                        case 2:
                            f2 += v.getPreco();
                            break;
                        case 3:
                            f3 += v.getPreco();
                            break;
                    }
                }
            }
        }
        System.out.println(i + "\t     " + count + "\t\t  " + df.format(valor) + "€\t\t        " + df.format(f1) + "€\t\t          " + df.format(f2) + "€\t\t         " + df.format(f3) + "€\t\t     " + clientes.size());
    }

    public static Venda parseLinhaVenda(String linha) {

        String[] line = null;

        line = linha.split(" ");

        for (int i = 0; i < 7; i++) {
            line[i].trim();
        }

        Venda v = new Venda(line[0], Float.parseFloat(line[1]), Integer.parseInt(line[2]), line[3], line[4], Integer.parseInt(line[5]), Integer.parseInt(line[6]));

        return v;
    }

    public static void carrega_vendas(String string, Hipermercado main) {
        BufferedReader inStream = null;

        try {
            inStream = new BufferedReader(new FileReader(string));
            String text = null;

            while ((text = inStream.readLine()) != null) {
                main.addVenda(parseLinhaVenda(text));
            }
           

        } catch (IOException e) {
            System.out.println(e.getMessage());

        };
    }

    public static void carrega_clientes(String string, Hipermercado main) {
        BufferedReader inStream = null;

        try {
            inStream = new BufferedReader(new FileReader(string));
            String text = null;

            while ((text = inStream.readLine()) != null) {

                main.addCliente(text);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        };

    }

    public static void carrega_produtos(String string, Hipermercado main) {
        BufferedReader inStream = null;
        try {
            inStream = new BufferedReader(new FileReader(string));
            String text = null;

            while ((text = inStream.readLine()) != null) {
                main.addProduto(text);

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        };

    }

    public static void gravaObj(String fich, Hipermercado main) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fich));
        oos.writeObject(main);

        oos.flush();
        oos.close();
    }

    public static Hipermercado leObj(String fich) throws IOException, ClassNotFoundException {
        Hipermercado te;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fich))) {
            te = (Hipermercado) ois.readObject();
        }
        return te;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int lifetime = 1, load = 0;
        long start = 0, startms = 0, endms = 0, end = 0, ns = 0, ms = 0;
        float s = 0.0f;
        Pagina pag;
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        ArrayList<String> vendas = new ArrayList<>();
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);
        String aux, aux2;
        String fileClientes = null, fileVendas = null, fileProdutos = null;
        Hipermercado main = new Hipermercado();
        System.out.println("/***********************************************************************************************************************************\\");
        System.out.println("/***********************************************************************************************************************************\\");
        System.out.println("/***                                                          GEREVENDAS                                                         ***\\");
        System.out.println("/***********************************************************************************************************************************\\");
        System.out.println("/***                                           Deseja carregar?                                                                  ***\\");
        System.out.println("/***********************************************************************************************************************************\\");
        System.out.println(" 1- Ficheiros Default");
        System.out.println(" 2- Carregar estado");
        System.out.println(" 3- Outro");
        aux = input.readLine();
        if (aux.equals("1")) {
            fileClientes = "Clientes.txt";
            fileProdutos = "Produtos.txt";
            System.out.println("Defaults:\n1 - Vendas_1M.txt\n2 - Vendas_3M.txt\n3 - Vendas_5M.txt");
            aux2 = input.readLine();
            if(aux2.equals("1"))
                fileVendas = "Vendas_1M.txt";
            else if(aux2.equals("2"))
                fileVendas = "Vendas_3M.txt";
            else if(aux2.equals("3"))
                fileVendas = "Vendas_5M.txt";
 
        } else if (aux.equals("2")) {
            System.out.println("Nome do ficheiro a ser carregado ou 1 para default (Default: hipermercado.dat)");
            aux2 = input.readLine();
            if(aux2.equals("1")){
                Crono.start();
                main = leObj("hipermercado.dat");
                Crono.stop();
                System.out.println("\nTempo demorado: " + Crono.print()+"\n");
            }
            else{
                Crono.start();
                main = leObj(aux2);
                Crono.stop();
                System.out.println("\nTempo demorado: " + Crono.print() + "\n");
            }
            load = 1;
        } else if (aux.equals("3")) {
            fileClientes = "Clientes.txt";
            fileProdutos = "Produtos.txt";
            System.out.println("Nome do ficheiro de vendas a ser carregado");
            aux2 = input.readLine();
            fileVendas = aux2;
           
        }
 
        while (lifetime == 1) {
            if (load == 0) {
                System.out.println("/***********************************************************************************************************************************\\");
                System.out.println("/***********************************************************************************************************************************\\");
                System.out.println("/***                                                          Carregamento de ficheiros                                                     ***\\");
                System.out.println("/***********************************************************************************************************************************\\");
                System.out.println("/*** Ficheiro " + fileProdutos);
                Crono.start();
                carrega_produtos(fileProdutos, main);
                Crono.stop();
                System.out.println("/*** Tempo: " + Crono.print());
                System.out.println("/*** Ficheiro " + fileClientes);
                Crono.start();
                carrega_clientes(fileClientes, main);
                Crono.stop();
                System.out.println("/*** Tempo: " + Crono.print() + " segundos");
                System.out.println("/*** Ficheiro " + fileVendas);
                Crono.start();
                carrega_vendas(fileVendas, main);
                Crono.stop();
                main.criaEstatisticas();
                System.out.println("/*** Tempo: " + Crono.print() + " segundos");
            }
            if (load < 2) {
                System.out.println("/***********************************************************************************************************************************\\");
                System.out.println("/***********************************************************************************************************************************\\");
                System.out.println("/***                                                          Estatísticas                                                       ***\\");
                System.out.println("/***********************************************************************************************************************************\\");
                System.out.println("\nVendas válidas: " + df.format(main.nvendasValidas()));
                System.out.println("Vendas inválidas: " + df.format(main.vendasInvalidas()));
                System.out.println("Número Total de Produtos: " + df.format(main.nProds()));
                System.out.println("Número Total de Clientes: " + df.format(main.nClie()));
                System.out.println("Total de Clientes que compraram: " + df.format(main.cliCompras()));
                System.out.println("Total de Clientes que nunca compraram: " + df.format(main.cliNcompras()));
                System.out.println("Total de Clientes diferentes: " + df.format(main.cliCompras()));
                System.out.println("Número de Unidades vendidas: " + df.format(main.vendasUnidades()));
                System.out.println("Total facturado: " + df.format(main.factTotal()));
                System.out.println("Total de compras preço = 0.0 : " + df.format(main.preco0()));
                System.out.println("Total de produtos nunca comprados: " + df.format(main.prodNcomprados()));
                System.out.println("Total de Produtos diferentes comprados: " + df.format(main.prodDif())+"\n");
                tabelaEst(main.getComprasmes());
                System.out.println("\n/***********************************************************************************************************************************\\");
            }

            load = 2;
            System.out.println("/***********************************************************************************************************************************\\");
            System.out.println("/***********************************************************************************************************************************\\");
            System.out.println("/***                                                          GEREVENDAS                                                         ***\\");
            System.out.println("/***********************************************************************************************************************************\\");
            System.out.println("/*** G- Gravar estado                                                                                                            ***\\");
            System.out.println("/*** 1- Lista ordenada alfabeticamente com os códigos dos produtos nunca comprados e o seu respectivo total.                     ***\\");
            System.out.println("/*** 2- Dado um mês válido, determinar o número total vendas realizadas e o número total de clientes distintos que as fizeram.   ***\\");
            System.out.println("/*** 3- Dado um cliente, determinar, para cada mês, quantas compras fez, quantos produtos distintos comprou e quanto gastou.     ***\\");
            System.out.println("/*** 4- Dado um produto, determinar, mês a mês, quantas vezes foi comprado, por quantos clientes diferentes e o total facturado. ***\\");
            System.out.println("/*** 5- Dado um cliente determinar a lista de códigos de produtos que mais comprou (e quantos).                                  ***\\");
            System.out.println("/*** 6- Determinar o conjunto dos X produtos mais vendidos em todo o ano e o número total de clientes que os compraram.          ***\\");
            System.out.println("/*** 7- Determinar, para cada filial, a lista dos três maiores compradores em termos de dinheiro facturado;                      ***\\");
            System.out.println("/*** 8- Determinar os códigos dos X clientes, que compraram mais produtos diferentes, indicando quantos.                         ***\\");
            System.out.println("/*** 9- Dado um produto, determinar o conjunto dos X clientes que mais o compraram e, para cada um, qual o valor gasto.          ***\\");
            System.out.println("/*** 0- Sair");
            System.out.println("/***********************************************************************************************************************************\\");
            System.out.println("/***********************************************************************************************************************************\\");

            aux = input.readLine();

            switch (aux) {
                  case "G":
                    System.out.println("Nome do ficheiro a ser guardado ou 1 para default (Default: hipermercado.dat)");
                    aux2 = input.readLine();
                    if(aux2.equals("1")){
                    Crono.start();
                    gravaObj("hipermercado.dat", main);
                    Crono.stop();
                    System.out.println("Tempo demorado: " + Crono.print());
                    }
                    else{
                        Crono.start();
                        gravaObj(aux2, main);
                        Crono.stop();
                        System.out.println("Tempo demorado: "+ Crono.print());
                    }
                    break;
                case "g":
                    System.out.println("Nome do ficheiro a ser guardado ou 1 para default (Default: hipermercado.dat)");
                    aux2 = input.readLine();
                    if(aux2.equals("1")){
                        Crono.start();
                        gravaObj("hipermercado.dat", main);
                        Crono.stop();
                        System.out.println("Tempo demorado: " + Crono.print());
                    }
                    else{
                        Crono.start();
                        gravaObj(aux2, main);
                        Crono.stop();
                        System.out.println("Tempo demorado: " + Crono.print());
                    }
                    break;

                case "1":
                    ParQuery1 p1 = new ParQuery1();
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    p1 = main.querie1();
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                    pag = p1.toPagina();
                    pag.printPagina();
                    break;
                case "2":
                    int mes = 0;
                    TreeMap<Integer, ParQuery2> q2 = new TreeMap<>();
                    ParQuery2 p = new ParQuery2();
                    System.out.println("/***********************************************************************************************************************************\\");
                    System.out.println("/***                                Introduza o mês que pretende visualizar.(1-Janeiro,...,12-Dezembro)                          ***\\");
                    System.out.println("/***********************************************************************************************************************************\\");
                    aux = input.readLine();
                    mes = Integer.parseInt(aux);
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    q2 = main.query2(mes);
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                    if (q2.containsKey(mes)) {
                        p = q2.get(mes);
                        System.out.println(p.toString());
                    }

                    break;

                case "3":
                    String b = new String();
                    ParQuery3 p3 = new ParQuery3();
                    System.out.println("/***********************************************************************************************************************************\\");
                    System.out.println("/***                                       Introduza o código de Cliente pretendido.                                              ***\\");
                    System.out.println("/***********************************************************************************************************************************\\");
                    aux = input.readLine();
                    b = aux;
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    p3 = main.query3(b);
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                    System.out.println(p3);
                    break;

                case "4":
                    String codP = new String();
                    ParQuery4 p4 = new ParQuery4();
                    System.out.println("/***********************************************************************************************************************************\\");
                    System.out.println("/***                                       Introduza o código de produto pretendido.                                              ***\\");
                    System.out.println("/***********************************************************************************************************************************\\");
                    aux = input.readLine();
                    codP = aux;
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    p4 = main.query4(codP);
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                    System.out.println(p4);

                    break;

                case "5":
                    String cena = new String();
                    ParQuery5 p5= new ParQuery5();
                    System.out.println("/***********************************************************************************************************************************\\");
                    System.out.println("/***                                       Introduza o código de Cliente pretendido.                                              ***\\");
                    System.out.println("/***********************************************************************************************************************************\\");
                    aux = input.readLine();
                    cena = aux;
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    p5 = main.query5(cena);
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                     pag = new Pagina();
                    pag=p5.toPagina();
                    pag.printPagina();
                    break;

                case "6":
                    int x = 0;
                    int i = 0;
                    TreeSet<ParQuery6> q6 = new TreeSet<>();
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    ParQuery6 p6 = new ParQuery6();
                    System.out.println("/***********************************************************************************************************************************\\");
                    System.out.println("/***                                       Introduza o número de produtos que pretende visualizar.                               ***\\");
                    System.out.println("/***********************************************************************************************************************************\\");
                    aux = input.readLine();
                    x = Integer.parseInt(aux);
                    q6 = main.query6(x);
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                    pag = new Pagina();
                    for(ParQuery6 p10: q6){
                        pag.addLinha(p10.toString());
                    }
                     
                    pag.printPagina();
                    break;

                case "7":
                    ParQuery7 p7 = new ParQuery7();
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    p7 = main.query7();
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                    System.out.println(p7);
                    break;

                case "8":
                    int l = 0;
                    TreeSet<ParQuery8> q8 = new TreeSet<>();
                    System.out.println("/***********************************************************************************************************************************\\");
                    System.out.println("/***                                       Introduza o número de clientes que pretende visualizar.                               ***\\");
                    System.out.println("/***********************************************************************************************************************************\\");
                    aux = input.readLine();
                    l = Integer.parseInt(aux);
                    start = System.nanoTime();
                    startms = System.currentTimeMillis();
                    q8 = main.query8(l);
                    endms = System.currentTimeMillis();
                    end = System.nanoTime();
                    ns = (end - start);
                    ms = (endms - startms);
                    s = (ms) / 1000;
                    System.out.println(" Tempo de Execução: " + ns + " ns");
                    System.out.println(" Tempo de Execução: " + ms + " ms");
                    System.out.println(" Tempo de Execução: " + s + " s\n");
                    pag = new Pagina();
                    for (ParQuery8 p8 : q8) {
                        pag.addLinha(p8.toString());
                    }
                    pag.printPagina();

                    break;

                case "9":
                    String codiP = new String();
                    int k = 0;
                    TreeSet<ParQuery9> q9 = new TreeSet<>();
                    System.out.println("/***********************************************************************************************************************************\\");
                    System.out.println("/***                                       Introduza o código de produto pretendido.                                             ***\\");
                    System.out.println("/***********************************************************************************************************************************\\");
                    aux = input.readLine();
                    codiP = aux;
                    if (main.factCheckCodP2(codiP)) {
                        System.out.println("/***********************************************************************************************************************************\\");
                        System.out.println("/***                                       Introduza o número de clientes que pretende visualizar.                               ***\\");
                        System.out.println("/***********************************************************************************************************************************\\");
                        aux = input.readLine();
                        k = Integer.parseInt(aux);
                        start = System.nanoTime();
                        startms = System.currentTimeMillis();
                        q9 = main.query9(codiP, k);
                        endms = System.currentTimeMillis();
                        end = System.nanoTime();
                        ns = (end - start);
                        ms = (endms - startms);
                        s = (ms) / 1000;
                        System.out.println(" Tempo de Execução: " + ns + " ns");
                        System.out.println(" Tempo de Execução: " + ms + " ms");
                        System.out.println(" Tempo de Execução: " + s + " s\n");
                        pag = new Pagina();
                        for (ParQuery9 p9 : q9) {
                            pag.addLinha(p9.toString());
                        }
                        pag.printPagina();
                    } else {
                        System.out.println("/***********************************************************************************************************************************\\");
                        System.out.println("/***                                       Introduza um código de produto válido.                                                ***\\");
                        System.out.println("/***********************************************************************************************************************************\\");
                        aux = input.readLine();
                        codiP = aux;
                        System.out.println("/***********************************************************************************************************************************\\");
                        System.out.println("/***                                       Introduza o número de clientes que pretende visualizar.                               ***\\");
                        System.out.println("/***********************************************************************************************************************************\\");
                        aux = input.readLine();
                        k = Integer.parseInt(aux);
                        start = System.nanoTime();
                        startms = System.currentTimeMillis();
                        q9 = main.query9(codiP, k);
                        endms = System.currentTimeMillis();
                        end = System.nanoTime();
                        ns = (end - start);
                        ms = (endms - startms);
                        s = (ms) / 1000;
                        System.out.println(" Tempo de Execução: " + ns + " ns");
                        System.out.println(" Tempo de Execução: " + ms + " ms");
                        System.out.println(" Tempo de Execução: " + s + " s\n");
                        pag = new Pagina();
                        for (ParQuery9 p9 : q9) {
                            pag.addLinha(p9.toString());
                        }
                        pag.printPagina();
                    }
                    break;
                    
                case "0":
                    System.exit(0);
                    break;
            }
        }
    }

}
