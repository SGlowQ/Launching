package com.appshare.android.ilisten.watch.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.appshare.android.ilisten.watch.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private static final String CORRECT_PASSWORD = "dgyzmyxzhkybystzymzxwgywyjhlkycdxzcyyjmmyyzzxzyylj";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        EditText passwordInput = binding.passwordInput;
        Button submitButton = binding.submitButton;

        // 直接设置按钮的文本
        submitButton.setText("默写完了点击进入设置");

        submitButton.setOnClickListener(v -> {
            String inputPassword = passwordInput.getText().toString();
            if (inputPassword.equals(CORRECT_PASSWORD)) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}