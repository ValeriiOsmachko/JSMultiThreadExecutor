package osmachko.com.ua.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import osmachko.com.ua.entity.Script;
import osmachko.com.ua.handler.JSHandler;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Valerii_Osmachko on 3/28/2017.
 */
@Service
@Slf4j
@PropertySource("classpath:engineconfig.properties")
public class JSExecutorServiceImpl implements JSExecutorService {

    @Resource(name = "${executionWay}")
    private JSHandler jsThreadHandler;

    @Override
    public void stopScript(String scriptId) {
        jsThreadHandler.stopJSExecution(scriptId);
    }

    @Override
    public Script executeScript(Script script) {
        log.debug("Passing script from service layer to handler for execution in thread");
        return jsThreadHandler.addIntoStorageAndExecuteScript(script);
    }

    @Override
    public List<Script> getAllScripts() {
        log.debug("Passing request for handler");
        return jsThreadHandler.getAllScripts();
    }

    @Override
    public void deleteScript(String scriptId) {
        jsThreadHandler.deleteScript(scriptId);
    }
}
