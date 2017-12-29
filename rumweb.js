function refreshContent() {
    loadRumRequestResponseSizeChart();
    loadRumAverageElapsedByWebserver();
       
}



var tafjrumRequestResponseChart = new RestfulCharts();
var rumAverageElapsedChart = null;
var rumWebData = new rumwebdata();


//var reloading;

function initContent(){
 var ch=document.getElementById("web_refresh").checked;
 if(ch==true){
    reloading=setInterval(refreshContent, 2000);   
 }
 if(ch==false){
    clearTimeout(reloading);
 }
}





var twcalc = new TimeCalc();




/*function initialiseContent(){
timeintervalsixth();
      initContent();
            var dt = new Date();
      var tzoff = dt.getTimezoneOffset();

       Highcharts.setOptions({
            global: {
                timezoneOffset: tzoff
               
            }
        }); */
  function initialiseContent(){     
  //timeintervalsixth();
  
    var dt = new Date();
    var tzoff = dt.getTimezoneOffset();

    Highcharts.setOptions({
     global: {
       timezoneOffset: tzoff
     }
    });     
    

            
  
    var rptinterval=1;
    rptinterval= $('#cmbAverageTime').val();
  var timetinterval = $('#cmbinterval').val();     
    
    tafjrumRequestResponseChart.errorMsgId='weberrormsg';
    tafjrumRequestResponseChart.categarr=["minute_time"];
    tafjrumRequestResponseChart.seriesarr=["avgRequestSize","avgResponseSize"];
    tafjrumRequestResponseChart.seriesnamearr=['Request Size','Response Size'];
    tafjrumRequestResponseChart.seriescolor=['#F4CB80','#F86877'];
    tafjrumRequestResponseChart.chartcontainerid='lastintervaldatasizechart';
    tafjrumRequestResponseChart.chartheadicon='rumreqrespicon';
    tafjrumRequestResponseChart.charttitle = 'Avg Request and Response Size';
    tafjrumRequestResponseChart.yaxistitle = 'Data Size in Bytes';
    tafjrumRequestResponseChart.charttype='area';
    //tafjrumRequestResponseChart.MAX_DATA_POINTS=3600;
     tafjrumRequestResponseChart.MAX_DATA_POINTS = timetinterval/rptinterval;;
    console.log("max points:::::"+tafjrumRequestResponseChart.MAX_DATA_POINTS);
    tafjrumRequestResponseChart.resturl=nestEapurl1+"webdata/getRumAvgReqRes?";
      //tafjrumRequestResponseChart.parturl="reportInterval="+rptinterval + "&" + twcalc.returnString;
      
    
    //var urlwebdata = tafjrumRequestResponseChart.resturl+tafjrumRequestResponseChart.parturl;
    //tafjrumRequestResponseChart.resturl = "localhost:8095/NestEAP/pulse/webdata/getRumAvgReqRes?";
    //tafjrumRequestResponseChart.nextFunctionCall=loadRumAverageElapsedByWebserver; // Will get executed only on Sucess
    tafjrumRequestResponseChart.nextFunctionCallonError=null; // Will get executed even when this call fails
    tafjrumRequestResponseChart.thisFunctionCallonError=null; // Will get executed even when there is an error in this function
    tafjrumRequestResponseChart.contenttype='text/plain';
    
    
    tafjrumRequestResponseChart.ptclickfn=function(){ 
      var timeValue=Highcharts.dateFormat('%H:%M:%S', this.category);  
      rumWebData.loadRumDataTable(timeValue); 
    };
    
    
    
    tafjrumRequestResponseChart.xlblfn = function() {
   // console.log("time format::::"+Highcharts.dateFormat('%H:%M:%S', this.value));
            return Highcharts.dateFormat('%H:%M:%S', this.value);
    };
    tafjrumRequestResponseChart.ttipshared = true;
    
    
    
    
    tafjrumRequestResponseChart.tooltipformatterfn = function() {
        var s = '<b>' + Highcharts.dateFormat('%H:%M:%S', this.x) + '</b>';
        $.each(this.points, function(i, point) {
            s += '<br/>' + point.series.name + ': ' + ' [' + (point.y) + ' Bytes]';
        });
        return s;
    };
    
      rumWebData.init(); 
      refreshContent();
      initContent();

    
    
    //rumWebData.init(); 
    
   // refreshContent();
    


}




