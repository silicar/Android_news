package com.cs.news1.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.adapter.MyAdapter;
import com.cs.news1.base.BaseActivity;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.databinding.ActivityMainBinding;
import com.cs.news1.databinding.DrawHeaderBinding;
import com.cs.news1.databinding.SheetDialogBinding;
import com.cs.news1.fragment.TabCook;
import com.cs.news1.fragment.TabJoke;
import com.cs.news1.fragment.TabNews;
import com.cs.news1.fragment.TabPhoto;
import com.cs.news1.fragment.TabRxjava;
import com.cs.news1.fragment.TabVideo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private ActionBarDrawerToggle mDrawerToggle;//这个是箭头
    private List<BaseFragment> mList_fm;
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        // getSupportActionBar().setHomeButtonEnabled(true);  //设置返回键可用
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true); //创建返回键，并实现打开关/闭监听

        binding.tlMain.setTitle("标题");
        //初始化ToolBar菜单
        binding.tlMain.inflateMenu(R.menu.menu_main);

        //添加抽屉导航栏头部
        DrawHeaderBinding headerBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.draw_header, binding.nvMain, false);
        headerBinding.tvHeaderContent.setText("安卓开发高手联盟");
        binding.nvMain.addHeaderView(headerBinding.getRoot());

        //初始化抽屉滑动ToolBar左上角图标动画效果，和标题变化
        mDrawerToggle = new ActionBarDrawerToggle(this, binding.dlMain, binding.tlMain, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // binding.tlMain.setVisibility(View.GONE);
                binding.tlMain.setTitle("");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // binding.tlMain.setVisibility(View.VISIBLE);
                binding.tlMain.setTitle("标题");
            }
        };
        mDrawerToggle.syncState();  //初始化状态
        binding.dlMain.addDrawerListener(mDrawerToggle); //将DrawerLayout与DrawerToggle绑定

        //初始化Fragment
        mList_fm = new ArrayList<>();
        mList_fm.add(new TabNews().setTitle("新闻"));
        mList_fm.add(new TabJoke().setTitle("笑话"));
        mList_fm.add(new TabPhoto().setTitle("图片"));
        mList_fm.add(new TabVideo().setTitle("视频"));
        mList_fm.add(new TabCook().setTitle("食物"));
        mList_fm.add(new TabRxjava().setTitle("RxJava"));

        mAdapter = new MyAdapter(getSupportFragmentManager(), mList_fm);
        binding.vpMain.setAdapter(mAdapter);

        // 初始化Tab
        // binding.tabMain.setTabMode(TabLayout.MODE_SCROLLABLE);
        binding.tabMain.setupWithViewPager(binding.vpMain);
        binding.tabMain.setTabsFromPagerAdapter(mAdapter);//去掉后标签点击的时候就不行了。

    }

    @Override
    protected void initListener(Bundle savedInstanceState) {
        //抽屉导航栏事件监听
        binding.nvMain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item1:
                        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_item2:
                        Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_item3:
                        Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_sub_item1:
                        Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_sub_item2:
                        Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_sub_item3:
                        Toast.makeText(MainActivity.this, "6", Toast.LENGTH_SHORT).show();
                        return true;


                }

                return true;
            }
        });

        //菜单事件监听
        binding.tlMain.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_bottosheetDialog:
                        showBottomSheetDialog();
                        break;
                    case R.id.action_about:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
                        startActivity(intent);
                        break;
                }

                return true;
            }
        });

    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        SheetDialogBinding sheetDialogBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.sheet_dialog, null, false);
        mBottomSheetDialog.setContentView(sheetDialogBinding.getRoot());
        sheetDialogBinding.dialogItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog ad = new AlertDialog.Builder(MainActivity.this).create();
                ad.setTitle("AlertDialog");
                ad.setIcon(R.mipmap.icon_touxiang);
                ad.setMessage("我是消息内容");
                ad.setButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mBottomSheetDialog.dismiss();

                    }
                });
                ad.setButton2("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.show();


            }
        });
        sheetDialogBinding.dialogItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "你点击了2", Toast.LENGTH_SHORT).show();
            }
        });
        sheetDialogBinding.dialogItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "你点击了3", Toast.LENGTH_SHORT).show();

            }
        });

        mBottomSheetDialog.show();
    }
}
