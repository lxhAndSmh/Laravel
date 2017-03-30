package com.liu.laravel.ui.main;

import android.content.Intent;
import android.net.Uri;
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

import com.facebook.drawee.view.SimpleDraweeView;
import com.liu.laravel.R;
import com.liu.laravel.bean.user.User;
import com.liu.laravel.bean.user.UserInfo;
import com.liu.laravel.global.Constants;
import com.liu.laravel.global.UserConstant;
import com.liu.laravel.ui.login.LoginActivity;
import com.liu.laravel.ui.topic.list.TopicListFragment;
import com.liu.laravel.util.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE = 0x10;

    private RelativeLayout rlUser;
    private TextView tvName;
    private TextView tvEmail;
    private TextView tvLogin;
    private ImageView imageLogout;
    private SimpleDraweeView imageAvater;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation)
    NavigationView navigationView;
    @BindView(R.id.activity_drawer)
    DrawerLayout drawerLayout;

    private TopicListFragment mFragment;

    private User user;

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
        imageAvater = (SimpleDraweeView) view.findViewById(R.id.drawer_header_avatar);
        imageLogout = (ImageView) view.findViewById(R.id.drawer_header_logout);
        tvLogin.setOnClickListener(this);
        imageLogout.setOnClickListener(this);
        toolbar.setNavigationIcon(R.mipmap.ic_actionbar_menu);
        toolbar.setTitle("精华");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(this);
    }

    private void initData(){

        if(UserConstant.newInstance(this).isLogin()){
            user = UserConstant.newInstance(this).getUserData();
            imageAvater.setImageURI(user.getAvatar());
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());

            rlUser.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
        }

        mFragment = new TopicListFragment();
        mFragment.TYPE = Constants.Topic.EXCELLENT;
        toFragment();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_best:
                        switchFragment("精华", Constants.Topic.EXCELLENT);
                        break;
                    case R.id.drawer_vote:
                        switchFragment("投票", Constants.Topic.VOTE);
                        break;
                    case R.id.drawer_newest:
                        switchFragment("最近", Constants.Topic.NEWEST);
                        break;
                    case R.id.drawer_nobody:
                        switchFragment("零回复", Constants.Topic.NOBODY);
                        break;
                    case R.id.drawer_work:
                        switchFragment("酷工作", Constants.Topic.JOBS);
                        break;
                    case R.id.drawer_wiki:
                        switchFragment("社区WIKI", Constants.Topic.WIKI);
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
                startActivityForResult(LoginActivity.newIntent(MainActivity.this), REQUEST_CODE);
                break;
            case R.id.drawer_header_logout:
                UserConstant.newInstance(this).logout();
                imageAvater.setImageURI(Uri.parse("res://com.liu.laravel/" + R.color.main_bg));

                rlUser.setVisibility(View.GONE);
                tvLogin.setVisibility(View.VISIBLE);
                ToastUtils.showShortTomast(MainActivity.this, "退出成功");
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

    private void switchFragment(String title, String type){
        toolbar.setTitle(title);
        mFragment.TYPE = type;
        toFragment();
    }

    private void toFragment(){
        if(mFragment.isAdded()){
            mFragment.getData();
        }else {
            getSupportFragmentManager().beginTransaction().add(R.id.container, mFragment).commit();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            UserInfo userInfo = (UserInfo) data.getExtras().getSerializable(Constants.Key.USER_DATA);
            User user = userInfo.getData();
            imageAvater.setImageURI(user.getAvatar());
            tvName.setText(user.getName());
            tvEmail.setText(user.getEmail());

            rlUser.setVisibility(View.VISIBLE);
            tvLogin.setVisibility(View.GONE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 权限操作回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
