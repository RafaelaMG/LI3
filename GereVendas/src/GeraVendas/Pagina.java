
package GeraVendas;

import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class Pagina {

    private HashMap<Integer,String> linha;
    private int linhas;
    private int pagina;
    private int linhaspPagina;

    public Pagina() {
        this.linha = new HashMap<>();
        this.linhas = 0;
        this.pagina = 0;
        this.linhaspPagina = 10;
    }

    public void addLinha(String s) {
        this.linha.put(linhas+1, s);
        this.linhas++;
    }

    public void printPagina() {
        int op = 1;
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader input = new BufferedReader(reader);
        String aux, aux2;
        while (op != 0) {
            for (int i = 1; i <= this.linhaspPagina && i+(this.pagina * this.linhaspPagina) <= this.linhas; i++) {
                System.out.println(this.linha.get(i+(this.pagina * this.linhaspPagina)));
            }
            System.out.println("1 - Pagina anterior; 2 - Pagina seguinte; 3 - Escolher pagina.");           
            System.out.println("0 - Sair");
            System.out.println("Pagina " + (this.pagina+1) + "/" + ((this.linhas/this.linhaspPagina)+1));
            try {
                aux = input.readLine();
                if (aux.equals("1")){
                    if(this.pagina > 0)
                        pagina--;
                    else
                        System.out.println("Erro: Primeira pagina");
                }
                else if (aux.equals("2")){
                    if(this.pagina < (this.linhas/this.linhaspPagina))
                        this.pagina++;
                    else
                        System.out.println("Erro: Ultima pagina");
                }
                else if (aux.equals("3")){
                    System.out.println("Ir para a pagina:");
                    aux2 = input.readLine();                    
                    if(Integer.parseInt(aux2) <= ((this.linhas/this.linhaspPagina)+1) && Integer.parseInt(aux2) > 0)
                        this.pagina = Integer.parseInt(aux2)-1;
                    else
                        System.out.println("Erro: Pagina invalida");
                }
                else if (aux.equals("0")){
                    op = 0;
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());

            };

        }
    }
}
