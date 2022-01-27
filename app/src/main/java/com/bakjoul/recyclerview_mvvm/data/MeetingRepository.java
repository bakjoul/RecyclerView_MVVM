package com.bakjoul.recyclerview_mvvm.data;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MeetingRepository {

    private static final String TAG = "Repository";

    private final MutableLiveData<List<Meeting>> meetingsLiveData = new MutableLiveData<>(new ArrayList<>());
    private long id = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Inject
    public MeetingRepository() {
        generateRandomMeetings();
    }

    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return meetingsLiveData;
    }

    public void addMeeting(
            @NonNull String subject,
            @NonNull LocalDateTime start,
            @NonNull LocalDateTime end,
            @NonNull Room room,
            @NonNull List<String> participants
    ) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null)
            meetings = new ArrayList<>();
        meetings.add(new Meeting(id, subject, start, end, room, participants));
        id++;
        meetingsLiveData.setValue(meetings);
    }

    public void deleteMeeting(Meeting meeting) {
        List<Meeting> meetings = meetingsLiveData.getValue();

        if (meetings == null)
            meetings = new ArrayList<>();
        for (Meeting m : meetings) {
            if (meeting.equals(m))
                meetings.remove(m);
        }
        meetingsLiveData.setValue(meetings);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void generateRandomMeetings() {
        addMeeting(
                "Réunion A",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(14, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 0)),
                Room.PEACH,
                new ArrayList<>(Arrays.asList("maxime@lamzone.com", "alex@lamzone.com"))
        );
        addMeeting("Réunion B",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)),
                Room.MARIO,
                new ArrayList<>(Arrays.asList("paul@lamzone.com", "viviane@lamzone.com"))
        );
        addMeeting("Réunion C",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 45)),
                Room.LUIGI,
                new ArrayList<>(Arrays.asList("amandine@lamzone.com", "luc@lamzone.com"))
        );
        Log.d(TAG, "generateRandomMeetings: ");
    }
}
