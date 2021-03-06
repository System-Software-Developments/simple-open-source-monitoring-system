package com.osms.monitoring.monitor.os.parser.top;

import com.osms.monitoring.monitor.os.parser.AbstractCpuCalculator;
import com.osms.monitoring.util.NumberUtils;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WindowsCpuCalculator extends AbstractCpuCalculator {
  private static final Logger logger = LoggerFactory.getLogger(WindowsCpuCalculator.class);
  
  protected Map<String, Process> processes = new HashMap<String, Process>();
  
  // n2.PercentProcessorTime - n1.PercentProcessorTime)/(n2.Timestamp_Sys100NS - n1.Timestamp_Sys100NS)) * 100 / cores
  class Process {
    long processedTime;
    long timestamp;
  }
  
  private boolean prepared;
  private int cores;

  public WindowsCpuCalculator() {
    cores = Runtime.getRuntime().availableProcessors();
  }

  protected Map<Map<String, Object>, Double> doCalculate(Map<String, Map<String, Object>> values) {
    processes = clean(processes, values);
    
    Iterator<Entry<String, Map<String, Object>>> iterator = values.entrySet().iterator();
    while(iterator.hasNext()) {
      Entry<String, Map<String, Object>> item = iterator.next();
      String name = item.getKey();
      Map<String, Object> detail = item.getValue();
      
       Process prv = processes.get(name);
       double cpu = 0;
       if(prv == null) {
         prv = new Process();
         prv.processedTime = (long) detail.get("cpu");
         prv.timestamp = (long) detail.remove("timestamp");
         processes.put(name, prv);
       } else {
         long currentProcessedTime = (long) detail.get("cpu");
         long currentTimestamp = (long) detail.remove("timestamp");
         long processed = currentProcessedTime - prv.processedTime;
         cpu = ((float)(processed) / (float)(currentTimestamp - prv.timestamp)) * 100.f;
         cpu = cpu / cores;
         cpu = NumberUtils.rountTo2decimal(cpu);
         prv.timestamp = currentTimestamp;
         prv.processedTime = currentProcessedTime;
       }
       if(cpu < 0) {
         logger.debug("Name: {}, CPU: {}", name, cpu);
         cpu = 0;
       } else if(cpu >= 100) {
         cpu = 99.0;
       }
       detail.put("cpu", cpu);
    }
    //First time not send data.
    if(!prepared) {
      logger.info("CPU calculation not prepared.");
      prepared = true;
      return null;
    }
    
    Map<Map<String, Object>, Double> calculated = new HashMap<Map<String, Object>, Double> ();
    iterator = values.entrySet().iterator();
    while(iterator.hasNext()) {
      Entry<String, Map<String, Object>> item = iterator.next();
      Map<String, Object> value = item.getValue();
      calculated.put(value, (double) value.get("cpu"));
    }

    logger.debug("Calculated process count: {}", calculated.size());
    return calculated;
  }
  
  private Map<String, Process> clean(Map<String, Process> processes, Map<String, Map<String, Object>> values) {
    Map<String, Process> tmp = new HashMap<String, Process> (processes);
    Iterator<Entry<String, Process>> iterator = tmp.entrySet().iterator();
    
    while(iterator.hasNext()) {
      Entry<String, Process> process = iterator.next();
      if(!values.containsKey(process.getKey())) {
        processes.remove(process.getKey());
      }
    }
    return processes;
  }

}
