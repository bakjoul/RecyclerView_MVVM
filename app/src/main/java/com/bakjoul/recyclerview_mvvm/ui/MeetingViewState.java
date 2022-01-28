package com.bakjoul.recyclerview_mvvm.ui;

import androidx.annotation.NonNull;

import java.util.List;
import java.util.Objects;

public class MeetingViewState {
    @NonNull
    private final List<MeetingItemViewState> meetingViewStateItemList;

    public MeetingViewState(@NonNull List<MeetingItemViewState> meetingViewStateItemList) {
        this.meetingViewStateItemList = meetingViewStateItemList;
    }

    @NonNull
    public List<MeetingItemViewState> getMeetingViewStateItemList() {
        return meetingViewStateItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeetingViewState that = (MeetingViewState) o;
        return meetingViewStateItemList.equals(that.meetingViewStateItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingViewStateItemList);
    }
}
