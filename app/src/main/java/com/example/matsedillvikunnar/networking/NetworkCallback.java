package com.example.matsedillvikunnar.networking;

public interface NetworkCallback<T> {

    void onSuccess(T result);

    void onFailure(String errorString);
}
