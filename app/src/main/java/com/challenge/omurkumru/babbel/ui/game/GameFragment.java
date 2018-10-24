package com.challenge.omurkumru.babbel.ui.game;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.challenge.omurkumru.babbel.Application;
import com.challenge.omurkumru.babbel.R;
import com.challenge.omurkumru.babbel.di.AppComponent;
import com.challenge.omurkumru.babbel.model.Word;
import com.challenge.omurkumru.babbel.utils.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class GameFragment extends Fragment {

    GameViewModel viewModel;

    @Inject
    ViewModelFactory mViewModelFactory;

    List<Word> wordList;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Inject the current activity inside Dagger 2 dependency graph
        AppComponent applicationComponent = ((Application)getActivity().getApplication()).getAppComponent();
        applicationComponent.inject(this);

        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(GameViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Game");

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            wordList = bundle.getParcelableArrayList("wordsArray");
        }

        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

    }
}
