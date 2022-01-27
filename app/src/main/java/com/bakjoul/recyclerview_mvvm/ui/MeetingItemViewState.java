package com.bakjoul.recyclerview_mvvm.ui;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class MeetingItemViewState {

    private final long id;

    @ColorRes
    private final int iconColor;

    @NonNull
    private final String subject;

    @NonNull
    private final List<String> participants;

    public MeetingItemViewState(long id, int iconColor, @NonNull String subject, @NonNull List<String> participants) {
        this.id = id;
        this.iconColor = iconColor;
        this.subject = subject;
        this.participants = participants;
    }

    public long getId() {
        return id;
    }

    public int getIconColor() {
        return iconColor;
    }

    @NonNull
    public String getSubject() {
        return subject;
    }

    @NonNull
    public List<String> getParticipants() {
        return participants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingItemViewState that = (MeetingItemViewState) o;
        return id == that.id && iconColor == that.iconColor && subject.equals(that.subject) && participants.equals(that.participants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, iconColor, subject, participants);
    }
}
