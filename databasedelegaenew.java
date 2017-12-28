package com.nys.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.nys.db.dao.NSIInterceptMgr;
import com.nys.db.dao.NSILockManager;
import com.nys.db.dao.NSIProcManager;
import com.nys.db.dao.NSISessionManager;
import com.nys.db.dao.NSITafjServiceMgr;
import com.nys.db.dao.NSITwsManager;
import com.nys.db.dao.filter.NSIBranchDashboardMgr;
import com.nys.db.dao.filter.NSIClientDataMgr;
import com.nys.db.dao.filter.NSIOfsmlManager;
import com.nys.db.dao.filter.NSIRealtimeMgr;
import com.nys.db.dao.filter.NSIT24Mgr;
import com.nys.db.dao.filter.NSITimingDataMgr;
import com.nys.db.dao.filter.NSIUserDashboardMgr;
import com.nys.db.dao.filter.NSIWebDataMgr;
import com.nys.pulse.objects.AvgTime;
import com.nys.pulse.objects.GenericAutoTime;
import com.pulse.jdbc.custom.objects.GenericAutoTimeData;
import com.nys.pulse.objects.CalendarData;
import com.nys.pulse.objects.OfsmlGenericAutoTime;
import com.nys.pulse.objects.TafjServiceGenericAutoTime;
import com.nys.pulse.objects.NSCProcIO;
import com.nys.pulse.objects.Time2AvgData;
import com.nys.t24.objects.NSCBranchDashboard;
import com.nys.t24.objects.NSCChannelFullData;
import com.nys.t24.objects.NSCClientData;
import com.nys.t24.objects.NSCLockData;
import com.nys.t24.objects.NSCOfsmlData;
import com.nys.t24.objects.NSCProcInfo;
import com.nys.t24.objects.NSCPulseInterceptData;
import com.nys.t24.objects.NSCPulseT24Data;
import com.nys.t24.objects.NSCPulseTwsData;
import com.nys.t24.objects.NSCRealTimeData;
import com.nys.t24.objects.NSCSesssionData;
import com.nys.t24.objects.NSCTafjService;
import com.nys.t24.objects.NSCUserDashboard;
import com.nys.t24.objects.NSCWebData;
import com.nys.t24.objects.T24FullData;
import com.nys.t24.objects.T24TimelineData;
import com.nys.t24.objects.BranchUserLstData;
import com.pulse.common.CommonFormat;
import com.pulse.jdbc.custom.objects.AvgReqResp;
import com.nys.pulse.objects.GroupedTimeData;
import com.pulse.jdbc.custom.objects.StringIds;
import com.pulse.logging.CIO;

public class DatabaseDelegate {
	




	static Logger log = Logger.getLogger(DatabaseDelegate.class.getName());
	
	NSISessionManager sessionMgrImpl;
	NSITwsManager twsDataMgrImpl;
	NSIInterceptMgr interceptMgrImpl;
	NSIProcManager procMgrImpl;
	NSIT24Mgr t24MgrImpl;
	static NSIClientDataMgr clientMgrImpl;
	NSIWebDataMgr webMgrImpl;
	NSITimingDataMgr timingMgrImpl;
	NSILockManager lockMgrImpl;
	private NSITafjServiceMgr tsMgrImpl;
	NSIUserDashboardMgr udMgrImpl;
	NSIBranchDashboardMgr bdMgrImpl;
	NSIRealtimeMgr rtMgrImpl;
	NSIOfsmlManager ofsmlMgrImpl;

	public NSIOfsmlManager getOfsmlMgrImpl() {
		return ofsmlMgrImpl;
	}
	public void setOfsmlMgrImpl(NSIOfsmlManager ofsmlMgrImpl) {
		this.ofsmlMgrImpl = ofsmlMgrImpl;
	}
public NSIRealtimeMgr getRtMgrImpl() {
		return rtMgrImpl;
	}
	public void setRtMgrImpl(NSIRealtimeMgr rtMgrImpl) {
		this.rtMgrImpl = rtMgrImpl;
	}
NSCUserDashboard uD=new NSCUserDashboard();


	public NSIBranchDashboardMgr getBdMgrImpl() {
		return bdMgrImpl;
	}
	public void setBdMgrImpl(NSIBranchDashboardMgr bdMgrImpl) {
		this.bdMgrImpl = bdMgrImpl;
	}
	public NSIUserDashboardMgr getUdMgrImpl() {
		return udMgrImpl;
	}
	public void setUdMgrImpl(NSIUserDashboardMgr udMgrImpl) {
		this.udMgrImpl = udMgrImpl;
	}
	public NSILockManager getLockMgrImpl() {
		return lockMgrImpl;
	}
	public void setLockMgrImpl(NSILockManager lockMgrImpl) {
		this.lockMgrImpl = lockMgrImpl;
	}
	public NSIProcManager getProcMgrImpl() {
		return procMgrImpl;
	}
	public void setProcMgrImpl(NSIProcManager procMgrImpl) {
		this.procMgrImpl = procMgrImpl;
	}
	public NSIInterceptMgr getInterceptMgrImpl() {
		return interceptMgrImpl;
	}
	public void setInterceptMgrImpl(NSIInterceptMgr interceptMgrImpl) {
		this.interceptMgrImpl = interceptMgrImpl;
	}
	public NSITwsManager getTwsDataMgrImpl() {
		return twsDataMgrImpl;
	}
	public void setTwsDataMgrImpl(NSITwsManager twsDataMgrImpl) {
		this.twsDataMgrImpl = twsDataMgrImpl;
	}
	public NSISessionManager getSessionMgrImpl() {
		return sessionMgrImpl;
	}
	public void setSessionMgrImpl(NSISessionManager sessionMgrImpl) {
		this.sessionMgrImpl = sessionMgrImpl;
	}
	
