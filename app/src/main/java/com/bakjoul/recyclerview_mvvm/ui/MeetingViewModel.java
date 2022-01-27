package com.bakjoul.recyclerview_mvvm.ui;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.bakjoul.recyclerview_mvvm.model.Meeting;
import com.bakjoul.recyclerview_mvvm.model.Room;
import com.bakjoul.recyclerview_mvvm.repository.Repository;

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

    private static final String TAG = "ViewModel";

    //private final MutableLiveData<List<Item>> itemLiveData;
    //private List<Item> itemList;

    @NonNull
    private final Repository repository;

    private final int id=0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Inject
    public MeetingViewModel(@NonNull Repository repository) {
        //meetingLiveData = new MutableLiveData<>();
        this.repository = repository;

        //init();
    }

/*    public MutableLiveData<List<Item>> getItemLiveData() {
        return itemLiveData;
    }*/

    public LiveData<List<Meeting>> getMeetingsLiveData() {
        return Transformations.map(repository.getMeetingsLiveData(), meetings -> {
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
    public void init() {
        //populate();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addMeeting() {
        repository.addMeeting(
                "Réunion Z",
                LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)),
                LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)),
                Room.BOWSER,
                new ArrayList<>(Arrays.asList("toto", "tata")));
    }

    /*    public void populate() {
        itemList = new ArrayList<>();
        itemList.add(new Item(id++, "ITEM"+id));
        itemList.add(new Item(id++, "ITEM"+id));
        itemList.add(new Item(id++, "ITEM"+id));
        itemList.add(new Item(id++, "ITEM"+id));
        itemList.add(new Item(id++, "ITEM"+id));
        itemList.add(new Item(id++, "ITEM"+id));
    }*/

/*    public void addItem() {
        itemList = itemLiveData.getValue();
        if (itemList == null) {
            itemList = new ArrayList<>();
        }
        itemList.add(new Item(id++, "ITEM"+id));
        itemLiveData.setValue(itemList);
        Log.d(TAG, "addItem: ");
    }*/

}

