package oriol.ivan.com.enserieproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class AuthActivity extends AppCompatActivity {
    Button crear_cuenta, entrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_auth);

        crear_cuenta = (Button) findViewById(R.id.crear_cuenta);
        entrar = (Button) findViewById(R.id.entrar_btn);

        crear_cuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(AuthActivity.this, CrearCuentaActivity.class);
                startActivity(intent);

            }
        });

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent().setClass(AuthActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
    }
}
