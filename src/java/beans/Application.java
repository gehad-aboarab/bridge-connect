/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Gehad
 */
@ManagedBean(name = "app")
@SessionScoped
public class Application {

    private HashMap<String, Boolean> selectMap = new HashMap<String, Boolean>();
    private HashMap<String, Boolean> managerProjectsMap = new HashMap<String, Boolean>();
    private String barData = null;
    private String selectedProject;
    private String selectedAdminProject;
    private String selectedAdminManager;
    private Projects currentProject;
    private Managers adminCurrentManager;
    private String selectedMaterial;
    private ArrayList<Plans> currentPlans;
    private String selectedStage;
    private String completed;
    private ArrayList<SelectItem> stages;
    private ArrayList<SelectItem> managers;
    private String materialQuantity;
    private String username;
    private String password;
    private String message;
    private Managers loggedUser;
    private String adminUsername;
    private String adminPassword;
    private Admin loggedAdmin;
    private String adminManagerFN;
    private String adminManagerLN;
    private String adminManagerAddress;
    private String adminManagerEmail;
    private String adminManagerPassword;
    private Object[] adminManagerProjects;
    private Object[] adminEditManagerProjects;
    private EntityManager em = null;

    public Application() {
        em = Persistence.createEntityManagerFactory("ConstructionPU").createEntityManager();
    }

//===============================SETTERS AND GETTERS===============================
    public Object[] getAdminEditManagerProjects() {
        return adminEditManagerProjects;
    }

    public void setAdminEditManagerProjects(Object[] adminEditManagerProjects) {
        this.adminEditManagerProjects = adminEditManagerProjects;
    }

    public Managers getAdminCurrentManager() {
        return adminCurrentManager;
    }

    public void setAdminCurrentManager(Managers adminCurrentManager) {
        this.adminCurrentManager = adminCurrentManager;
    }

    public String getAdminManagerFN() {
        return adminManagerFN;
    }

    public void setAdminManagerFN(String adminManagerFN) {
        this.adminManagerFN = adminManagerFN;
    }

    public String getAdminManagerLN() {
        return adminManagerLN;
    }

    public void setAdminManagerLN(String adminManagerLN) {
        this.adminManagerLN = adminManagerLN;
    }

    public String getAdminManagerAddress() {
        return adminManagerAddress;
    }

    public void setAdminManagerAddress(String adminManagerAddress) {
        this.adminManagerAddress = adminManagerAddress;
    }

    public String getAdminManagerEmail() {
        return adminManagerEmail;
    }

    public void setAdminManagerEmail(String adminManagerEmail) {
        this.adminManagerEmail = adminManagerEmail;
    }

    public String getAdminManagerPassword() {
        return adminManagerPassword;
    }

    public void setAdminManagerPassword(String adminManagerPassword) {
        this.adminManagerPassword = adminManagerPassword;
    }

    public Object[] getAdminManagerProjects() {
        return adminManagerProjects;
    }

