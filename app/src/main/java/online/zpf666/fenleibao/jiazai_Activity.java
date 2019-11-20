package online.zpf666.fenleibao;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import online.zpf666.fenleibao.db.MyOpenHelper;


public class jiazai_Activity extends AppCompatActivity {

    MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_jiazai_);

        myOpenHelper=new MyOpenHelper(this,"fengleibao.db",null,1);
        //新建一个子线程
        Thread thread =new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);//程序休眠三秒后启动MainActivity
                    Intent intent=new Intent(getApplicationContext(),login_1_Activity.class);
                    startActivity(intent);
                    finish();//关闭当前活动，否则按返回键还能回到启动画面
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();//启动线程
    }
}
