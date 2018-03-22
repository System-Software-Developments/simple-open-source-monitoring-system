package com.osms.monitoring.monitor.os.parser;

import com.osms.monitoring.monitor.os.parser.top.TopResultParser;
import com.osms.monitoring.monitor.os.runner.CmdType;
import java.util.Map;

public class ParserFactory {
  
  public static ResultParser getTypeExcutor(CmdType cmdType, Map config) {
    if(cmdType == CmdType.cmd) {
      return new CommonResultParser(config);
    }
    else if(cmdType == CmdType.top) {
      return new TopResultParser(config);
    }
    return null;
  }
}