	public NSIT24Mgr getT24MgrImpl() {
		return t24MgrImpl;
	}
	public void setT24MgrImpl(NSIT24Mgr t24MgrImpl) {
		this.t24MgrImpl = t24MgrImpl;
	}
	
	public NSIWebDataMgr getWebMgrImpl() {
		return webMgrImpl;
	}
	public void setWebMgrImpl(NSIWebDataMgr webMgrImpl) {
		this.webMgrImpl = webMgrImpl;
	}

	public NSITimingDataMgr getTimingMgrImpl() {
		return timingMgrImpl;
	}
	public void setTimingMgrImpl(NSITimingDataMgr timingMgrImpl) {
		this.timingMgrImpl = timingMgrImpl;
	}
	public NSIClientDataMgr getClientMgrImpl() {
		return clientMgrImpl;
	}
	public void setClientMgrImpl(NSIClientDataMgr clientMgrImpl) {
		this.clientMgrImpl = clientMgrImpl;
	}
	public NSITafjServiceMgr getTsMgrImpl() {
		return tsMgrImpl;
	}
	public void setTsMgrImpl(NSITafjServiceMgr tsMgrImpl) {
		this.tsMgrImpl = tsMgrImpl;
	}
	
	public List<NSCSesssionData> getAlldata(){
		
		List<NSCSesssionData> sessionData=null;
		sessionData=sessionMgrImpl.getAllData();
		
		if(sessionData!=null)
			return sessionData;
		
			return sessionData;
		}
	
public List<CalendarData> getCalendarData(){
		
	    CalendarData caldata = null;
		List<CalendarData> calLstData = new ArrayList<CalendarData>();
		
		caldata = new CalendarData();
		caldata.setBadge(true);
		caldata.setDate("2017-03-09");
		caldata.setTitle("09/03/2017 Summary");
		caldata.setBody("<p class=\"lead\">Overall Branches</p><p> Request Count: 1200 </p>");
		caldata.setFooter("Top Request Done By: GB0010001");
		caldata.setClassname("purple-event");
		
		calLstData.add(0, caldata);
		
		caldata = new CalendarData();
		caldata.setBadge(true);
		caldata.setDate("2017-03-07");
		caldata.setTitle("07/03/2017 Summary");
		caldata.setBody("<p class=\"lead\">Overall Branches</p><p> Request Count: 2000 </p>");
		caldata.setFooter("Top Request Done By: GB0010001");
		caldata.setClassname("purple-event");
		
		calLstData.add(1, caldata);
		
		caldata = new CalendarData();
		caldata.setBadge(true);
		caldata.setDate("2017-03-02");
		caldata.setTitle("02/03/2017 Summary");
		caldata.setBody("<p class=\"lead\">Overall Branches</p><p> Request Count: 9340 </p>");
		caldata.setFooter("Top Request Done By: GB0010001");
		caldata.setClassname("purple-event");
		
		calLstData.add(2, caldata);
		
		
		return calLstData;				
}

public List<long[]> getDataByTimeForDygraph(Long stime, Long etime ,String reportInterval){
	System.out.println("getDatabytimefor dygraph");
	long[] allData = null;
	List<GenericAutoTime> t24Data = null;
	List<long[]> allLstData = new ArrayList<long[]>();
	t24Data=t24MgrImpl.getDataByTime(stime, etime, reportInterval);
	System.out.println("report Interval:::"+reportInterval);
	int ri = Integer.parseInt(reportInterval);
	System.out.println("ri::::::::::::::::::::"+ri);
	long et = etime/1000;
	if(t24Data!=null){			
		int j = 0;
		int k = ri, n = 0;
		long l = 0, m = 0;			
		for(long st = stime/1000; st <= et; st++){
						
		int status = 0; 
		
		if(ri>1){
		  
		  l=st+k;
		  if(st<l){				  
		    for(GenericAutoTime lstdata: t24Data){					    	
			    if(st == lstdata.getMinute_time()){		
				  m = m + lstdata.getId_count();
				  if(lstdata.getId_count()>0)
				  n++;
				}
			}	
		    k--;
		  }else if(st==l){				  
			  allData = new long[2];				  
			  allData[0] = st;
			  if(n==0)
			  n=1;
			  allData[1] = m/n;			 
			  status = 1;
			  st--;
			  k=ri;
			  m=0;
			  n=0;
		  }
																		  
		}else{
			
		  for(GenericAutoTime lstdata: t24Data){													    
			if(st == lstdata.getMinute_time()){		
			allData = new long[2];
			allData[0] = lstdata.getMinute_time();
			allData[1] = lstdata.getId_count();			
			status = 1;
		    }
		  }
		  
		  if(status == 0){	
				allData = new long[2];
				allData[0] = st;
				allData[1] = 0;								
				status = 1;
		  }
			
		}						
		
		if(status == 1){				
			allLstData.add(j, allData);
			j++;
		}
		
		}		
		return allLstData;
	}else{			
		int j = 0;
		for(long st = stime/1000; st <= et; st=st+ri){
			allData = new long[2];																			
			allData[0] = st;
			allData[1] = 0;			
																
			allLstData.add(j, allData);
			j++;			
		    
		}			
		return allLstData;
	}
		
 }
    
