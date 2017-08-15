package com.g4s8.rxkeyboard;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;

/**
 * Hide keyboard {@link Completable} task.
 *
 * @author Kirill (g4s8.public@gmail.com)
 * @version $Id$
 * @since 1.1
 */
final class HideTask extends Completable {

    private final InputMethodManager imm;
    private final View view;
    private final RxSoftKeyboard.HideFlags flags;

    HideTask(@NonNull final InputMethodManager imm,
             @NonNull final View view,
             @NonNull final RxSoftKeyboard.HideFlags flags) {
        this.imm = imm;
        this.view = view;
        this.flags = flags;
    }

    @Override
    protected void subscribeActual(final CompletableObserver src) {
        final ReceiverSubscription receiver = new ReceiverSubscription(src);
        src.onSubscribe(receiver);
        final boolean shown = this.imm.hideSoftInputFromWindow(
            view.getWindowToken(),
            flags.value(),
            receiver
        );
        if (!shown) {
            src.onComplete();
        }
    }
}
