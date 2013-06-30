package blog.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import blog.version.VersionInfo;

@Controller
public class StatusController {

    @RequestMapping(value="/status", method = RequestMethod.GET)
    public String status(Map<String, Object> model) {
        model.put("version", VersionInfo.VERSION);
        model.put("buildDate", VersionInfo.BUILD_DATE);
        return "/status";
    }
    
    @RequestMapping(value="/status.txt", method = RequestMethod.GET, produces="text/plain")
    @ResponseBody
    public String statusText() {
        StringBuilder text = new StringBuilder();
        text.append("version=\"");
        text.append(VersionInfo.VERSION);
        text.append("\"\n");
        text.append("buildDate=\"");
        text.append(VersionInfo.BUILD_DATE);
        text.append("\"\n");
        return text.toString();
    }
    
}
