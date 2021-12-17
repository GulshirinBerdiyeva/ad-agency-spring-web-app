package com.bsu.project.service;

import com.bsu.project.entity.Schedule;
import com.bsu.project.repository.ScheduleRepository;

/**
 * @author Gulshirin Berdiyeva
 */
public class ScheduleService extends AbstractService<Schedule> {

    public ScheduleService(ScheduleRepository repository) {
        super("Schedule", repository);
    }

}
