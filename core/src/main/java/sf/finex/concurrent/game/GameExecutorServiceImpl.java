package sf.finex.concurrent.game;

import sf.finex.service.concurrent.GameExecutorService;

import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * @author m0nster.mind
 */
@Singleton
public class GameExecutorServiceImpl implements GameExecutorService {

    @Inject
    @Named("Game")
    private ScheduledExecutorService executorService;

    @Override
    public Future<?> execute(GameTask task) {
        return executorService.submit(task);
    }

    @Override
    public Future<?> execute(GameTask task, long delay, TimeUnit delayUnit) {
        return executorService.schedule(task, delay, delayUnit);
    }

    @Override
    public ScheduledFuture<?> execute(GameTask task, long delay, long period, TimeUnit timeUnit) {
        return executorService.scheduleAtFixedRate(task, delay, period, timeUnit);
    }

}
