package br.usjt.ucsist.cadbasico.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import br.usjt.ucsist.cadbasico.R;
import br.usjt.ucsist.cadbasico.model.Contato;
import br.usjt.ucsist.cadbasico.model.ContatoViewModel;
import br.usjt.ucsist.cadbasico.model.Usuario;
import br.usjt.ucsist.cadbasico.model.UsuarioViewModel;

import static android.app.Activity.RESULT_OK;


public class ContatoFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ContatoViewModel contatoViewModel;
    private Contato contatoCorrente;
    private EditText editTextNome;
    private EditText editTextEmail;
    private EditText editTextTelefone;
    private Button buttonSalvar;
    private ImageView fotoContato;
    private TextView linkContato;


    private String mParam1;
    private Contato mParam2;

    public ContatoFragment() {
        // Required empty public constructor
    }


    public static ContatoFragment newInstance(String param1, Contato param2) {
        ContatoFragment fragment = new ContatoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putSerializable(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = (Contato)getArguments().getSerializable(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_contato, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        editTextNome = view.findViewById(R.id.editTextNomeC);
        editTextEmail = view.findViewById(R.id.editTextEmailC);
        editTextTelefone = view.findViewById(R.id.editTextTelefoneC);
        fotoContato = view.findViewById(R.id.fotoContato);
        linkContato = view.findViewById(R.id.linkContato);
        linkContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        buttonSalvar= view.findViewById(R.id.buttonSalvarC);
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });


        contatoViewModel = new ViewModelProvider(this).get(ContatoViewModel.class);

        contatoViewModel.getSalvoSucesso().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable final Boolean sucesso) {
                if(sucesso){
                    Toast.makeText(getActivity(),"Contato salvo com sucesso",
                            Toast.LENGTH_SHORT).show();
                    limparCampos();
                }else{
                    Toast.makeText(getActivity(),"Falha ao salvar o contato!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(mParam2!=null){
            contatoCorrente = mParam2;
            editTextNome.setText(contatoCorrente.getNome());
            editTextEmail.setText(contatoCorrente.getEmail());
            editTextTelefone.setText(contatoCorrente.getTelefone());
        }


    }

    public void tirarFoto(){
        dispatchTakePictureIntent();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            fotoContato.setImageBitmap(imageBitmap);
            //contatoCorrente.setImagem(ImageUtil.encode(imageBitmap));
            //Log.d("IMAGEMBITMAPENCODED-->",contatoCorrente.getImagem());
        }

    }


    public void salvar() {

        if(contatoCorrente == null){
            contatoCorrente = new Contato();
        }
        if(validarCampos()){
            contatoCorrente.setNome(editTextNome.getText().toString());
            contatoCorrente.setEmail(editTextEmail.getText().toString());
            contatoCorrente.setTelefone(editTextTelefone.getText().toString());

            if(mParam2==null){
                contatoViewModel.salvarContato(contatoCorrente);
            }else{
                contatoViewModel.alterarContato(contatoCorrente);
            }


        }


    }

    private void limparCampos(){
        editTextNome.setText("");
        editTextEmail.setText("");
        editTextTelefone.setText("");
    }

    public boolean validarCampos(){

        boolean valido = true;
        if(editTextNome.getText().toString().trim().length()==0){
            valido = false;
            Toast.makeText(getActivity(),"Nome inválido!",
                    Toast.LENGTH_SHORT).show();
        }
        if(editTextEmail.getText().toString().trim().length()==0){
            valido = false;
            Toast.makeText(getActivity(),"Email inválido!",
                    Toast.LENGTH_SHORT).show();
        }
        if(editTextTelefone.getText().toString().trim().length()==0){
            valido = false;
            Toast.makeText(getActivity(),"Telefone inválido!",
                    Toast.LENGTH_SHORT).show();
        }
        return valido;

    }


}