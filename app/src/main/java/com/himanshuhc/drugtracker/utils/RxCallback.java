package com.himanshuhc.drugtracker.utils;

public interface RxCallback<T> {
    void onSuccess(T result);
    void onError(String errorMessage);
}
