package osmachko.com.ua.storage;

import com.sun.javafx.iio.ImageStorage;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import osmachko.com.ua.entity.Script;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Valerii_Osmachko on 3/30/2017.
 */
@Data
@Component("classicalStorage")
public class Storage implements IStorage<String, Thread> {


    private List<Script> scripts = new CopyOnWriteArrayList<>();
    private Map<String, Thread> threadContainer = new ConcurrentHashMap<>();

    public Storage() {

    }

    @Override
    public Map<String, Thread> getStorage() {
        return threadContainer;
    }

    @Override
    public List<Script> getScripts() {
        return scripts;
    }


}
