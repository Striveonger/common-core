package com.striveonger.common.third.prometheus.metric;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Prometheus Default Exporter
 * @author Mr.Lee
 * @since 2024-11-02 21:57
 */
@Controller
public class TailMetricController {
    private final Logger log = LoggerFactory.getLogger(TailMetricController.class);

    @Resource
    private MetricsContext context;

    @GetMapping(value = "/exporter/tail/metrics")
    public void metrics(HttpServletResponse response) {

        List<Metric> metrics = context.readAll();

        // 设置响应内容类型为文本
        response.setContentType("text/plain;charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            for (Metric metric : metrics) {
                out.print(metric.toString());
            }
        } catch (IOException e) {
            log.info("Monitor write fail...", e);
        }
    }

}