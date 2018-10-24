package com.challenge.omurkumru.babbel.ui.game;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.challenge.omurkumru.babbel.Application;
import com.challenge.omurkumru.babbel.R;
import com.challenge.omurkumru.babbel.di.AppComponent;
import com.challenge.omurkumru.babbel.model.GameWord;
import com.challenge.omurkumru.babbel.model.Word;
import com.challenge.omurkumru.babbel.ui.entrance.EntranceFragment;
import com.challenge.omurkumru.babbel.utils.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameFragment extends Fragment implements Animation.AnimationListener {

    GameViewModel viewModel;

    @Inject
    ViewModelFactory mViewModelFactory;

    List<Word> wordList;

    @BindView(R.id.esp_word_textview)
    TextView espWordTextView;

    @BindView(R.id.english_word_textview)
    TextView enWordTextView;

    @BindView(R.id.score_count_textview)
    TextView scoreCountTextView;

    @BindView(R.id.remaining_life_count_textview)
    TextView remainingLifeCountTextView;

    @BindView(R.id.wrong_button)
    FloatingActionButton wrongButton;

    @BindView(R.id.correct_button)
    FloatingActionButton correctButton;

    GameWord gameWord;

    Animation animation;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        //Inject the current activity inside Dagger 2 dependency graph
        AppComponent applicationComponent = ((Application) getActivity().getApplication()).getAppComponent();
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

        viewModel.getRemainingLife().observe(this, remainingLife -> {

            if (remainingLife > 0) {
                remainingLifeCountTextView.setText(String.valueOf(remainingLife));
                setNewWord();
            } else {
                correctButton.setClickable(false);
                wrongButton.setClickable(false);
                stopWordAnimation();
                showGameFinishDialog();
            }
        });

        viewModel.getScore().observe(this, score -> {
            scoreCountTextView.setText(String.valueOf(score));
            setNewWord();
        });

        animation = AnimationUtils.loadAnimation(getContext(),
                R.anim.move);

        animation.setAnimationListener(this);

        setNewWord();
    }

    private void showGameFinishDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Game Over");
        builder.setMessage("Your Score: " + String.valueOf(viewModel.getScore().getValue()));

        builder.setPositiveButton("OK", (dialog, which) -> {
            Fragment entranceFragment = new EntranceFragment();
            FragmentTransaction transaction = null;
            if (getFragmentManager() != null) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, entranceFragment);
                transaction.commit();
            }
            dialog.dismiss();
        });

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);

        dialog.show();
    }

    private void setNewWord() {

        gameWord = viewModel.getGameWord(wordList);

        enWordTextView.setText(gameWord.getEnWord());
        espWordTextView.setText(gameWord.getEspWord());

        startWordAnimation();
    }

    private void startWordAnimation() {
        espWordTextView.startAnimation(animation);
    }

    private void stopWordAnimation() {

        espWordTextView.clearAnimation();
        espWordTextView.animate().cancel();
        animation.setAnimationListener(null);
    }

    @OnClick(R.id.correct_button)
    public void hitCorrectButton() {
        if (gameWord.isTrue()) {
            viewModel.increaseScore();
        } else {
            viewModel.decreaseRemainingLife();
        }
    }

    @OnClick(R.id.wrong_button)
    public void hitWrongButton() {

        if (!gameWord.isTrue()) {
            viewModel.increaseScore();
        } else {
            viewModel.decreaseRemainingLife();
        }
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    //todo: decreaseRemainingLife trigger twice
    @Override
    public void onAnimationEnd(Animation animation) {
        viewModel.decreaseRemainingLife();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


}
