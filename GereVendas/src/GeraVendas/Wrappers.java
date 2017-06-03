package GeraVendas;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeMap;

public class Wrappers implements Serializable {

   public static class ParQuery1 implements Serializable {

        private TreeMap<String, Produto> listaprodutos;
        private int total;

        public ParQuery1(TreeMap<String, Produto> listaprodutos, int total) {
            this.listaprodutos = listaprodutos;
            this.total = total;
        }

        public ParQuery1() {
            this.listaprodutos = new TreeMap<>();
            this.total = 0;
        }

        public TreeMap<String, Produto> getListaprodutos() {
            TreeMap<String, Produto> novo = new TreeMap<>();

            for (Produto p : listaprodutos.values()) {
                novo.put(p.getCodP(), p.clone());
            }

            return novo;
        }

        public void setListaprodutos(TreeMap<String, Produto> listaprodutos) {
            this.listaprodutos = listaprodutos;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();

            s.append(this.getListaprodutos().values() + "\n");
            s.append("Total de produtos não comprados: " + this.getTotal());
            return s.toString();

        }
        
        public Pagina toPagina() {
            Pagina p = new Pagina();
            StringBuilder s = new StringBuilder();
            p.addLinha("Total de produtos não comprados: " + this.getTotal());
            for(Produto pr: this.listaprodutos.values())
                p.addLinha("Produto: " + pr.getCodP());
                        

            return p;

        }

    }

    public static class ParQuery2 implements Serializable {

        private int totclientes;
        private int totvendas;

        public ParQuery2() {
            this.totclientes = 0;
            this.totvendas = 0;
        }

        public ParQuery2(int totc, int totv) {
            this.totclientes = totc;
            this.totvendas = totv;
        }

        public ParQuery2(ParQuery2 p) {
            this.totclientes = p.getTotclientes();
            this.totvendas = p.getTotvendas();
        }

        public int getTotclientes() {
            return this.totclientes;
        }

        public int getTotvendas() {
            return this.totvendas;
        }

        public void setClientes(int totc) {
            this.totclientes = totc;
        }

        public void setTotal(int total2) {
            this.totvendas = total2;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();

            s.append(" Total de Vendas:" + this.getTotvendas() + "\n");
            s.append(" Total de Clientes diferentes:" + this.getTotclientes() + "\n");

            return s.toString();
        }

    }

    public static class ParQuery3 implements Serializable {

        private ArrayList<Integer> total_compras;
        private ArrayList<Integer> produtos_distintos;
        private float total_facturado;

        public ArrayList<Integer> getTotal_compras() {
            ArrayList<Integer> novo = new ArrayList<>();
            for (Integer i : this.total_compras) {
                novo.add(i);
            }

            return novo;
        }

        public void setTotal_compras(ArrayList<Integer> total_compras) {
            this.total_compras = total_compras;
        }

        public ArrayList<Integer> getProdutos_distintos() {
            ArrayList<Integer> novo = new ArrayList<>();
            for (Integer i : this.produtos_distintos) {
                novo.add(i);
            }

            return novo;
        }

        public void setProdutos_distintos(ArrayList<Integer> produtos_distintos) {
            this.produtos_distintos = produtos_distintos;
        }

        public float getTotal_facturado() {
            return total_facturado;
        }

        public void setTotal_facturado(float total_facturado) {
            this.total_facturado = total_facturado;
        }

        public ParQuery3(ArrayList<Integer> total_compras, ArrayList<Integer> produtos_distintos, float total_facturado) {
            this.total_compras = total_compras;
            this.produtos_distintos = produtos_distintos;
            this.total_facturado = total_facturado;
        }

        public ParQuery3() {
            this.total_compras = new ArrayList<>();
            this.produtos_distintos = new ArrayList<>();
            this.total_facturado = 0.0f;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            int i = 1;
            int c = 1;
            for (Integer a : this.getTotal_compras()) {
                s.append(" Mês " + i + ": " + a + "\n");
                i++;
            }

            for (Integer b : this.getProdutos_distintos()) {
                s.append(" Produtos diferentes no mês " + c + ": " + b + "\n");
                c++;
            }

            s.append(" Total Gasto: " + this.getTotal_facturado() + "\n");

            return s.toString();
        }

    }

    public static class ParQuery4 implements Serializable {

        private ArrayList<Integer> total_compras;
        private ArrayList<Integer> clientes_distintos;
        private ArrayList<Float> facturado_mes;
        private float total_facturado;

