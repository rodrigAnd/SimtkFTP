package br.com.simtk.simtkrelatorio.view.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import br.com.simtk.simtkrelatorio.R;
import br.com.simtk.simtkrelatorio.model.ModelDados;
import br.com.simtk.simtkrelatorio.util.DadosPreference;
import br.com.simtk.simtkrelatorio.view.fragments.dialog.ConfiguracaoDialog;
import br.com.simtk.simtkrelatorio.view.fragments.estoque.EstoqueFragment;
import br.com.simtk.simtkrelatorio.view.fragments.financeiro.FinanceiroFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    //objeto para acessar bottomNavigation
    private BottomNavigationView bottomNavView;
    private Menu menu;
    private RecyclerView rc_estoque;
    ModelDados modelDados;

    String appPermission [] = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET

    };
    public static final int CODIGO_PERMISSOES_REQUERIDAS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavView = (BottomNavigationView) findViewById(R.id.bottom_nav_view);
        bottomNavView.setOnNavigationItemSelectedListener(this);
        if(permissoes()){
            DadosPreference dados = new DadosPreference();
            dados.dadosOp(getApplicationContext(), "OP1", "01");
            Fragment estoqueFragment = EstoqueFragment.newInstance();
            openFragment(estoqueFragment);
        }else{
            Toast.makeText(this, "permissões negadas", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.navigation_estoque:{
                DadosPreference dados = new DadosPreference();
                dados.dadosOp(getApplicationContext(), "OP1", "01");
                Fragment estoqueFragment = EstoqueFragment.newInstance();
                openFragment(estoqueFragment);
                break;
            }
            case R.id.navigation_financeiro:{
                DadosPreference dados = new DadosPreference();
                dados.dadosOp(getApplicationContext(), "OP1", "02");
                Fragment financeiroFragment = FinanceiroFragment.newInstance();
                openFragment(financeiroFragment);
               break;
            }


        }
        Log.d("criou UI", "Criou UI");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id = item.getItemId();

       if(id==R.id.config_menu){
           DialogFragment config = new ConfiguracaoDialog();
           config.show(getSupportFragmentManager(), "teste");
            return true;
       }
       return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu = (Menu) findViewById(R.id.config_menu);


        return true;

    }


    private void openFragment(Fragment fragment) {

     if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
       }
    }
    public Boolean permissoes(){
        List<String> permissoesRequeridas = new ArrayList();
        for(String permissao : appPermission){
            if(ContextCompat.checkSelfPermission(this, permissao)
                    != PackageManager.PERMISSION_GRANTED){
                permissoesRequeridas.add(permissao);
            }
        }

        if(!permissoesRequeridas.isEmpty()){
            ActivityCompat.requestPermissions(this, permissoesRequeridas.toArray(new
                    String[permissoesRequeridas.size()]), CODIGO_PERMISSOES_REQUERIDAS);
            return false;
        }
        return true;
    }
}



