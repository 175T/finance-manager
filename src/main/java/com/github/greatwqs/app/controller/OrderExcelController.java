package com.github.greatwqs.app.controller;

import com.github.greatwqs.app.component.RequestComponent;
import com.github.greatwqs.app.domain.dto.OrderDownloadDto;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.interceptor.annotation.LoginRequired;
import com.github.greatwqs.app.service.OrderExcelService;
import com.github.greatwqs.app.service.OrderService;
import com.github.greatwqs.app.service.SubjectService;
import com.github.greatwqs.app.service.TicketTypeService;
import com.github.greatwqs.app.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

import static com.github.greatwqs.app.common.AppConstants.PAGE_ORDER_QUERY;

/**
 * 订单 Excel 导入 & 导出!
 *
 * @author greatwqs
 * Create on 2020/7/9
 */
@Slf4j
@RequestMapping("")
@Controller
public class OrderExcelController {

    @Autowired
    private RequestComponent requestComponent;

    @Autowired
    private TicketTypeService ticketTypeService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderExcelService orderExcelService;


    /***
     * 前端技术不够, 后端代码来凑!
     * 这里是在Ajax里面调用的!!!
     * 获取下载的地址, 再回传给前端, 前端再打开一个窗口下载!
     */
    @ResponseBody
    @LoginRequired
    @RequestMapping(value = "order_get_download_url")
    public String orderDownloadUrl(
            HttpServletResponse response,
            @RequestParam(name = "page1", required = false, defaultValue = "1") Integer startPage,
            @RequestParam(name = "page2", required = false, defaultValue = "1") Integer endPage,
            @RequestParam(name = "startTime", required = false) String createTimeStart,
            @RequestParam(name = "endTime", required = false) String createTimeEnd,
            @RequestParam(name = "queryName", required = false) String orderAccount,
            @RequestParam(name = "queryReceipt", required = false) String ticketNo,
            @RequestParam(name = "queryFashion", required = false) String payType,
            @RequestParam(name = "queryGrade", required = false) String orderClass,
            @RequestParam(name = "queryRemark", required = false) String orderDes,
            @RequestParam(name = "business", required = false) String business,
            @RequestParam(name = "queryProject", required = false) String subjectId,
            @RequestParam(name = "queryMoneys", required = false, defaultValue = "0") Integer orderPriceType
    ) throws IOException {
        OrderDownloadDto downloadDto = new OrderDownloadDto(
                createTimeStart, createTimeEnd, orderAccount, ticketNo,
                payType, orderClass, orderDes, subjectId, orderPriceType,
                startPage, endPage);
        log.info("order_download api, query params: " + JsonUtil.toJson(downloadDto));
        return "order_download?" + requestComponent.getRequest().getQueryString();
    }

    /***
     * 订单导出页面, 前端传参
     * http://finance.myrealhost.com:9001/querySelectPageServlet?
     * page1=1&page2=1&startTime=&endTime=&queryName=&queryReceipt=&queryFashion=&queryGrade=&queryProject=&queryRemark=&business=
     *
     * page1=1&             startPage
     * page2=1&             endPage
     * startTime=&          startTime: 2020-07-01
     * endTime=&            endTime: 2020-07-04
     * queryName=&          queryName: OrderAccount
     * queryReceipt=&       queryReceipt: TicketNo
     * queryFashion=&       queryFashion: PayType
     * queryGrade=&         queryGrade: OrderClass
     * queryProject=&       queryProject: subjectId
     * queryRemark=&        queryRemark: OrderDes
     * business=            business: {暂时找不到对应的字段}
     */
    @LoginRequired
    @RequestMapping(value = "order_download")
    public void orderDownloadFile(
            HttpServletResponse response,
            @RequestParam(name = "page1", required = false, defaultValue = "1") Integer startPage,
            @RequestParam(name = "page2", required = false, defaultValue = "1") Integer endPage,
            @RequestParam(name = "startTime", required = false) String createTimeStart,
            @RequestParam(name = "endTime", required = false) String createTimeEnd,
            @RequestParam(name = "queryName", required = false) String orderAccount,
            @RequestParam(name = "queryReceipt", required = false) String ticketNo,
            @RequestParam(name = "queryFashion", required = false) String payType,
            @RequestParam(name = "queryGrade", required = false) String orderClass,
            @RequestParam(name = "queryRemark", required = false) String orderDes,
            @RequestParam(name = "business", required = false) String business,
            @RequestParam(name = "queryProject", required = false) String subjectId,
            @RequestParam(name = "queryMoneys", required = false, defaultValue = "0") Integer orderPriceType
    ) throws IOException {
        UserPo userPo = requestComponent.getLoginUser();
        OrderDownloadDto downloadDto = new OrderDownloadDto(
                createTimeStart, createTimeEnd, orderAccount, ticketNo,
                payType, orderClass, orderDes, subjectId, orderPriceType,
                startPage, endPage);
        log.info("order_download api, query params: " + JsonUtil.toJson(downloadDto));
        orderExcelService.download(response, downloadDto, userPo);
    }

    /***
     * 订单上传页面, 前端传参Excel文件
     */
    @LoginRequired
    @RequestMapping(value = "order_upload")
    public void orderUploadExcel(
            HttpServletResponse response,
            @RequestParam("leadExcel") MultipartFile file
    ) throws IOException, ParseException {
        UserPo userPo = requestComponent.getLoginUser();
        orderExcelService.upload(file, userPo);
        // 上传成功后定位到首页
        response.setStatus(HttpStatus.FOUND.value());
        response.sendRedirect(PAGE_ORDER_QUERY);
    }

}
