package com.challenge.omurkumru.babbel.ui.entrance;

import android.arch.lifecycle.ViewModel;

public class EntranceViewModel extends ViewModel {

    private EntranceRepository repository;

    public EntranceViewModel(EntranceRepository repository) {
        this.repository = repository;
    }

}
