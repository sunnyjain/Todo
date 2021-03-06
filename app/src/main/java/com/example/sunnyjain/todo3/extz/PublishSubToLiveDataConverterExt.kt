package com.example.sunnyjain.todo3.extz

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.res.Resources
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject


/**
 * Extension function helper methods
 **/

/**
 * this is the mapper that will return me the live data i will subject to.
 * */
fun <T> PublishSubject<T>.toLiveData(compositeDisposable: CompositeDisposable): LiveData<T> {
    val data = MutableLiveData<T>()
    compositeDisposable.add(this.subscribe { t: T -> data.value = t })
    return data
}

fun <T> PublishSubject<T>.success(t: T) {
    with(this){
        onNext(t)
    }
}


fun <T> Single<T>.performOnBackOutOnMain(): Single<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun <T> PublishSubject<T>.performOnBackOutOnMain(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun Int.toPx(): Float = (this * Resources.getSystem().displayMetrics.density)