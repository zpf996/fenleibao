package online.zpf666.fenleibao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import online.zpf666.fenleibao.db.MyOpenHelper;
import online.zpf666.fenleibao.db.UserDb;
import online.zpf666.fenleibao.bean.user;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class login_1_Activity extends AppCompatActivity implements View.OnClickListener {

    //定义组件
    @BindView(R.id.login_1_button_dll)
    ImageView pass;

    @BindView(R.id.login_1_text_wjmm)
    ImageView login_1_text_wjmm;

    @BindView(R.id.login_1_text_zczh)
    ImageView login_1_text_zczh;

    @BindView(R.id.login_1_edittext_yh)
    EditText etusername;

    @BindView(R.id.login_1_edittext_mm)
    EditText etpasswd;
    UserDb user1;
    user user;

MyOpenHelper myOpenHelper;
SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login_1_);

        ButterKnife.bind(this);
        myOpenHelper=new MyOpenHelper(login_1_Activity.this);
        db=myOpenHelper.getReadableDatabase();
    }
    @OnClick({R.id.login_1_button_dll,R.id.login_1_text_wjmm,R.id.login_1_text_zczh})
    public void onClick(View view)
    {
        switch(view.getId()){
            case R.id.login_1_text_zczh:
                Intent i1=new Intent(login_1_Activity.this, login_registered_Activity.class);
                startActivity(i1);
                break;
            case R.id.login_1_text_wjmm:
                Intent intent=new Intent(login_1_Activity.this,login_forget_Activity.class);
                startActivity(intent);
                break;
            case R.id.login_1_button_dll:
                String username=etusername.getText().toString();
                String passwd=etpasswd.getText().toString();

                if( TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(),"请输入用户名",Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断是否输入密码
                else if(TextUtils.isEmpty(passwd)){
                    Toast.makeText(getApplicationContext(),"请输入密码",Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(Logined(username,passwd))
                {
                    Toast.makeText(getApplicationContext(),"登陆成功，即将跳转",Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent(login_1_Activity.this,index_Activity.class);
                    startActivity(intent2);
                }else
                {
                    Toast.makeText(getApplicationContext(),"密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
    public boolean Logined(String username,String passwd)
    {

        Cursor cursor =db.rawQuery("select * from user where username=? and password=?",new String[]{ username,passwd });
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
}
