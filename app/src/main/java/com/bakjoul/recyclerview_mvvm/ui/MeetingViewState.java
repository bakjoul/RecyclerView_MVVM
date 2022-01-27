package com.bakjoul.recyclerview_mvvm.ui;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class MeetingViewState {
    @NonNull
    private final List<MeetingViewState> meetingViewStates;

    public MeetingViewState(@NonNull List<MeetingViewState> meetingViewStates) {
        this.meetingViewStates = meetingViewStates;
    }

    @NonNull
    public List<MeetingViewState> getMeetingViewStates() {
        return meetingViewStates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingViewState that = (MeetingViewState) o;
        return meetingViewStates.equals(that.meetingViewStates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingViewStates);
    }
}
