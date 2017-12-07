String json = null;
List<Time2AvgData> list;
	public List<Time2AvgData> getRumAvgReqRes(String date, String stime, String etime) {
		System.out.println("getRumAvgReqRes called");
		System.out.println("date:::::"+date);
		System.out.println("stime::::::::::"+stime);
		System.out.println("etime::::::::::"+etime);
		Long sTime = Long.parseLong(stime);
		Long eTime = Long.parseLong(etime);
		Time2AvgData allData = null;
		List<Time2AvgData> webData = null;
		List<Time2AvgData> allWebdata1 = new ArrayList<Time2AvgData>();
		webData=webMgrImpl.getRumAvgReqRes(stime, etime, date);	
		Time2AvgData t2a = new Time2AvgData();
		t2a.getAvgRequestSize();
		t2a.getMinute_time();
		System.out.println("min time"+t2a.getMinute_time());
		System.out.println("req size"+t2a.getAvgRequestSize());
		long et = eTime/1000;
		if(t2a==null){
			System.out.println("t2a null");
		}else{
			System.out.println("t2a1 != NULL=="+t2a);
		}
		
		if(webData!= null) {
			System.out.println("not equal to null value");
			int j = 0;
			for(long st = sTime/1000; st <= et; st++){
				System.out.println("st::::"+st);
				
//				Gson gson = new Gson();
//				json = gson.toJson(T2A); 
				allData = new Time2AvgData();																			
//				allData.setMinute_time(allData.getMinute_time());
				allData.setMinute_time(st);
				allData.setLinkid(allData.getLinkid());
				allData.setAvgResponseSize(allData.getAvgResponseSize());
				allData.setAvgRequestSize(allData.getAvgRequestSize());
				allData.setsMinuteTime(allData.getsMinuteTime());				
				allWebdata1.add(j, allData);
				j++;	
			}
		}
			
		
			else{
			System.out.println("else condition");
			int w = 0;
			for(long st = sTime/1000; st <= et; st++){
			System.out.println("two");
			allData = new Time2AvgData();
			allData.setMinute_time(st);
			allData.setLinkid(0);
			allData.setAvgResponseSize(0);
			allData.setAvgRequestSize(0);
			allData.setsMinuteTime("");
			}
			
		}
		
		
		
		
		
		
		//		for(Time2AvgData lstdata: webData){	
//			System.out.println("minute_time:::::::"+lstdata.getMinute_time());
//		    if(st == lstdata.getMinute_time()){
//		    	System.out.println("called");
//		    }else {
//		    	System.out.println("not called");
//		    }
//		}
		return allWebdata1;
	}