    public void setAdminManagerProjects(Object[] adminManagerProjects) {
        this.adminManagerProjects = adminManagerProjects;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Managers getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Managers loggedUser) {
        this.loggedUser = loggedUser;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public Admin getLoggedAdmin() {
        return loggedAdmin;
    }

    public void setLoggedAdmin(Admin loggedAdmin) {
        this.loggedAdmin = loggedAdmin;
    }

    public String getMaterialQuantity() {
        return materialQuantity;
    }

    public void setMaterialQuantity(String materialQuantity) {
        this.materialQuantity = materialQuantity;
    }

    public void setStages(ArrayList<SelectItem> stages) {
        this.stages = stages;
    }

    public String getSelectedStage() {
        return selectedStage;
    }

    public void setSelectedStage(String selectedStage) {
        this.selectedStage = selectedStage;
    }

    public void setSelectMap(HashMap<String, Boolean> selectMap) {
        this.selectMap = selectMap;
    }

    public HashMap<String, Boolean> getSelectMap() {
        return this.selectMap;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public ArrayList<Plans> getCurrentPlans() {
        return currentPlans;
    }

    public void setCurrentPlans(ArrayList<Plans> currentPlans) {
        this.currentPlans = currentPlans;
    }

    public String getSelectedProject() {
        return selectedProject;
    }

    public Projects getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Projects currentProject) {
        this.currentProject = currentProject;
    }

    public String getSelectedMaterial() {
        return selectedMaterial;
    }

    public void setSelectedMaterial(String selectedMaterial) {
        this.selectedMaterial = selectedMaterial;
    }

    public void setManagers(ArrayList<SelectItem> managers) {
        this.managers = managers;
    }

    public HashMap<String, Boolean> getManagerProjectsMap() {
        return managerProjectsMap;
    }

    public void setManagerProjectsMap(HashMap<String, Boolean> managerProjectsMap) {
        this.managerProjectsMap = managerProjectsMap;
    }

    public String getSelectedAdminProject() {
        return selectedAdminProject;
    }

    public void setSelectedAdminProject(String selectedAdminProject) {
        this.selectedAdminProject = selectedAdminProject;
    }

    public String getSelectedAdminManager() {
        return selectedAdminManager;
    }

//===============================LOGIN, VALIDATION AND LOGOUT===============================
    public String login() throws NoSuchAlgorithmException {
        // get list of usernames to validate entered username
        Query q = em.createQuery("SELECT m from Managers m");
        List<Managers> res = q.getResultList();
        for (Managers m : res) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            String digest = new String(md.digest(password.getBytes()));
            if (m.getEmail().equals(username) && m.getPassword().equals(digest)) {
                loggedUser = m;
                return "listProjects";
            }
        }
        message = "Incorrect username or password";
        return "login";
    }

    public String adminLogin() throws NoSuchAlgorithmException {
        loggedAdmin = new Admin();
        MessageDigest md = MessageDigest.getInstance("MD5");
        String digest = new String(md.digest(adminPassword.getBytes()));
        if (loggedAdmin.getUsername().equals(adminUsername) && loggedAdmin.getPassword().equals(digest)) {
            return "adminCrud";
        }
        message = "Incorrect username or password";
        return "adminLogin";
    }

    public String checkLogin() {
        if (loggedUser == null) {
            return "login";
        }
        message = "";
        return null;
    }

    public String checkAdminLogin() {
        if (loggedAdmin == null) {
            return "adminLogin";
        }
        message = "";
        return null;
    }

    public String logout() {
        if (loggedUser != null) {
            loggedUser = null;
        } else if (loggedAdmin != null) {
            loggedAdmin = null;
        }
        message = "";
        return "login";
    }

//===============================MANAGER FUNCTIONALITIES===============================
    public void setSelectedProject(String selectedProject) {
        //retrieve the selected project from the manager's list of projects
        this.selectedProject = selectedProject;
        for (Projects p : loggedUser.getProjectsCollection()) {
            if (p.getProjectId() == Integer.parseInt(selectedProject)) {
                currentProject = p;
            }
        }
        //retrieve all the project plans (stages completed)
        currentPlans = new ArrayList<>();
        Query q = em.createNamedQuery("Plans.findByProjectId");
        q.setParameter("projectId", currentProject.getProjectId());
        List res = q.getResultList();
        for (Object o : res) {
            Plans p = (Plans) o;
            if (p.getCompleted() < 100 && p.getCompleted() > 0) {
                currentPlans.add(p);
            }
        }
    }

    public ArrayList<SelectItem> getProjects() {
        // return all projects that the logged manager works on
        ArrayList<SelectItem> projects = new ArrayList<>();
        if (loggedUser == null) {
            Query q = em.createNamedQuery("Projects.findAll");
            List res = q.getResultList();
            for (Object o : res) {
                Projects p = (Projects) o;
                projects.add(new SelectItem(p.getProjectId(), p.getProjectName(), ""));
            }
        } else {
            for (Projects p : loggedUser.getProjectsCollection()) {
                projects.add(new SelectItem(p.getProjectId(), p.getProjectName(), ""));
            }
        }

        return projects;
    }

