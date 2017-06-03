package GeraVendas;

import GeraVendas.Wrappers.*;

import java.io.Serializable;
import java.util.*;

public class Hipermercado implements Serializable {

    private ArrayList<Venda> vendasCarr;
    private HashMap<String, Cliente> listaclientes;
    private TreeMap<String, ArrayList<Venda>> vendascliente;
    private Facturacao fact;
    private Filial filial;
    
    

    public Hipermercado() {
        this.vendasCarr = new ArrayList<>();
        
        this.listaclientes = new HashMap<>();
        
        this.vendascliente = new TreeMap<>();
        
        this.fact=new Facturacao();
        
        this.filial=new Filial();
    }

    public void addProduto(String text) {
        this.fact.addProduto(text);
    }

    public void addCliente(String text) {
        this.listaclientes.put(text, new Cliente(text));
    }

    public void  vendasValidas() {
       for (Venda v : this.vendasCarr) {
            if (this.listaclientes.containsKey(v.getCodC()) && fact.factCheckCodP(v.getCodP())) {
                fact.addVendasValid(v.clone());
                
            }
        }  
       
    }
    
 public ArrayList<Venda>  carregaValidas() {
     ArrayList<Venda> novo= new ArrayList<>();
       for (Venda v : this.vendasCarr) {
            if (this.listaclientes.containsKey(v.getCodC()) && fact.factCheckCodP(v.getCodP())) {
                novo.add(v.clone());
                
            }
        }  
       return novo;
    }
 
    public int nvendasValidas() {
        return fact.vendasValidSize();
    }

    public int vendasInvalidas() {
             
        ArrayList<Venda> invalidas = new ArrayList<>();

        for (Venda v : this.vendasCarr) {
            if (!(this.listaclientes.containsKey(v.getCodC()) && fact.getListaprodutos().containsKey(v.getCodP()))) {
                invalidas.add(v.clone());
            }
        }

         return invalidas.size(); 
    }

    public void addVenda(Venda v) {
          this.vendasCarr.add(v);
        if (this.listaclientes.containsKey(v.getCodC()) && fact.factCheckCodP(v.getCodP())) {
                fact.addVendasValid(v.clone());
        
    }
    }

    public int vendasUnidades() {
        int i = 0;
        for (Venda v : fact.getVendasValid()) {
            i += v.getUni();
        }
        return i;
    }

    public float factTotal() {
        float i = 0.0f;

        for (Venda v : fact.getVendasValid()) {
            i += v.getPreco();
        }

        return i;
    }

    public int preco0() {
        int i = 0;

        for (Venda v : fact.getVendasValid()) {
            if (v.getPreco() == 0) {
                i++;

            }
        }
        return i;

    }

    public int cliCompras() {
        TreeSet<String> novo = new TreeSet<>();

        for (Venda v : fact.getVendasValid()) {
            novo.add(v.getCodC());
        }

        return novo.size();

    }

    public int cliNcompras() {

        return cliCompras() - this.listaclientes.size();
    }

    public int prodDif() {
        TreeSet<String> aux = new TreeSet<>();

        for (Venda v : fact.getVendasValid()) {
            aux.add(v.getCodP());
        }

        return aux.size();

    }

    public int prodNcomprados() {

        TreeMap<String, Produto> novo = fact.getListaprodutos();
        Produto p = new Produto();

        for (Venda v : fact.getVendasValid()) {
            p.setCodP(v.getCodP());
            novo.remove(v.getCodP(), p);

        }

        return novo.size();

    }

    public int nProds() {
        return this.fact.nProds();
    }

    public int nClie() {
        return this.listaclientes.size();
    }

    public int vendasDup() {
        Set<Venda> novo = new HashSet<>();
        int total;

        novo.addAll(this.vendasCarr);

        total = (this.vendasCarr.size() - novo.size());

        return total;
    }

    public void criaEstatisticas() {
        this.fact.setComprasmes(parseAllLinhasToMap());
        this.fact.setProdutosvendas(parseAllLinhasToMap2());
        this.filial.setComprasporfilial(parseAllLinhasToMap3());
        this.fact.setComprasuni(parseAllLinhasToMap4());
        this.vendascliente = this.fact.parseAllLinhasToMap5();
        //this.fact.setVendasValid(carregaValidas());
        
    }
    
