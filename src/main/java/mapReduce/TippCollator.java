package mapReduce;

import com.hazelcast.mapreduce.Collator;
import de.othr.vs.data.entity.Veranstaltung;

import java.util.List;
import java.util.Map;

/**
 * Created by stt44293 on 27.06.2017.
 */
public class TippCollator implements Collator<Map.Entry<String, List<Veranstaltung>>, List<Veranstaltung>> {
    public List<Veranstaltung> collate(Iterable<Map.Entry<String, List<Veranstaltung>>> iterable) {
        return null;
    }
}
