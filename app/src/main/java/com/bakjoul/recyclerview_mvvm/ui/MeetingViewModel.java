package com.bakjoul.recyclerview_mvvm.ui;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.bakjoul.recyclerview_mvvm.data.Meeting;
import com.bakjoul.recyclerview_mvvm.data.MeetingRepository;
import com.bakjoul.recyclerview_mvvm.data.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MeetingViewModel extends ViewModel {

    @NonNull
    private final MeetingRepository meetingRepository;

    private final MediatorLiveData<MeetingViewState> meetingViewStateMediatorLiveData = new MediatorLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Inject
    public MeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;

        init();
    }

    private void init() {
        final LiveData<List<Meeting>> meetingsLiveData = meetingRepository.getMeetingsLiveData();
        meetingViewStateMediatorLiveData.addSource(meetingsLiveData, meetings -> meetingViewStateMediatorLiveData.setValue(getMeetingViewState(meetingsLiveData.getValue())));
    }

    @NonNull
    public LiveData<MeetingViewState> getMeetingViewStateLiveData() {
        return meetingViewStateMediatorLiveData;
    }

    @NonNull
    private MeetingViewState getMeetingViewState(@NonNull final List<Meeting> meetings) {
        List<MeetingItemViewState> meetingItemViewStates = new ArrayList<>();
        for (Meeting meeting : meetings) {
            meetingItemViewStates.add(
                    new MeetingItemViewState(
                            meeting.getId(),
                            meeting.getRoom().getColor(),
                            meeting.getSubject(),
                            meeting.getParticipants()
                    )
            );
        }
        return new MeetingViewState(meetingItemViewStates);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addMeeting() {
        meetingRepository.addMeeting(
                "Réunion Z",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)),
                Room.BOWSER,
                new ArrayList<>(Arrays.asList("toto", "tata")));
    }
}
