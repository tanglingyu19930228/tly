/**
 * @项目名称 BHF
 * @Title ExcelComponent.java 
 * @Package com.ljdy.BHF.utils 
 * @Description (excel组件类；根据excel模板文件、数据，生成带有数据的新excel文件) 
 * @Author 李金阳 lijy@luojiadeyi.com  
 * @Date 2016年12月20日 下午3:34:40 
 * @Version V1.0 
 * @版权 珞珈德毅科技股份有限公司所有
 * @修改历史  
 *     1. [2016年12月20日]创建文件 by 李金阳
 */
package com.ljdy.BHF.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.AreaReference;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;

/** 
 * @ClassName ExcelComponent 
 * @Description (excel组件类；根据excel模板文件、数据，生成带有数据的新excel文件) 
 * @Author 李金阳 lijy@luojiadeyi.com    
 * @Date 2016年12月20日 下午3:34:40 
 * @修改历史  
 *     1. [2016年12月20日]创建文件 by 李金阳
 */
public class ExcelComponent {

    private static boolean isSuccess = false;
    
    /**
     * 生成单sheet页excel文件
     * 
     * @param template
     *            excel模板文件
     * @param target
     *            生成的新excel文件
     * @param discreteData
     *            离散数据，以键值对的格式存储在Map中
     * @param listData
     *            列表数据，以Map的格式存储在List中
     * @param columns
     *            列表数据项
     * @param listName
     *            列表名称
     * @return
     * @throws Exception
     */
    public static boolean generateSingleSheetExcelFile(File template,
            File target, Map<String, Object> discreteData,
            List<Map<String, Object>> listData, String[] columns,
            String listName) throws Exception {
        // 文件合法性校验
        if (isFileLegal(template, target)) {
            // 模板跟目标文件合法性校验通过
            Workbook workbook = getWorkbook(template);
            // 填充离散数据
            fillDisreteData(workbook, discreteData);
            // 填充列表数据
            fillListData(workbook, listData, columns, listName);
            // 输出目标文件
            writeToTarget(workbook, target);
            isSuccess = true;
        }
        return isSuccess;
    }
    
    /**
     * 生成单sheet页excel文件
     * 
     * @param template
     *            excel模板文件
     * @param target
     *            生成的新excel文件
     * @param sheetName
     *            生成的sheet名称
     * @param listData
     *            列表数据，以Map的格式存储在List中
     * @param columns
     *            列表数据项
     * @param listName
     *            列表名称
     * @return
     * @throws Exception
     */
    public static boolean generateSingleSheetExcelFile(File template,
            File target, String sheetName,
            List<Map<String, Object>> listData, String[] columns,
            int sheetIndex, String type) throws Exception {
        // 文件合法性校验
        if (isFileLegal(template, target)) {
            // 模板跟目标文件合法性校验通过
            Workbook workbook = getWorkbook(template);
            // 填充列表数据
            fillListData(workbook, listData, columns, sheetIndex, type);
            // 删除多余sheet
            deleteOtherSheet(workbook, sheetName);
            // 输出目标文件
            writeToTarget(workbook, target);
            isSuccess = true;
        }
        return isSuccess;
    }
    
     /**
     * @Title generateMultiSheetExcelFile 
     * @Description (生成多sheet页的Excel文件) 
     * @param template      excel模板
     * @param target        生成的新excel文件
     * @param sheetName     sheet页名称
     * @param listData      列表数据，以Map的格式存储在List中
     * @param columns       列表数据项
     * @param listName      数表名称
     * @param type
     * @return
     * @throws Exception
     * @Return boolean 返回类型 
     * @Throws 
     * @Date  2016年12月28日
     * @修改历史  
     *     1. [2016年12月28日]创建文件 by 徐成
     */
    public static boolean generateMultiSheetExcelFile(File template,
            File target, String sheetName,
            List<Map<String, Object>> listData, String[] columns,
            int sheetIndex, String type) throws Exception{
        // 文件合法性校验
        if (isFileLegal(template, target)) {
            // 模板跟目标文件合法性校验通过
            Workbook workbook = getWorkbook(template);
            // 填充列表数据
            //fillListData(workbook, listData, columns, listName, type);
            fillListData(workbook, listData, columns, sheetIndex, type);
            // 输出目标文件
            writeToTarget(workbook, target);
            isSuccess = true;
        }
        return isSuccess;
    }
    
