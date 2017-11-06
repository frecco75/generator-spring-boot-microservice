package <%=packageName%>.service;

import com.techolution.model.<%=capModelName%>;
//import com.techolution.training.repository.<%=capModelName%>Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;


@Service
public class <%=capModelName%>Service {

    private final Logger log = LoggerFactory.getLogger(<%=capModelName%>Service.class);

//    private final <%=capModelName%>Repository <%=modelName%>Repository;

//    public <%=capModelName%>Service(<%=capModelName%>Repository <%=modelName%>Repository) {
//        this.<%=modelName%>Repository = <%=modelName%>Repository;
//    }

    public List<<%=capModelName%>> get<%=capModelName%>s() {
//        return <%=modelName%>Repository.findAll();
        return null;
    }

    public <%=capModelName%> get<%=capModelName%>(final String id) {
//        return <%=modelName%>Repository.findOne(id);
  return null;
    }

//    public <%=capModelName%> create<%=capModelName%>(final <%=capModelName%> <%=modelName%>) {
//        try{
//            return <%=modelName%>Repository.save(<%=modelName%>);
//        }catch (Exception ex) {
//            log.error("Exception occurs during saving/creating <%=modelName%> in mongo db for ", <%=modelName%>.getName());
//            return null;
//        }

  return null;
    }

    public <%=capModelName%> update<%=capModelName%>(final String id, <%=capModelName%> <%=modelName%>) {
      return null;
  }
//        <%=capModelName%> <%=modelName%>Db = <%=modelName%>Repository.findOne(id);
//        if(StringUtils.isEmpty(<%=modelName%>Db)) {
//            return null;
//        }
//        <%=modelName%>Db.setName(<%=modelName%>.getName());
//        <%=modelName%>Db.setPositionId(<%=modelName%>.getPositionId());
//        <%=modelName%>Db.setVideoLink(<%=modelName%>.getVideoLink());
//        <%=modelName%>Db.setAssessmentLink(<%=modelName%>.getAssessmentLink());
//        <%=modelName%>Db.setSubjects(<%=modelName%>.getSubjects());
//        try{
//            return <%=modelName%>Repository.save(<%=modelName%>Db);
//        }catch (Exception ex) {
//            log.error("Exception occurs during updating <%=modelName%> in mongo db for ", <%=modelName%>.getName());
//            return null;
//        }
//    }

}
