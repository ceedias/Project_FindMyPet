package Utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static String FIRST_COLUMN = "Reserva";
    public static String SECOND_COLUMN = "Nome";
    public static String THIRD_COLUMN = "Valor";
    public static String FOURTH_COLUMN = "Imagem";
    public static String FIFTH_COLUMN = "sgTransacao";
    public static String ROOT_URL = "http://201.56.209.143:80/webapi/";
    //public static String ROOT_URL = "http://192.168.15.10:63312/";

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    private static final Locale BRAZIL = new Locale("pt", "BR");
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);

    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00", REAL);

    public static String mascaraDinheiro(double valor, DecimalFormat moeda) {
        return moeda.format(valor);
    }

    public static String formatDateString(String strData) {
        //SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        SimpleDateFormat formato = new SimpleDateFormat("EEE MMM d HH:mm:ss Z yyyy");
        Date data = null;
        try {
            data = formato.parse(strData);
        } catch (ParseException e) {
            e.printStackTrace();
            return strData;
        }
        formato.applyPattern("dd/MM/yyyy HH:mm:ss");
        return formato.format(data);
    }

    public static String getDataAgora() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String teste = df.format(c.getTime());
        return teste;
    }

    public static String getRealValue(long value){
        String resp;

        double newValue = value / 100;
        double dummy = (value % 100);
        double restValue =   dummy / 100;

        resp =  Double.toString(newValue + restValue);

        return resp;
    }

    public static Long getCentsValue(Double value){
        double resp;
        resp =  value * 100;

        return  (new Double(resp)).longValue();
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean isValidCPF(String cpf) {
        if ((cpf==null) || (cpf.length()!=11)) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    public static boolean isValidCNPJ(String cnpj) {
        if ((cnpj==null)||(cnpj.length()!=14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0,12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0,12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0,12) + digito1.toString() + digito2.toString());
    }
}
