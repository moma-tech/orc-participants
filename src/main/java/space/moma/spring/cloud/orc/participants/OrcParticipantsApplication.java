package space.moma.spring.cloud.orc.participants;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import space.moma.spring.cloud.orc.participants.dao.Participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ivan
 * @version 17-3-20
 */
@SpringBootApplication
@RestController
public class OrcParticipantsApplication implements CommandLineRunner {
    private static List<Participant> participants = new ArrayList<Participant>();

    public static void main(String[] args) {
        SpringApplication.run(OrcParticipantsApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        participants.add(new Participant("Ryan", "Baxter", "MA", "S", Arrays.asList("123", "456")));
        participants.add(new Participant("Stephanie", "Baxter", "MA", "S", Arrays.asList("456")));
    }

    @RequestMapping("/")
    public List<Participant> getParticipants() {
        return participants;
    }

    @RequestMapping("/races/{id}")
    public List<Participant> getParticipants(@PathVariable String id) {
        return participants.stream().filter(p -> p.getRaces().contains(id)).collect(Collectors.toList());
    }
}
