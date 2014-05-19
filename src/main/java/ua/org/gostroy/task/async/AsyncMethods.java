package ua.org.gostroy.task.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import ua.org.gostroy.entity.User;

import java.util.concurrent.Future;

/**
 * Created by panser on 5/19/14.
 */
public class AsyncMethods {

    @Async
    public void addUser(User user) {
//        ...
    }

    @Async
    public Future<Long> performSomeReallyHairyMath(long input) {
// ...
        Long result = 10L;
        return new AsyncResult<Long>(result);
    }
}
