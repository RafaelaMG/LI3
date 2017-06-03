package GeraVendas;

import GeraVendas.Wrappers.*;
import java.io.Serializable;
import java.util.*;

public class Facturacao implements Serializable {

    private TreeMap<String, Produto> listaprodutos;
    private ArrayList<Venda> vendasValid;
    private TreeMap<String, ArrayList<Venda>> produtosvendas;
    private TreeMap<Integer, ArrayList<Venda>> comprasmes;   
    private TreeMap<Integer, ArrayList<Venda>> comprasuni;

    
      public Facturacao(TreeMap<String, Produto> listaprodutos, ArrayList<Venda> vendasValid, TreeMap<String, ArrayList<Venda>> produtosvendas, TreeMap<Integer, ArrayList<Venda>> comprasmes, TreeMap<Integer, ArrayList<Venda>> comprasporfilial,TreeMap<Integer, ArrayList<Venda>> comprasuni) {
        this.listaprodutos = listaprodutos;
        this.vendasValid = vendasValid;
        this.produtosvendas = produtosvendas;
        this.comprasmes = comprasmes;
     
        this.comprasuni=this.comprasuni;
    }
      
      public Facturacao(){
          this.listaprodutos=new TreeMap<>();
          this.vendasValid=new ArrayList<>();
          this.produtosvendas= new TreeMap<>();
          this.comprasmes=new TreeMap<>();
         
          this.comprasuni=new TreeMap<>();
      }
    
    
    
    
    public TreeMap<String, Produto> getListaprodutos() {
       return listaprodutos;
            
    }

    public void setListaprodutos(TreeMap<String, Produto> listaprodutos) {
        this.listaprodutos = listaprodutos;
    }
    
    public void addVendasValid(Venda v){
        this.vendasValid.add(v);
    }

    public ArrayList<Venda> getVendasValid() {
        ArrayList<Venda> novo = new ArrayList<>();
        for(Venda v: this.vendasValid)
            novo.add(v.clone());
        return novo;
    }
    
    public int vendasValidSize(){
        return this.vendasValid.size();
    }

    public void setVendasValid(ArrayList<Venda> vendasValid) {
        this.vendasValid = vendasValid;
    }

    public TreeMap<String, ArrayList<Venda>> getProdutosvendas() {
        TreeMap<String, ArrayList<Venda>> novo = new TreeMap<>();
        novo.putAll(this.produtosvendas);
        return novo;
    }
    
    public void addProduto(String text){
        this.listaprodutos.put(text, new Produto(text));
    }

    public void setProdutosvendas(TreeMap<String, ArrayList<Venda>> produtosvendas) {
        this.produtosvendas = produtosvendas;
    }

    public TreeMap<Integer, ArrayList<Venda>> getComprasmes() {
        TreeMap<Integer, ArrayList<Venda>> novo = new TreeMap<>();
        novo.putAll(this.comprasmes);
        return novo;
    }

    public void setComprasmes(TreeMap<Integer, ArrayList<Venda>> comprasmes) {
        this.comprasmes = comprasmes;
    }

    

    public TreeMap<Integer, ArrayList<Venda>> getComprasuni() {
        TreeMap<Integer, ArrayList<Venda>> novo = new TreeMap<>();
        novo.putAll(this.comprasuni);
        return novo;
    }

    public void setComprasuni(TreeMap<Integer, ArrayList<Venda>> comprasuni) {
        this.comprasuni = comprasuni;
    }

  
    public TreeMap<String, ArrayList<Venda>> parseAllLinhasToMap5() {
        TreeMap<String, ArrayList<Venda>> prod = new TreeMap<>();
        ArrayList<Venda> novo = new ArrayList<>();
        for (Venda s : this.vendasValid) {
            if (prod.containsKey(s.getCodC())) {
                novo = prod.get(s.getCodC());
                novo.add(s.clone());
                prod.put(s.getCodC(), novo);
            } else {
                novo = new ArrayList<>();
                novo.add(s.clone());
                prod.put(s.getCodC(), novo);
            }
        }
       
        return prod;
    }
    
    public int nProds(){
        return this.listaprodutos.size();
    }

    public ParQuery1 querie1() {
        int total;
        ParQuery1 c = new ParQuery1();

        TreeMap<String, Produto> novo = this.listaprodutos;
        Produto p = new Produto();

        for (Venda v : this.vendasValid) {
            p.setCodP(v.getCodP());
            novo.remove(v.getCodP(), p);

        }

        total = novo.size();
        c.setTotal(total);
        c.setListaprodutos(novo);

        return c;

    }

    public float factAux(ArrayList<Venda> vendas) {
        float fact = 0.0f;

        for (Venda v : vendas) {
            fact += v.getPreco() * v.getUni();
        }

        return fact;
    }

