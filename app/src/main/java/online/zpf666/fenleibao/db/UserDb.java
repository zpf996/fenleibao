package online.zpf666.fenleibao.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import online.zpf666.fenleibao.db.MyOpenHelper;
import online.zpf666.fenleibao.bean.user;
//数据库的操作类,增删该查功能实现
public class UserDb {
    MyOpenHelper myopenhelper;
     SQLiteDatabase db;
    user user;
    //构造函数
    public UserDb(SQLiteDatabase db){
        this.db=db;
    }
    //insert方法
    public boolean insertUser(String id,String passwd){
        long insertResult=0;
        //判断user表是否为空
        if (!CheckIsDataAlreadyInDBorNot(id)){
            return false;
        }
        else {

            ContentValues contentValues = new ContentValues();
 //           contentValues.put("name", user.getName());
            contentValues.put("id",id);
 //           contentValues.put("phone", user.getPhone());
            contentValues.put("passwd", passwd);
            insertResult = db.insert("User", null, contentValues);
        }
        if (insertResult==-1){
            return false;
        }
        Log.e("registered","is true");
        return true;
    }
    //删除方法
    public boolean delete(user user){
        if (user==null){
            Log.e("my sqlite","user is null");
            return false;
        }
        //参数：表名，属性名？表示占位符，属性名的属性值
        int deleteResult=db.delete("user","id=?",
                new String[]{user.getId()+""});
        if (deleteResult==0){
            Log.e("my sqlite","delete is failure");
            return false;
        }
        else
            return true;
    }
    //查询操作
    public user query(user user) {
        user result = new user();
        //判断非空
        if (user == null) {
            Log.e("my sqlite", "user is null");
            return null;
        }
        /**
         * cursor是返回数据集的指针
         * 它支持在查询的数据集合中以各种方式移动
         */

        /**
         * query方法
         * 参数：
         * 要查询的表，
         * 要查询的列（不填就查寻所有列），
         * 查询条件，
         * 查询条件对应的值，
         * groupby（分组语句）
         * having（分组条件）
         * orderby （排序方式）
         */
        Cursor cursor = db.query("user", null, "id=?",
                new String[]{user.getId() + ""}, null, null, null);
        while (cursor.moveToNext()) {
            result.setId(cursor.getInt(2));
     //       result.setName(cursor.getString(4));
     //       result.setPhone(cursor.getInt(11));
        }
        return result;
    }
    //查询用户是否存在
    public boolean CheckIsDataAlreadyInDBorNot(String v)
    {
        Cursor cursor =db.rawQuery("select * from Login where id=?",new String[]{ v });
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;

    }
    //注册操作
    public boolean registered(String username,String passwd)
    {
        db.execSQL("insert into user(username,passwd) values (?,?)", new Object[] { username, passwd});
        return true;
    }
    //登陆操作
    public boolean Logined(String username,String passwd)
    {

        Cursor cursor =db.rawQuery("select * from Login where id=? and passwd=?",new String[]{ username,passwd });
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
    //忘记密码

}