    public ArrayList<SelectItem> getMaterials() {
        // retrieve a list of all available materials for purchase
        ArrayList<SelectItem> materials = new ArrayList<>();
        Query q = em.createNamedQuery("Materials.findAll");
        List res = q.getResultList();
        for (Object o : res) {
            materials.add(new SelectItem(((Materials) o).getMaterialId(), ((Materials) o).getMaterialName(), ""));
        }

        return materials;
    }

    public ArrayList<SelectItem> getStages() {
        // retrieve all stages of a project
        stages = new ArrayList<>();
        Query q = em.createNamedQuery("Stages.findAll");
        List res = q.getResultList();
        for (Object o : res) {
            stages.add(new SelectItem(((Stages) o).getStageId(), ((Stages) o).getStageName(), ""));
        }

        return stages;
    }

    public String updateProject() {
        // check the completion of the selected stage
        for (Plans p : currentPlans) { // stages under 100%
            if (p.getStages().getStageId() == Integer.parseInt(selectedStage)) { // if the selected stage is a current stage
                if (Integer.parseInt(completed) < p.getCompleted()) {
                    message = "Invalid entry, please try again";
                    return "updateProject";
                } else {
                    em.getTransaction().begin();
                    p.setCompleted(Integer.parseInt(completed)); // update the database
                    em.getTransaction().commit();
                    return "viewProject";
                }
            }
        }

        // retrieves all plans for that project
        Query q = em.createNamedQuery("Plans.findByProjectId");
        q.setParameter("projectId", currentProject.getProjectId());
        List allProjectPlans = q.getResultList();

        // checks if the selected stage is already completed
        for (Object o : allProjectPlans) {
            Plans p = (Plans) o;
            if (p.getStages().getStageId() == Integer.parseInt(selectedStage)) {
                if (p.getCompleted() == 100) {
                    message = "Invalid entry, please try again";
                    return "updateProject";
                }
            }
        }

        // checks if the the dependees of the selected stage are not completed
        q = em.createNamedQuery("Stages.findByStageId");
        q.setParameter("stageId", Integer.parseInt(selectedStage));
        List res = q.getResultList();
        Stages s = (Stages) res.get(0);
        Collection<Stages> dependees = s.getStagesCollection1();
        for (Stages dep : dependees) {
            for (Object obj : allProjectPlans) {
                Plans p = (Plans) obj;
                if (p.getStages().getStageId() == dep.getStageId() && p.getCompleted() < 100) {
                    message = "Invalid entry, please try again";
                    return "updateProject";
                }
            }
        }
        // at this point, the project has completed the dependees stages
        // and the plan for the selected stage needs to be updated
        em.getTransaction().begin();
        for (Object o : allProjectPlans) {
            Plans plan = (Plans) o;
            if (plan.getStages().getStageId() == s.getStageId()) {
                plan.setCompleted(Integer.parseInt(completed));
                break;
            }
        }
        em.getTransaction().commit();
        return "viewProject";
    }

    public String addMaterial() {
        // checks if material is already purchased and adds to its quantity
        Query q = em.createNamedQuery("ProjectMaterials.findByProjectId");
        q.setParameter("projectId", currentProject.getProjectId());
        List res = q.getResultList();
        for (Object o : res) {
            ProjectMaterials pm = (ProjectMaterials) o;
            if (pm.getMaterials().getMaterialId() == Integer.parseInt(selectedMaterial)) {
                em.getTransaction().begin();
                pm.setQuantity(pm.getQuantity() + Integer.parseInt(materialQuantity));
                pm.setTotalCost(pm.getTotalCost() + (Integer.parseInt(materialQuantity) * pm.getMaterials().getPrice()));
                em.getTransaction().commit();
                return "addMaterial";
            }
        }

        // adds a new material to the project materials
        em.getTransaction().begin();
        q = em.createNamedQuery("Materials.findByMaterialId");
        q.setParameter("materialId", Integer.parseInt(selectedMaterial));
        List mats = q.getResultList();
        ProjectMaterials pm = new ProjectMaterials();
        pm.setProjects(currentProject);
        pm.setQuantity(Integer.parseInt(materialQuantity));
        Materials m = (Materials) mats.get(0);
        pm.setMaterials(m);
        pm.setTotalCost(Integer.parseInt(materialQuantity) * m.getPrice());
        ProjectMaterialsPK pk = new ProjectMaterialsPK();
        pk.setMaterialId(m.getMaterialId());
        pk.setProjectId(currentProject.getProjectId());
        pm.setProjectMaterialsPK(pk);
        currentProject.getProjectMaterialsCollection().add(pm);
        em.persist(pm);
        em.getTransaction().commit();
        return "addMaterial";
    }

