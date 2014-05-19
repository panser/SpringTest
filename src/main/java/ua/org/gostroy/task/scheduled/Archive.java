package ua.org.gostroy.task.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by panser on 5/19/14.
 */
public class Archive {

    @Scheduled(fixedRate=86400000)
    public void archiveOldSpittles1() {
    // ...
    }

    @Scheduled(cron="0 0,15,30,45 * 1-30 * ?")
    public void archiveOldSpittles2() {
    // ...
    }
}
