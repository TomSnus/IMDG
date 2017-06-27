package mapReduce;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;
import de.othr.vs.data.entity.Veranstaltung;

import java.util.List;

/**
 * Created by stt44293 on 27.06.2017.
 */
public class TippReducerFactory implements ReducerFactory<String, Veranstaltung, List<Veranstaltung>> {


    public Reducer<Veranstaltung, List<Veranstaltung>> newReducer(String s) {
        return new Reducer<Veranstaltung, List<Veranstaltung>>() {
            @Override
            public void reduce(Veranstaltung veranstaltung) {

            }

            @Override
            public List<Veranstaltung> finalizeReduce() {
                return null;
            }
        };
    }


}