	public List<GenericAutoTime> getDataByTime(Long stime, Long etime ,String reportInterval){
		GenericAutoTime allData = null;
		List<GenericAutoTime> t24Data = null;
		List<GenericAutoTime> allLstData = new ArrayList<GenericAutoTime>();
		t24Data=t24MgrImpl.getDataByTime(stime, etime, reportInterval);
		
		int ri = Integer.parseInt(reportInterval);
//		System.out.println("ri======="+ri);
		long et = etime/1000;
		if(t24Data!=null){			
			int j = 0;
			int k = ri, n = 0;
			long l = 0, m = 0;			
			for(long st = stime/1000; st <= et; st++){
							
			int status = 0; 
			
			if(ri>1){
			  l=st+k;
			  if(st<l){				  
				//  System.out.println("st<1");
			    for(GenericAutoTime lstdata: t24Data){	
				    if(st == lstdata.getMinute_time()){	
					  m = m + lstdata.getId_count();
					  
					  if(lstdata.getId_count()>0)
					  n++;
					}
				}	
			    k--;
			  }else if(st==l){
				  //System.out.println("st value :: "+st);
				  allData = new GenericAutoTime();
				  allData.setMinute_time(st);
				  if(n==0)
				  n=1;
				  allData.setId_count(m/n);
				  allData.setTime_String("");
				  allData.setID(0);
				  status = 1;
				  st--;
				  k=ri;
				  m=0;
				  n=0;
			  }
																			  
			}else{
				
			  for(GenericAutoTime lstdata: t24Data){
				if(st == lstdata.getMinute_time()){		
				allData = new GenericAutoTime();
				allData.setMinute_time(lstdata.getMinute_time());
				allData.setId_count(lstdata.getId_count());
				allData.setTime_String(lstdata.getTime_String());
				allData.setID(lstdata.getID());		
				status = 1;
			    }
			  }
			  
			  if(status == 0){	
					allData = new GenericAutoTime();
					allData.setMinute_time(st);
					allData.setId_count(0);
					allData.setTime_String("");
					allData.setID(0);	
//					System.out.println("all data status zero of:::"+allData);
					status = 1;
			  }
				
			}						
			
			if(status == 1){
				//System.out.println("allLstData==="+allLstData);
				allLstData.add(j, allData);
				j++;
			}
			
			}		
			return allLstData;
		}else{			
			int j = 0;
			for(long st = stime/1000; st <= et; st=st+ri){
				allData = new GenericAutoTime();																			
				allData.setMinute_time(st);
				allData.setId_count(0);
				allData.setTime_String("");
				allData.setID(0);					
				allLstData.add(j, allData);
				j++;			
			    
			}			
			return allLstData;
		}
			
	 }	
		
     public List<T24TimelineData> getDataforTimeline(String bid,String uid,String sdate,String edate,Long stime,Long etime,Long ltime){			
		List<T24TimelineData> t24Data = null;		
		t24Data=t24MgrImpl.getDataforTimeline(bid,uid,sdate,edate,stime,etime,ltime);	
		return t24Data;			
	 }	
     
     public Object getNewDataCountforTimeline(String bid,String uid,String sdate,String edate,Long ntime){			 			 		
 		return t24MgrImpl.getNewDataCountforTimeline(bid,uid,sdate,edate,ntime);			
 	 }
     
     public List<BranchUserLstData> getBranchLst(){			 			 		
  		return t24MgrImpl.getBranchLst();			
  	 }
  
     public List<BranchUserLstData> getUserLst(){			 			 		
   		return t24MgrImpl.getUserLst();			
   	 }
     
	 public String getTime(String date,long stime,Long etime) throws Exception{
	    	String output = null;
			List<NSCWebData> webdata;
			Date formattedDate = new Date();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			
			DateFormat format =  new SimpleDateFormat("MM/dd/yyyy");
			try
			{
				formattedDate = df.parse(date);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				log.error(e);
			}
			 String webdate =format.format(formattedDate);
			
			webdata = webMgrImpl.getTime(webdate,stime,etime);
			output = CIO.ParseList(webdata);

			return output;
	 }
    public List<NSCWebData> getAllWebData() throws Exception{		
    	
    		List<NSCWebData> webData=null;
    			webData = webMgrImpl.getAllWebData();       
    		if(webData!=null)
    			return webData;
    		return webData;
    		
    				
    		}
	public static String getClientData(Long stime, Long etime) {
		String output = null;
		List<NSCClientData> clientdata;
		clientdata = clientMgrImpl.getClientData(stime,etime);
		output = CIO.ParseList(clientdata);
		return output;
	}
	public List<T24FullData> getRumDataTable(String starttime, String endtime, String date) 
	{
		List<T24FullData> lst = null;
		lst = t24MgrImpl.getRumDataTable(starttime, endtime, date);
		
		if(!lst.isEmpty())
			return lst;
		return lst;
	}
	public List<StringIds> getDistinctData(String distinct)
	{
		List<StringIds> lst = null;
		lst = t24MgrImpl.getDistinctData(distinct);
		return lst;
	}

