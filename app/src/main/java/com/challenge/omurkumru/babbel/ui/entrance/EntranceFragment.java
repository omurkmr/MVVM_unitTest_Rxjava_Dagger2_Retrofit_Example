package com.challenge.omurkumru.babbel.ui.entrance;

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
import com.challenge.omurkumru.babbel.utils.ViewModelFactory;


import javax.inject.Inject;

import butterknife.OnClick;


public class EntranceFragment extends Fragment {

    EntranceViewModel viewModel;

    @Inject
    ViewModelFactory mViewModelFactory;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Inject the current activity inside Dagger 2 dependency graph
        AppComponent applicationComponent = ((Application) getActivity().getApplication()).getAppComponent();
        applicationComponent.inject(this);

        viewModel = ViewModelProviders.of(this, mViewModelFactory).get(EntranceViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Enterence");

        return inflater.inflate(R.layout.fragment_entrance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {



    }

    @OnClick(R.id.start_game_button)
    public void startGameButtonTap(View view) {


    }

    @OnClick(R.id.quit_game_button)
    public void quitGameButtonTap(View view) {

    }
}