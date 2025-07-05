package com.appshare.android.ilisten.watch.ui.gallery;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.appshare.android.ilisten.watch.R;
import com.appshare.android.ilisten.watch.databinding.FragmentGalleryBinding;
import com.google.android.material.snackbar.Snackbar;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private View rootView; // 保存根视图引用

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        rootView = binding.getRoot(); // 初始化根视图

        // 添加按钮点击事件处理
        setupButtonListeners();

        return rootView;
    }

    private void setupButtonListeners() {
        // 获取按钮引用
        Button btnWeather = binding.btnWeather;
        Button btnFileManager = binding.btnFileManager;
        Button btnFirFox = binding.btnFirFox;
        Button btnCalc = binding.btnCalc;
        Button btnNovel = binding.btnNovel;

        // 设置按钮点击事件
        btnWeather.setOnClickListener(v -> openApp("org.breezyweather", "天气"));
        btnFileManager.setOnClickListener(v -> openApp("com.alphainventor.filemanager", "文件管理器"));
        btnFirFox.setOnClickListener(v -> openApp("org.mozilla.firefox", "Firefox浏览器"));
        btnCalc.setOnClickListener(v -> openApp("advanced.scientific.calculator.calc991.plus", "计算器"));
        btnNovel.setOnClickListener(v -> openApp("com.dragon.read", "番茄小说"));
    }

    // 统一的应用启动方法
    @SuppressLint("QueryPermissionsNeeded")
    private void openApp(String packageName, String appName) {
        try {
            Intent intent = requireActivity().getPackageManager()
                    .getLaunchIntentForPackage(packageName);

            if (intent != null) {
                startActivity(intent);
            } else {
                // 直接在代码中构造消息，不依赖资源文件
                String message = "未找到" + appName + "应用，请检查是否已安装";
                Snackbar.make(rootView, message, Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .show();
            }
        } catch (Exception e) {
            // 处理异常情况
            String errorMessage = "打开应用时出错: " + e.getMessage();
            Snackbar.make(rootView, errorMessage, Snackbar.LENGTH_LONG)
                    .setAnchorView(R.id.fab)
                    .show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        rootView = null;
    }
}