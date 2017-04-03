package osmachko.com.ua.storage;

import osmachko.com.ua.entity.Script;
import java.util.List;
import java.util.Map;

/**
 * Created by Valerii_Osmachko on 3/30/2017.
 */
public interface IStorage<K, V> {

    Map<K, V> getStorage();

    List<Script> getScripts();


}