        public ParQuery4() {
            this.total_compras = null;
            this.clientes_distintos = null;
            this.facturado_mes = null;
            this.total_facturado = 0.0f;
        }

        public ParQuery4(ArrayList<Integer> total_compras, ArrayList<Integer> clientes_distintos, ArrayList<Float> facturado_mes, float total_facturado) {
            this.total_compras = total_compras;
            this.clientes_distintos = clientes_distintos;
            this.facturado_mes = facturado_mes;
            this.total_facturado = total_facturado;
        }

        public ArrayList<Integer> getTotalCompras() {
            return this.total_compras;
        }

        public ArrayList<Integer> getClientes_distintos() {
            return this.clientes_distintos;
        }

        public ArrayList<Float> getFacturadoMes() {
            return this.facturado_mes;
        }

        public float getTotalFacturado() {
            return this.total_facturado;
        }

        public void setTotal_compras(ArrayList<Integer> total_compras) {
            this.total_compras = total_compras;
        }

        public void setClientes_distintos(ArrayList<Integer> clientes_distintos) {
            this.clientes_distintos = clientes_distintos;
        }

        public void setFacturado_mes(ArrayList<Float> facturado_mes) {
            this.facturado_mes = facturado_mes;
        }

        public void setTotal_facturado(float total_facturado) {
            this.total_facturado = total_facturado;
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            int i = 1;
            int c = 1;

            for (Integer a : this.getTotalCompras()) {
                s.append(" Total de compras mês " + i + ": " + a + "\n");
                i++;
            }
            s.append("\n");
            for (Integer b : this.getClientes_distintos()) {
                s.append(" Clientes diferentes no mês " + c + ": " + b + "\n");
                c++;
            }
            s.append("\n");
            for (Float f : this.getFacturadoMes()) {
                s.append(" Total facturado no mês: " + f + "\n");
            }
            s.append("\n");
            s.append(" Total Facturado Anual: " + this.getTotalFacturado() + "\n");

            return s.toString();
        }

    }

    public static class ParQuery5 implements Serializable {

        TreeMap<Integer, HashSet<Venda>> produtoscomprados;

        public TreeMap<Integer, HashSet<Venda>> getProdutoscomprados() {
            return produtoscomprados;
        }

        public void setProdutoscomprados(TreeMap<Integer, HashSet<Venda>> produtoscomprados) {
            this.produtoscomprados = produtoscomprados;
        }

        public ParQuery5(TreeMap<Integer, HashSet<Venda>> produtoscomprados) {
            this.produtoscomprados = produtoscomprados;
        }

        public ParQuery5() {
            this.produtoscomprados = new TreeMap<>();
        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            HashSet<Venda> novo = new HashSet<>();

            for (Integer i : this.produtoscomprados.descendingKeySet()) {
                novo = this.produtoscomprados.get(i);
                Iterator<Venda> it = novo.iterator();
                s.append("\nProduto: " + it.next().getCodP() + "\n");
                s.append("Quantidade Máxima comprada: " + i + "\n");

            }
            return s.toString();
        }
        
        public Pagina toPagina() {
            Pagina p = new Pagina();
            StringBuilder s = new StringBuilder();
            HashSet<Venda> novo = new HashSet<>();

            for (Integer i : this.produtoscomprados.descendingKeySet()) {
                novo = this.produtoscomprados.get(i);
                Iterator<Venda> it = novo.iterator();
                p.addLinha("Produto: " + it.next().getCodP());
                p.addLinha("Quantidade Máxima comprada: " + i);
               

            }
                        

            return p;

        }

    }

    public static class ParQuery6 implements Serializable {

        private String codp;
        private int totCdist;
        private int totUvend;

        public ParQuery6() {
            this.codp = "";
            this.totCdist = 0;
            this.totUvend = 0;
        }

        public ParQuery6(String p, int c, int u) {
            this.codp = p;
            this.totCdist = c;
            this.totUvend = u;
        }

        public ParQuery6(ParQuery6 p) {
            this.codp = p.getCodp();
            this.totCdist = p.getTotCdist();
            this.totUvend = p.getTotUvend();
        }

        public String getCodp() {
            return codp;
        }

        public int getTotCdist() {
            return totCdist;
        }

        public void setTotCdist(int totCdist) {
            this.totCdist = totCdist;
        }

        public int getTotUvend() {
            return totUvend;
        }

        public void setTotUvend(int totUvend) {
            this.totUvend = totUvend;
        }

        public void setCodp(String codp) {
            this.codp = codp;
        }