function loadtimeintervalcombo(){
  if (document.getElementById('cmbinterval').value == "86400"){
    timeintervaleight();
    //timeintervalfirst();
  }else if(document.getElementById('cmbinterval').value == "43200"){
    timeintervalfirst();
  }else if(document.getElementById('cmbinterval').value == "28800"){
    timeintervalfirst();
  }else if(document.getElementById('cmbinterval').value == "14400"){
    timeintervalnine();
    //timeintervalsecond();
  }else if(document.getElementById('cmbinterval').value == "10800"){
    timeintervalnine();
    //timeintervalsecond();
  }else if(document.getElementById('cmbinterval').value == "7200"){
    timeintervalsecond();
  }else if(document.getElementById('cmbinterval').value == "3600"){
    timeintervalthird();
  }else if(document.getElementById('cmbinterval').value == "1800"){
    timeintervalforth();
  }else if(document.getElementById('cmbinterval').value == "900"){
    timeintervalforth();
  }else if(document.getElementById('cmbinterval').value == "600"){
    timeintervalforth();
  }else if(document.getElementById('cmbinterval').value == "300"){
    timeintervalforth();
  }else if(document.getElementById('cmbinterval').value == "120"){
    timeintervalfifth();
  }else if(document.getElementById('cmbinterval').value == "60"){
    timeintervalsixth();
  }else if(document.getElementById('cmbinterval').value == "30"){
    timeintervalseventh();
  }

}

var timeintervalcombo = document.getElementById("cmbAverageTime");

