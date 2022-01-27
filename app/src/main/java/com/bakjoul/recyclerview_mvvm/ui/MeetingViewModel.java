package com.bakjoul.recyclerview_mvvm.ui;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Inject
    public MeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public LiveData<List<MeetingItemViewState>> getMeetingItemViewStateLiveData() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
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
            return meetingItemViewStates;
        });
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
