package com.liu.laravel.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.liu.laravel.R;
import com.liu.laravel.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlUser;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvLogin;
    private ImageView imageLogout;
    private ImageView imageAvater;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigationView;
    @BindView(R.id.activity_drawer)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initView(){
        View view = navigationView.getHeaderView(0);
        rlUser = (RelativeLayout) view.findViewById(R.id.drawer_header_rl);
        tvName = (TextView) view.findViewById(R.id.drawer_header_name);
        tvEmail = (TextView) view.findViewById(R.id.drawer_header_email);
        tvLogin = (TextView) view.findViewById(R.id.drawer_header_login);
        imageAvater = (ImageView) view.findViewById(R.id.drawer_header_avatar);
        imageLogout = (ImageView) view.findViewById(R.id.drawer_header_logout);
        tvLogin.setOnClickListener(this);
        imageLogout.setOnClickListener(this);
        toolbar.setNavigationIcon(R.mipmap.ic_actionbar_menu);
        toolbar.setTitle("Laravel");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);
    }

    private void initData(){
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_best:
                        ToastUtils.showShortTomast(MainActivity.this, "精华");
                        break;
                    case R.id.drawer_vote:
                        ToastUtils.showShortTomast(MainActivity.this, "投票");
                        break;
                    case R.id.drawer_newest:
                        ToastUtils.showShortTomast(MainActivity.this, "最近");
                        break;
                    case R.id.drawer_nobody:
                        ToastUtils.showShortTomast(MainActivity.this, "零回复");
                        break;
                    case R.id.drawer_work:
                        ToastUtils.showShortTomast(MainActivity.this, "酷工作");
                        break;
                    case R.id.drawer_wiki:
                        ToastUtils.showShortTomast(MainActivity.this, "社区WIKI");
                        break;
                    case R.id.drawer_theme:
                        ToastUtils.showShortTomast(MainActivity.this, "切换主题");
                        break;
                    case R.id.drawer_help:
                        ToastUtils.showShortTomast(MainActivity.this, "登录帮助");
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.drawer_header_login:
                ToastUtils.showShortTomast(MainActivity.this, "登录");
                break;
            case R.id.drawer_header_logout:
                ToastUtils.showShortTomast(MainActivity.this, "登出");
                break;
            default:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_setting:
                ToastUtils.showShortTomast(MainActivity.this, "设置");
                break;
            case R.id.main_about:
                ToastUtils.showShortTomast(MainActivity.this, "关于");
                break;
        }
        return true;
    }
}
