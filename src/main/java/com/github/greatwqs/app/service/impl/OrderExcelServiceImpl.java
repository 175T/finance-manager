package com.github.greatwqs.app.service.impl;

import com.github.greatwqs.app.common.AppConstants;
import com.github.greatwqs.app.domain.dto.OrderDownloadDto;
import com.github.greatwqs.app.domain.dto.OrderDto;
import com.github.greatwqs.app.domain.po.UserPo;
import com.github.greatwqs.app.domain.vo.OrderVo;
import com.github.greatwqs.app.domain.vo.SubjectVo;
import com.github.greatwqs.app.domain.vo.TicketTypeVo;
import com.github.greatwqs.app.manager.SubjectManager;
import com.github.greatwqs.app.manager.TicketTypeManager;
import com.github.greatwqs.app.mapper.OrderlistMapper;
import com.github.greatwqs.app.service.OrderExcelService;
import com.github.greatwqs.app.service.OrderService;
import com.github.greatwqs.app.utils.JsonUtil;
import com.github.greatwqs.app.utils.UrlUtil;
import com.github.greatwqs.app.utils.collection.Lists;
import com.github.greatwqs.app.utils.excel.ExcelExport;
import com.github.greatwqs.app.utils.excel.ExcelParseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author greatwqs
 * Create on 2020/7/9
 */
