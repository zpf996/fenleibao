package online.zpf666.fenleibao;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import online.zpf666.fenleibao.Fragment.fragment_me;
import online.zpf666.fenleibao.Fragment.fragment_soso;
import online.zpf666.fenleibao.Fragment.fragment_yuyue;

public class index_Activity extends AppCompatActivity {
    private online.zpf666.fenleibao.Fragment.fragment_me fragment_me;
    private online.zpf666.fenleibao.Fragment.fragment_soso fragment_soso;
    private online.zpf666.fenleibao.Fragment.fragment_yuyue fragment_yuyue;
    private Fragment[] fragments;
    private int lastfragment=0;  //显示最后一个fragment

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_me:
                    if (lastfragment != 0) {
                        switchFraments(lastfragment, 0);
                        lastfragment = 0;
                    }
                    return true;
                case R.id.navigation_soso:
                    if (lastfragment != 1) {
                        switchFraments(lastfragment, 1);
                        lastfragment = 1;
                    }
                    return true;
                case R.id.navigation_yuyue:
                    if (lastfragment != 2) {
                        switchFraments(lastfragment, 2);
                        lastfragment = 2;
                    }
                    return true;
            }
            return false;
        }
    };

    /**
     * 切换Fragment
     *
     * @param lastI 上个显示Fragment的索引
     * @param i    需要显示的Fragment的索引
     */
    private void switchFraments(int lastI, int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastI]);//隐藏上一个fragment
        if (!fragments[i].isAdded()) {
            transaction.add(R.id.container, fragments[i]);
        }
        transaction.show(fragments[i]).commitAllowingStateLoss();

    }

    /**
     * 初始化了3个fragment和第一个显示的fragment
     */
    private void initFragments() {
        fragment_me = new fragment_me();
        fragment_soso = new fragment_soso();
        fragment_yuyue=new fragment_yuyue();
        fragments = new Fragment[]{fragment_soso,fragment_me,fragment_yuyue};
        lastfragment = 0;
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment_soso)
                .show(fragment_soso)
                .commit();

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        ButterKnife.bind(this);
        initFragments();
    }

}
