package com.example.sunnyjain.todo3.extz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.example.sunnyjain.todo3.repository.TaskRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


/**
 * Extension function helper methods
 **/

fun <T> Single<T>.performOnBackOutOnMain(): Single<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}