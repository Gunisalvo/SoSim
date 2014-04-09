package org.sosim.http.controladores;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.sosim.model.IoResource;
import org.sosim.model.PcbColor;
import org.sosim.model.SoSimProcess;
import org.sosim.simulation.PcbQueueFullException;
import org.sosim.simulation.SoSimSimulation;
import org.sosim.simulation.SoSimSimulationFactory;
import org.sosim.util.ApplicationRepositories;
import org.sosim.util.PropertiesRepository;

@ManagedBean
@ViewScoped
public class SoSimConsoleBean implements Serializable{

	private static final long serialVersionUID = 8114411214428541798L;

	transient private final PropertiesRepository icons = ApplicationRepositories.ICONS;
	
	//transient private final PropertiesRepository uiMessages = ApplicationRepositories.UI_MESSAGES;
	
	transient private final PropertiesRepository uiLabels = ApplicationRepositories.UI_LABELS;
	
	private final Integer REFRESH_INTERVAL = 1500;
	
	private Integer referesh;
	
	private Boolean ajaxPollRunning;
	
	private Boolean soSimSimulationRunning;
	
	private SoSimSimulation simulation = SoSimSimulationFactory.getDefaultSimulation();
	
	private final List<PcbColor> selectableColorList = PcbColor.getSelectableList();
	
	private IoResource nextResourceIcon;
	
	private PcbColor selectedColor;
	
	private Integer selectedPriority;
	
	private Integer frameNumber;
	
	public SoSimConsoleBean(){
		
		//System.out.println("ConsoleBean created!");
		this.ajaxPollRunning = true;
		this.soSimSimulationRunning = false;
		this.frameNumber = -1;
		
	}
	
	public void createProcess(){
		
		SoSimProcess created = new SoSimProcess(this.selectedColor,this.selectedPriority);
		
		try{
			this.simulation.createProcess(created);
		}catch (PcbQueueFullException ex){
			
		}

		
	}
	
	public synchronized void createProcessTest(){
		try{
			this.simulation.createProcess(new SoSimProcess(PcbColor.getRandomColor(), 1));
		}catch (PcbQueueFullException ex){
			
		}
	}
	
	public void redrawConsole(){
		
		if(isSoSimSimulationRunning()){
			this.simulation.incrementSimulationClock();
			this.frameNumber++;
			this.nextResourceIcon = IoResource.getRandomResource();
		}
		
	}
	
	public void startOrPauseSimulation(){
		if(isSoSimSimulationRunning()){
			pauseSimulation();
		}else{
			startSimulation();
		}
		
	}
	
	public void startSimulation(){
		
		if(this.frameNumber == -1){
			this.frameNumber++;
		}
		
		if(!this.soSimSimulationRunning){
			this.soSimSimulationRunning = true;
		}
		
	}
	
	public void pauseSimulation(){
		if(this.soSimSimulationRunning){
			this.soSimSimulationRunning = false;
		}
	}
	
	public void stopSimulation(){
		
		this.simulation = SoSimSimulationFactory.getDefaultSimulation();
		this.soSimSimulationRunning = false;
		this.frameNumber = -1;
		
	}
	
	public String getStartPauseButtonIcon(){
		
		if(isSoSimSimulationRunning()){
			return this.icons.getParameter("btn_pause");
		}else{
			if(this.simulation.isReady()){
				return this.icons.getParameter("btn_play");
			}else{
				return this.icons.getParameter("btn_play_disabled");
			}
		}
	}
	
	public String getStartPauseButtonState(){
		
		String state;
		
		if( this.simulation.isReady() ){
			state = "false";
		}else{
			state = "true";
		}
		
		return state;
	}
	
	public String getStopButtonIcon(){
		
		if( this.frameNumber >= 0 ){
			return this.icons.getParameter("btn_stop");
		}else{
			return this.icons.getParameter("btn_stop_disabled");
		}
		
	}
	
	public String getStopButtonState(){
		
		String state;
		
		if( this.frameNumber >= 0 ){
			state = "false";
		}else{
			state = "true";
		}
		
		return state;
	}
	
	public String getRunningPcbInfo(){
		
		if(!(this.simulation.getRunningProcess() == null)){
			return "PID:" + this.simulation.getRunningProcess().getId() + " Priority:"+this.simulation.getRunningProcess().getPriority();
		}else{
			return "--";
		}
	}
	public String getRunningPcbIcon(){
		
		if(!(this.simulation.getRunningProcess() == null)){
			return this.getPcbIconUrl(this.simulation.getRunningProcess());
		}else{
			return this.icons.getParameter(PcbColor.DISABLED.getIconCode());
		}
	}

	public String getIoPcbInfo(){
		
		if(!(this.simulation.getIoProcess() == null)){
			return "PID:" + this.simulation.getIoProcess().getId() + " Priority:"+this.simulation.getIoProcess().getPriority();
		}else{
			return "--";
		}
	}
	
	public String getIoPcbIcon(){
		
		if(!(this.simulation.getIoProcess() == null)){
			return this.getPcbIconUrl(this.simulation.getIoProcess());
		}else{
			return this.icons.getParameter(PcbColor.DISABLED.getIconCode());
		}
		
	}
	
	private String getPcbIconUrl(SoSimProcess process){
		
		return this.icons.getParameter(process.getColor().getIconCode());
		
	}
	
	public String getCpuIcon(){
		
		if(this.simulation.getRunningProcess() == null){
			return this.icons.getParameter("ico_cpu_disabled");
		}else{
			return this.icons.getParameter("ico_cpu");
		}
		
	}
	
	public String getIoIcon(){
		
		if(this.simulation.getIoProcess() == null){
			return this.icons.getParameter(IoResource.DISABLED.getIconCode());
		}else{
			return this.icons.getParameter(this.nextResourceIcon.getIconCode());
		}
		
	}
	
	public String getNextPcbForPriority( int priority ){

		if( this.simulation.getNextProcessForPriority(priority) == null ){
			return this.icons.getParameter(PcbColor.DISABLED.getIconCode());
		}else{
			SoSimProcess next = this.simulation.getNextProcessForPriority(priority);
			System.out.println(next);
			return this.icons.getParameter( next.getColor().getIconCode() );
		}
	}
	
	public String getUiLabel(String label){
		
		return this.uiLabels.getParameter(label);
		
	}
	
	public void setAjaxPollRunning(boolean ajaxPollRunning){
		this.ajaxPollRunning = ajaxPollRunning;
	}
	
	public boolean isAjaxPollRunning() {
		return ajaxPollRunning;
	}

	public boolean isSoSimSimulationRunning() {
		return soSimSimulationRunning;
	}

	public Integer getFrameNumber() {
		return frameNumber;
	}

	public PcbColor getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(PcbColor selectedColor) {
		this.selectedColor = selectedColor;
	}

	public List<PcbColor> getSelectableColorList() {
		return selectableColorList;
	}

	public void setRefreshInterval( int refresh ){
		this.referesh = refresh;
	}
	
	public Integer getRefreshInterval() {
		return REFRESH_INTERVAL;
	}
	
	public List<Integer> getAvailablePriorities(){
		return this.simulation.getAvailablePriorities();
	}
	
	@Override
	public void finalize(){
		//System.out.println("ConsoleBean destroyed!");
	}
}
