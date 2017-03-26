package co.dmssolutions.beans;

import co.dmssolutions.RoadSection;
import co.dmssolutions.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author DMS Solutions
 */
@Named
@SessionScoped
public class AppBean implements Serializable {

    private static final String EMBEDDED_DATA_FILE = "data.txt";
//    private static final String EMBEDDED_DATA_FILE = "testData.txt";

    private UploadedFile _file;
    private RoadSection _roadSection;


    // Methods ------------------------------------------------------------------------------------
    /**
     * Resets environment
     */
    public void reset() {
        _file = null;
        initBean();
        addInfoMessage("Reset done");
    }


    /**
     * Inits environment
     */
    @PostConstruct
    public void initBean() {
        try {
            ArrayList<String> rawStatData;

            if (_file != null && _file.getSize() > 0) {
                rawStatData = Util.processRawStatData(_file.getInputstream());
            } else {
                rawStatData = Util.processRawStatData(new FileInputStream(loadEmbeddedDataFile(EMBEDDED_DATA_FILE)));
            }

            _roadSection = new RoadSection(Util.convertRawDataToVehicleList(rawStatData));

        } catch (Exception ex) {
            Logger.getLogger(AppBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Util methods -------------------------------------------------------------------------------
    public File loadEmbeddedDataFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(fileName).getFile());
    }


    public void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }


    public void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }


    public void addInfoMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }


    public void addWarnMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }


    public String isActivePage(String pageName) {
        if (FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith(pageName)) {
            return "active";
        }
        return "";
    }


    // Getters & Setters --------------------------------------------------------------------------
    public UploadedFile getFile() {
        return _file;
    }


    public void setFile(UploadedFile file) {
        if (file.getSize() > 0) {
            _file = file;
            addSuccessMessage("File '" + _file.getFileName() + "' was uploaded. Size: " + _file.getSize() + " bytes");
        }
    }


    public RoadSection getRoadSection() {
        return _roadSection;
    }


    public ArrayList<SelectItem> getDaysSI() {
        ArrayList<SelectItem> result = new ArrayList<>();
        for (int n = 0; n < _roadSection.getTotalDays(); n++) {
            result.add(new SelectItem(n, "Day " + (n + 1)));
        }
        return result;
    }

}
