package osmachko.com.ua.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import osmachko.com.ua.entity.Script;
import osmachko.com.ua.service.JSExecutorService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Valerii_Osmachko on 3/16/2017.
 */
@Slf4j
@RestController
public class ScriptController {

    @Autowired
    private JSExecutorService jsExecutorService;

    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/execute")
    public ResponseEntity<String> executeScript(@RequestBody String script) throws JsonProcessingException {
        log.debug(Marker.ANY_MARKER, "Getting script from request body and creating script object", script);
        Script jsScript = new Script();
        jsScript.setScript(script);
        jsExecutorService.executeScript(jsScript);
        String jsonInString = mapper.writeValueAsString(jsScript);
        log.debug(Marker.ANY_NON_NULL_MARKER, "Converting result of execution into json", jsScript);
        return new ResponseEntity<String>(jsonInString, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<String>> getAllScripts() {
        List<Script> list = jsExecutorService.getAllScripts();
        List<String> strings =
                list.stream().map(e -> {
                    try {
                        return convertScriptIntoString(e);
                    } catch (JsonProcessingException e1) {
                        return null;
                    }
                }).collect(Collectors.toList());
        return new ResponseEntity<List<String>>(strings, HttpStatus.OK);
    }

    @DeleteMapping("/stop/{id}")
    public ResponseEntity stopScriptExecution(@PathVariable String id) {
        jsExecutorService.stopScript(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteScript(@PathVariable String id) {
        jsExecutorService.deleteScript(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    private String convertScriptIntoString(Script script) throws JsonProcessingException {
        return mapper.writeValueAsString(script);
    }


}
