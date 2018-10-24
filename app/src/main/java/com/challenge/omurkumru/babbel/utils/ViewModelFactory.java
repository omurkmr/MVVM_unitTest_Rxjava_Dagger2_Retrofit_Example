package com.challenge.omurkumru.babbel.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.challenge.omurkumru.babbel.ui.entrance.EntranceRepository;
import com.challenge.omurkumru.babbel.ui.entrance.EntranceViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private EntranceRepository mEntranceRepository;
    private Context mContext;

    @Inject
    public ViewModelFactory(Context context, EntranceRepository entranceRepository) {
        mEntranceRepository = entranceRepository;
        mContext = context;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EntranceViewModel.class)) {
            return (T) new EntranceViewModel(mEntranceRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}