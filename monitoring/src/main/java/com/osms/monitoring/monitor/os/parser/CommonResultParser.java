package com.osms.monitoring.monitor.os.parser;

import com.osms.monitoring.monitor.os.ConfigConstants;
import com.osms.monitoring.util.OsmsProperties;
import com.osms.monitoring.util.OsmsUtils;
import com.osms.monitoring.util.SizeUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonResultParser extends ResultParser {
  private final static Logger logger = LoggerFactory.getLogger(CommonResultParser.class);

  protected Map config;
  
  public CommonResultParser() {
  }
  
  public CommonResultParser(Map config) {
    this.config = config;
  }
  
  public Map<String, Object> parse(String result) {
    if(result == null) {
      return null;
    }
    String[] lines = result.split("\n");
    if(lines.length == 0) {
      return null;
    }
    
    return doMapping(lines);
  }

  protected Map<String, Object> doMapping(String[] lines) {
    Map<String, Object> resultMap = new HashMap<String, Object>();

    Set<String> keys = OsmsProperties.getList(config.get(ConfigConstants.key));
    Set<String> excludes = OsmsProperties.getList(config.get(ConfigConstants.exclude));

    Map offsetMap = (Map) config.get(ConfigConstants.offset);
    int offsetFrom = 0;
    int offsetTo = 0;
    if (offsetMap != null) {
      if (offsetMap.get(ConfigConstants.from) != null) {
        offsetFrom = Integer.valueOf((String) offsetMap.get(ConfigConstants.from));
      }
      if (offsetMap.get(ConfigConstants.to) != null) {
        offsetTo = Integer.valueOf((String) offsetMap.get(ConfigConstants.to));
      }
    }

    String maxColString = (String) config.get(ConfigConstants.maxCol);
    int maxCol = 0;
    if (maxColString != null && !maxColString.isEmpty()) {
      maxCol = Integer.valueOf(maxColString);
      logger.debug("Max column : {}", maxCol);
    }

    Map<String, Object> valuesMap = (Map<String, Object>) config.get(ConfigConstants.value);
    for (String k : valuesMap.keySet()) {
      Map<String, Object> valueMap = (Map<String, Object>) valuesMap.get(k);
      logger.debug("value map : {}", valueMap);
      int keyColumn = 0;
      if (valueMap.get(ConfigConstants.keyCol) != null) {
        keyColumn = Integer.valueOf((String) valueMap.get(ConfigConstants.keyCol));
      }
      int valueColumn = Integer.valueOf((String) valueMap.get(ConfigConstants.valueCol));
      String devicename = (String) valueMap.get(ConfigConstants.devicename);
      if (devicename != null) {
        devicename = devicename.trim();
      }
      Boolean keyStrict = Boolean.valueOf((String) valueMap.get(ConfigConstants.keystrict));
      String sizeunitstr = (String) valueMap.get(ConfigConstants.sizeunit);
      SizeUnit sizeunit = null;
      if (sizeunitstr != null && sizeunitstr.length() > 0) {
        sizeunit = SizeUnit.valueOf(sizeunitstr.trim());
      }
      String tag = (String) valueMap.get(OsmsProperties.Cons_tag);
      String desc = (String) valueMap.get(OsmsProperties.Cons_desc);
      
      for (int i = offsetFrom; i < (offsetTo == 0 ? lines.length : offsetTo); i++) {
        if (offsetTo > (lines.length + 1)) {
          logger.warn("offsetTo: {}, lines size: {}", offsetTo, lines.length);
          continue;
        }
        logger.trace("lines[{}]:{}", i, lines[i]);
        String[] split2;
        if (maxCol == 0) {
          split2 = lines[i].trim().split("\\s+");
        } else {
          split2 = lines[i].trim().split("\\s+", maxCol);
        }
        if (!(split2.length > keyColumn && split2.length > valueColumn)) {
          continue;
        }

        String column = split2[keyColumn];
        Object value = split2[valueColumn];
        if (value == null || value.toString().isEmpty()) {
          logger.warn("'{}' column has null value", column);
          continue;
        }

        if (keys == null) {
          if (excludes != null) {
            if (keyStrict) {
              if (excludes.contains(column)) {
                continue;
              }
            } else {
              boolean skip = false;
              for (String exclude : excludes) {
                if (column.indexOf(exclude) >= 0) {
                  skip = true;
                  break;
                }
              }
              if (skip) {
                continue;
              }
            }
          }
          if (sizeunit != null) {
            value = sizeunit.to(Long.valueOf((String) value));
          }
          devicename = OsmsUtils.getDevicename(devicename, column);
          resultMap.put(devicename, value);
          addDeviceMeta(devicename, value, tag, desc, column);
        } else {
          if (keyStrict) {
            if (keys.contains(split2[keyColumn])) {
              logger.debug(lines[i]);
              if (sizeunit != null) {
                value = sizeunit.to(Long.valueOf((String) value));
              }
              devicename = OsmsUtils.getDevicename(devicename, column);
              resultMap.put(devicename, value);
              addDeviceMeta(devicename, value, tag, desc, column);
            }
          } else {
            for (String key : keys) {
              if (split2[keyColumn].indexOf(key) >= 0) {
                logger.debug(lines[i]);
                if (sizeunit != null) {
                  value = sizeunit.to(Long.valueOf((String) value));
                }
                devicename = OsmsUtils.getDevicename(devicename, key);
                resultMap.put(devicename, value);
                addDeviceMeta(devicename, value, tag, desc, key);
                break;
              }
            }
          }
        }
      }
    }

    return resultMap;
  }

  protected void addDeviceMeta(String devicename, Object value, String tag, String desc, String key) {
    Map<String, Object> deviceMeta = OsmsProperties.getDeviceMeta(devicename);
    if(deviceMeta == null) {
      deviceMeta = new HashMap<String, Object> ();
      deviceMeta.put(OsmsProperties.Cons_value, value);
      if(tag != null) {
        deviceMeta.put(OsmsProperties.Cons_tag, tag);
      }
      if(desc != null) {      
        deviceMeta.put(OsmsProperties.Cons_desc, desc);
      }
      OsmsProperties.setDeviceMeta(devicename, deviceMeta);
    }
  }

}
