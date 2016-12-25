package org.ceeker.web.sbootm.common.monitor;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 统计db被调用的次数，数据会上传到admin server
 * @author zhangxiaoling01
 * @date  2016年5月29日 下午12:35:19
 * @see
 */
@Component
public class DbMetrics implements PublicMetrics {

    private Collection<CrudRepository> repositories;

    public DbMetrics(Collection<CrudRepository> repositories) {
        this.repositories = repositories;
    }

    @Override
    public Collection<Metric<?>> metrics() {
        List<Metric<?>> metrics = new LinkedList<>();
        for (CrudRepository repository : repositories) {
            String name = DbCountRunner.getRepositoryName(repository.getClass());
            String metricName = "counter.datasource." + name;
            metrics.add(new Metric(metricName, repository.count()));
        }
        return metrics;
    }

}
