package com.example.sns_project.service;

import com.example.sns_project.domain.entity.Alarm;
import com.example.sns_project.domain.entity.User;
import com.example.sns_project.exception.AppException;
import com.example.sns_project.exception.ErrorCode;
import com.example.sns_project.repository.AlarmRepository;
import com.example.sns_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AlarmService {
    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;

    public Page<AlarmResponse> getAlarms(Pageable pageable, String username) {
        User user = findByUserName(username);
        Page<Alarm> alarms = alarmRepository.findByUser(user, pageable);

        Page<AlarmResponse> alarmResponses = alarms.map(alarm -> AlarmResponse.fromEntity(alarm, userRepository.findByUserName(alarm.getFromUserName()).get().getImagePath()));
        return alarmResponses;
    }
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(()-> new AppException(ErrorCode.USERID_NOT_FOUND,ErrorCode.USERID_NOT_FOUND.getMessage()));
    }
}
