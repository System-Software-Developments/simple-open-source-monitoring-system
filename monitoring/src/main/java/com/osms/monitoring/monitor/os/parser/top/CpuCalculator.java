package com.osms.monitoring.monitor.os.parser.top;

import java.util.Map;

public interface CpuCalculator {

  Map<Map<String, Object>, Double> calculate(Map<String, Map<String, Object>> values);

}
