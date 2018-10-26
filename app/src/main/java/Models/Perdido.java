package Models;

import java.io.Serializable;

public class Perdido implements Serializable {

    public long id;
    public String raca;
    public String cor;

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }

    public void setRaca(String telefone){
        this.raca = raca;
    }
    public String getRaca(){
        return this.raca;
    }

    public void setCor(String cpf){
        this.cor = cor;
    }
    public String getCor(){
        return this.cor;
    }
}
