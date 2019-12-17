package online.zpf666.fenleibao.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import online.zpf666.fenleibao.jiazai_Activity;
import online.zpf666.fenleibao.login_1_Activity;

//创建数据库，不是操作类
public class MyOpenHelper extends SQLiteOpenHelper {
    private static final String name="fenlei";      //数据库名称
    private static final int version=1;                //版本号

    //构造函数
    public MyOpenHelper(Context context){
        super(context,name,null,version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建表
        sqLiteDatabase.execSQL("create table user(id integer primary key autoincrement,username char(8),password char(8))");
        //sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS" +
         //       "Registered(id INTEGER primary key autoincrement,)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
