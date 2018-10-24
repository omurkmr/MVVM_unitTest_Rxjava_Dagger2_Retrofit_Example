package com.challenge.omurkumru.babbel.ui.entrance;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.challenge.omurkumru.babbel.utils.networking.ApiResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class EntranceViewModel extends ViewModel {

    private EntranceRepository repository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    public EntranceViewModel(EntranceRepository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ApiResponse> getWordsReponse() {
        return responseLiveData;
    }

    public void getWordList() {

        disposables.add(repository.getWordList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(d -> {
                            responseLiveData.setValue(ApiResponse.loading());
                        }
                )
                .subscribe(
                        result -> {
                            responseLiveData.setValue(ApiResponse.success(result));
                        },
                        throwable -> {
                            responseLiveData.setValue(ApiResponse.error(throwable));
                        }
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }

}
