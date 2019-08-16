package movetracker.loogbook;

import java.util.List;
import java.util.Map;

public class Logbook {
    private  List<String> visitorNames;
    private  List<Integer> visitorId;
    private  Map<String, List<Integer>> logsByName;

    public Logbook(List<String> visitorNames, List<Integer> visitorId, Map<String, List<Integer>> logsByName) {
        this.visitorNames = visitorNames;
        this.visitorId = visitorId;
        this.logsByName = logsByName;
    }

    public List<String> getVisitorNames() {
        return visitorNames;
    }

    public void setVisitorNames(List<String> visitorNames) {
        this.visitorNames = visitorNames;
    }

    public List<Integer> getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(List<Integer> visitorId) {
        this.visitorId = visitorId;
    }

    public Map<String, List<Integer>> getLogsByName() {
        return logsByName;
    }

    public void setLogsByName(Map<String, List<Integer>> logsByName) {
        this.logsByName = logsByName;
    }
}
