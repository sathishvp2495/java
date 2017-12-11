public List<Time2AvgData> getRumAvgReqRes(String date, String stime, String etime) {
		Long sTime = Long.parseLong(stime);
		Long eTime = Long.parseLong(etime);
		Time2AvgData allData = null;
		List<Time2AvgData> webData = null;
		List<Time2AvgData> allWebdata1 = new ArrayList<Time2AvgData>();
		webData=webMgrImpl.getRumAvgReqRes(date,stime,etime);	
		Time2AvgData t2a = new Time2AvgData();
		t2a.getAvgRequestSize();
		t2a.getMinute_time();
		int ri = Integer.parseInt(date);
		long et = eTime/1000;
		if(webData!= null) {	
			int status = 0; 
			int j = 0;
			int k = ri, n = 0;
			long l = 0, m = 0;	
		for(long st = sTime/1000; st <= et; st++){


		if(ri<1){
			  
			  l=st+k;
			  if(st<l){				  
			    for(Time2AvgData lstdata: webData){					    	
				    if(st == lstdata.getMinute_time()){	
				    	System.out.println("st+++++++"+st);
					  m = m + lstdata.getLinkid();
					  if(lstdata.getLinkid()>0)
					  n++;
					}
				}	
			    k--;
			  }else if(st==l){				  
				  allData = new Time2AvgData();
				  allData.setMinute_time(st);
				  if(n==0)
				  n=1;
				  allData.getMinute_time();
				  allData.getLinkid();
				  allData.getAvgResponseSize();
				  allData.getAvgRequestSize();
				  allData.getsMinuteTime();
				  status = 1;
				  st--;
				  k=ri;
				  m=0;
				  n=0;
			  }
																			  
			}
			else {
			System.out.println("1st for condition callled");
			for(Time2AvgData lstdata: webData){			
				System.out.println("2nd for condition called");
				if(st == lstdata.getMinute_time()){		
					System.out.println("if condtion called");
					System.out.println("st::::::::::"+st);
				allData = new Time2AvgData();
				allData.setLinkid(lstdata.getLinkid());
				allData.setAvgResponseSize(lstdata.getAvgResponseSize());
				allData.setAvgRequestSize(lstdata.getAvgRequestSize());
				allData.setsMinuteTime(lstdata.getsMinuteTime());		
				status = 1;
			    }
			  }
			  if(status == 0){	
				  System.out.println("status = 0 called");
					allData = new Time2AvgData();
			allData.setMinute_time(st);
			allData.setLinkid(0);
			allData.setAvgResponseSize(0);
			allData.setAvgRequestSize(0);
			allData.setsMinuteTime("");
			allWebdata1.add(j, allData);
			j++;
			  }
			}						
			if(status == 1){
				System.out.println("status::::=="+status);

				allWebdata1.add(j, allData);
				System.out.println("all web data:::"+allWebdata1);
				j++;
			}
			}
			return allWebdata1;
		}else {
			System.out.println("web data else condition");
			int j = 0;
			for(long st = sTime/1000; st <= et; st=st++){
				allData = new Time2AvgData();																			
				allData.setMinute_time(st);
				allData.setLinkid(0);
				allData.setAvgResponseSize(0);
				allData.setAvgRequestSize(0);
				allData.setsMinuteTime("");
				System.out.println("allData status one:::::+++"+allData);					
				allWebdata1.add(j, allData);
				j++;				    
			}			
			return allWebdata1;
		}
		}
