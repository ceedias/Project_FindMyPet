package Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Extrato implements Serializable {

    public Extrato(){
    }

    public List<Credito> credito;
    public ArrayList<Bonus> bonus;

}
