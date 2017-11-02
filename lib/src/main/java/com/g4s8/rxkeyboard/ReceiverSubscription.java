package com.g4s8.rxkeyboard;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Subscription based on {@link ResultReceiver}.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 1.1
 */
final class ReceiverSubscription extends ResultReceiver implements Disposable {

    private final AtomicReference<CompletableObserver> obs;

    ReceiverSubscription(@NonNull final CompletableObserver obs) {
        super(new Handler(Looper.getMainLooper()));
        this.obs = new AtomicReference<>(obs);
    }

    @Override
    protected void onReceiveResult(final int resultCode, final Bundle resultData) {
        final CompletableObserver observer = obs.get();
        if (observer != null) {
            observer.onComplete();
        }
    }

    @Override
    public void dispose() {
        obs.set(null);
    }

    @Override
    public boolean isDisposed() {
        return obs.get() == null;
    }
}
