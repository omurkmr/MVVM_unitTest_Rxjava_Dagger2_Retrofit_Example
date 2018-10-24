package com.challenge.omurkumru.babbel.ui.entrance;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.annotation.NonNull;

import com.challenge.omurkumru.babbel.model.Word;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.TestScheduler;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class EntranceViewModelTest {

    private EntranceViewModel entranceViewModel;

    @Mock
    private EntranceRepository repository;

    private TestScheduler testScheduler;

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @BeforeClass
    public static void setUpRxSchedulers() {
        Scheduler immediate = new Scheduler() {
            @Override
            public Disposable scheduleDirect(@NonNull Runnable run, long delay, @NonNull TimeUnit unit) {
                // this prevents StackOverflowErrors when scheduling with a delay
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }


    @Before
    public void setUp() {

        testScheduler = new TestScheduler();
        entranceViewModel = Mockito.spy(new EntranceViewModel(repository));
    }

    @Test
    public void getWordsReponseTest() {
        Assert.assertNotNull(entranceViewModel.getWordsReponse());
    }

    @Test
    public void getWordListSuccess() {

        Mockito.doReturn(Observable.just(new ArrayList<Word>())).when(repository).getWordList();
        entranceViewModel.getWordList();
        testScheduler.triggerActions();

    }

    @Test
    public void getWordListFail() {

        Mockito.when(repository.getWordList())
                .thenReturn(Observable.error(
                        new Exception("Fail")));

        entranceViewModel.getWordList();
        testScheduler.triggerActions();

    }
}
