package <%=packageName%>;

import com.techolution.training.<%=capModelName%>;
import com.techolution.training.service.<%=capModelName%>Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/json/<%=modelName%>")
public class <%=capModelName%>RestController {

    final <%=capModelName%>Service <%=modelName%>Service;

    public <%=capModelName%>RestController(<%=capModelName%>Service <%=modelName%>Service) {
        this.<%=modelName%>Service = <%=modelName%>Service;
    }

    @GetMapping("/")
    public ResponseEntity<List<<%=capModelName%>>> get<%=capModelName%>() {
        List<<%=capModelName%>> <%=modelName%>s = <%=modelName%>Service.get<%=capModelName%>();
        if (<%=modelName%>s != null) {
            return new ResponseEntity(<%=modelName%>s, HttpStatus.OK);
        } else {
            return new ResponseEntity(<%=modelName%>s, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<v> get<%=capModelName%>(@PathVariable final String id) {
<%=capModelName%> <%=modelName%> = <%=modelName%>Service.get<%=capModelName%>(id);
        if (<%=modelName%> != null) {
            return new ResponseEntity(<%=modelName%>, HttpStatus.OK);
        } else {
            return new ResponseEntity(<%=modelName%>, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<<%=capModelName%>> create<%=capModelName%>(@RequestBody <%=capModelName%> <%=modelName%>) {
<%=capModelName%> saved<%=capModelName%> = <%=modelName%>Service.create<%=capModelName%>e(<%=modelName%>);
        if (saved<%=capModelName%> != null) {
            return new ResponseEntity(saved<%=capModelName%>, HttpStatus.CREATED);
        } else {
            return new ResponseEntity(saved<%=capModelName%>, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<<%=capModelName%>> update<%=capModelName%>(@PathVariable final String id, @RequestBody <%=capModelName%> <%=modelName%>) {
        <%=capModelName%> updated<%=capModelName%> = <%=modelName%>Service.update<%=capModelName%>(id, <%=modelName%>);
        if(updated<%=capModelName%>!= null) {
            return new ResponseEntity<<%=capModelName%>>(updated<%=capModelName%>, HttpStatus.OK);
        } else{
            return new ResponseEntity<<%=capModelName%>>(updated<%=capModelName%>, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