    public String removeMaterial() {
        // retrieves all materials for the current project and removes selected one/s
        Collection<ProjectMaterials> materials = currentProject.getProjectMaterialsCollection();
        Query q = em.createNamedQuery("ProjectMaterials.findByProjectId");
        q.setParameter("projectId", currentProject.getProjectId());
        List<ProjectMaterials> pmlist = q.getResultList();

        for (Iterator<ProjectMaterials> it = materials.iterator(); it.hasNext();) {
            ProjectMaterials entry = it.next();
            if (selectMap.get(entry.getMaterials().getMaterialId())) {
                it.remove();
                em.getTransaction().begin();
                for (ProjectMaterials pm : pmlist) {
                    if (pm.getMaterials().getMaterialId() == entry.getMaterials().getMaterialId()) {
                        q = em.createQuery("delete from ProjectMaterials m where m = :pm");
                        q.setParameter("pm", pm);
                        q.executeUpdate();
                    }
                }
                em.getTransaction().commit();
            }
        }
        return "viewProject";
    }

    public String getBarData() {
        Query q = em.createNamedQuery("Plans.findByProjectId");
        q.setParameter("projectId", currentProject.getProjectId());
        List res = q.getResultList();
        StringBuffer str = new StringBuffer();
        StringBuffer s = new StringBuffer();
        str.append("[['Stage', 'Completed', {role: 'style'}],");
        for (int i = 0; i < res.size(); i++) {
            Plans p = (Plans) res.get(i);
            str.append("['" + p.getStages().getStageName() + "', " + p.getCompleted() + ", 'blue'],");
        }
        str.append("]");
        barData = str.toString();
        System.out.println(barData);
        return barData;
    }

//===============================ADMIN FUNCTIONALITIES=======================================
    public ArrayList<SelectItem> getClients() {
        ArrayList<SelectItem> clients = new ArrayList<>();
        Query q = em.createNamedQuery("Clients.findAll");
        List res = q.getResultList();
        for (Object o : res) {
            Clients c = (Clients) o;
            clients.add(new SelectItem(c.getClientId(), c.getClientName(), ""));
        }
        return clients;
    }

    public void setSelectedAdminManager(String selectedAdminManager) {
        this.selectedAdminManager = selectedAdminManager;
        // get the manager selected and store in adminCurrentManager
        Query q = em.createNamedQuery("Managers.findByManagerId");
        q.setParameter("managerId", Integer.parseInt(selectedAdminManager));
        List res = q.getResultList();
        for (Object o : res) {
            Managers m = (Managers) o;
            if (m.getManagerId() == Integer.parseInt(selectedAdminManager)) {
                setAdminCurrentManager(m);
            }
        }
    }

    public ArrayList<SelectItem> getManagers() {
        managers = new ArrayList<>();
        Query q = em.createNamedQuery("Managers.findAll");
        List res = q.getResultList();
        for (Object o : res) {
            managers.add(new SelectItem(((Managers) o).getManagerId(), ((Managers) o).getFirstName() + " " + ((Managers) o).getLastName(), ""));
        }

        return managers;
    }

    public ArrayList<SelectItem> getFilteredProjects() {
        ArrayList<SelectItem> filtered = new ArrayList<>();
        Collection<Projects> managerProjects = adminCurrentManager.getProjectsCollection();
        Query q = em.createNamedQuery("Projects.findAll");
        List res = q.getResultList();

        for (Object o : res) {
            boolean found = false;
            Projects p = (Projects) o;
            for (Projects mp : managerProjects) {
                if (p.getProjectId() == mp.getProjectId()) {
                    found = true;
                }
            }
            if (!found) {
                filtered.add(new SelectItem(p.getProjectId(), p.getProjectName(), ""));
            }
        }
        return filtered;
    }