    public TreeMap<Integer, ArrayList<Venda>> getComprasmes(){
        return this.fact.getComprasmes();
    }

    public TreeMap<Integer, ArrayList<Venda>> parseAllLinhasToMap() {
        TreeMap<Integer, ArrayList<Venda>> array = new TreeMap<>();
        ArrayList<Venda> novo = new ArrayList<>();
        for (Venda s : fact.getVendasValid()) {
            if (array.containsKey(s.getMes())) {
                novo = array.get(s.getMes());
                novo.add(s.clone());
                array.put(s.getMes(), novo);
            } else {
                novo = new ArrayList<>();
                novo.add(s.clone());
                array.put(s.getMes(), novo);
            }
        }
      
        return array;
    }

    public TreeMap<String, ArrayList<Venda>> parseAllLinhasToMap2() {
        TreeMap<String, ArrayList<Venda>> prod = new TreeMap<>();
        ArrayList<Venda> novo = new ArrayList<>();
        for (Venda s : fact.getVendasValid()) {
            if (prod.containsKey(s.getCodP())) {
                novo = prod.get(s.getCodP());
                novo.add(s.clone());
                prod.put(s.getCodP(), novo);
            } else {
                novo = new ArrayList<>();
                novo.add(s.clone());
                prod.put(s.getCodP(), novo);
            }
        }
        
        return prod;
    }

    public TreeMap<Integer, ArrayList<Venda>> parseAllLinhasToMap3() {
        TreeMap<Integer, ArrayList<Venda>> array = new TreeMap<>();
        ArrayList<Venda> novo = new ArrayList<>();
        for (Venda s : fact.getVendasValid()) {
            if (array.containsKey(s.getFilial())) {
                novo = array.get(s.getFilial());
                novo.add(s.clone());
                array.put(s.getFilial(), novo);
            } else {
                novo = new ArrayList<>();
                novo.add(s.clone());
                array.put(s.getFilial(), novo);
            }
        }
        
        return array;
    }

    public TreeMap<Integer, ArrayList<Venda>> parseAllLinhasToMap4() {
        TreeMap<Integer, ArrayList<Venda>> array = new TreeMap<>();
        ArrayList<Venda> novo = new ArrayList<>();
        for (Venda s : fact.getVendasValid()) {
            if (array.containsKey(s.getUni())) {
                novo = array.get(s.getUni());
                novo.add(s.clone());
                array.put(s.getUni(), novo);
            } else {
                novo = new ArrayList<>();
                novo.add(s.clone());
                array.put(s.getUni(), novo);
            }
        }
        
        return array;
    }

    public float factAux(ArrayList<Venda> vendas) {
        float fact = 0.0f;

        for (Venda v : vendas) {
            fact += v.getPreco() * v.getUni();
        }

        return fact;
    }
    
    public ParQuery1 querie1() {        
        return this.fact.querie1();
    }
    
    public ParQuery4 query4(String codP){
        return this.fact.query4(codP);
    }
    
    public TreeSet<ParQuery6> query6(int x) {
        return this.fact.query6(x);
    }
    
    public ParQuery7 query7() {
        return this.filial.query7();
    }
    
    public TreeSet<ParQuery9> query9(String codp, int x){
        return this.fact.query9(codp, x);
    }
    
    public Boolean factCheckCodP2(String codiP){
        return this.fact.factCheckCodP2(codiP);
    }

    public ParQuery3 query3(String cliente) {
        ParQuery3 p3 = new ParQuery3();
        TreeMap<Integer, ArrayList<Venda>> comprasmes=fact.getComprasmes();
        int ncompm = 0;
        int nprod = 0;
        int i = 1;
        float fact = 0.0f;
        float facta = 0.0f;
        ArrayList<Integer> nvcompras = new ArrayList<>();
        ArrayList<Integer> prodis = new ArrayList<>();
        ArrayList<Float> factmes = new ArrayList<>();
        float t_facturado = 0.0f;
        ArrayList<Venda> ven = new ArrayList<>();
        ArrayList<Venda> vCodp = new ArrayList<>();
        HashSet<String> prodt = new HashSet<>();

        for (Integer c : comprasmes.keySet()) {
            ven = comprasmes.get(c);

            for (Venda v : ven) {
                if (v.getCodC().equals(cliente)) {
                    vCodp.add(v.clone());
                    prodt.add(v.getCodP());

                }
                nprod = prodt.size();
            }
            fact = factAux(vCodp);
            factmes.add(fact);

            prodt = new HashSet<>();
            ncompm = vCodp.size();
            nvcompras.add(ncompm);

            prodis.add(nprod);
            vCodp = new ArrayList<>();

        }

        for (Float f : factmes) {
            facta += f;
        }

        p3 = new ParQuery3(nvcompras, prodis, facta);

        return p3;
    }

