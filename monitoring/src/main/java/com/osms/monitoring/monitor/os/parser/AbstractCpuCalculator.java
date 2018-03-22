package com.osms.monitoring.monitor.os.parser;

import com.osms.monitoring.monitor.os.parser.top.CpuCalculator;
import java.util.Map;

public abstract class AbstractCpuCalculator implements CpuCalculator {

  
  @Override
  public Map<Map<String, Object>, Double> calculate(Map<String, Map<String, Object>> values) {
    
    return doCalculate(values);
  }

  protected abstract Map<Map<String, Object>, Double> doCalculate(Map<String, Map<String, Object>> values);

}