    /**
     * 删除excel中，多余的sheet
     * 
     * @param workbook
     *            excel的workbook对象
     * @param sheetName
     *            需要过滤的sheet名称
     * @return
     * @throws Exception
     */
    private static void deleteOtherSheet(Workbook workbook, String sheetName) {
        List<Integer> lists = new ArrayList<>();
        
        Iterator<Sheet> sheets = workbook.iterator();
        while(sheets.hasNext()) {
            Sheet sheet = sheets.next();
            
            if(!sheet.getSheetName().equals(sheetName)) {
                int index = workbook.getSheetIndex(sheet);
                lists.add(index);
            }
        }
        
        Collections.reverse(lists);
        
        for(Integer i : lists) {
            workbook.removeSheetAt(i);
        }
        
    }
    
    /**
     * 生成单sheet页excel文件
     * 
     * @param template
     *            excel模板文件
     * @param target
     *            生成的新excel文件
     * @param allData
     *            所有列表数据
     * @param columns
     *            列表数据项
     * @return
     * @throws Exception
     */
    public static boolean generateSingleSheetExcelFile(File template,
            File target, Map<String, List<Map<String, Object>>> allData, String[] columns, String type) throws Exception {
        // 文件合法性校验
        if (isFileLegal(template, target)) {
            // 模板跟目标文件合法性校验通过
            Workbook workbook = getWorkbook(template);
            
            for (String str : allData.keySet()) {
                // 填充列表数据
                fillListData(workbook, allData.get(str), columns, str, type);
            }
            
            // 输出目标文件
            writeToTarget(workbook, target);
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * 生成单sheet页excel文件，并且支持纵向合并相同的单元格
     * 
     * @param template
     *            excel模板文件
     * @param target
     *            生成的新excel文件
     * @param discreteData
     *            离散数据，以键值对的格式存储在Map中
     * @param listData
     *            列表数据，以Map的格式存储在List中
     * @param columns
     *            列表数据项
     * @param listName
     *            列表名称
     * @param mergeColumns
     *            合并的列项
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static boolean generateSingleSheetExcelFileSupportMergeSameCell(
            File template, File target, Map<String, Object> discreteData,
            List<Map<String, Object>> listData, String[] columns,
            String listName, int[] mergeColumns) throws IOException, Exception {
        // 文件合法性校验
        if (isFileLegal(template, target)) {
            // 模板跟目标文件合法性校验通过
            Workbook workbook = getWorkbook(template);
            // 填充离散数据
            fillDisreteData(workbook, discreteData);
            // 填充列表数据
            fillListData(workbook, listData, columns, listName);
            // 纵向合并指定的列
            mergeSameCell(workbook, listData, listName, mergeColumns);
            // 输出目标文件
            writeToTarget(workbook, target);
            isSuccess = true;
        }
        return isSuccess;
    }

    /**
     * 生成多sheet页excel文件
     * 
     * @param template
     *            excel模板文件
     * @param target
     *            生成的新excel文件
     * @param discreteData
     *            离散数据，以键值对的格式存储在Map中
     * @param listDataMap
     *            列表数据，以键值对的格式存储多个列表数据List<Map<String,Object>>
     * @param columns
     *            列表数据项，每个列表的数据项都存储在一个String中，以英文逗号分隔
     * @param listName
     *            列表名称，以String数组来存储
     * @return
     * @throws Exception
     */
    public static boolean generateMultiSheetExcelFile(File template,
            File target, Map<String, Object> discreteData,
            Map<String, List<Map<String, Object>>> listDataMap,
            String[] columns, String[] listNames) throws Exception {
        // 文件合法性校验
        if (isFileLegal(template, target)) {
            // 进行判断列表数据的个数、数据项个数以及列表名称的个数是否一致
            if (listDataMap != null && columns != null && listNames != null
                    && listDataMap.size() == columns.length
                    && listDataMap.size() == listNames.length) {
                int length = listNames.length; // 列表名称个数
                // 模板跟目标文件合法性校验通过
                Workbook workbook = getWorkbook(template);
                for (int i = 0; i < length; i++) {
                    String listName = listNames[i];
                    List<Map<String, Object>> listData = listDataMap
                            .get(listName); // 根据名称获取单个列表数据
                    String column = columns[i]; // 单个列表数据项
                    // 填充列表数据
                    fillListData(workbook, listData, column.split(","), listName);
                }
                // 输出目标文件
                writeToTarget(workbook, target);
                isSuccess = true;
            }
        }
        return isSuccess;
    }
    
    
    /**
     * 生成多sheet页excel文件，支持纵向合并相同的单元格
     * @param template excel模板文件
     * @param target 生成的新excel文件
     * @param discreteData 离散数据，以键值对的格式存储在Map中
     * @param listDataMap 列表数据，以键值对的格式存储多个列表数据List<Map<String,Object>>
     * @param columns 列表数据项，每个列表的数据项都存储在一个String中，以英文逗号分隔
     * @param listNames 列表名称，以String数组来存储
     * @param mergeColumns 合并列，以List来存储
     * @return
     * @throws Exception
     */
    public static boolean generateMultiSheetExcelFileSupportMergeSameCell(File template,
            File target, Map<String, Object> discreteData,
            Map<String, List<Map<String, Object>>> listDataMap,
            String[] columns, String[] listNames,List<int[]> mergeColumns) throws Exception {
        // 文件合法性校验
        if (isFileLegal(template, target)) {
            // 进行判断列表数据的个数、数据项个数以及列表名称的个数是否一致
            if (listDataMap != null && columns != null && listNames != null
                    && listDataMap.size() == columns.length
                    && listDataMap.size() == listNames.length) {
                int length = listNames.length; // 列表名称个数
                int mergeColumnLength = 0; //合并列个数
                if (mergeColumns != null) {
                    mergeColumnLength = mergeColumns.size();
                }
                // 模板跟目标文件合法性校验通过
                Workbook workbook = getWorkbook(template);
                // 填充离散数据
                fillDisreteData(workbook, discreteData);
                for (int i = 0; i < length; i++) {
                    String listName = listNames[i];
                    List<Map<String, Object>> listData = listDataMap
                            .get(listName); // 根据名称获取单个列表数据
                    String column = columns[i]; // 单个列表数据项
                    // 填充列表数据
                    fillListData(workbook, listData, column.split(","), listName);
                    //根据合并列个数判断是否存在需要合并的列
                    if (mergeColumnLength > i) {
                        int[] mergeColumn = mergeColumns.get(i);
                        mergeSameCell(workbook, listData, listName, mergeColumn);
                    }
                }
                // 输出目标文件
                writeToTarget(workbook, target);
                isSuccess = true;
            }
        }
        return isSuccess;
    }

    /**
     * 判断模板文件、目标文件是否合法
     * 
     * @param template
     *            excel模板文件
     * @param target
     *            生成的新excel文件
     * @return
     * @throws Exception
     */
    private static boolean isFileLegal(File template, File target)
            throws Exception {
        // 首先判断模板文件是否合法
        boolean isTemplateLegal = isTemplateLegal(template);
        // 判断生成的新excel文件是否合法
        boolean isTargetLegal = isTargetLegal(target);
        if (isTemplateLegal && isTargetLegal) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断模板文件是否合法
     * 
     * @param template
     *            excel模板文件
     * @return
     * @throws Exception
     */
    private static boolean isTemplateLegal(File template) throws Exception {
        boolean isLegal = false;
        if (template != null && template.length() > 0) {
            String templateName = template.getName();
            if (templateName != null
                    && (templateName.endsWith(".xls") || templateName
                            .endsWith(".xlsx"))) {
                isLegal = true;
            } else {
                throw new Exception("模板文件格式有误，必须为.xls或.xlsx格式");
            }
        } else {
            throw new Exception("模板文件不存在");
        }
        return isLegal;
    }

    /**
     * 判断生成的目标文件是否合法
     * 
     * @param target
     * @return
     * @throws Exception
     */
    private static boolean isTargetLegal(File target) throws Exception {
        boolean isLegal = false;
        if (target != null) {
            String targetName = target.getName();
            if (targetName.endsWith(".xls") || targetName.endsWith(".xlsx")) {
                isLegal = true;
            } else {
                throw new Exception("目标文件格式有误，必须为.xls或.xlsx");
            }
        } else {
            throw new Exception("目标文件格式有误，必须为.xls或.xlsx");
        }
        return isLegal;
    }

    /**
     * 获取模板文件的工作簿对象
     * 
     * @param template
     * @return
     * @throws IOException
     */
    private static Workbook getWorkbook(File template) throws Exception {
        Workbook workbook = null;
        //POIFSFileSystem fs = null;
        InputStream inputStream = new FileInputStream(template);
        //fs = new POIFSFileSystem(inputStream);
        workbook = WorkbookFactory.create(inputStream);
        return workbook;
    }

    /**
     * 填充离散数据
     * 
     * @param workbook
     *            模板文件工作簿对象
     * @param discreteData
     *            离散数据
     */
    private static void fillDisreteData(Workbook workbook,
            Map<String, Object> discreteData) {
        // 遍历离散数据，根据map的key去查找excel文件预先定义好的名称，然后获取名称的引用，找到对应的单元格，然后把map中的value设置到单元格中
        if (discreteData != null && !discreteData.isEmpty()) {
            SpreadsheetVersion spreadsheetVersion = workbook
                    .getSpreadsheetVersion(); // excel的版本
            // 离散数据不为空
            Set<Entry<String, Object>> entries = discreteData.entrySet();
            for (Entry<String, Object> entry : entries) {
                String key = entry.getKey() == null ? "" : entry.getKey();
                String value = entry.getValue() == null ? "" : entry.getValue()
                        .toString();
                Name name = workbook.getName(key);
                if (name != null) {
                    AreaReference areaReference = new AreaReference(
                            name.getRefersToFormula(), spreadsheetVersion);
                    CellReference[] cellReferences = areaReference
                            .getAllReferencedCells();
                    for (CellReference cellReference : cellReferences) {
                        // 设置单元格内容
                        Sheet sheet = workbook.getSheet(cellReference
                                .getSheetName());
                        Row row = sheet.getRow(cellReference.getRow());
                        if (row == null) {
                            row = sheet.createRow(cellReference.getRow());
                        }
                        Cell cell = row.getCell(cellReference.getCol());
                        if (cell == null) {
                            cell = row.createCell(cellReference.getCol());
                        }
                        cell.setCellValue(value);
                    }
                }
            }
        }
    }

     /**
     * @Title fillListData 
     * @Description (填充sheet页数据) 
     * @param workbook      模板工作簿对象
     * @param listData      列表数据
     * @param columns       列表数据项
     * @param sheetIndex    sheet页索引
     * @param type          输出数据的类型
     * @Return void 返回类型 
     * @Throws 
     * @Date  2017年1月11日
     * @修改历史  
     *     1. [2017年1月11日]创建文件 by 徐成
     */
    private static void fillListData(Workbook workbook,List<Map<String, Object>> listData, 
            String[] columns,int sheetIndex, String type){
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        
        int listDataSize = listData.size(); // 列表记录数
        int columnsLength = columns.length; // 行数
        int rowIndex = 1; // 列表0开始单元格所在的行
        int columnIndex = 0; // 列表0开始单元格所在的列
        // 遍历列表0中的列，给新增的行填充数据
        for (Map<String, Object> map : listData) {
            Row row = null;// 开始的行数取列表0开始单元格所在行
            
            if("text".equals(type)) {
                int LastRowNum = sheet.getLastRowNum() + 1;
                if (listDataSize > 1) {
                    sheet.shiftRows(rowIndex, LastRowNum, listDataSize - 1); // 列表0所在行下移位置，给列表数据有位置填充
                }
                row = sheet.createRow(rowIndex);
            }else if("double".equals(type)) {
                row = sheet.getRow(rowIndex);
            }
            
            // 需要插入的列数，根据columns的长度决定
            for (int i = 0; i < columnsLength; i++) {
                // 开始的列数取列表0开始单元格所在列
                Object columnValue = map.get(columns[i]) == null ? "": map.get(columns[i]);
                if("text".equals(type)) {
                    if(columnValue!=null){
                        if(columnValue.getClass().getSimpleName().equals("Double")){
                            double cellValue =Double.parseDouble(columnValue.toString());
                            row.createCell(columnIndex + i).setCellValue(formatDouble(cellValue));
                        }else{
                            row.createCell(columnIndex + i).setCellValue(columnValue.toString());
                        }
                    }else{
                        row.createCell(columnIndex + i).setCellValue("");
                    }
                }else if("double".equals(type)) {
                    double cellValue ;
                    if(columnValue==null || columnValue.toString().trim().equals("")){
                        row.getCell(columnIndex + i).setCellValue(0);
                    }else {
                        cellValue =Double.parseDouble(columnValue.toString());
                        row.getCell(columnIndex + i).setCellValue(formatDouble(cellValue));
                    }
                }
            }
            rowIndex++; // 行号自增
        }
    }
    
    /**
     * 填充列表数据
     * 
     * @param workbook
     *            模板文件工作簿对象
     * @param listData
     *            列表数据
     * @param columns
     *            列表数据项
     * @param listName
     *            列表名称【模板文件中定义的名称】
     * @param type
     *            输出数据的类型
     */
    private static void fillListData(Workbook workbook,List<Map<String, Object>> listData, 
            String[] columns,String listName, String type) {
        // 找到列表0对应的行，然后遍历列表数据，把数据填充到行中
        SpreadsheetVersion spreadsheetVersion = workbook.getSpreadsheetVersion(); // excel的版本
        Name name = workbook.getName(listName);
        if (name != null && listData != null && columns != null) {
            int listDataSize = listData.size(); // 列表记录数
            int columnsLength = columns.length; // 行数
            // 进行列表数据跟列表数据项判断，只有当有列表数据和列表数据项才进行列表数据填充操作
            if (listDataSize > 0 && columnsLength > 0) {
                // 说明列表有数据，需要逐行填充列表数据
                // 获取列表0所对应的地址引用
                AreaReference areaReference = new AreaReference(name.getRefersToFormula(), spreadsheetVersion);
                CellReference[] cellReferences = areaReference.getAllReferencedCells(); // 数组的大小为列表的列数
                // 事先约定好的列表是在同一行的，由于需要重复输出列表数据，因此需要把列表0下移list.zise-1
                int rowIndex = cellReferences[0].getRow(); // 列表0开始单元格所在的行
                int columnIndex = cellReferences[0].getCol(); // 列表0开始单元格所在的列
                Sheet sheet = workbook.getSheet(cellReferences[0].getSheetName());
                
                // 遍历列表0中的列，给新增的行填充数据
                for (Map<String, Object> map : listData) {
                    Row row = null;// 开始的行数取列表0开始单元格所在行
                    
                    if("text".equals(type)) {
                        int LastRowNum = sheet.getLastRowNum() + 1;
                        if (listDataSize > 1) {
                            sheet.shiftRows(rowIndex, LastRowNum, listDataSize - 1); // 列表0所在行下移位置，给列表数据有位置填充
                        }
                        row = sheet.createRow(rowIndex);
                    }else if("double".equals(type)) {
                        row = sheet.getRow(rowIndex);
                    }
                    
                    // 需要插入的列数，根据columns的长度决定
                    for (int i = 0; i < columnsLength; i++) {
                        // 开始的列数取列表0开始单元格所在列
                        Object columnValue = map.get(columns[i]) == null ? "": map.get(columns[i]);
                        if("text".equals(type)) {
                            if(columnValue!=null){
                                if(columnValue.getClass().getSimpleName().equals("Double")){
                                    double cellValue =Double.parseDouble(columnValue.toString());
                                    row.createCell(columnIndex + i).setCellValue(formatDouble(cellValue));
                                }else{
                                    row.createCell(columnIndex + i).setCellValue(columnValue.toString());
                                }
                            }else{
                                row.createCell(columnIndex + i).setCellValue("");
                            }
                        }else if("double".equals(type)) {
                            double cellValue ;
                            if(columnValue==null || columnValue.toString().trim().equals("")){
                                row.getCell(columnIndex + i).setCellValue(0);
                            }else {
                                cellValue =Double.parseDouble(columnValue.toString());
                                row.getCell(columnIndex + i).setCellValue(formatDouble(cellValue));
                            }
                        }
                        
                    }
                    rowIndex++; // 行号自增
                }
                
            }
        }
    }
    
    /**
     * 填充列表数据
     * 
     * @param workbook
     *            模板文件工作簿对象
     * @param listData
     *            列表数据
     * @param columns
     *            列表数据项
     * @param listName
     *            列表名称【模板文件中定义的名称】
     */
    private static void fillListData(Workbook workbook,
            List<Map<String, Object>> listData, String[] columns,
            String listName) {
        // 找到列表0对应的行，然后遍历列表数据，把数据填充到行中
        SpreadsheetVersion spreadsheetVersion = workbook
                .getSpreadsheetVersion(); // excel的版本
        Name name = workbook.getName(listName);
        if (name != null && listData != null && columns != null) {
            int listDataSize = listData.size(); // 列表记录数
            int columnsLength = columns.length; // 行数
            // 进行列表数据跟列表数据项判断，只有当有列表数据和列表数据项才进行列表数据填充操作
            if (listDataSize > 0 && columnsLength > 0) {
                // 说明列表有数据，需要逐行填充列表数据
                // 获取列表0所对应的地址引用
                AreaReference areaReference = new AreaReference(
                        name.getRefersToFormula(), spreadsheetVersion);
                CellReference[] cellReferences = areaReference
                        .getAllReferencedCells(); // 数组的大小为列表的列数
                // 事先约定好的列表是在同一行的，由于需要重复输出列表数据，因此需要把列表0下移list.zise-1
                int rowIndex = cellReferences[0].getRow(); // 列表0开始单元格所在的行
                int columnIndex = cellReferences[0].getCol(); // 列表0开始单元格所在的列
                Sheet sheet = workbook.getSheet(cellReferences[0]
                        .getSheetName());
                
                // 遍历列表0中的列，给新增的行填充数据
                for (Map<String, Object> map : listData) {
                    Row row = sheet.getRow(rowIndex); // 开始的行数取列表0开始单元格所在行
                    // 需要插入的列数，根据columns的长度决定
                    for (int i = 0; i < columnsLength; i++) {
                        // 开始的列数取列表0开始单元格所在列
                        Object columnValue = map.get(columns[i]) == null ? "": map.get(columns[i]);
                        if(columnValue!=null){
                            if(columnValue.getClass().getSimpleName().equals("Double")){
                                double cellValue =Double.parseDouble(columnValue.toString());
                                row.createCell(columnIndex + i).setCellValue(formatDouble(cellValue));
                            }else{
                                row.createCell(columnIndex + i).setCellValue(columnValue.toString());
                            }
                        }else{
                            row.createCell(columnIndex + i).setCellValue("");
                        }
                        
                    }
                    rowIndex++; // 行号自增
                }
                
            }
        }
    }

    /**
     * 纵向合并相同的单元格
     * 
     * @param workbook
     *            excel文件工作簿
     * @param listData
     *            列表数据
     * @param listName
     *            列表名称
     * @param mergeColumns
     *            合并列
     * @throws Exception
     */
    private static void mergeSameCell(Workbook workbook,
            List<Map<String, Object>> listData, String listName,
            int[] mergeColumns) throws Exception {
        SpreadsheetVersion spreadsheetVersion = workbook
                .getSpreadsheetVersion(); // excel的版本
        Name name = workbook.getName(listName);
        if (name != null && mergeColumns != null) {
            AreaReference areaReference = new AreaReference(
                    name.getRefersToFormula(), spreadsheetVersion);
            CellReference[] cellReferences = areaReference
                    .getAllReferencedCells(); // 数组的大小为列表的列数
            Sheet sheet = workbook.getSheet(cellReferences[0].getSheetName());
            int lastRowIndexOfList = cellReferences[0].getRow();
            int firstRowIndexOfList = lastRowIndexOfList - listData.size() + 1;
            int length4MergeColumns = mergeColumns.length;
            for (int i = 0; i < length4MergeColumns; i++) {
                // 对行数据中的列进行比较，合并
                mergeSameForSingleColumn(sheet, firstRowIndexOfList,
                        lastRowIndexOfList, mergeColumns[i]);
            }
        }
    }

    /**
     * 纵向合并单列数据
     * 
     * @param sheet
     *            sheet页
     * @param startRowIndex
     *            开始行
     * @param endRowIndex
     *            结束行
     * @param columnIndex
     *            合并列
     * @throws Exception
     */
    private static void mergeSameForSingleColumn(Sheet sheet, int startRowIndex,
            int endRowIndex, int columnIndex) throws Exception {
        if (endRowIndex < startRowIndex) {
            throw new Exception("合并时，结束行不能小于开始行");
        } else {
            int count = 0;
            while (startRowIndex < endRowIndex) {
                String currentColumnValue = sheet.getRow(startRowIndex)
                        .getCell(columnIndex).getStringCellValue();
                String nextColumnValue = sheet.getRow(startRowIndex + 1)
                        .getCell(columnIndex).getStringCellValue();
                if (currentColumnValue.equals(nextColumnValue)) {
                    count++;
                } else {
                    // 合并前面的值
                    sheet.addMergedRegion(new CellRangeAddress(startRowIndex
                            - count, startRowIndex, columnIndex, columnIndex));
                    count = 0;
                }
                startRowIndex++;
            }
            if (count > 0) {
                sheet.addMergedRegion(new CellRangeAddress(startRowIndex
                        - count, startRowIndex, columnIndex, columnIndex));
            }
        }
    }

    /**
     * 输出目标文件
     * 
     * @param workbook
     *            excel文件工作簿
     * @param target
     *            模板文件
     * @throws IOException
     */
    private static void writeToTarget(Workbook workbook, File target)
            throws IOException {
        FileOutputStream fos = new FileOutputStream(target); // 目标文件输出流
        workbook.write(fos);
    }
    
     /**
     * @Title formatDouble 
     * @Description (double数据格式化) 
     * @param a
     * @return
     * @Return String 返回类型 
     * @Throws 
     * @Date  2017年2月17日
     * @修改历史  
     *     1. [2017年2月17日]创建文件 by 徐成
     */
    private static String formatDouble(double a){
        DecimalFormat df = new DecimalFormat("0.0000");
        String b= df.format(a);
        for(int i=0;i<=b.length();i++){
            if(b.charAt(b.length()-1)=='0'){
                b = b.substring(0,b.length()-1);
            }
            if(b.charAt(b.length()-1)=='.'){
                b = b.substring(0,b.length()-1);
            }
        }
        return b;
     }
}
