package osmachko.com.ua.storage;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import osmachko.com.ua.entity.Script;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;

/**
 * Created by Valerii_Osmachko on 3/30/2017.
 */
@Data
@Component("executorStorage")
public class ExecutorStorage implements IStorage<String, Future<Script>> {

    public ExecutorStorage() {

    }

    private Map<String, Future<Script>> threadContainer = new ConcurrentHashMap<>();
    private List<Script> scripts = new CopyOnWriteArrayList<>();

    @Override
    public Map<String, Future<Script>> getStorage() {
        return threadContainer;
    }

    @Override
    public List<Script> getScripts() {
        return scripts;
    }
}
