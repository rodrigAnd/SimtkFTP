package br.com.simtk.simtkrelatorio.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAtual {


    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm ");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
