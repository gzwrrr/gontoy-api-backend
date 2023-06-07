package org.gontoy.gapi.framework.common.util.object;

import org.gontoy.gapi.framework.common.pojo.PageParam;

/**
 * {@link org.gontoy.gapi.framework.common.pojo.PageParam} 工具类
 *
 * @author 芋道源码
 */
public class PageUtils {

    public static int getStart(PageParam pageParam) {
        return (pageParam.getPageNo() - 1) * pageParam.getPageSize();
    }

}
