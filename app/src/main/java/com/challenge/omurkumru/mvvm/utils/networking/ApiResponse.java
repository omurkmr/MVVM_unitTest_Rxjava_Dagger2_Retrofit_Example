package com.challenge.omurkumru.mvvm.utils.networking;

import com.challenge.omurkumru.mvvm.model.Word;
import com.challenge.omurkumru.mvvm.utils.Status;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

import static com.challenge.omurkumru.mvvm.utils.Status.ERROR;
import static com.challenge.omurkumru.mvvm.utils.Status.LOADING;
import static com.challenge.omurkumru.mvvm.utils.Status.SUCCESS;

public class ApiResponse {

    public final Status status;

    @Nullable
    public final List<Word> data;

    @Nullable
    public final Throwable error;

    private ApiResponse(Status status, @Nullable List<Word> data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(LOADING, null, null);
    }

    public static ApiResponse success(@NonNull List<Word> data) {
        return new ApiResponse(SUCCESS, data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(ERROR, null, error);
    }

}