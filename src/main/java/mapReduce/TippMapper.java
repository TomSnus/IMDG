package mapReduce;

import Server.ServerRest;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import de.othr.vs.data.entity.Veranstaltung;

import java.util.Arrays;
import java.util.List;

/**
 * Created by stt44293 on 27.06.2017.
 */
public class TippMapper implements Mapper<String, Veranstaltung, String, Veranstaltung> {

    private final String[] suchwoerter;
    private final List<String> wordList;

    public void map(String s, Veranstaltung veranstaltung, Context<String, Veranstaltung> context) {
        String pattern = veranstaltung.getBeschreibung() + veranstaltung.getTitel();
        String patternArray[] = pattern.split(" ");
        List<String> patternList = Arrays.asList(patternArray);

        for(String p : patternList){
            for(String q : wordList){
                if(p.toLowerCase().equals(q.toLowerCase())){
                    context.emit(s, veranstaltung);
                }
            }
        }

    }

    public TippMapper(String[] suchwoerter) {
        this.suchwoerter = suchwoerter;
        wordList = Arrays.asList(suchwoerter);

    }
}
