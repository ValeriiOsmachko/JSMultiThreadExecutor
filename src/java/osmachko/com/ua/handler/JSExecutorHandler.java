package osmachko.com.ua.handler;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import osmachko.com.ua.entity.Script;
import osmachko.com.ua.storage.IStorage;
import osmachko.com.ua.threadexecutor.CallableRunner;
import osmachko.com.ua.threadstatus.ThreadStatus;
import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Valerii_Osmachko on 3/30/2017.
 */
@Data
@Component("executorHandler")
@PropertySource("classpath:engineconfig.properties")
public class JSExecutorHandler implements JSHandler {

    @Resource(name = "executorStorage")
    private IStorage<String, Future<Script>> storage;
    @Value("${engineType}")
    private String engineTypeName;
    private ExecutorService executor = Executors.newFixedThreadPool(20);

    @Override
    public Script addIntoStorageAndExecuteScript(Script script) {
        script.setId(UUID.randomUUID().toString());
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(engineTypeName);
        storage.getScripts().add(script);
        Future<Script> future = executor.submit(new CallableRunner(script, engine));
        storage.getStorage().put(script.getId(), future);
        try {
            Script futureScript = future.get(25, TimeUnit.SECONDS);
            for (Script element : storage.getScripts()) {
                if (element.getId().equals(script.getId())) {
                    element = futureScript;
                }
            }
            if (futureScript != null) {
                storage.getScripts()
                        .stream()
                        .filter(e -> e.getId().equals(script.getId()))
                        .findFirst()
                        .ifPresent(el -> el.setStatus(ThreadStatus.DONE.toString()));
            }


            return futureScript;
        } catch (Exception e) {
            future.cancel(true);
            storage.getScripts()
                    .stream()
                    .filter(el -> el.getId().equals(script.getId()))
                    .findFirst()
                    .ifPresent(el -> el.setStatus(ThreadStatus.TIME_OUT.toString()));
            return null;
        }
    }

    @Override
    public List<Script> getAllScripts() {
        return storage.getScripts();
    }

    @Override
    public void stopJSExecution(String scriptId) {
        Optional<Map.Entry<String, Future<Script>>> entry = storage.getStorage()
                .entrySet()
                .stream()
                .filter(e -> e.getKey().equals(scriptId))
                .findFirst();
        if (entry.isPresent()) {
            entry.get().getValue().cancel(true);
        }
        storage.getScripts().stream().filter(e -> e.getId().equals(scriptId)).findFirst().ifPresent(e -> e.setStatus(ThreadStatus.INTERRUPTED.toString()));
    }

    @Override
    public void deleteScript(String scriptId) {
        Optional<Script> script = storage.getScripts().stream().filter(e -> e.getId().equals(scriptId)).findFirst();
        if (script.isPresent()) {
            storage.getScripts().remove(script.get());
        }
    }

}
