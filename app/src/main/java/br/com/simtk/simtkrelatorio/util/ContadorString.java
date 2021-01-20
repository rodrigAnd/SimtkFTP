package br.com.simtk.simtkrelatorio.util;

public class ContadorString {

    public String contadorString(String texto){

        int tamanho = texto.length();

        if(tamanho == 2){
            String nova = "0"+texto;
            return nova;
        }else{
             String nova = "00"+texto;
             return nova;
        }
    }
}
