package Models;

import java.io.Serializable;
import java.util.Date;

public class Compra implements Serializable {

    public long id;
    public long usuarioid;
    public long cartaoid;
    public long valor;
    public Date data;
    public String latitude;
    public String longitude;
    public long debito;

    public void setId(long id){
        this.id = id;
    }
    public long getId(){
        return this.id;
    }

    public void setUsuarioId(long usuarioid){
        this.usuarioid = usuarioid;
    }
    public long getUsuarioId(){
        return this.usuarioid;
    }

    public void setCartaoId(long cartaoid){
        this.cartaoid = cartaoid;
    }
    public long getCartaoId(){
        return this.cartaoid;
    }

    public void setValor(long valor){
        this.valor = valor;
    }
    public long getValor(){
        return this.valor;
    }

    public void setData(Date data){
        this.data = data;
    }
    public Date getData(){
        return this.data;
    }

    public void setlatitude(String latitude){
        this.latitude = latitude;
    }
    public String getlatitude(){
        return this.latitude;
    }

    public void setlongitude(String longitude){
        this.longitude = longitude;
    }
    public String getlongitude(){
        return this.longitude;
    }

    public void setDebito(long debito){
        this.debito = debito;
    }
    public long getDebito(){
        return this.debito;
    }
}
