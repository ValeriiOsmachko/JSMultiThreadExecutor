package osmachko.com.ua.threadexecutor;

import javassist.bytecode.analysis.Executor;
import osmachko.com.ua.entity.Script;

import javax.script.ScriptEngine;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created by Valerii_Osmachko on 3/30/2017.
 */
public class ExecutorServiceThreadRunner {

    private Script script;
    private ScriptEngine scriptEngine;

    public Future<Script> runScriptInThread() {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<Script> future = executor.submit(new CallableRunner(script, scriptEngine));
        try {
            future.get(120, TimeUnit.SECONDS);
        } catch (Exception e) {
            future.cancel(true);
        }
        return future;
    }
}
