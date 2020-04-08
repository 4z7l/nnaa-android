package com.mashup.nnaa.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mashup.nnaa.R;
import com.mashup.nnaa.network.RetrofitHelper;
import com.mashup.nnaa.network.model.QuestionnaireDto;
import com.mashup.nnaa.util.AccountManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainHomeFragment extends Fragment {
    private RecyclerView rvQuestionnaires;
    private TextView tvWelcome;

    public static MainHomeFragment newInstance() {
        MainHomeFragment fragment = new MainHomeFragment();

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);

        // main questionnaires recycler view init
        rvQuestionnaires = view.findViewById(R.id.rv_questionnaires);
        rvQuestionnaires.setLayoutManager(
                new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        rvQuestionnaires.setAdapter(new MainQuestionnaireAdapter());

        tvWelcome = view.findViewById(R.id.tv_welcome);

        String userName = AccountManager.getInstance().getUserAuthHeaderInfo().getName();

    /*    Bundle kakao_bundle = this.getArguments();
        if (kakao_bundle != null) {
            userName = kakao_bundle.getString("kakao");
        }
        Bundle facebook_bundle = this.getArguments();
        if (facebook_bundle != null) {
            userName = facebook_bundle.getString("facebook");
        }*/
        Bundle login_bundle = this.getArguments();
        if (login_bundle != null) {
            userName = login_bundle.getString("name");
        }

        tvWelcome.setText(Html.fromHtml(getString(R.string.main_welcome, userName)));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        requestReceivedQuestionnaires();

    }


    private void requestReceivedQuestionnaires() {
        RetrofitHelper.getInstance().getReceivedQuestionnaire(new Callback<List<QuestionnaireDto>>() {
            @Override
            public void onResponse(Call<List<QuestionnaireDto>> call, Response<List<QuestionnaireDto>> response) {
                List<QuestionnaireDto> questionnaires = response.body();
                showReceivedQuestionnaires(questionnaires);
            }

            @Override
            public void onFailure(Call<List<QuestionnaireDto>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail to get data!\n" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showReceivedQuestionnaires(List<QuestionnaireDto> questionnaires) {

    }
}
