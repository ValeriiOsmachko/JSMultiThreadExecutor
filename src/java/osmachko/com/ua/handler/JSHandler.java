package osmachko.com.ua.handler;

import osmachko.com.ua.entity.Script;

import java.util.Collection;
import java.util.List;

/**
 * Created by Valerii_Osmachko on 3/29/2017.
 */
public interface JSHandler {

    Script addIntoStorageAndExecuteScript(Script script);

    List<Script> getAllScripts();

    void stopJSExecution(String scriptId);

    void deleteScript(String scriptId);

}
