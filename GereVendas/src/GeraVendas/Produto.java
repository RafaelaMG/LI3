package GeraVendas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;


public class Produto implements Serializable{
    private String codP;

    public Produto() {
        this.codP = "";
    }

    public Produto(String codP) {
        this.codP = codP;
    }
    
    public Produto(Produto p){
        this.codP = p.getCodP();
}

    public String getCodP() {
        return codP;
    }

    public void setCodP(String codP) {
        this.codP = codP;
    }

   
    public String toString() {
       StringBuilder s= new StringBuilder("Produto: ");
   
       s.append(this.getCodP()+ "\n");
        return s.toString();
        
    }
    
         public boolean equals(Object o){
        
   if(this == o) return true;
   
   if((o==null)||(this.getClass() != o.getClass()))
   return false;
   else { Produto p = (Produto) o;
       return ( this.getCodP().equals(p.getCodP()));
    }
    
}
     
   public Produto clone(){
    return new Produto (this);
} 
    
}