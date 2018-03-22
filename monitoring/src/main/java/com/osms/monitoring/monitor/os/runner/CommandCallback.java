package com.osms.monitoring.monitor.os.runner;

import java.util.Map;

public interface CommandCallback {
  void call(Map<String, Object> data);
}
