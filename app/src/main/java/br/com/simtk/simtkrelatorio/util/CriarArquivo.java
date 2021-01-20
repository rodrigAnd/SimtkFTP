package br.com.simtk.simtkrelatorio.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.getExternalStorageDirectory;

public class CriarArquivo  {

    private String TAG = "result";


    public boolean salvarArquivo(String conteudoArquivo, Context context)  {

        DadosPreference dados = new DadosPreference();
        String arquivo = dados.dadosPreferenceRecuperar(context, "codCli")+"_"
                        +dados.dadosPreferenceRecuperar(context, "codCli")+"."
                        +dados.dadosPreferenceRecuperar(context, "numVend");

        String novoArquivo = dados.dadosPreferenceRecuperar(context, "codCli")+"_"
                +dados.dadosPreferenceRecuperar(context, "codCli")+"_"
                +dados.dadosPreferenceRecuperar(context, "numVend")+".txt";
        Log.d(TAG, "salvarArquivo: " + novoArquivo);

        // criando variavel com none e caminho da pasta
        File folder = new File(context.getFilesDir(), arquivo);

        //verificando se ja existe para criar
        if(!folder.exists()) {
            try {
                folder.createNewFile(); //criando novo arquivo

            } catch (IOException e) {
                e.printStackTrace();

            }
        }

            try {
                FileOutputStream out = context.openFileOutput(arquivo, Context.MODE_PRIVATE);
                out.write(conteudoArquivo.getBytes());
                out.close();
                MyFTP ftp = new MyFTP(context, arquivo, novoArquivo);
                ftp.start();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return  false;
            }
    }

    // LER UM ARQUIVO TEXTO

    public String lerArquivoTexto(Context context,  String nome) {

        try {

            File f = new File(context.getFilesDir(), nome);


            if (f.exists()) {

                FileInputStream in = context.openFileInput(nome);

                int tamanho = in.available();

                byte bytes[] = new byte[tamanho];

                in.read(bytes);

                String s = new String(bytes);



                in.close();
                return s;

            }

        } catch (Exception e) {

            Log.e("ERRO", e.getMessage());

        }
        return "arquivo não encontrado";
    }
}


