package logexamp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class myTimerLoggings {
    private static final Logger debugLogger = LogManager.getLogger("debugLogger");
    private static final Logger infoLogger = LogManager.getLogger("infoLogger");
    private static final Logger errorLogger = LogManager.getLogger("errorLogger");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void main(String[] args) {


        Timer debugTimer = new Timer();
        Timer infoTimer = new Timer();
        Timer errorTimer = new Timer();

        debugTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String currenTime = LocalDateTime.now().format(timeFormatter);
                debugLogger.debug(currenTime);
            }
        }, 0, 1000);

        infoTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String currentTime = LocalDateTime.now().withSecond(0).format(timeFormatter);
                infoLogger.info(currentTime);
            }
        }, 0, 60000);

        errorTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                String currentTime = LocalDateTime.now().withMinute(0).withSecond(0).format(timeFormatter);
                errorLogger.error(currentTime);
            }
        }, 0, 3600000);

        // Uygulama kapanırken zamanlayıcıları durdur
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            debugTimer.cancel();
            infoTimer.cancel();
            errorTimer.cancel();
        }));
    }
}

