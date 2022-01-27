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

    private final int id = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Inject
    public MeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return Transformations.map(meetingRepository.getMeetingsLiveData(), meetings -> {
            List<Meeting> meetingsList = new ArrayList<>();
            for (Meeting meeting : meetings) {
                meetingsList.add(new Meeting(
                        meeting.getId(),
                        meeting.getSubject(),
                        meeting.getStart(),
                        meeting.getEnd(),
                        meeting.getRoom(),
                        meeting.getParticipants()
                ));
            }
            return meetingsList;
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
