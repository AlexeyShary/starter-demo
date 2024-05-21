package t1.openschool.sampleservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class EtcService {
    public int getRandomValue() {
        Random random = new Random();
        return random.nextInt();
    }

    public int getRandomIntWithDelay() {
        Random random = new Random();
        int delay = random.nextInt(500) + 500;
        try {
            Thread.sleep(delay);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return getRandomValue();
    }
}
