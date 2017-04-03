package osmachko.com.ua.threadexecutor;

import osmachko.com.ua.entity.Script;
import osmachko.com.ua.threadstatus.ThreadStatus;
import javax.script.ScriptEngine;
import java.io.StringWriter;
import java.util.concurrent.Callable;

/**
 * Created by Valerii_Osmachko on 3/30/2017.
 */
public class CallableRunner implements Callable<Script> {

    private Script script;
    private ScriptEngine scriptEngine;

    public CallableRunner(Script script, ScriptEngine scriptEngine) {
        this.script = script;
        this.scriptEngine = scriptEngine;
    }

    @Override
    public Script call() throws Exception {
        StringWriter stringWriter = new StringWriter();
        scriptEngine.getContext().setWriter(stringWriter);
        script.setStatus(ThreadStatus.RUNNING.toString());
        Object result = scriptEngine.eval(script.getScript());
        if (result != null) {
            script.setReturnedValue(result);
        }
        script.setConsoleOutPut(stringWriter.toString());
        return script;
    }
}
