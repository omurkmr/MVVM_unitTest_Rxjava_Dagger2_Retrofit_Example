package com.challenge.omurkumru.babbel.ui.entrance;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.challenge.omurkumru.babbel.Application;
import com.challenge.omurkumru.babbel.R;
import com.challenge.omurkumru.babbel.di.AppComponent;
import com.challenge.omurkumru.babbel.model.Word;
import com.challenge.omurkumru.babbel.ui.game.GameFragment;
import com.challenge.omurkumru.babbel.utils.ViewModelFactory;
import com.challenge.omurkumru.babbel.utils.networking.ApiResponse;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class EntranceFragment extends Fragment {

    private static final String TAG = EntranceFragment.class.getSimpleName();

    EntranceViewModel viewModel;

    @Inject
    ViewModelFactory mViewModelFactory;

    //ProgressDialog was deprecated in API level 26
    //I know that but because of the limited time, I used temporarily.
    ProgressDialog progress;

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

        getActivity().setTitle("Entrance");

        viewModel.getWordsReponse().observe(this, this::consumeResponse);

        return inflater.inflate(R.layout.fragment_entrance, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);

        progress = new ProgressDialog(view.getContext());
        progress.setTitle("Game Loading");
        progress.setMessage("Give me just a sec..");
        progress.setCancelable(false);

    }

    @OnClick(R.id.start_game_button)
    public void startGameButtonTap(View view) {

        viewModel.getWordList();
    }

    @OnClick(R.id.quit_game_button)
    public void quitGameButtonTap(View view) {

        getActivity().finish();
    }

    /*
     * method to handle response
     * */
    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                Log.i(TAG,"loading");
                progress.show();
                break;

            case SUCCESS:
                Log.i(TAG,"success");
                progress.dismiss();

                ArrayList<Word> wordList = new ArrayList<>(apiResponse.data.size());
                wordList.addAll(apiResponse.data);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("wordsArray", wordList);

                Fragment gameFragment = new GameFragment();
                gameFragment.setArguments(bundle);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, gameFragment );
                transaction.commit();
                break;

            case ERROR:
                Log.i(TAG,"Error");
                progress.dismiss();
                Toast.makeText(getContext(), "Something gone wrong(check your network)", Toast.LENGTH_SHORT).show();
                break;

            default:
                progress.dismiss();
                break;
        }
    }
}