package GeraVendas;




import java.io.Serializable;


public class Cliente implements Serializable{
    private String codC;
    
      public Cliente() {
        this.codC = "";
    }
    
    public Cliente(String codC) {
        this.codC = codC;
    }
    
    public Cliente(Cliente c) {
        this.codC = c.getCodC();
    }

    public String getCodC() {
        return codC;
    }

    public void setCodC(String codC) {
        this.codC = codC;
    }

   
    public String toString() {
        StringBuilder s= new StringBuilder();
        s.append("Cliente: " + this.getCodC() + "\n");
        return s.toString();
        
    }
    
     public boolean equals(Object o){
        
   if(this == o) return true;
   
   if((o==null)||(this.getClass() != o.getClass()))
   return false;
   else { Cliente c = (Cliente) o;
       return ( this.getCodC().equals(c.getCodC()));
    }
    
}
     
   public Cliente clone(){
    return new Cliente (this);
} 
    
}