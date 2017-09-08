package com.weiren.reserve;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
    WebView web_view;
    private String url = "http://192.168.0.11:8080/yuyue/f/reserve/index";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web_view = (WebView) findViewById(R.id.webView);
       initView();
        web_view.loadUrl(url);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }



    private void initView() {
        WebSettings setting = web_view.getSettings();
        setting.setJavaScriptEnabled(true);//支持Js
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);//缓存模式
        //是否支持画面缩放，默认不支持
        setting.setBuiltInZoomControls(true);
        setting.setSupportZoom(true);
        //是否显示缩放图标，默认显示
        setting.setDisplayZoomControls(false);
        //设置网页内容自适应屏幕大小
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            web_view.goBack();
            return true;
            //showExitDialog();
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showExitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("预约挂号平台");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setMessage("是否退出当前应用？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());

            }
        }).setNegativeButton("取消", null);

        builder.show();

    }
}
