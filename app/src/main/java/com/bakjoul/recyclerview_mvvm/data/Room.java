package com.bakjoul.recyclerview_mvvm.data;

import androidx.annotation.ColorRes;
import androidx.annotation.StringRes;

import com.bakjoul.recyclerview_mvvm.R;

public enum Room {
    MARIO(R.string.mario, R.color.mario),
    PEACH(R.string.peach, R.color.peach),
    BOWSER(R.string.bowser, R.color.bowser),
    KOOPA(R.string.koopa, R.color.koopa),
    LUIGI(R.string.luigi, R.color.luigi),
    YOSHI(R.string.yoshi, R.color.yoshi),
    DONKEY(R.string.donkey, R.color.donkey),
    TOAD(R.string.toad, R.color.toad);

    @StringRes
    private final int name;

    @ColorRes
    private final int color;

    Room(@StringRes int name, @ColorRes int color) {
        this.name = name;
        this.color = color;
    }

    public int getName() {
        return name;
    }

    public int getColor() {
        return color;
    }
}