    public ParQuery5 query5(String cliente) {
        ParQuery5 p5 = new ParQuery5();
        
        TreeSet<Venda> vendas = new TreeSet<>(new comprasQuery5());
        TreeMap<Integer, HashSet<Venda>> prodmaiscomprados = new TreeMap<>();
        ArrayList<Venda> array = new ArrayList<>();
        HashSet<Venda> produtos = new HashSet<>();
        int uni = 0;

        for (Integer a : fact.getComprasuni().keySet()) {
            array = fact.getComprasuni().get(a);

            for (Venda v : array) {
                if (v.getCodC().equals(cliente)) {
                    vendas.add(v.clone());
                }
            }
            for (Venda f : vendas) {
                uni = f.getUni();
                if (prodmaiscomprados.containsKey(uni)) {
                    produtos = prodmaiscomprados.get(uni);
                    produtos.add(f.clone());
                    prodmaiscomprados.put(uni, produtos);
                } else {
                    produtos = new HashSet<>();
                    produtos.add(f.clone());
                    prodmaiscomprados.put(uni, produtos);

                }
            }

        }

        p5 = new ParQuery5(prodmaiscomprados);
        return p5;
    }

    public int quantAux(ArrayList<Venda> vendas) {
        int qt = 0;
        for (Venda v : vendas) {
            qt += v.getUni();
        }

        return qt;
    }

    public TreeMap<Integer, ParQuery2> query2(int mes) {
        TreeMap<Integer, ParQuery2> tree = new TreeMap<>();
        ParQuery2 par = new ParQuery2();
        HashSet<String> cli = new HashSet<>();
        ArrayList<Venda> ven = new ArrayList<>();
        int totvemes = 0;
        String c = "";

        if (fact.getComprasmes().containsKey(mes)) {
            ven = fact.getComprasmes().get(mes);
            totvemes = ven.size();

            for (Venda v : ven) {
                c = v.getCodC();
                cli.add(c);
            }
        }
        par = new ParQuery2(cli.size(), totvemes);
        tree.put(mes, par);

        return tree;
    }

    class comprasQuery5 implements Comparator<Venda>, Serializable {

        public int compare(Venda i1, Venda i2) {
            int n1 = i1.getUni();
            int n2 = i1.getUni();

            if (n1 > n2) {
                return -1;
            }

            if (n1 < n2) {
                return 1;
            } else {
                return i1.getCodP().compareTo(i2.getCodP());
            }
        }

    }

    public TreeSet<ParQuery8> query8(int x) {
        TreeSet<ParQuery8> prod = new TreeSet<>(new ComparatorParQuery8());
        TreeSet<ParQuery8> res = new TreeSet<>(new ComparatorParQuery8());
        ArrayList<Venda> venC = new ArrayList<>();
        HashSet<String> pr = new HashSet<>();
        ParQuery8 p8 = new ParQuery8();
        String p = new String();
        int totp = 0;
        for (String c : this.vendascliente.keySet()) {
            venC = this.vendascliente.get(c);
            for (Venda v : venC) {
                pr.add(v.getCodP());
            }
            p = c;
            totp = pr.size();
            p8 = new ParQuery8(p, totp);
            prod.add(p8);
            pr = new HashSet<>();
            totp = 0;
            p = new String();
        }
        int i = 0;
        Iterator<ParQuery8> it = prod.iterator();

        while (it.hasNext() && i < x) {
            res.add(it.next());
            i++;
        }

        return res;
    }

    class ComparatorParQuery8 implements Comparator<ParQuery8>, Serializable {

        public int compare(ParQuery8 c1, ParQuery8 c2) {
            int res;
            int totP1 = c1.getTotp();
            int totP2 = c2.getTotp();

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
