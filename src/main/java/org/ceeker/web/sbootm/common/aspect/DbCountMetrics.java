package org.ceeker.web.sbootm.common.aspect;

import java.util.Collection;

import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.Metric;

/**
 * 统计db被调用的次数，数据会上传到admin server
 * @author zhangxiaoling01
 * @date  2016年5月29日 下午12:35:19
 * @see
 */
public class DbCountMetrics implements PublicMetrics {

    @Override
    public Collection<Metric<?>> metrics() {
        // TODO Auto-generated method stub
        return null;
    }

//    private Collection<CrudRepository> repositories;
//
//    public DbCountMetrics(Collection<CrudRepository> repositories) {
//        this.repositories = repositories;
//    }
//
//    @Override
//    public Collection<Metric<?>> metrics() {
//        List<Metric<?>> metrics = new LinkedList<>();
//        for (CrudRepository repository : repositories) {
//            String name = DbCountRunner.getRepositoryName(repository.getClass());
//            String metricName = "counter.datasource." + name;
//            metrics.add(new Metric(metricName, repository.count()));
//        }
//        return metrics;
//    }

}