    public ParQuery4 query4(String codP) {
        ParQuery4 p4 = new ParQuery4();
        int ncompm;
        int nprod = 0;
        int i = 1;
        float fact = 0.0f;
        float facta = 0.0f;
        ArrayList<Integer> nvcompras = new ArrayList<>();
        ArrayList<Integer> clidis = new ArrayList<>();
        ArrayList<Float> factmes = new ArrayList<>();

        ArrayList<Venda> ven = new ArrayList<>();
        ArrayList<Venda> vCodp = new ArrayList<>();
        HashSet<String> clidt = new HashSet<>();

        for (Integer c : this.comprasmes.keySet()) {
            ven = this.comprasmes.get(c);

            for (Venda v : ven) {
                if (v.getCodP().equals(codP)) {
                    vCodp.add(v.clone());
                    clidt.add(v.getCodC());

                }

                nprod = clidt.size();
            }
            fact = factAux(vCodp);
            factmes.add(fact);
            clidt = new HashSet<>();
            ncompm = vCodp.size();
            nvcompras.add(ncompm);

            clidis.add(nprod);
            vCodp = new ArrayList<>();

        }

        for (Float a : factmes) {
            facta += a;
        }

        p4 = new ParQuery4(nvcompras, clidis, factmes, facta);

        return p4;
    }

    public TreeSet<ParQuery6> query6(int x) {
        TreeSet<ParQuery6> prod = new TreeSet<>(new ComparatorParQuery6());
        TreeSet<ParQuery6> res = new TreeSet<>(new ComparatorParQuery6());
        ArrayList<Venda> venP = new ArrayList<>();
        HashSet<String> cli = new HashSet<>();
        ParQuery6 p6 = new ParQuery6();
        String p = new String();
        int uv = 0;
        int clidis = 0;
        for (String c : this.produtosvendas.keySet()) {
            venP = this.produtosvendas.get(c);
            for (Venda v : venP) {
                cli.add(v.getCodC());
                uv += v.getUni();
            }
            p = c;
            clidis = cli.size();
            p6 = new ParQuery6(p, clidis, uv);
            prod.add(p6);
            cli = new HashSet<>();
            clidis = 0;
            uv = 0;
            p = new String();
        }
        int i = 0;
        Iterator<ParQuery6> it = prod.iterator();

        while (it.hasNext() && i < x) {
            res.add(it.next());
            i++;
        }

        return res;
    }

    class ComparatorParQuery6 implements Comparator<ParQuery6>, Serializable {

        public int compare(ParQuery6 c1, ParQuery6 c2) {
            int res;
            int totUnVend1 = c1.getTotUvend();
            int totUnVend2 = c2.getTotUvend();

            if (totUnVend1 > totUnVend2) {
                return -1;
            } else if (totUnVend1 < totUnVend2) {
                return 1;
            } else {
                return 0;
            }
        }

    }

    public float valorC(ArrayList<Venda> vendas, String c) {
        float valor = 0.0f;

        for (Venda v : vendas) {
            if (v.getCodC().equals(c)) {
                valor += v.getPreco() * v.getUni();

            }

        }

        return valor;
    }

    public int count(ArrayList<Venda> vendas, String c) {
        int count = 0;

        for (Venda v : vendas) {
            if (v.getCodC().equals(c)) {
                count += v.getUni();
            }
        }

        return count;
    }

    

    public TreeSet<ParQuery9> query9(String codp, int x) {
        TreeSet<ParQuery9> cli = new TreeSet<>(new ComparatorParQuery9());
        TreeSet<ParQuery9> res = new TreeSet<>(new ComparatorParQuery9());
        ArrayList<Venda> venP = new ArrayList<>();

        venP = this.produtosvendas.get(codp);
        ParQuery9 p9 = new ParQuery9();
        String codc = new String();
        int quantos = 0;
        float valor = 0.0f;

        for (Venda v : venP) {
            codc = v.getCodC();

            valor = valorC(venP, codc);
            quantos = count(venP, codc);
            p9 = new ParQuery9(codc, quantos, valor);
            cli.add(p9);
            p9 = new ParQuery9();

        }

        int i = 0;
        Iterator<ParQuery9> it = cli.iterator();

        while (it.hasNext() && i < x) {
            res.add(it.next());
            i++;
        }

        return res;
    }
    
    public Boolean factCheckCodP(String codiP){
        return this.listaprodutos.containsKey(codiP);
    }
    
    public Boolean factCheckCodP2(String codiP){
        return this.produtosvendas.containsKey(codiP);
    }

    class ComparatorParQuery9 implements Comparator<ParQuery9>, Serializable {

        public int compare(ParQuery9 c1, ParQuery9 c2) {
            int res;
            float totP1 = c1.getComprouN();
            float totP2 = c2.getComprouN();

            if (totP1 > totP2) {
                return -1;
            } else if (totP1 < totP2) {
                return 1;
            } else {
                return 0;
            }
        }
    }

}