        public boolean equals(Object o) {

            if (this == o) {
                return true;
            }

            if ((o == null) || (this.getClass() != o.getClass())) {
                return false;
            } else {
                ParQuery6 v = (ParQuery6) o;
                return (this.getTotCdist() == (v.getTotCdist()) && this.getTotUvend() == (v.getTotUvend()) && this.getCodp().equals(v.getCodp()));
            }

        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(" Produto:" + this.getCodp() + "\n");
            s.append(" Total de Unidades Vendidas:" + this.getTotUvend() + "\n");
            s.append(" Total de Clientes diferentes:" + this.getTotCdist() + "\n");

            return s.toString();
        }
        
          

        public ParQuery6 clone() {
            return new ParQuery6(this);
        }

    }

    public static class ParQuery7 implements Serializable {

        private TreeMap<Integer, ArrayList<Venda>> listacompradores;

        public TreeMap<Integer, ArrayList<Venda>> getListacompradores() {
            return listacompradores;
        }

        public void setListacompradores(TreeMap<Integer, ArrayList<Venda>> listacompradores) {
            this.listacompradores = listacompradores;
        }

        public ParQuery7(TreeMap<Integer, ArrayList<Venda>> listacompradores) {
            this.listacompradores = listacompradores;

        }

        public ParQuery7() {
            this.listacompradores = new TreeMap<>();

        }

        public String toString() {
            StringBuilder s = new StringBuilder();
            ArrayList<Venda> novo = new ArrayList<>();
            int c = 1;

            for (Integer i : this.listacompradores.keySet()) {
                novo = this.listacompradores.get(i);
                Iterator<Venda> it = novo.iterator();
                s.append("\nFilial " + i + "\n");
                c = 1;
                while (it.hasNext() && c <= 3) {
                    s.append("Cliente: " + it.next().getCodC() + "\n");
                    c++;
                }

            }
            return s.toString();
        }

    }
    public static class ParQuery8  implements Serializable {
    private String codC;
    private int totp;

    public ParQuery8() {
        this.codC = " ";
        this.totp = 0;
    }

    public ParQuery8(String codC, int totp) {
        this.codC = codC;
        this.totp = totp;
    }
    
    public ParQuery8(ParQuery8 p){
        this.codC = p.getCodC();
        this.totp = p.getTotp();
    }

    public String getCodC() {
        return codC;
    }

    public void setCodC(String codC) {
        this.codC = codC;
    }

    public int getTotp() {
        return totp;
    }

    public void setTotp(int totp) {
        this.totp = totp;
    }
    
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        } else {
            ParQuery8 v = (ParQuery8) o;
            return (this.getTotp() == (v.getTotp())  && this.getCodC().equals(v.getCodC()));
        }

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(" Cliente:" + this.getCodC() + "\n");
        s.append(" Total de Produtos:" + this.getTotp() + "\n");

        return s.toString();
    }

    public ParQuery8 clone() {
        return new ParQuery8(this);
    }
    
}

    
    public static class ParQuery9  implements Serializable {
    private String codC;
    private int comprouN;
    private float  valorgasto;

    public ParQuery9() {
        this.codC = new String();
        this.comprouN = 0;
        this.valorgasto = 0.0f;
    }
    
    public ParQuery9(String codC,int c, float valorgasto) {
        this.codC = codC;
        this.comprouN = c;
        this.valorgasto = valorgasto;
    }
    
    public ParQuery9(ParQuery9 p) {
        this.codC = p.getCodC();
        this.comprouN = p.getComprouN();
        this.valorgasto = p.getValorgasto();
    }

    public int getComprouN() {
        return comprouN;
    }
    
    public String getCodC() {
        return codC;
    }

    public void setCodC(String codC) {
        this.codC = codC;
    }

    public float getValorgasto() {
        return valorgasto;
    }

    public void setValorgasto(float valorgasto) {
        this.valorgasto = valorgasto;
    }

    public void setComprouN(int comprouN) {
        this.comprouN = comprouN;
    }
    
     public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        } else {
            ParQuery9 v = (ParQuery9) o;
            return (this.getCodC().equals(v.getCodC())  && this.getValorgasto()==(v.getValorgasto()) && this.getComprouN()==(v.getComprouN()));
        }

    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(" Cliente:" + this.getCodC() + "\n");
        s.append(" Quantidade de vezes comprado:" + this.getComprouN() + "\n");
        s.append(" Valor total gasto:" + this.getValorgasto() + "\n");

        return s.toString();
    }

    public ParQuery9 clone() {
        return new ParQuery9(this);
    }
    
    
    
    
}


}