	public String getRumTwsWebChart(String date ,long stime ){
		String output = null;
        	
		List<AvgTime> twsData = new ArrayList<AvgTime>();
		
		try {
			
			if(stime > 0) {
				long ctime = CommonFormat.getLongNow();
				long fromTime = ctime - (stime * 1000) ;
				twsData = twsDataMgrImpl.getRumTwsWebChart(date,fromTime);
								
			} else {
				twsData = twsDataMgrImpl.getRumTwsWebChart(date);	
			}
			output = CIO.ParseList(twsData);

		}
		catch(Exception ex){
			ex.printStackTrace();
			log.error(ex);
		}
		
		return output;
		
	}
	
	public String getRumTwsReqRespChart(String date,int stime) {
	
	String output = "";
		List<AvgReqResp> twsData = new ArrayList<AvgReqResp>();
	
		
	try {
	
		if(stime > 0) {
			long ctime = CommonFormat.getLongNow();
			long fromTime = ctime - (stime * 1000) ;
			twsData = twsDataMgrImpl.getRumTwsReqRespChart(date,fromTime);
		} 
		output = com.nys.common.CIO.convToJsonString(twsData);
	} catch(Exception ex){
		ex.printStackTrace();
		log.error(ex);
	}
	return output;
	}
	
	public String getRumTwsData(String date,long stime,long etime){
		
		String output = "";
		List<NSCPulseTwsData> twsData = new ArrayList<NSCPulseTwsData>();
		try{
			twsData = twsDataMgrImpl.getRumTwsData(date,stime,etime);
			
			output = CIO.ParseList(twsData);
			return output;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return output;
		
	}
	
	public List<NSCPulseInterceptData> getAllInterceptData()
	{
		List<NSCPulseInterceptData> lst = null;
		lst = interceptMgrImpl.getAllInterceptData();
		if(!lst.isEmpty())
			return lst;
		
		return lst;
	}
	
	/*public List<StringIds> getProcDistinctData(String request)
	{
		List<StringIds> procData = null;
		System.out.println("delegate");
		procData = procMgrImpl.getProcDistinctData(request);
		if(!procData.isEmpty())
			return procData;
		return null;
	}
	public String getProcErrorData(String date,String hostname){
		String procData = null;
		procData = procMgrImpl.getProcErrorData(date,hostname);
		return procData;	
		
	}*/
	
	public List<NSCProcInfo> getAllProcData(){
		List<NSCProcInfo> procData=null;
		procData=procMgrImpl.getAllProcData();
		return procData;
	}
	public List<T24FullData> getRumAvgDataTable(String sDate,Long stTime, Long endTime) 
	{
		List<T24FullData> lst = null;
		lst = t24MgrImpl.getRumAvgDataTable( sDate,stTime,  endTime);
		return lst;
	}
//	public List<Time2AvgData> getRumAvgReqRes(String date, String stime, String etime) {
//
//		List<Time2AvgData> lst = null;
//		lst = webMgrImpl.getRumAvgReqRes(date, stime, etime);
//		return lst;
//	}
	
	
	
	
	
	
	
	
	
	
	public List<Time2AvgData> getRumAvgReqRes(String reportInterval,String stime, String etime){
        Long sTime = Long.parseLong(stime);
        Long eTime = Long.parseLong(etime);
        Time2AvgData allData = null;
        List<Time2AvgData> webData = null;
        List<Time2AvgData> allLstData = new ArrayList<Time2AvgData>();
        webData=webMgrImpl.getRumAvgReqRes(reportInterval,stime, etime);
        
        //System.out.println("webData:::"+webData);
//        int ri = Integer.parseInt(reportInterval);
        int ri = Integer.valueOf(reportInterval);
        //int ri = Integer.parseInt(reportInterval);
        
      System.out.println("ri======="+ri);
        long et = eTime/1000;
        if(webData!=null){          
            //System.out.println("online data = null");
            
    		int j = 0;
    		int k = ri, n = 0, p = 0;
    		long l = 0, m = 0,r=0;	      
    		String s = null;
            for(long st = sTime/1000; st <= et; st++){
                            //System.out.println("call 1");
            int status = 0; 
            
            if(ri>1){
            	
            	//System.out.println("startTime:::::::"+sTime);
            	//System.out.println("endtime:::::::::"+eTime);
                //System.out.println("ri>1");
                l=st+k;
                System.out.println("st value::::::"+st);
                //System.out.println("l value = "+l);
                if(st<l){         
                  //System.out.println("st<1");
                  for(Time2AvgData lstdata: webData){  
                    //System.out.println("generic auto time caled 639");
                  if(st*1000 == lstdata.getMinute_time()){
                      System.out.println("st+++++++"+st);
      				//allData.setMinute_time(lstdata.getMinute_time());
    				allData.setLinkid(lstdata.getLinkid());
    				allData.setAvgResponseSize(lstdata.getAvgResponseSize());
    				allData.setAvgRequestSize(lstdata.getAvgRequestSize());
    				allData.setsMinuteTime(lstdata.getsMinuteTime()); 
                      
                      
                    m = m + lstdata.getAvgResponseSize();
                    if(lstdata.getAvgResponseSize()>0)
                   n++;
                    r = r + lstdata.getAvgRequestSize();
                    if(lstdata.getAvgRequestSize()>0)
                   p++;
                    
                    
                    //s = s + lstdata.getsMinuteTime();

                  }
                } 
                  //System.out.println("first K = "+k);
                  k--;
                  //System.out.println("Second K = "+k);
                }else if(st==l){
                	//System.out.println("call st =1 ");
                	//System.out.println("st double check::"+st);
                  //System.out.println("line no 648");
                  allData = new Time2AvgData();
                  
                  allData.setMinute_time(st);
                  if(n==0)
                  n=1;
                  if(p==0)
                  p=1;
                  allData.setLinkid(0);
                  allData.setsMinuteTime("");
                  //allData.setLinkid(0);
                  allData.setAvgResponseSize(0);
                  allData.setAvgRequestSize(0);
                  status = 1;
                  st--;
                  k=ri;
                  m=0;
                  n=0;
                  p=0;
                }                          
              }                                    
            else{
                
              for(Time2AvgData lstdata: webData){
                  //System.out.println("line 351");
                  //System.out.println("st data::::"+st);
                  //System.out.println("lst data time:::"+lstdata.getMinute_time());
                if(st*1000 == lstdata.getMinute_time()){     
                    //System.out.println("st:::::::::: called");
                allData = new Time2AvgData();
				allData.setMinute_time(lstdata.getMinute_time());
				allData.setLinkid(lstdata.getLinkid());
				allData.setAvgResponseSize(lstdata.getAvgResponseSize());
				allData.setAvgRequestSize(lstdata.getAvgRequestSize());
				allData.setsMinuteTime(lstdata.getsMinuteTime());     
                status = 1;
                }
              }
              
              if(status == 0){  
                  //System.out.println("status = zero");
                    allData = new Time2AvgData();
					allData.setMinute_time(st);
					allData.setLinkid(0);
					allData.setAvgResponseSize(0);
					allData.setAvgRequestSize(0);
					allData.setsMinuteTime("");
					status = 1;
              }
                
            }                       
            
            if(status == 1){
                //System.out.println("status=1");
//              System.out.println("allLstData==="+allLstData);
                allLstData.add(j, allData);
                j++;
            }
            
            }       
            return allLstData;
        }else{          
            //System.out.println("t24data is null");
            int j = 0;
            for(long st = sTime/1000; st <= et; st=st+ri){
                allData = new Time2AvgData();                                                                            
                allData.setMinute_time(st);
				allData.setLinkid(0);
				allData.setAvgResponseSize(0);
				allData.setAvgRequestSize(0);
				allData.setsMinuteTime("");
              //  System.out.println("allData status one:::::");                  
                allLstData.add(j, allData);
                j++;            
                
            }           
            return allLstData;
        }
            
     }  
	
	
	
	
	
//	public List<Time2AvgData> getRumAvgReqRes(String date, String stime, String etime) {
//		Long sTime = Long.parseLong(stime);
//		Long eTime = Long.parseLong(etime);
//		Time2AvgData allData = null;
//		List<Time2AvgData> webData = null;
//		List<Time2AvgData> allWebdata1 = new ArrayList<Time2AvgData>();
//		webData=webMgrImpl.getRumAvgReqRes(date,stime,etime);	
//		Time2AvgData t2a = new Time2AvgData();
//		t2a.getAvgRequestSize();
//		t2a.getMinute_time();
//		int ri = Integer.parseInt(date);
//		long et = eTime/1000;
//		if(webData!= null) {	
//			int status = 0; 
//			int j = 0;
//			int k = ri, n = 0;
//			long l = 0, m = 0;	
//		for(long st = sTime/1000; st <= et; st++){
//
//
//		if(ri<1){
//			  
//			  l=st+k;
//			  if(st<l){				  
//			    for(Time2AvgData lstdata: webData){					    	
//				    if(st == lstdata.getMinute_time()){	
//				    	System.out.println("st+++++++"+st);
//					  m = m + lstdata.getLinkid();
//					  if(lstdata.getLinkid()>0)
//					  n++;
//					}
//				}	
//			    k--;
//			  }else if(st==l){				  
//				  allData = new Time2AvgData();
//				  allData.setMinute_time(st);
//				  if(n==0)
//				  n=1;
//				  allData.getMinute_time();
//				  allData.getLinkid();
//				  allData.getAvgResponseSize();
//				  allData.getAvgRequestSize();
//				  allData.getsMinuteTime();
//				  status = 1;
//				  st--;
//				  k=ri;
//				  m=0;
//				  n=0;
//			  }
//																			  
//			}
//			else {
//			System.out.println("1st for condition callled");
//			for(Time2AvgData lstdata: webData){			
//				System.out.println("2nd for condition called");
//				if(st == lstdata.getMinute_time()){		
//					System.out.println("if condtion called");
//					System.out.println("st::::::::::"+st);
//				allData = new Time2AvgData();
//				allData.setMinute_time(lstdata.getMinute_time());
//				allData.setLinkid(lstdata.getLinkid());
//				allData.setAvgResponseSize(lstdata.getAvgResponseSize());
//				allData.setAvgRequestSize(lstdata.getAvgRequestSize());
//				allData.setsMinuteTime(lstdata.getsMinuteTime());		
//				status = 1;
//			    }
//			  }
//			  if(status == 0){	
//				  System.out.println("status = 0 called");
//					allData = new Time2AvgData();
//			allData.setMinute_time(st);
//			allData.setLinkid(0);
//			allData.setAvgResponseSize(0);
//			allData.setAvgRequestSize(0);
//			allData.setsMinuteTime("");
//			allWebdata1.add(j, allData);
//			j++;
//			  }
//			}						
//			if(status == 1){
//				System.out.println("status::::=="+status);
//
//				allWebdata1.add(j, allData);
//				System.out.println("all web data:::"+allWebdata1);
//				j++;
//			}
//			}
//			return allWebdata1;
//		}else {
//			System.out.println("web data else condition");
//			int j = 0;
//			for(long st = sTime/1000; st <= et; st=st++){
//				allData = new Time2AvgData();																			
//				allData.setMinute_time(st);
//				allData.setLinkid(0);
//				allData.setAvgResponseSize(0);
//				allData.setAvgRequestSize(0);
//				allData.setsMinuteTime("");
//				System.out.println("allData status one:::::+++"+allData);					
//				allWebdata1.add(j, allData);
//				j++;				    
//			}			
//			return allWebdata1;
//		}
//		}
	
	
	
	
	
	
	
	public List<NSCProcIO> getRealProcIO() {
		List<NSCProcIO> procData = null;
		procData = procMgrImpl.getRealProcIO();
		return procData;
	}
	/***
	 * Get Lock Data from H2 database
	 * @return
	 */
	public List<NSCLockData> getlockData(){
		try{
		List<NSCLockData> lockData;		
		lockData=lockMgrImpl.getLockData();
		return lockData;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	
	public String getAvgWebElapsedByServer1(String date,
			String stTime, String endTime) {
		try{
		    String lst = null;
			lst = timingMgrImpl.getAvgWebElapsedByServer1(date, stTime, endTime);
			return lst;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
//	public List<GroupedTimeData> getAvgWebElapsedByServer(String date,
//			String stTime, String endTime) {
//		try{
//		    List<GroupedTimeData> lst = null;
//			lst = timingMgrImpl.getAvgWebElapsedByServer(date, stTime, endTime);
//			return lst;
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		return null;
//	}
	
	
	public List<GroupedTimeData> getAvgWebElapsedByServer(String reportInterval, String stTime, String endTime){
//        Long sTime = Long.parseLong(stTime);
//        Long eTime = Long.parseLong(endTime);
    System.out.println("get Data By Time called");
    GroupedTimeData allData = null;
    List<GroupedTimeData> timingData = null;
    List<GroupedTimeData> allLstData = new ArrayList<GroupedTimeData>();
    timingData=timingMgrImpl.getAvgWebElapsedByServer(reportInterval,stTime, endTime);
    
    int ri = Integer.valueOf(reportInterval);
    Long sTime = Long.parseLong(stTime);
    Long eTime = Long.parseLong(endTime);
    long et = eTime/1000;
    if(timingData!=null){   

      int j = 0;
      int k = ri, n = 0;
      long l = 0, m = 0,lid = 0;   
      String serName = null;
      for(long st = sTime/1000; st <= et; st++){
              
      int status = 0; 
      
      if(ri>1){
        l=st+k;
        if(st<l){     
        	//System.out.println("st<1 called");
          for(GroupedTimeData lstdata: timingData){  
            if(st == lstdata.getTimeMsecs()){
                allData.setTimeMsecs(st);
                allData.setId_count(lstdata.getId_count());
                allData.setWeb_server_name(lstdata.getWeb_server_name());
                
                //m = m + lstdata.getId_count();
                //lid = lid + lstdata.getLinkid();
                //serName = lstdata.getWeb_server_name();
                //status = 1;
               //System.out.println(" m value :::"+m);
			  //if(lstdata.getId_count()>0)
			  //n++;
			  //serName = allData.getWeb_server_name();
          }
          
        } 
          k--;
        } else if(st==l){        
          allData = new GroupedTimeData();
          allData.setTimeMsecs(st);
          allData.setId_count(0);
          allData.setWeb_server_name("");
          //if(n==0)
          //n=1;
          //System.out.println("m valu = "+m);
          
//          System.out.println("server name:::"+serName);
//          if(m!=0){
//          allData.setWeb_server_name(serName);
//          }else {
//        	  allData.setWeb_server_name("");
//          }
          allData.setLinkid(0);
          status = 1;
          st--;
          k=ri;
          m=0;
          n=0;
         // serName = null;
        }
                                        
      }else{
        
        for(GroupedTimeData lstdata: timingData){
          //System.out.println("line 351");
        if(st == lstdata.getTimeMsecs()){   
          //System.out.println("st::::::::::");
        allData = new GroupedTimeData();
        allData.setTimeMsecs(lstdata.getTimeMsecs());
        allData.setId_count(lstdata.getId_count());
        allData.setWeb_server_name(lstdata.getWeb_server_name());
        status = 1;
          }
        }
        
        if(status == 0){  
          //System.out.println("status = zero");
          allData = new GroupedTimeData();
          allData.setTimeMsecs(st);
          allData.setId_count(0);
          allData.setWeb_server_name("");
//          System.out.println("all data status zero of:::"+allData);
          status = 1;
        }
        
      }           
      
      if(status == 1){
        allLstData.add(j, allData);
        j++;
      }
      
      }   
      return allLstData;
    }else{      
      //System.out.println("t24data is null");
      int j = 0;
      for(long st = sTime/1000; st <= et; st=st+ri){
    	  System.out.println("call else condition:::::");
        allData = new GroupedTimeData();                                      
          allData.setTimeMsecs(st);
          allData.setId_count(0);
          allData.setWeb_server_name("");
        //System.out.println("allData status one:::::");          
        allLstData.add(j, allData);
        j++;      
      }     
      return allLstData;
    }
   }  
	
	
	
	
//	public List<GroupedTimeData> getAvgWebElapsedByServer(String interval,String stTime, String endTime){
//      Long sTime = Long.parseLong(stTime);
//      Long eTime = Long.parseLong(endTime);
//
//			GroupedTimeData allData = null;
//			List<GroupedTimeData> timingData = null;
//			List<GroupedTimeData> allLstData = new ArrayList<GroupedTimeData>();
//			timingData=timingMgrImpl.getAvgWebElapsedByServer(interval,stTime, stTime);	
//			
//			int ri = Integer.parseInt(interval);
//			long et = eTime/1000;
//			if(timingData!=null){			
//				int j = 0;
//				int k = ri, n = 0;
//				long l = 0, m = 0;			
//				for(long st = sTime/1000; st <= et; st++){
//								
//				int status = 0; 
//				
//				if(ri>1){
//				  
//				  l=st+k;
//				  if(st<l){				 
//					  System.out.println("st < l called");
//				    for(GroupedTimeData lstdata: timingData){					    	
//					    if(st == lstdata.getTimeMsecs()){	
//					        allData = new GroupedTimeData();
//					        allData.setTimeMsecs(st);
//					        allData.setId_count(lstdata.getId_count());
//					        allData.setWeb_server_name(lstdata.getWeb_server_name());
//						  m = m + lstdata.getId_count();
//						  if(lstdata.getId_count()>0)
//						  n++;
//						}
//					}	
//				    k--;
//				  }else if(st==l){				  
//					  System.out.println("st == l called");
//					  allData = new GroupedTimeData();
//					  allData.setTimeMsecs(st);
//					  if(n==0)
//					  n=1;
//					  System.out.println("m value = "+m);
//					  allData.setId_count(m/n);
//					  allData.setWeb_server_name("");
//					  allData.setLinkid(0);
//					  status = 1;
//					  st--;
//					  k=ri;
//					  m=0;
//					  n=0;
//				  }
//																				  
//				}else{
//					
//				  for(GroupedTimeData lstdata: timingData){													    
//					if(st == lstdata.getTimeMsecs()){		
//					allData = new GroupedTimeData();
//					allData.setTimeMsecs(lstdata.getTimeMsecs());
//					allData.setId_count(lstdata.getId_count());
//					allData.setWeb_server_name(lstdata.getWeb_server_name());
//					allData.setLinkid(lstdata.getLinkid());		
//					status = 1;
//				    }
//				  }
//				  
//				  if(status == 0){	
//						allData = new GroupedTimeData();
//						allData.setTimeMsecs(st);
//						allData.setId_count(0);
//						allData.setWeb_server_name("");
//						allData.setLinkid(0);	
//						status = 1;
//				  }
//					
//				}						
//				
//				if(status == 1){				
//					allLstData.add(j, allData);
//					j++;
//				}
//				
//				}		
//				return allLstData;
//			}else{			
//				int j = 0;
//				for(long st = sTime/1000; st <= et; st=st+ri){
//					allData = new GroupedTimeData();																			
//					allData.setTimeMsecs(st);
//					allData.setId_count(0);
//					allData.setWeb_server_name("");
//					allData.setLinkid(0);	
//																		
//					allLstData.add(j, allData);
//					j++;			
//				    
//				}			
//				return allLstData;
//			}
//	}
	
	
	
	public String getRUMBranchSLAData(String branchid, String date, String stime, String etime) {
		String output = null;
		try{
			output = clientMgrImpl.getRUMBranchSLAData(branchid, date, stime, etime);
			return output;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return output;
	}
	public List<T24FullData> getAvgElapsedData(String reportDate, String user,
			String branch, String appServer, String webServer, String channel,
			Long sTime, Long eTime) {
		List<T24FullData> output = null;
		try{
			output = t24MgrImpl.getAvgElapsedData(reportDate,user,branch,appServer,webServer,channel,sTime,eTime);
			return output;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return output;
	}
	public String executeHistoryTimeQuery(String reportDate, String user,
			String branch, String appServer, String webServer, String stime,
			String etime, String rinterval, String channel) {
		String output = t24MgrImpl.executeHistoryTimeQuery(reportDate, user, branch, appServer, webServer, stime, etime, rinterval, channel);
		return output;
	}
	
	public List<NSCPulseT24Data> historicQuery(String userid, String app_name, String user_ip, String report_date, String activity, String branch,
			String server, String channel,String startTime, String endTime){
		return t24MgrImpl.historicQuery(userid, app_name, user_ip, report_date, activity, branch, server, channel, startTime, endTime);
	}
	
	
	public List<NSCTafjService> getAlltsData() {
		return tsMgrImpl.getAlltafjServiceData();
	}
	public void insertRecord(String appname,String vername,String appfn,String appid,String signon,String branchid,String contractstatus,String t24error,String ecode,
			long reqsize,long respsize,long eltime,long id,long reqstarttime,long t24_date,long t24_time, String raddr,String laddr) {
		tsMgrImpl.insertRecord(appname,vername,appfn,appid, signon, branchid, contractstatus, t24error, ecode,reqsize,respsize,eltime,id,reqstarttime,t24_date, t24_time, raddr,laddr);
	}
	
    public List<TafjServiceGenericAutoTime> getTafjServiceDataByTime(Long stime, Long etime ,String reportInterval){
		
		List<TafjServiceGenericAutoTime> tsData=null;
		tsData=tsMgrImpl.getDataByTime(stime, etime, reportInterval);	
		if(tsData!=null)
			return tsData;
			return tsData;			
		}	
	public List<NSCUserDashboard> getUserDashboardData(String date){
		
		return udMgrImpl.getUserDashboardData(date);
	}
	public List<NSCBranchDashboard> getBranchDashboardData(String date){
		return bdMgrImpl.getBranchDashboardData(date);
	}
    /*public void getUserDashboardData(String date){
		
		 udMgrImpl.getUserDashboardData(date);
	}
	public void getBranchDashboardData(String date){
		bdMgrImpl.getBranchDashboardData(date);
	}*/
	
	public void loadBranchDashboardData(){
		bdMgrImpl.loadBranchDashboard();
	}
	public void updateRealtimeData(){
		rtMgrImpl.updateRealtimeData();
	}
	public List<NSCRealTimeData> getRealtimeData(String date, long stime){
		return rtMgrImpl.getRealTimeData(date, stime);
	}
	
	//-----------------ofsml data-----------------------------
	public void insertOfsmlData(NSCOfsmlData data){
		try{
			ofsmlMgrImpl.insertOfsmlData(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//-----------------------ofsml online data--------------------
	public List<OfsmlGenericAutoTime> getofsmlOnlineData(long stime,long etime,String interval){
			OfsmlGenericAutoTime allData = null;
			List<OfsmlGenericAutoTime> ofsmlData = null;
			List<OfsmlGenericAutoTime> allLstData = new ArrayList<OfsmlGenericAutoTime>();
			ofsmlData=ofsmlMgrImpl.getDataByTime(stime, etime, interval);	
			
			int ri = Integer.parseInt(interval);
			long et = etime/1000;
			if(ofsmlData!=null){			
				int j = 0;
				int k = ri, n = 0;
				long l = 0, m = 0;			
				for(long st = stime/1000; st <= et; st++){
								
				int status = 0; 
				
				if(ri>1){
				  
				  l=st+k;
				  if(st<l){				  
				    for(OfsmlGenericAutoTime lstdata: ofsmlData){					    	
					    if(st == lstdata.getMinute_time()){		
						  m = m + lstdata.getId_count();
						  if(lstdata.getId_count()>0)
						  n++;
						}
					}	
				    k--;
				  }else if(st==l){				  
					  allData = new OfsmlGenericAutoTime();
					  allData.setMinute_time(st);
					  if(n==0)
					  n=1;
					  allData.setId_count(m/n);
					  allData.setTime_String("");
					  allData.setID(0);
					  status = 1;
					  st--;
					  k=ri;
					  m=0;
					  n=0;
				  }
																				  
				}else{
					
				  for(OfsmlGenericAutoTime lstdata: ofsmlData){													    
					if(st == lstdata.getMinute_time()){		
					allData = new OfsmlGenericAutoTime();
					allData.setMinute_time(lstdata.getMinute_time());
					allData.setId_count(lstdata.getId_count());
					allData.setTime_String(lstdata.getTime_String());
					allData.setID(lstdata.getID());		
					status = 1;
				    }
				  }
				  
				  if(status == 0){	
						allData = new OfsmlGenericAutoTime();
						allData.setMinute_time(st);
						allData.setId_count(0);
						allData.setTime_String("");
						allData.setID(0);	
						status = 1;
				  }
					
				}						
				
				if(status == 1){				
					allLstData.add(j, allData);
					j++;
				}
				
				}		
				return allLstData;
			}else{			
				int j = 0;
				for(long st = stime/1000; st <= et; st=st+ri){
					allData = new OfsmlGenericAutoTime();																			
					allData.setMinute_time(st);
					allData.setId_count(0);
					allData.setTime_String("");
					allData.setID(0);	
																		
					allLstData.add(j, allData);
					j++;			
				    
				}			
				return allLstData;
			}
	}

	
}
