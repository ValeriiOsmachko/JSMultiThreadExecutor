package osmachko.com.ua.service;

import osmachko.com.ua.entity.Script;

import java.util.List;

/**
 * Created by Valerii_Osmachko on 3/28/2017.
 */
public interface JSExecutorService {

    void stopScript(String scriptId);

    Script executeScript(Script script);

    List<Script> getAllScripts();

    void deleteScript(String scriptId);

}
