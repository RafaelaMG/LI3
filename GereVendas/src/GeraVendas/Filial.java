
package GeraVendas;
import GeraVendas.Wrappers.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.TreeSet;


public class Filial implements Serializable{
     public TreeMap<Integer, ArrayList<Venda>> comprasporfilial;

    public Filial(TreeMap<Integer, ArrayList<Venda>> comprasporfilial) {
        this.comprasporfilial = comprasporfilial;
    }
    public Filial(){
        this.comprasporfilial=new TreeMap<>();
    }

    public TreeMap<Integer, ArrayList<Venda>> getComprasporfilial() {
        return comprasporfilial;
    }

    public void setComprasporfilial(TreeMap<Integer, ArrayList<Venda>> comprasporfilial) {
        this.comprasporfilial = comprasporfilial;
    }
     
     
     
   public ParQuery7 query7() {

        ParQuery7 p7 = new ParQuery7();
        TreeSet<Venda> compradores = new TreeSet<>(new comprasCli());
        ArrayList<Venda> novo = new ArrayList<>();
        ArrayList<Venda> clientes = new ArrayList<>();
        TreeMap<Integer, ArrayList<Venda>> listfilial = new TreeMap<>();

        int filial = 0;
        int i = 3;
        String cli = null;
        float prec = 0.0f;

        for (Integer a : this.comprasporfilial.keySet()) {
            novo = this.comprasporfilial.get(a);

            for (Venda v : novo) {
                compradores.add(v.clone());

            }

            for (Venda f : compradores) {
                filial = f.getFilial();
                if (listfilial.containsKey(filial)) {
                    clientes = listfilial.get(filial);
                    clientes.add(f.clone());
                    listfilial.put(filial, clientes);
                } else {
                    clientes = new ArrayList<>();
                    clientes.add(f.clone());
                    listfilial.put(filial, clientes);

                }
            }

        }

        p7 = new ParQuery7(listfilial);

        return p7;

    }

    class comprasCli implements Comparator<Venda>, Serializable {

        public int compare(Venda i1, Venda i2) {
            float n1 = i1.getPreco() * i1.getUni();
            float n2 = i2.getPreco() * i1.getUni();

            if (n1 > n2) {
                return -1;
            }
            return 1;
        }

    }
}