    public String addManager() throws NoSuchAlgorithmException {
        em.getTransaction().begin();
        Managers manager = new Managers();
        manager.setFirstName(adminManagerFN);
        manager.setLastName(adminManagerLN);
        manager.setAddress(adminManagerAddress);
        manager.setEmail(adminManagerEmail);
        MessageDigest md = MessageDigest.getInstance("MD5");
        manager.setPassword(new String(md.digest(adminManagerPassword.getBytes())));
        Collection<Projects> projects = new ArrayList<>();
        for (int i = 0; i < adminManagerProjects.length; i++) {
            int proj_id = Integer.parseInt(adminManagerProjects[i].toString());
            Projects p = em.find(Projects.class, proj_id);
            projects.add(p);
        }
        if (projects.size() > 0) {
            manager.setProjectsCollection(projects);
        }
        em.persist(manager);
        em.getTransaction().commit();
        return "adminCrud";
    }

    public String removeManager() {
        Query q = em.createNamedQuery("Managers.findByManagerId");
        q.setParameter("managerId", Integer.parseInt(selectedAdminManager));
        List<Managers> managers = q.getResultList();
        em.getTransaction().begin();
        q = em.createQuery("delete from Managers m where m = :mid");
        q.setParameter("mid", managers.get(0));
        q.executeUpdate();
        em.getTransaction().commit();
        return "adminCrud";
    }

    public String editManager() {
        Query q = em.createNamedQuery("Managers.findByManagerId");
        q.setParameter("managerId", Integer.parseInt(selectedAdminManager));
        List<Managers> managers = q.getResultList();
        Managers m = managers.get(0);
        em.getTransaction().begin();
        for (Object o : adminEditManagerProjects) {
            String s = (String) o;
            q = em.createNamedQuery("Projects.findByProjectId");
            q.setParameter("projectId", Integer.parseInt(s));
            List<Projects> projs = q.getResultList();
            Projects p = projs.get(0);
            m.getProjectsCollection().add(p);
        }
        em.getTransaction().commit();
        return "adminEditManager";
    }

    public String removeProject() {
        Query q = em.createNamedQuery("Projects.findByProjectId");
        q.setParameter("projectId", Integer.parseInt(selectedAdminProject));
        List<Projects> projects = q.getResultList();

        em.getTransaction().begin();
        q = em.createQuery("delete from ProjectMaterials p where p.projects = :pm");
        q.setParameter("pm", projects.get(0));
        q.executeUpdate();
        q = em.createQuery("delete from Plans p where p.projects = :pp");
        q.setParameter("pp", projects.get(0));
        q.executeUpdate();
        q = em.createNamedQuery("Managers.findAll");
        List res = q.getResultList();
        for (Object o : res) {
            Managers m = (Managers) o;
            Collection<Projects> managerProj = m.getProjectsCollection();
            for (Projects proj : managerProj) {
                if (proj.getProjectId() == Integer.parseInt(selectedAdminProject)) {
                    q = em.createQuery("delete from Manages p where p = :selectedp");
                    q.setParameter("selectedp", proj);
                    q.executeUpdate();
                }
            }
        }
        q = em.createQuery("delete from Projects p where p = :pp");
        q.setParameter("pp", projects.get(0));
        q.executeUpdate();
        em.getTransaction().commit();
        return "adminCrud";
    }

    public String removeManagerProjects() {
        // retrieves all manager projects and removes selected one/s
        Collection<Projects> projects = adminCurrentManager.getProjectsCollection();

        for (Iterator<Projects> it = projects.iterator(); it.hasNext();) {
            Projects entry = it.next();
            if (managerProjectsMap.get(entry.getProjectId())) {
                it.remove();
                em.getTransaction().begin();
                for (Projects p : projects) {
                    if (p.getProjectId() == entry.getProjectId()) {
                        Query q = em.createQuery("delete from Manages p where p = :selectedp");
                        q.setParameter("selectedp", p);
                        q.executeUpdate();
                    }
                }
                em.getTransaction().commit();
            }
        }
        return "adminEditManager";
    }

}
