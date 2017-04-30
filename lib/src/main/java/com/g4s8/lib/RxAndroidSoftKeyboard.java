package com.g4s8.lib;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;

/**
 * Reactive android software keyboard implemented via
 * {@link android.view.inputmethod.InputMethodManager}.
 *
 * @author g4s8 (g4s8.public@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Keep
public final class RxAndroidSoftKeyboard implements RxSoftKeyboard {

    private final InputMethodManager imm;

    public RxAndroidSoftKeyboard(@NonNull final Context ctx) {
        this(
            InputMethodManager.class.cast(
                ctx.getSystemService(
                    Context.INPUT_METHOD_SERVICE
                )
            )
        );
    }

    public RxAndroidSoftKeyboard(@NonNull final InputMethodManager imm) {
        this.imm = imm;
    }

    @NonNull
    @Override
    public Completable show(@NonNull final View view, @NonNull final ShowFlags flags) {
        return Completable.create(
            new CompletableOnSubscribe() {
                @Override
                public void subscribe(final CompletableEmitter emitter) throws Exception {
                    final boolean shown = RxAndroidSoftKeyboard.this.imm.showSoftInput(
                        view,
                        flags.value(),
                        new EmitterReceiver(emitter)
                    );
                    if (!shown) {
                        emitter.onComplete();
                    }
                }
            }
        );
    }

    @NonNull
    @Override
    public Completable hide(@NonNull final IBinder token, @NonNull final HideFlags flags) {
        return Completable.create(
            new CompletableOnSubscribe() {
                @Override
                public void subscribe(final CompletableEmitter emitter) throws Exception {
                    final boolean hidden = RxAndroidSoftKeyboard.this.imm.hideSoftInputFromWindow(
                        token,
                        flags.value(),
                        new EmitterReceiver(emitter)
                    );
                    if (!hidden) {
                        emitter.onComplete();
                    }
                }
            }
        );
    }

    private static final class EmitterReceiver extends ResultReceiver {

        private final CompletableEmitter emitter;

        private EmitterReceiver(@NonNull final CompletableEmitter emitter) {
            super(new Handler(Looper.getMainLooper()));
            this.emitter = emitter;
        }

        @Override
        protected void onReceiveResult(final int resultCode, final Bundle resultData) {
            this.emitter.onComplete();
        }
    }
}