function timeintervalfirst(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per 10 Mins", "Per 30 Mins", "Per Hour"];
   var optionsvalue = ["600", "1800", "3600"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
   
}

function timeintervalsecond(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per Minute", "Per 10 Mins", "Per 30 Mins"];
   var optionsvalue = ["60", "600", "1800"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
   
}

function timeintervalthird(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per 30 Secs", "Per Minute", "Per 10 Mins"];
   var optionsvalue = ["30", "60", "600"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
   
}

function timeintervalforth(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per 15 Secs", "Per 30 Secs", "Per Minute"];
   var optionsvalue = ["15", "30", "60"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
   
}

function timeintervalfifth(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per Sec", "Per 15 Secs", "Per 30 Secs"];
   var optionsvalue = ["1", "15", "30"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
   
}

function timeintervalsixth(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per Sec", "Per 15 Secs"];
   var optionsvalue = ["1", "15"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
   
}

function timeintervalseventh(){
   
   $("#cmbAverageTime").empty();        
   
   var elem = document.createElement("option");
   elem.textContent = "Per Sec";
   elem.value = "1";
   timeintervalcombo.appendChild(elem);      
   
}

function timeintervaleight(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per 15 Mins","Per 30 Mins", "Per Hour"];
   var optionsvalue = ["900", "1800","3600"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
   
}

function timeintervalnine(){
   
   $("#cmbAverageTime").empty();
   
   var optionstext = ["Per 5 Mins","Per 10 Mins", "Per 15 Mins"];
   var optionsvalue = [ "300","600","900"];
   
   for(var i = 0; i < optionstext.length; i++) {
     var opttxt = optionstext[i];
     var optval = optionsvalue[i];
     var elem = document.createElement("option");
     elem.textContent = opttxt;
     elem.value = optval;
     timeintervalcombo.appendChild(elem);
   }
}





var rptinterval = 1;
function loadRumAverageElapsedByWebserver() {
        twcalc.intervalFieldContainer="cmbinterval";
      twcalc.calculateNow();
      
        rptinterval= $('#cmbAverageTime').val();
    var timetinterval = $('#cmbinterval').val();  
      
        var fullDate = twcalc.getTodaysDate();
        console.log("today's date:::"+fullDate);
        var chartcontainer = 'averageelapsedrumwebserverchart';
        $('#' + chartcontainer + " :first-child").show();
        var yaxistitle = 'Elapsed Time in msecs';
        var seriescolor = '#fcd271';
        var seriesname = 'Time Elapsed';
        var MAX_DATA_POINTS = timetinterval/rptinterval;
        var chartseriestitle = 'Avg webserver Elapsed for a given interval';
        var charttype = 'line';
        var headerimgcontainer = '';
        //var MAX_DATA_POINTS=900;
        console.log("twcalc full data sec::::"+JSON.stringify(twcalc));
        //var MAX_DATA_POINTS=timetinterval/rptinterval;
           var ptclick = function() {
             rumWebData.loadRumDataTable(this.category);
        }
        //var rptinterval = $('#cmbAverageTime').val();
        var urldata =nestEapurl1+"timingdata/getdata?reportInterval="+rptinterval+ "&" + twcalc.returnString;
          
       document.getElementById("weberrormsg").innerHTML="";
        getRestData(urldata, '', true,
            function(valJS) {
                try {
                    //lastChartInterval = rptinterval;
                    var aData = [];
                    var tdata = [],
                        unTdata = [];
                    var inc = 0;
                    $.each(valJS, function(key, obj) {
                        //tdata.push(TimeCalc.getStdTimeSecsFormat((obj.timeMsecs)*rptinterval));
                        tdata.push(TimeCalc.getStdTimeSecsFormat(obj.timeMsecs));
                        aData.push(obj.id_count);
                        //unTdata.push(obj.minute_time*1000);
                        inc++;
                        if (inc >= MAX_DATA_POINTS) {
                            return false;
                        }
                    });
                    console.log("INFO: Size =" + aData.length);
                    rumAverageElapsedChart = loadHCLine(chartcontainer, yaxistitle, chartseriestitle, seriesname, aData, tdata, seriescolor, null, ptclick);
                   // rumWebData.loadRumDataTable(tdata[tdata.length - 1]);
                } catch (err) {
                    console.trace();
                    console.error("Err :" + err.message);
                    document.getElementById("weberrormsg").innerHTML="No Data Available";  
                }
            },
            function() {
                document.getElementById("weberrormsg").innerHTML="No Data Available";
            });

 }
 
 
      var rptinterval=1;
function loadRumRequestResponseSizeChart() {
console.log("rum avg req res called");
document.getElementById("weberrormsg").innerHTML="";
  twcalc.intervalFieldContainer="cmbinterval";
  twcalc.calculateNow();
  

    rptinterval= $('#cmbAverageTime').val();
  var timetinterval = $('#cmbinterval').val();   
  
  
  console.log("twcalc full data::::"+JSON.stringify(twcalc));
  console.log("max points CALLED:::::"+tafjrumRequestResponseChart.MAX_DATA_POINTS);
    lastChartInterval= $('#cmbAverageTime').val();
  var timetinterval = $('#cmbinterval').val(); 
// Below needs to be done for every call
console.log("lastChartInterval::::"+lastChartInterval);
  tafjrumRequestResponseChart.parturl="reportInterval="+rptinterval+ "&" + twcalc.returnString;
  
  var fullDate = twcalc.getTodaysDate();
  
  //tafjrumRequestResponseChart.parturl="reportDate="+fullDate+ "&" + twcalc.returnString;

  //tafjrumRequestResponseChart.parturl="reportDate="+fullDate+ "&" + "starttime=1504767811178&endtime=1504767814506";

  tafjrumRequestResponseChart.executeDataIntoChart();
  lastChartInterval=1;
}

function rumwebdata(){
    this.RumDataDisplayDT = new DTWrapper();
    this.myPageTable = {isRunning: false };
    this.init=function(){
        var iam=this;
        iam.defineTables();
    };
    this.loadRumDataTable=function(timeEntry){
        var iam=this;
       if (iam.myPageTable.isRunning === false) {
             iam.myPageTable.isRunning=true;
              if (typeof timeEntry !== "undefined" && timeEntry !== null) {
                  var headerTitle = "RUM Display Data For Time " + timeEntry;
                  var tableid = "#rumwebdatatable";
                  var mtime = TimeCalc.getUnixTimeMs(timeEntry, "HH:mm:ss");
                  var gtoday = TimeCalc.getToday("YYYYMMDD");
                  
                  var fullData = "reportDate=" + gtoday + "&starttime=" + mtime + "&endtime=" + (mtime + (1000 * lastChartInterval));
          executeCallbackXHR(nestEapurl+'t24data/tabledata?', fullData, '',
                          function(valJS) {
                        iam.myPageTable.isRunning = false;
                              iam.RumDataDisplayDT.clearAndAdd(valJS);
                          },
                          function() { }, 'application/x-www-form-urlencoded', 'POST',true);
              } else {                  
            }

          } else {

          }

    };

    this.defineTables=function(){
        var iam=this;
        var RumDataDisplayData = ["web_time", "client_ip", "branch_mnemonic", "user_id", "app_server_name", "request_type",
                                  "req_key", "t24_error_msg", "client_pageload", "filter_process", "server_latency", "servlet_elapsed",
                                  "webt24_elapsed", "t24_elapsed", "db_elapsed", "client_total_elapsed"
                              ];

                              var RumDataDisplayHeader = ["Time", "IP Address", "Branch", "User ID", "Web Server", "Request Type",
                                  "Request Key", "TimeOut", "Client Page Load", "Pulse Plugin Time(In&nbsp;Secs)", "Server Latency", "Servlet Elapsed",
                                  "Interface Elapsed(In&nbsp;Secs)", "T24 Elapsed(In&nbsp;Secs)", "DB Elapsed(In&nbsp;Secs)", "Total Time"
                              ];

                              var DisRumDataDisplayData = {
                                  "data": RumDataDisplayData,
                                  "header": RumDataDisplayHeader,
                                "filter_process":{
                                    formatfn: function(milliseconds) {
                                   var seconds = (milliseconds/1000);
                      
                                 return (seconds.toFixed(0));
                                 }
                               },
                                  "webt24_elapsed":{
                                   formatfn: function(milliseconds) {
                                     var seconds = (milliseconds/1000);
                      
                                       return (seconds.toFixed(0));
                                    }
                                  },

                               "t24_elapsed":{
                                   formatfn: function(milliseconds) {
                                   var seconds = (milliseconds/1000);
                      
                                   return (seconds.toFixed(0));
                                    }
                                },
                               "db_elapsed":{
                                   formatfn: function(milliseconds) {
                                      var seconds = (milliseconds/1000);
                      
                                      return (seconds.toFixed(0));
                                        }
                                    },

                                  "t24_req_size": {
                                      visible: false
                                  },
                                   "client_ip": {
                                      visible: false
                                  },
                                   "branch_mnemonic": {
                                      visible: false
                                  }, 
                                  "t24_error_msg": {
                                      visible: false
                                  }, 
                                  "servlet_elapsed": {
                                      visible: false
                                  },  
                                  "client_total_elapsed": {
                                      visible: false
                                  },
                                  "web_time": {
                                      longtimeformat: 'HH:mm:ss'
                                  },
                                  
                              };

        iam.RumDataDisplayDT.createDT('rumwebdatatable', null, DisRumDataDisplayData, false, "", true, null, null, false, true);
    };
}

/*var tafjrumRequestResponseChart = new RestfulCharts();

var rumAverageElapsedChart = new RestfulCharts();

var rumWebData = new rumwebdata();

function initContent(){
 var ch=document.getElementById("web_refresh").checked;
 if(ch==true){
        reloading=setInterval(refreshContent, 3000);   }
 if(ch==false){
        clearTimeout(reloading);
 }
}

function refreshContent() {
  loadRumRequestResponseSizeChart();
}

var twcalc = new TimeCalc();
var twwebcalc = new TimeCalc();

function initialiseContent(){
  
  initContent();
    var dt = new Date();
  var tzoff = dt.getTimezoneOffset();

   Highcharts.setOptions({
        global: {
            timezoneOffset: tzoff
        }
    }); 
   rumWebData.init();   
      tafjrumRequestResponseChart.errorMsgId='weberrormsg';
      tafjrumRequestResponseChart.categarr=["minute_time"];
      tafjrumRequestResponseChart.seriesarr=["avgresponse","avgrequest"];
      tafjrumRequestResponseChart.seriesnamearr=['Request Size',"Response Size"];
      tafjrumRequestResponseChart.seriescolor=['#F4CB80','#F86877'];
      tafjrumRequestResponseChart.chartcontainerid='lastintervaldatasizechart';
      tafjrumRequestResponseChart.chartheadicon='rumreqrespicon';
      tafjrumRequestResponseChart.charttitle = 'Avg Request and Response Size';
      tafjrumRequestResponseChart.yaxistitle = 'Data Size in Bytes';
      tafjrumRequestResponseChart.charttype='area';
      tafjrumRequestResponseChart.MAX_DATA_POINTS=900;
      //tafjrumRequestResponseChart.resturl=nestEapurl+"realtimeactivity/getdata?";
      tafjrumRequestResponseChart.resturl=thiralApi+"realtimeactivity/getdata?";
      tafjrumRequestResponseChart.nextFunctionCallonError=null; // Will get executed even when this call fails
      tafjrumRequestResponseChart.thisFunctionCallonError=null; // Will get executed even when there is an error in this function
      tafjrumRequestResponseChart.contenttype='text/plain';
      tafjrumRequestResponseChart.ptclickfn=function(){   
        rumWebData.loadRumDataTable(Highcharts.dateFormat('%H:%M:%S', this.category));
      };
      tafjrumRequestResponseChart.xlblfn = function() {
          return Highcharts.dateFormat('%H:%M:%S', this.value);
      };
      tafjrumRequestResponseChart.ttipshared = true;
      tafjrumRequestResponseChart.tooltipformatterfn = function() {
          var s = '<b>' + Highcharts.dateFormat('%H:%M:%S', this.x) + '</b>';
          $.each(this.points, function(i, point) {
              s += '<br/>' + point.series.name + ': ' + ' [' + (point.y) + ' Bytes]';
          });
          return s;         
      };
      
      rumAverageElapsedChart.errorMsgId='weberrormsg';
      rumAverageElapsedChart.categarr=['minute_time'];
      rumAverageElapsedChart.seriesarr=['avgwebelapsed'];
      rumAverageElapsedChart.seriesnamearr=['Time Elapsed'];
      rumAverageElapsedChart.seriescolor=['#F4CB80'];
      rumAverageElapsedChart.chartcontainerid='averageelapsedrumwebserverchart';
      rumAverageElapsedChart.chartheadicon='';
      rumAverageElapsedChart.charttitle = 'Avg webserver Elapsed for a given interval';
      rumAverageElapsedChart.yaxistitle = 'Elapsed Time in msecs';
      rumAverageElapsedChart.charttype='line';
      rumAverageElapsedChart.MAX_DATA_POINTS=900;
      //rumAverageElapsedChart.resturl=nestEapurl+"realtimeactivity/getdata?";
      rumAverageElapsedChart.resturl=thiralApi+"realtimeactivity/getdata?";
      rumAverageElapsedChart.nextFunctionCallonError=null; // Will get executed even when this call fails
      rumAverageElapsedChart.thisFunctionCallonError=null; // Will get executed even when there is an error in this function
      rumAverageElapsedChart.contenttype='text/plain';
      rumAverageElapsedChart.ptclickfn=function(){   
        rumWebData.loadRumDataTable(Highcharts.dateFormat('%H:%M:%S', this.category));
      };
       rumAverageElapsedChart.nextFunctionCall=function(valJS){
          var tdata = [];
          $.each(valJS,function(key,val){
             tdata.push(Highcharts.dateFormat('%H:%M:%S', (val.minute_time)));
          });
              rumWebData.loadRumDataTable(tdata[tdata.length - 1]);
     }
      rumAverageElapsedChart.xlblfn = function() {
          return Highcharts.dateFormat('%H:%M:%S', this.value);
      };
      rumAverageElapsedChart.ttipshared = true;
      rumAverageElapsedChart.tooltipformatterfn = function() {
          var s = '<b>' + Highcharts.dateFormat('%H:%M:%S', this.x) + '</b>';
          $.each(this.points, function(i, point) {
              s += '<br/>' + point.series.name + ': ' + ' [' + (point.y) + ' Bytes]';
          });
          return s;         
      };
      
     
}

function loadRumRequestResponseSizeChart() {
  document.getElementById("weberrormsg").innerHTML="";
    twcalc.intervalFieldContainer="cmbinterval";
    twcalc.calculateNow();  
    tafjrumRequestResponseChart.parturl="reportDate="+twcalc.getTodaysDate()+ "&" + twcalc.returnStrictString;
    // + "&starttime=" + (twcalc.starttime).toFixed(0);
    tafjrumRequestResponseChart.executeDataIntoChart();
    lastChartInterval=1 ;
    
    document.getElementById("weberrormsg").innerHTML="";
    twwebcalc.intervalFieldContainer="cmbinterval";
    twwebcalc.calculateNow(); 
    rumAverageElapsedChart.parturl="reportDate="+twwebcalc.getTodaysDate() + "&" + twwebcalc.returnStrictString;
    //+ "&starttime=" + (twwebcalc.starttime).toFixed(0) ;
    rumAverageElapsedChart.executeDataIntoChart();
      lastChartInterval=1 ;
  }

function rumwebdata(){
    this.RumDataDisplayDT = new DTWrapper();
    this.myPageTable = {isRunning: false };
    this.init=function(){
        var iam=this;
        iam.defineTables();
    };
    this.loadRumDataTable=function(timeEntry){
        var iam=this;
       if (iam.myPageTable.isRunning === false) {
             iam.myPageTable.isRunning=true;
              if (typeof timeEntry !== "undefined" && timeEntry !== null) {
                  var headerTitle = "RUM Display Data For Time " + timeEntry;
                  var tableid = "#rumwebdatatable";
                  var mtime = TimeCalc.getUnixTimeMs(timeEntry, "HH:mm:ss");
                  var gtoday = TimeCalc.getToday("YYYYMMDD");
                  var fullData = "reportDate=" + gtoday + "&starttime=" + mtime + "&endtime=" + (mtime + (1000 * lastChartInterval));
          //executeCallbackXHR(nestEapurl+'/t24data/tabledata?', fullData, '',
          executeCallbackXHR(thiralApi+'t24data/tabledata?', fullData, '',
                          function(valJS) {
                        iam.myPageTable.isRunning = false;
                              iam.RumDataDisplayDT.clearAndAdd(valJS);
                          },
                          function() { }, 'application/x-www-form-urlencoded', 'POST',true);
              } else {                  
            }

          } else {

          }

    };

    this.defineTables=function(){
        var iam=this;
        var RumDataDisplayData = ["web_time", "client_ip", "branch_mnemonic", "user_id", "app_server_name", "request_type",
                                  "req_key", "t24_error_msg", "client_pageload", "filter_process", "server_latency", "servlet_elapsed",
                                  "webt24_elapsed", "t24_elapsed", "db_elapsed", "client_total_elapsed"
                              ];

                              var RumDataDisplayHeader = ["Time", "IP Address", "Branch", "User ID", "Web Server", "Request Type",
                                  "Request Key", "TimeOut", "Client Page Load", "Pulse Plugin Time(In&nbsp;Secs)", "Server Latency", "Servlet Elapsed",
                                  "Interface Elapsed(In&nbsp;Secs)", "T24 Elapsed(In&nbsp;Secs)", "DB Elapsed(In&nbsp;Secs)", "Total Time"
                              ];

                              var DisRumDataDisplayData = {
                                  "data": RumDataDisplayData,
                                  "header": RumDataDisplayHeader,
                                "filter_process":{
                                    formatfn: function(milliseconds) {
                                   var seconds = (milliseconds/1000);
                      
                                 return (seconds.toFixed(0));
                                 }
                               },
                                  "webt24_elapsed":{
                                   formatfn: function(milliseconds) {
                                     var seconds = (milliseconds/1000);
                      
                                       return (seconds.toFixed(0));
                                    }
                                  },

                               "t24_elapsed":{
                                   formatfn: function(milliseconds) {
                                   var seconds = (milliseconds/1000);
                      
                                   return (seconds.toFixed(0));
                                    }
                                },
                               "db_elapsed":{
                                   formatfn: function(milliseconds) {
                                      var seconds = (milliseconds/1000);
                      
                                      return (seconds.toFixed(0));
                                        }
                                    },

                                  "t24_req_size": {
                                      visible: false
                                  },
                                   "client_ip": {
                                      visible: false
                                  },
                                   "branch_mnemonic": {
                                      visible: false
                                  }, 
                                  "t24_error_msg": {
                                      visible: false
                                  }, 
                                  "servlet_elapsed": {
                                      visible: false
                                  },  
                                  "client_total_elapsed": {
                                      visible: false
                                  },
                                  "web_time": {
                                      longtimeformat: 'HH:mm:ss'
                                  },
                                  
                              };

        iam.RumDataDisplayDT.createDT('rumwebdatatable', null, DisRumDataDisplayData, false, "", true, null, null, false, true);
    };
}*/


