package osmachko.com.ua.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Valerii_Osmachko on 3/28/2017.
 */
@Data
@Component
public class Script {

    private String id;
    private String status;
    private String script;
    private String consoleOutPut;
    private Object returnedValue;


}
