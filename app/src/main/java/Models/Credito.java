package Models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Credito implements Serializable{

    public long id;
    public long usuarioid;
    public long compraid;
    public long quantidade;
    //public Date date;
    public int uso;

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

    public void setCompraId(long compraid){
        this.compraid = compraid;
    }
    public long getCompraId(){
        return this.compraid;
    }

    public void setQuantidade(long quantidade){
        this.quantidade = quantidade;
    }
    public long getQuantidade(){
        return this.quantidade;
    }

    /*
    public void setData(Date date) throws ParseException {


        this.date = date;
    }
    public Date getData(){
        return this.date;
    }
    */
    public void setUso(int uso){
        this.uso = uso;
    }
    public int getUso(){
        return this.uso;
    }

}
