package Models;

import java.io.Serializable;

public class Veiculo implements Serializable {

    public long id;
    public String placa;
    public String NomeVeiculo;

    public void setNomeVeiculo(String NomeVeiculo){
        this.NomeVeiculo = NomeVeiculo;
    }

    public String getNomeVeiculo(){
        return this.NomeVeiculo;
    }

    public void setplaca(String placa){
        this.placa = placa;
    }

    public String getplaca(){
        return this.placa;
    }

}
