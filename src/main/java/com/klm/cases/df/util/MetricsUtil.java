package com.klm.cases.df.util;

import java.util.List;

import com.klm.cases.df.actuator.Root;
import com.klm.cases.df.actuator.Trace;
import com.klm.cases.df.model.Metrics;


public class MetricsUtil {

	private long status2xxCount, status4xxCount, status5xxCount, totalCount = 0;
	private long maxTime=Integer.MIN_VALUE, minTime = Integer.MAX_VALUE;
	private long totalResponseTime,averageTime=Long.MIN_VALUE;
	
	public Metrics processAndReturnMetrics(Root traceData) {
		List<Trace> traces = traceData.getTraces();
		if(traces!=null && traces.size() != 0) {
			traces.forEach((t) -> {
				if(t.getRequest().getUri().contains("/travel")) {
					totalCount++;
					int status = t.getResponse().getStatus();
					if(status>=200 && status<300)
						status2xxCount++;
					else if(status>=400 && status<500)
						status4xxCount++;
					else if(status>=500)
						status5xxCount++;
					totalResponseTime+=t.getTimeTaken();
					maxTime=Math.max(t.getTimeTaken(),maxTime);
					minTime=Math.min(t.getTimeTaken(),minTime);
				}
			});
		}
		averageTime=totalResponseTime/totalCount;
		Metrics metrics = new Metrics();
		metrics.setTotalCount(totalCount);
		metrics.setStatusOKCount(status2xxCount);
		metrics.setStatus4XXCount(status4xxCount);
		metrics.setStatus5XXCount(status5xxCount);
		metrics.setMaxTime(maxTime==Integer.MIN_VALUE?0:maxTime);
		metrics.setMinTime(minTime==Integer.MAX_VALUE?0:minTime);
		metrics.setAvgTime(averageTime);
		return metrics;
	}
}
