package com.github.greatwqs.app.service;

import com.github.greatwqs.app.domain.dto.OrderDownloadDto;
import com.github.greatwqs.app.domain.dto.OrderDto;
import com.github.greatwqs.app.domain.dto.OrderSearchDto;
import com.github.greatwqs.app.domain.po.OrderPo;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.OrderVo;
import com.github.greatwqs.app.domain.vo.page.OrderPageVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * 订单模块 (收入和支出模块), Excel 上传&下载 模块
 *
 * @author greatwqs
 * Create on 2020/7/9
 */
public interface OrderExcelService {

    /**
     * 订单下载
     *
     * @param response
     * @param downloadDto
     * @param userPo
     */
    void download(HttpServletResponse response, OrderDownloadDto downloadDto, UserPo userPo) throws IOException;

    /***
     * Excel 表单数据上传
     * 表列: 0-时间, 1-缴费/收款人, 2-项目, 3-金额, 4-票据, 5-票据号, 6-收支方式, 7-备注, 8-班级
     *
     * @param file
     * @param userPo
     * @throws IOException
     */
    void upload(MultipartFile file, UserPo userPo) throws IOException, ParseException;
}
