package br.com.simtk.simtkrelatorio.util;


import android.content.Context;
import android.util.Log;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;


public class MyFTP extends Thread {
    Context context;
    String nomeArquivo;
    String TAG = "FTP";
    String novoArquivo;
    private Object LerArquivoActivity;

    public MyFTP(Context context, String nomeArquivo, String novoArquivo) {
        this.context = context;
        this.nomeArquivo = nomeArquivo;
        this.novoArquivo = novoArquivo;
    }
    @Override
    public void run() {
        try {
            FTPClient ftpClient = new FTPClient();
            FTPClientConfig config = new FTPClientConfig();
            config.getServerTimeZoneId();
            ftpClient.configure(config);
            ftpClient.connect("x.xxx.xxx.xxx", 21);
            int teste = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(teste)) {
                ftpClient.disconnect();
                Log.d("recusou", "O servidor FTP recusou a conexão.");
            }
            ftpClient.login("comum@simtk.nethospedagem.com", "Tkremoto");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            try {
                boolean status = false;
                File folderNew = new File(context.getFilesDir(), nomeArquivo);
                File folderArqw = new File(context.getFilesDir(), novoArquivo);
                InputStream local = new FileInputStream(folderNew);

                ftpClient.storeFile("/grel/env/" + nomeArquivo, local);
                FTPFile[] files = ftpClient.listFiles("/grel/");


                for (int i = 0; i < files.length; i++) {

                    if (files[i].getName().equals(novoArquivo)) {
                        Log.d(TAG, "arquivo encontrado " + files[i]);
                        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                        ftpClient.enterLocalActiveMode();
                        ftpClient.enterLocalPassiveMode();

                        FileOutputStream desFileStream = new FileOutputStream(folderArqw);
                        Log.d(TAG, "Download do arquivo"+ files[i]);
                        ftpClient.retrieveFile("/grel/" + novoArquivo, desFileStream);
                        ftpClient.deleteFile("/grel"+novoArquivo);
                        break;
                    }
                }
                ftpClient.disconnect();
                Log.d("fechou", "ftp encerrado");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SocketException socketException) {
            socketException.printStackTrace();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}