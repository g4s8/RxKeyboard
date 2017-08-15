package com.g4s8.rxkeyboard;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import io.reactivex.Completable;

/**
 * Reactive software android keyboard.
 *
 * @author g4s8 (g4s8.public@gmail.com)
 * @version $Id$
 * @since 1.0
 */
@Keep
public interface RxSoftKeyboard {

    /**
     * Show keyboard flags.
     *
     * @see InputMethodManager
     */
    enum ShowFlags {
        NONE(0),
        IMPLICIT(InputMethodManager.SHOW_IMPLICIT);

        private final int value;

        ShowFlags(final int value) {
            this.value = value;
        }

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        public int value() {
            return this.value;
        }
    }

    /**
     * Hide keyboard flags.
     *
     * @see InputMethodManager
     */
    enum HideFlags {
        NONE(0),
        IMPLICIT_ONLY(InputMethodManager.HIDE_IMPLICIT_ONLY);

        private final int value;

        HideFlags(final int value) {
            this.value = value;
        }

        @RestrictTo(RestrictTo.Scope.LIBRARY)
        public int value() {
            return this.value;
        }
    }

    /**
     * Show keyboard async for view with flags.
     *
     * @param view  target view.
     * @param flags keyboard flags
     * @return completable action
     */
    @NonNull
    Completable show(@NonNull final View view, @NonNull final ShowFlags flags);

    /**
     * Hide keyboard async for window token with flags.
     *
     * @param view  target view
     * @param flags keyboard flags
     * @return completable action
     */
    @NonNull
    Completable hide(@NonNull final View view, @NonNull final HideFlags flags);
}
