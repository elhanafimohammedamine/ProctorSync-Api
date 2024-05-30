package com.ensah.proctorsync.services.exam;

import com.ensah.proctorsync.DTOs.exam.NewExamRequest;

public interface IExamService {

    String create(NewExamRequest newExamRequest);
}
