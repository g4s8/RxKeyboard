package com.g4s8.rxkeyboard;

import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import io.reactivex.Completable;

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

    private final Context ctx;

    /**
     * Ctor.
     *
     * @param ctx Android context
     */
    public RxAndroidSoftKeyboard(@NonNull final Context ctx) {
        this.ctx = ctx;
    }

    private InputMethodManager imm() {
        return InputMethodManager.class.cast(
            ctx.getSystemService(
                Context.INPUT_METHOD_SERVICE
            )
        );
    }

    @NonNull
    @Override
    public Completable show(@NonNull final View view, @NonNull final ShowFlags flags) {
        return new ShowTask(imm(), view, flags);
    }

    @NonNull
    @Override
    public Completable hide(@NonNull final View view, @NonNull final HideFlags flags) {
        return new HideTask(imm(), view, flags);
    }
}
