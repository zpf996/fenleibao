package online.zpf666.fenleibao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import online.zpf666.fenleibao.db.MyOpenHelper;

public class login_forget_Activity extends AppCompatActivity {

    //绑定事件
    @BindView(R.id.login_Forget_edittext_yh)
    EditText etusername;

    @BindView(R.id.login_Forget_edittext_yzm)
    EditText etauthcode;

    @BindView(R.id.login_Forget_edittext_xmm)
    EditText etpasswd;

    @BindView(R.id.login_Forget_edittext_qrmm)
    EditText etfpasswd;

    @BindView(R.id.login_Forget_button_qrfs)
    ImageView  login_Forget_button_qrfs;

    @BindView(R.id.login_Forget_button_qrxg)
    ImageView  login_Forget_button_qrxg;

    MyOpenHelper myOpenHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_forget_);
        ButterKnife.bind(this);
        myOpenHelper=new MyOpenHelper(this);
        db=myOpenHelper.getReadableDatabase();
    }
    @OnClick({R.id.login_Forget_button_qrfs,R.id.login_Forget_button_qrxg})
    public void OnClick(View v){
        String username=etusername.getText().toString();//用户名
        String passwd=etpasswd.getText().toString();//密码
        String qrpasswd=etfpasswd.getText().toString();//再次输入密码
        String authcode=etauthcode.getText().toString();//验证码
        switch (v.getId()){
            case R.id.login_Forget_button_qrfs:
                if( TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    Toast.makeText(getApplicationContext(),"123456",Toast.LENGTH_SHORT).show();
                    break;
            case R.id.login_Forget_button_qrxg:
                if( TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断密码是否为空
                else if(TextUtils.isEmpty(passwd)){
                    Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                //两次密码输入一致
                else if(!passwd.equals(qrpasswd)){
                    Toast.makeText(getApplicationContext(),"两次密码输入不一致",Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断验证码是否为空
                else if(TextUtils.isEmpty(authcode)) {
                    Toast.makeText(getApplicationContext(), "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(!authcode.equals("123456")) {
                    Toast.makeText(getApplicationContext(), "验证码输入错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(forgetpasswd(username,passwd)){
                    Toast.makeText(getApplicationContext(), "密码已修改", Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent(login_forget_Activity.this,login_1_Activity.class);
                    startActivity(intent2);
                }
                break;
        }
    }
    public boolean OnKeyDown(int keyCode, KeyEvent event){
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
        Intent intent=new Intent();
    intent.setClass(login_forget_Activity.this, login_1_Activity.class);
    startActivity(intent);
            login_forget_Activity.this.finish();
    }
    return false;
    }
    public boolean forgetpasswd(String username,String passwd)
    {
        ContentValues values=new ContentValues();
        values.put("password",passwd);
        db.update("user",values,"username=?",new String[]{username});
        db.close();
        return false;
    }
}
