package org.gontoy.gapi.module.infra.service.job;

import org.gontoy.gapi.framework.common.pojo.PageResult;
import org.gontoy.gapi.framework.quartz.core.service.JobLogFrameworkService;
import org.gontoy.gapi.module.infra.controller.admin.job.vo.log.JobLogExportReqVO;
import org.gontoy.gapi.module.infra.controller.admin.job.vo.log.JobLogPageReqVO;
import org.gontoy.gapi.module.infra.dal.dataobject.job.JobLogDO;

import java.util.Collection;
import java.util.List;

/**
 * Job 日志 Service 接口
 *
 * @author 芋道源码
 */
public interface JobLogService extends JobLogFrameworkService {

    /**
     * 获得定时任务
     *
     * @param id 编号
     * @return 定时任务
     */
    JobLogDO getJobLog(Long id);

    /**
     * 获得定时任务列表
     *
     * @param ids 编号
     * @return 定时任务列表
     */
    List<JobLogDO> getJobLogList(Collection<Long> ids);

    /**
     * 获得定时任务分页
     *
     * @param pageReqVO 分页查询
     * @return 定时任务分页
     */
    PageResult<JobLogDO> getJobLogPage(JobLogPageReqVO pageReqVO);

    /**
     * 获得定时任务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 定时任务分页
     */
    List<JobLogDO> getJobLogList(JobLogExportReqVO exportReqVO);

}