@Slf4j
@Service
public class OrderExcelServiceImpl implements OrderExcelService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrderlistMapper orderlistMapper;

    @Autowired
    private SubjectManager subjectManager;

    @Autowired
    private TicketTypeManager ticketTypeManager;

    @Autowired
    private OrderService orderService;

    @Override
    public void download(HttpServletResponse response, OrderDownloadDto downloadDto,
                         UserPo userPo) throws IOException {
        // orderVoList to ExcelExport
        List<OrderVo> orderVoList = orderService.queryDownload(downloadDto, userPo);
        ExcelExport<OrderVo> excelExport = buildExcelExport(orderVoList);

        // response
        response.setContentType("application/msexcel;charset=UTF-8");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
                this.buildFileDownloadHeader(excelExport.getFileName()));
        excelExport.downloadExcel(response.getOutputStream());
    }

    /***
     * http response header file name.
     * @param fileName
     * @return
     */
    private String buildFileDownloadHeader(String fileName) {
        return "attachment;filename*=UTF-8''" + UrlUtil.escapeContentDisposition(fileName);
    }

    /***
     * 构建 Excel 导出数据
     * @param orderVoList
     * @return
     */
    private ExcelExport<OrderVo> buildExcelExport(List<OrderVo> orderVoList) {
        ExcelExport<OrderVo> excelExport = initExcelExport();

        final String sheetName = "收支信息汇总表";
        HSSFWorkbook workbook = excelExport.toExcel(sheetName, orderVoList);
        excelExport.setHssfWorkbook(workbook);
        excelExport.setSheetName(sheetName);
        excelExport.setFileName(this.buildFileName());
        return excelExport;
    }

    /**
     * 导出文件名称
     *
     * @return
     */
    private String buildFileName() {
        final String dateTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        return "收支信息_$DATE.xls".replace("$DATE", dateTime);
    }

    /***
     * 初始化导出 Excel 的表头
     * @return
     */
    private ExcelExport<OrderVo> initExcelExport() {
        ExcelExport<OrderVo> excelExport = new ExcelExport<>();
        excelExport.setDateFormat("yyyy-MM-dd");
        excelExport.setTargetTimeZone(TimeZone.getDefault());
        excelExport.setLocale(AppConstants.LOCALE);

        excelExport.putCreateExcelRule("时间", OrderVo::getOrdertime);
        excelExport.putCreateExcelRule("缴费/收款人", OrderVo::getOrderaccount);
        excelExport.putCreateExcelRule("项目", OrderVo::getSubjectName);
        excelExport.putCreateExcelRule("金额", OrderVo::getOrderprice);
        excelExport.putCreateExcelRule("票据", OrderVo::getTicketTypeName);
        excelExport.putCreateExcelRule("票据号", OrderVo::getTicketno);
        excelExport.putCreateExcelRule("收支方式", OrderVo::getPaytype);
        excelExport.putCreateExcelRule("备注", OrderVo::getOrderdes);
        excelExport.putCreateExcelRule("班级", OrderVo::getOrderclass);

        return excelExport;
    }

    /***
     * Excel 表单数据上传
     * 表列: 0-时间, 1-缴费/收款人, 2-项目, 3-金额, 4-票据, 5-票据号, 6-收支方式, 7-备注, 8-班级
     *
     * @param file
     * @param userPo
     * @throws IOException
     */
    @Override
    @Transactional
    public void upload(MultipartFile file, UserPo userPo) throws IOException, ParseException {
        final int columnNum = 9;
        final Workbook workbook = ExcelParseUtil.getWorkBook(file);
        final List<String[]> excelDataRowTempList = ExcelParseUtil.readExcel(workbook, columnNum);
        // if any excelDataRow empty, filter it.
        final List<String[]> excelDataRowList = excelDataRowTempList.stream()
                .filter(excelDataRow -> !containsEmptyElement(excelDataRow))
                .collect(Collectors.toList());
        log.info("excel upload api data: " + JsonUtil.toJson(excelDataRowList));

        final List<OrderDto> orderDtoList = parseToOrderDtoList(excelDataRowList, userPo.getId());
        if (Lists.isEmpty(orderDtoList)) {
            return;
        }

        final List<Long> createdOrderIdList = Lists.newLinkedList();
        for (final OrderDto orderDto : orderDtoList) {
            final Long createOrderId = this.orderService.create(orderDto, userPo);
            createdOrderIdList.add(createOrderId);
        }
        log.warn("upload excel, createdOrderIdList: " + JsonUtil.toJson(createdOrderIdList));
    }

    /***
     * String[] 中是否含有 "" 或者 null 的元素?
     * @param dataRow
     * @return
     */
    private Boolean containsEmptyElement(String[] dataRow) {
        if (dataRow == null) {
            return true;
        }

        Optional<String> emptyItemOptional = Arrays.stream(dataRow)
                .filter(item -> StringUtils.isEmpty(StringUtils.trimToEmpty(item)))
                .findFirst();
        return emptyItemOptional.isPresent();
    }

    /***
     * 把 Excel 解析出来的每行数据转化到订单创建的 DTO
     * @param dataRowList
     * @param currentUserId
     * @return
     * @throws ParseException
     */
    private List<OrderDto> parseToOrderDtoList(List<String[]> dataRowList, Long currentUserId) throws ParseException {
        Map<String, SubjectVo> subjectNameVoMap = subjectManager.getNameVoMap();
        Map<String, TicketTypeVo> ticketTypeNameVoMap = ticketTypeManager.getNameVoMap();
        List<OrderDto> orderDtoList = Lists.newArrayList();
        for (String[] dataRow : dataRowList) {
            try {
                // 0-时间, 1-缴费/收款人, 2-项目, 3-金额, 4-票据, 5-票据号, 6-收支方式, 7-备注, 8-班级
                final OrderDto orderDto = new OrderDto();
                orderDto.setId(null);
                orderDto.setOrderaccount(StringUtils.trimToEmpty(dataRow[1]));
                orderDto.setOrderclass(StringUtils.trimToEmpty(dataRow[8]));
                final SubjectVo subjectVo = subjectNameVoMap.get(StringUtils.trimToEmpty(dataRow[2]));
                orderDto.setSubjectid(subjectVo != null ? subjectVo.getId() : 0);
                final TicketTypeVo ticketTypeVo = ticketTypeNameVoMap.get(StringUtils.trimToEmpty(dataRow[4]));
                orderDto.setTickettypeid(ticketTypeVo != null ? subjectVo.getId() : 0);
                orderDto.setTicketno(StringUtils.trimToEmpty(dataRow[5]));
                orderDto.setOrderprice(new BigDecimal(StringUtils.trimToEmpty(dataRow[3])));
                orderDto.setPaytype(StringUtils.trimToEmpty(dataRow[6]));
                orderDto.setOrdertime(DateUtils.parseDate(StringUtils.trimToEmpty(dataRow[0]), "yyyy-MM-dd"));
                orderDto.setOrderdes(StringUtils.trimToEmpty(dataRow[7]));
                orderDto.setCreateuserid(currentUserId);
                orderDto.setUpdateuserid(currentUserId);
                orderDtoList.add(orderDto);
            } catch (Exception ex) {
                log.error("Excel dataRow exception: " + ex.getMessage()
                        + ", dataRow: " + JsonUtil.toJson(dataRow), ex);
                throw ex;
            }

        }
        return orderDtoList;
    }
}
