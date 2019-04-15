package com.mocha.hellocordova;

import android.os.Bundle;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mocha.hellocordova.fragment.IdleMoudle;
import com.mocha.hellocordova.fragment.MyMoudle;
import com.mocha.hellocordova.fragment.TotleMoudle;

public class TabhostActivity extends FragmentActivity implements View.OnClickListener {
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;

    private RadioGroup rGroup;

    private Fragment f1,f2,f3;
    private FragmentManager manager;
    private FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        /**
         * 拿到事务管理器并开启事务
         */
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        /**
         * 初始化按钮
         */
        rb1 = (RadioButton) findViewById(R.id.rb_1);
        rb2 = (RadioButton) findViewById(R.id.rb_2);
        rb3 = (RadioButton) findViewById(R.id.rb_3);

        rGroup = (RadioGroup) findViewById(R.id.rg);

        /**
         * 为三个按钮添加监听
         */
        rb1.setOnClickListener(this);
        rb2.setOnClickListener(this);
        rb3.setOnClickListener(this);

        /**
         * 启动默认选中第一个
         */
        rGroup.check(R.id.rb_1);
        f1 = new MyMoudle();
        transaction.replace(R.id.fl_content, f1);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        switch (v.getId()) {
            case R.id.rb_1 :
                /**
                 * 为了防止重叠，需要点击之前先移除其他Fragment
                 */
                hideFragment(transaction);
                f1 = new MyMoudle();
                transaction.replace(R.id.fl_content, f1);
                transaction.commit();

                break;
            case R.id.rb_2 :
                hideFragment(transaction);
                f2 = new TotleMoudle();
                transaction.replace(R.id.fl_content, f2);
                transaction.commit();

                break;
            case R.id.rb_3 :
                hideFragment(transaction);
                f3 = new IdleMoudle();
                transaction.replace(R.id.fl_content, f3);
                transaction.commit();
                break;

            default :
                break;
        }
    }

    /*
     * 去除（隐藏）所有的Fragment
     * */
    private void hideFragment(FragmentTransaction transaction) {
        if (f1 != null) {
            //transaction.hide(f1);隐藏方法也可以实现同样的效果，不过我一般使用去除
            transaction.remove(f1);
        }
        if (f2 != null) {
            //transaction.hide(f2);
            transaction.remove(f2);
        }
        if (f3 != null) {
            //transaction.hide(f3);
            transaction.remove(f3);
        }


    }

}
