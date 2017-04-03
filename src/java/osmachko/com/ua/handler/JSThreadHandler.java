package osmachko.com.ua.handler;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import osmachko.com.ua.entity.Script;
import osmachko.com.ua.storage.IStorage;
import osmachko.com.ua.threadexecutor.ThreadRunner;
import osmachko.com.ua.threadstatus.ThreadStatus;
import javax.annotation.Resource;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;


/**
 * Created by Valerii_Osmachko on 3/28/2017.
 */
@Data
@Component("ThreadHandler")
@PropertySource("classpath:engineconfig.properties")
public class JSThreadHandler implements JSHandler {

    @Resource(name = "classicalStorage")
    private IStorage<String, Thread> storage;
    @Value("${engineType}")
    private String engineTypeName;

    @Override
    public Script addIntoStorageAndExecuteScript(Script script) {
        script.setId(UUID.randomUUID().toString());
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(engineTypeName);
        ThreadRunner threadRunner = new ThreadRunner(script, engine);
        Thread thread = new Thread(threadRunner);
        storage.getScripts().add(script);
        storage.getStorage().put(threadRunner.getScript().getId(), thread);
        thread.start();
        try {
            thread.join();
            threadRunner.getScript().setStatus(ThreadStatus.DONE.toString());
            List<Script> scripts = storage.getScripts();
            for (Script element : scripts) {
                if (element.getId().equals(script.getId())) {
                    element = threadRunner.getScript();
                }
            }
            storage.getStorage().put(threadRunner.getScript().getId(), thread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return threadRunner.getScript();
    }

    @Override
    public List<Script> getAllScripts() {
        return storage.getScripts();
    }

    @Override
    public void stopJSExecution(String scriptId) {
        storage.getStorage()
                .entrySet().stream().filter(e -> e.getKey().equals(scriptId))
                .findFirst().ifPresent(element -> element.getValue().stop());
        storage.getScripts().stream()
                .filter(e -> e.getId().equals(scriptId))
                .findFirst()
                .ifPresent(e -> e.setStatus(ThreadStatus.INTERRUPTED.toString()));
    }

    @Override
    public void deleteScript(String scriptId) {
        Optional<Script> script = storage.getScripts().stream().filter(e -> e.getId().equals(scriptId)).findFirst();
        if (script.isPresent()) {
            storage.getScripts().remove(script.get());
        }
    }


}
