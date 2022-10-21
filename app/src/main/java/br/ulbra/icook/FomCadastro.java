package br.ulbra.icook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import de.hdodenhof.circleimageview.CircleImageView;
public class FomCadastro extends AppCompatActivity {

    private CircleImageView fotoUsuario;
    private Button btSelecionarFoto, btCadastrar;
    private EditText edtNome, edtEmail, edtSenha;
    private TextView txtMensagemErro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fom_cadastro);
        IniciarComponentes();
        edtNome.addTextChangedListener(cadastroTextWatcher);
        edtEmail.addTextChangedListener(cadastroTextWatcher);
        edtSenha.addTextChangedListener(cadastroTextWatcher);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CadastrarUsuario(v);



            }
        });
    }

    public void CadastrarUsuario(View view) {
        String email = edtEmail.getText().toString();
        String senha = edtSenha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    Snackbar snackbar = Snackbar.make(view, "Cadastro realizado com sucesso!", Snackbar.LENGTH_INDEFINITE)
                            .setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    finish();
                                }
                            });
                    snackbar.show();
                } else {

                    String erro;

                    try {
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erro = "Coloque uma senha com no minimo 6 caracteres!";

                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "E-mail Invalido";

                    } catch (FirebaseAuthUserCollisionException e) {
                        erro = "Esta conta já foi criada!";

                    } catch (FirebaseNetworkException e) {
                        erro = "sem conexão com a Internet!";
                    } catch (Exception e) {

                        erro = "Erro ao cadastra o usúario!";
                    }
                    txtMensagemErro.setText(erro);
                }
            }
        });
    }

    public void IniciarComponentes() {
        fotoUsuario = findViewById(R.id.fotoUsuario);
        btSelecionarFoto = findViewById(R.id.btnCadastrar);
        edtNome = findViewById(R.id.edtNomeCad);
        edtEmail = findViewById(R.id.edtEmailCad);
        edtSenha = findViewById(R.id.edtSenhaCad);
        txtMensagemErro = findViewById(R.id.txt_mensagemErro);
        btCadastrar = findViewById(R.id.btnCadastrar);
    }

    TextWatcher cadastroTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();
            String senha = edtSenha.getText().toString();

            if (!nome.isEmpty() && !email.isEmpty() && !senha.isEmpty()) {
                btCadastrar.setEnabled(true);
                btCadastrar.setBackgroundColor(getResources().getColor(R.color.orange));
            } else {
                btCadastrar.setEnabled(false);
                btCadastrar.setBackgroundColor(getResources().getColor(R.color.white));
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}