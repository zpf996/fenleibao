package online.zpf666.fenleibao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class login_registered_Activity extends AppCompatActivity {

    @BindView(R.id.login_registered_button_qrzc)
    ImageView login_registered_button_qrzc;

    @BindView(R.id.login_1_edittext_yh)
    EditText etusername;

    @BindView(R.id.login_1_edittext_mm)
    EditText etpasswd;

    @BindView(R.id.login_registered_textview_qrmm)
    EditText etqrpasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_registered_);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.login_registered_button_qrzc})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.login_registered_button_qrzc:
                String username=etusername.getText().toString();
                String passwd=etpasswd.getText().toString();
                String qrpasswd=etqrpasswd.getText().toString();

                //数据验证
                if( TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                //两次密码输入一致
                else if(TextUtils.isEmpty(passwd)){
                    Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!passwd.equals(qrpasswd)){
                    Toast.makeText(getApplicationContext(),"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    return;
                }

                break;
        }
    }
}
