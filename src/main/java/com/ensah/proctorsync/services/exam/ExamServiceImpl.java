package com.ensah.proctorsync.services.exam;

import com.ensah.proctorsync.DTOs.exam.NewExamRequest;
import com.ensah.proctorsync.entities.*;
import com.ensah.proctorsync.exception.NotFoundException;
import com.ensah.proctorsync.helpers.OperationCheck;
import com.ensah.proctorsync.repositories.exam.IExamRepository;
import com.ensah.proctorsync.services.classroom.IClassroomService;
import com.ensah.proctorsync.services.examtype.IExamTypeService;
import com.ensah.proctorsync.services.group.GroupServiceImpl;
import com.ensah.proctorsync.services.monitoring.IMonitoringService;
import com.ensah.proctorsync.services.pedagogicelement.IPedagogicElementService;
import com.ensah.proctorsync.services.semester.ISemesterService;
import com.ensah.proctorsync.services.session.ISessionService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
public class ExamServiceImpl implements IExamService {
    private final IExamRepository examRepository;
    private final ISessionService sessionService;
    private final ISemesterService semesterService;
    private final IPedagogicElementService pedagogicElementService;
    private final IExamTypeService examTypeService;
    private final IClassroomService classroomService;
    private final IMonitoringService monitoringService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ExamServiceImpl.class);

    @Override
    public String create(NewExamRequest newExamRequest) {

        Session session = sessionService
                .getSessionById(newExamRequest.getSessionId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Session with id " + newExamRequest.getSessionId() + " does not exist");
                            LOGGER.error("Error while creating exam, exception thrown because of session does not exist", notFoundException);
                            return notFoundException;
                        }
                );

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        LocalDate fStartDate = LocalDate.from(LocalDateTime.parse(newExamRequest.getExamDate(), dateFormatter));
        LocalTime fStartTime = LocalTime.from(LocalDateTime.parse(newExamRequest.getExamDate(), dateFormatter));

        String semesterName = (LocalDate.now().getMonth().getValue() < 8 && LocalDate.now().getMonth().getValue() > 1) ? "Semestre 2" : "Semestre 1";


        Semester semester = semesterService
                .getSemesterByName(semesterName)
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Semester with name " + semesterName + " does not exist");
                            LOGGER.error("Error while creating exam, exception thrown because of Semester does not exist", notFoundException);
                            return notFoundException;
                        }
                );

        PedagogicElement pedagogicElement = pedagogicElementService
                .findPedagogicElementById(newExamRequest.getSubjectId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Pedagogic element with id " + newExamRequest.getSubjectId() + " does not exist");
                            LOGGER.error("Error while creating exam, exception thrown because of Pedagogic element does not exist", notFoundException);
                            return notFoundException;
                        }
                );

        ExamType examType = examTypeService
                .getExamTypeById(newExamRequest.getExamTypeId())
                .orElseThrow(
                        () -> {
                            NotFoundException notFoundException = new NotFoundException("Exam type with id " + newExamRequest.getSubjectId() + " does not exist");
                            LOGGER.error("Error while creating exam, exception thrown because of Exam type does not exist", notFoundException);
                            return notFoundException;
                        }
                );



        Exam newExam = Exam
                .builder()
                .semester(semester)
                .pedagogicElement(pedagogicElement)
                .session(session)
                .examType(examType)
                .startDateTime(getStartDateTime(fStartDate, fStartTime))
                .endDateTime(getEndDateTime(fStartDate, fStartTime, newExamRequest.getActualDuration(), newExamRequest.getPlannedDuration()))
                .academicYear(newExamRequest.getAcademicYear())
                .build();

        Exam savedExam = examRepository.save(newExam);


        if (!newExamRequest.getRoomIds().isEmpty()) {
            newExamRequest
                    .getRoomIds()
                    .forEach(crId -> {
                        Classroom classroom = classroomService
                                .findClassroomById(crId)
                                .orElseThrow(
                                        () -> {
                                            NotFoundException notFoundException = new NotFoundException("classroom with id " + crId + " does not exist");
                                            LOGGER.error("Error while creating exam, exception thrown because of classroom does not exist", notFoundException);
                                            return notFoundException;
                                        }
                                );

                        Monitoring newMonitoring = Monitoring
                                .builder()
                                .coordinator(pedagogicElement.getCoordinator())
                                .classroom(classroom)
                                .exam(savedExam)
                                .build();

                        Monitoring savedMonitoring = monitoringService.save(newMonitoring);
                        classroom.getMonitoring().add(savedMonitoring);
                        savedExam.getMonitoring().add(savedMonitoring);
                        classroomService.save(classroom);
                    });


            examRepository.save(savedExam);
        }

        return OperationCheck.check(savedExam, "Exam saved successfully", "Failed to save exam");
    }


    private LocalDateTime getStartDateTime(LocalDate startDate, LocalTime startTime) {
        return LocalDateTime.of(startDate, startTime);
    }

    private LocalDateTime getEndDateTime(LocalDate startDate, LocalTime startTime, int realDuration, int expectedDuration) {
        LocalDateTime startDateTime = getStartDateTime(startDate, startTime);
        int duration = (realDuration > 0) ? realDuration : expectedDuration;
        return startDateTime.plusSeconds(duration);
    }

}

