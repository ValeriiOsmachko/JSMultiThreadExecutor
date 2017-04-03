package osmachko.com.ua.threadexecutor;

import lombok.Data;
import osmachko.com.ua.entity.Script;
import osmachko.com.ua.threadstatus.ThreadStatus;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.UUID;

/**
 * Created by Valerii_Osmachko on 3/29/2017.
 */
@Data
public class ThreadRunner implements Runnable {

    private String currentThreadId;
    private Script script;
    private ScriptEngine scriptEngine;


    public ThreadRunner(Script script, ScriptEngine scriptEngine) {
        currentThreadId = UUID.randomUUID().toString();
        this.script = script;
        this.scriptEngine = scriptEngine;
    }

    @Override
    public void run() {
        StringWriter stringWriter = null;
        try {
            stringWriter = new StringWriter();
            scriptEngine.getContext().setWriter(stringWriter);
            script.setStatus(ThreadStatus.RUNNING.toString());
            Object result = scriptEngine.eval(script.getScript());
            if (result != null) {
                script.setReturnedValue(result);
            }
            script.setConsoleOutPut(stringWriter.toString());
            cleanStringWriterBuffer(stringWriter);
        } catch (ScriptException e) {
            cleanStringWriterBuffer(stringWriter);
        }
    }

    private void cleanStringWriterBuffer(StringWriter stringWriter) {
        stringWriter.flush();
        try {
            stringWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
